package com.aop.login.util;

import java.lang.reflect.Field;

public class Test {

    public static void main(String [] args) throws NoSuchFieldException {
        TestBean bean=new TestBean();
        Class<TestBean>clazz=TestBean.class;
        Field bytesField=clazz.getDeclaredField("bytes");
        System.out.println(">>>>>>>>bytes>>>>>>>>>"+bytesField.getType());

        Field ste=clazz.getDeclaredField("str");
        System.out.println(">>>>>>>str>>>>>>>>>>>"+ste.getType());

        Field aByte=clazz.getDeclaredField("aByte");
        System.out.println(">>>>>aByte>>>>>>"+aByte.getType());

        Field aBoolean=clazz.getDeclaredField("aBoolean");
        System.out.println(">>>>>>aBoolean>>>>>"+aBoolean.getType());

        Field iInt=clazz.getDeclaredField("iInt");
        System.out.println(">>>>>iInt>>>>>>"+iInt.getType());

        Field integer=clazz.getDeclaredField("integer");
        System.out.println(">>>>integer>>>>>>>"+integer.getType());

        Field bytes2=clazz.getDeclaredField("bytes2");
        System.out.println(">>>>bytes2>>>>>>>"+bytes2.getType());

    }
}
