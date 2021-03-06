package com.kubrick.sbt.web.annotation.log;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import javassist.ClassClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.LocalVariableAttribute;
import javassist.bytecode.MethodInfo;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.thymeleaf.util.ArrayUtils;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * @author k
 * <p>
 * final
 * 1、to db、es
 * 2、format json
 */
@Slf4j
@Aspect
@Component
public class ServiceLogAspect {

    private static final String dateFormat = "yyyy-MM-dd HH:mm:ss";

    @Pointcut("@annotation(com.kubrick.sbt.web.annotation.log.ServiceLog)")
    public void logPointCut() {
    }

    ;

    /**
     * 3. 环绕通知
     *
     * @param joinPoint
     */
    @Around("logPointCut()")
    public Object logAround(ProceedingJoinPoint joinPoint) {

        try {
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = signature.getMethod();
            Class<?> targetClass = method.getDeclaringClass();

            StringBuffer classAndMethodDesc = new StringBuffer();

            ServiceLog classAnnotation = targetClass.getAnnotation(ServiceLog.class);
            ServiceLog methodAnnotation = method.getAnnotation(ServiceLog.class);

            if (classAnnotation != null) {
                if (classAnnotation.ignore()) {
                    return joinPoint.proceed();
                }
                classAndMethodDesc.append(classAnnotation.value()).append("-");
            }

            if (methodAnnotation != null) {
                if (methodAnnotation.ignore()) {
                    return joinPoint.proceed();
                }
                classAndMethodDesc.append(methodAnnotation.value());
            }
            String className = targetClass.getName();
            String methodName = method.getName();

            Object[] methodArgs = joinPoint.getArgs();

            String[] paramNames = getFieldsName(className, methodName);
            String paramsInfo = logParam(paramNames, methodArgs);

            String classMethodInfo = className + "#" + methodName + "#" + classAndMethodDesc;
            //String params = JSONObject.toJSONStringWithDateFormat(joinPoint.getArgs(), dateFormat, SerializerFeature.WriteMapNullValue);

            long start = System.currentTimeMillis();
            Object result = joinPoint.proceed();
            long timeConsuming = System.currentTimeMillis() - start;
            String resultJson = JSONObject.toJSONStringWithDateFormat(result, dateFormat, SerializerFeature.WriteMapNullValue);
            log.info("{} 调用--> 参数:{} 返回值:{} 耗时:{}ms", classMethodInfo, paramsInfo, resultJson, timeConsuming);

            return result;

        } catch (Throwable throwable) {
            log.error(throwable.getMessage(), throwable);
        }
        return null;
    }

    /**
     * 使用javassist来获取方法参数名称
     *
     * @param class_name  类名
     * @param method_name 方法名
     * @return
     * @throws Exception
     */
    private String[] getFieldsName(String class_name, String method_name) throws Exception {
        Class<?> clazz = Class.forName(class_name);
        String clazz_name = clazz.getName();
        ClassPool pool = ClassPool.getDefault();
        ClassClassPath classPath = new ClassClassPath(clazz);
        pool.insertClassPath(classPath);

        CtClass ctClass = pool.get(clazz_name);
        CtMethod ctMethod = ctClass.getDeclaredMethod(method_name);
        MethodInfo methodInfo = ctMethod.getMethodInfo();
        CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
        LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute.getAttribute(LocalVariableAttribute.tag);
        if (attr == null) {
            return null;
        }
        String[] paramsArgsName = new String[ctMethod.getParameterTypes().length];
        int pos = Modifier.isStatic(ctMethod.getModifiers()) ? 0 : 1;
        for (int i = 0; i < paramsArgsName.length; i++) {
            paramsArgsName[i] = attr.variableName(i + pos);
        }
        return paramsArgsName;
    }

    /**
     * 判断是否为基本类型：包括String
     *
     * @param clazz clazz
     * @return true：是;     false：不是
     */
    private boolean isPrimite(Class<?> clazz) {
        if (clazz.isPrimitive() || clazz == String.class) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * 打印方法参数值  基本类型直接打印，非基本类型需要重写toString方法
     *
     * @param paramsArgsName  方法参数名数组
     * @param paramsArgsValue 方法参数值数组
     */
    private String logParam(String[] paramsArgsName, Object[] paramsArgsValue) {
        if (ArrayUtils.isEmpty(paramsArgsName) || ArrayUtils.isEmpty(paramsArgsValue)) {
            return "";
        }
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < paramsArgsName.length; i++) {
            //参数名
            String name = paramsArgsName[i];
            //参数值
            Object value = paramsArgsValue[i];
            buffer.append(name + " = ");
            if (isPrimite(value.getClass())) {
                buffer.append(value + "  ,");
            } else {
                buffer.append(value.toString() + ",");
            }
        }

        return buffer.toString();
    }


}

