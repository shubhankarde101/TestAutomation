package com.apple.phoenix.MyCode;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class Test {
    public void mytest1() {
        System.out.println("Hello public");
    }
    private void mytest2() {
        System.out.println("Hello private");
    }
}

public class ReflectionTest {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Test1 t = new Test1();
        t.mytest1();
        Method m1 = t.getClass().getDeclaredMethod("mytest2");
        System.out.println("The current thread is: "+Thread.currentThread().getName());
        m1.setAccessible(true);
        System.out.println(m1.getName());
        m1.invoke(t);
    }
}