package com.kubrick.jsbt.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * @author k
 * @version 1.0.0
 * @ClassName Beans
 * @description: TODO
 * @date 2021/3/13 下午7:06
 */
public class Beans implements MethodInterceptor {

    static final Class<?>[] C = new Class[0];
    static final Object[] EMPTY_ARGS = new Object[0];
    private PropertyChangeSupport propertySupport;

    public static Object newInstance(Class<Bean> clazz) {
        try {
            Beans interceptor = new Beans();
            Enhancer e = new Enhancer();
            e.setSuperclass(clazz);
            e.setCallback(interceptor);
            Object bean = e.create();
            interceptor.propertySupport = new PropertyChangeSupport(bean);
            return bean;
        } catch (Throwable e) {
            e.printStackTrace();
            throw new Error(e.getMessage());
        }

    }

    public static void main(String[] args) {

        Bean bean = (Bean) newInstance(Bean.class);

        bean.addPropertyChangeListener(
                new PropertyChangeListener() {
                    @Override
                    public void propertyChange(PropertyChangeEvent evt) {
                        System.out.println(evt);
                    }
                }
        );

        bean.setSampleProperty("TEST");


    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {

        propertySupport.addPropertyChangeListener(listener);

    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.removePropertyChangeListener(listener);
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        Object retValFromSuper = null;
        try {
            if (!Modifier.isAbstract(method.getModifiers())) {
                retValFromSuper = proxy.invokeSuper(obj, args);
            }
        } finally {
            String name = method.getName();
            if ("addPropertyChangeListener".equals(name)) {
                addPropertyChangeListener((PropertyChangeListener) args[0]);
            } else if (name.equals("removePropertyChangeListener")) {
                removePropertyChangeListener((PropertyChangeListener) args[0]);
            }
            if (name.startsWith("set") &&
                    args.length == 1 &&
                    method.getReturnType() == Void.TYPE) {

                char propName[] = name.substring("set".length()).toCharArray();

                propName[0] = Character.toLowerCase(propName[0]);
                propertySupport.firePropertyChange(new String(propName), null, args[0]);

            }
        }
        return retValFromSuper;
    }


}
