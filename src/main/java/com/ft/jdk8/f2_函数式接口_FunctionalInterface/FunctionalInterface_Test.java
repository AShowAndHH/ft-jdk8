package com.ft.jdk8.f2_函数式接口_FunctionalInterface;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @Description:
 *      函数式接口(Functional Interface)就是一个有且仅有一个抽象方法，但是可以有多个非抽象方法的接口。
 * @Author: fangt
 * @CreateTime: 2021/6/15 21:05
 */
@Slf4j
public class FunctionalInterface_Test {
    /**
     * 函数式接口
     */
    @Test
    public void test(){
        // 可以使用 Lambda 表达式来表示该接口的一个实现(注：JAVA 8 之前一般是用匿名类实现的)
        Say say = name -> log.info("Hello " + name);
        say.sayHello("Lucky");
    }
}
