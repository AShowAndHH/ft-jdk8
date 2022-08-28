package com.ft.jdk8;

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
    void play();

    /**
     * 静态方法
     */
    static void eat() {
        log.info("猫吃鱼");
    }

    /**
     * 默认方法
     */
    default void run() {
        log.info("猫跑");
    }

    /**
     * 默认方法
     */
    default void climb() {
        log.info("猫爬树");
    }
}
