package com.ft.jdk8.jdk8_03_接口默认方法and静态方法;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description: 白猫
 * @Author: fangt
 * @CreateTime: 2021/6/9 21:54
 */
@Slf4j
public class WhiteCat implements Cat {
    @Override
    public void play() {
        log.info("白猫玩");
    }

    @Override
    public void run() {     // 默认方法可重写、也可不重写
        log.info("白猫跑");
    }
}
