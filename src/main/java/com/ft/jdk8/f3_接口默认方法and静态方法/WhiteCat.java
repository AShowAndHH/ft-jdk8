package com.ft.jdk8.f3_接口默认方法and静态方法;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description: 白猫
 * @Author: fangt
 * @CreateTime: 2021/6/9 21:54
 */
@Slf4j
public class WhiteCat implements Cat {
    @Override
    public void abstractMethod() {
        log.info("子类重写接口抽象方法");
    }

    @Override
    public void defaultMethod() {
        log.info("子类重写接口默认方法");
    }
}
