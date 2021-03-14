package com.kubrick.jsbt.cglib;

import net.sf.cglib.core.KeyFactory;

/**
 * @author k
 * @version 1.0.0
 * @ClassName KeySample
 * @description: TODO
 * @date 2021/3/13 下午7:10
 */
public class KeySample {
    private interface MyFactory {
        public Object newInstance(int a, char[] b, String d);
    }
    public static void main(String[] args) {
        MyFactory f = (MyFactory) KeyFactory.create(MyFactory.class);
        Object key1 = f.newInstance(20, new char[]{ 'a', 'b' }, "hello");
        Object key2 = f.newInstance(20, new char[]{ 'a', 'b' }, "hello");
        Object key3 = f.newInstance(20, new char[]{ 'a', '_' }, "hello");
        System.out.println(key1.equals(key2));
        System.out.println(key2.equals(key3));
    }
}
