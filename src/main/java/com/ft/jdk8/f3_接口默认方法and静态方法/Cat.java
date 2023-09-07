package com.ft.jdk8.f3_接口默认方法and静态方法;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description: 猫
 * @Author: fangt
 * @CreateTime: 2021/6/9 21:51
 */
public interface Cat {
    Logger log = LoggerFactory.getLogger("Cat");
    /**
     * 抽象方法
     */
    void abstractMethod();

    /**
     * 静态方法
     */
    static void staticMethod() {
        log.info("接口静态方法");
    }

    /**
     * 默认方法
     */
    default void defaultMethod() {
        log.info("接口默认方法1");
    }

    /**
     * 默认方法
     */
    default void defaultMethod2() {
        log.info("接口默认方法2");
    }
}
