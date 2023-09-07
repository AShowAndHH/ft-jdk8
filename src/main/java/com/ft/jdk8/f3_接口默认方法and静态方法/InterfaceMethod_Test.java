package com.ft.jdk8.f3_接口默认方法and静态方法;

import org.junit.Test;

/**
 * @Description:
 *      JDK8 开始新加了接口默认方法，便于接口的扩展
 *      接口中同时还可以定义静态方法，静态方法通过接口名调用
 * @Author: fangt
 * @CreateTime: 2021/6/9 21:48
 */
public class InterfaceMethod_Test {
    /**
     * 默认方法、静态方法
     */
    @Test
    public void test(){
        WhiteCat whiteCat = new WhiteCat();
        whiteCat.abstractMethod();
        whiteCat.defaultMethod();
        whiteCat.defaultMethod2();
        Cat.staticMethod();
    }
}
