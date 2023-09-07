package com.ft.jdk8.f1_Lambda表达式;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.function.*;

/**
 * @Description:
 *      Lambda 允许把函数作为一个方法的参数（函数作为参数传递到方法中）
 *      Lambda 表达式免去了使用匿名方法的麻烦，并且给予Java简单但是强大的函数化的编程能力。
 * @Author: fangt
 * @CreateTime: 2021/6/9 21:40
 */
@Slf4j
public class Lambda_Test {
    /**
     * 函数式编程
     */
    @Test
    public void test01(){
        // 1. 不接收参数，返回int类型，示例：() -> 5
        Supplier<Integer> supplier = () -> 5;
        log.info("{}", supplier.get());
        IntSupplier intSupplier = () -> 5;
        log.info("{}", intSupplier.getAsInt());

        // 2. 接收一个int类型参数，返回int类型，示例：x -> 2 * x
        UnaryOperator<Integer> unaryOperator = x -> 2 * x;
        log.info("{}", unaryOperator.apply(5));
        IntUnaryOperator intUnaryOperator = x -> 2 * x;
        log.info("{}", intUnaryOperator.applyAsInt(5));

        // 3. 接收两个int类型参数，返回int类型，示例：(x, y) -> x + y
        BinaryOperator<Integer> binaryOperator = (x, y) -> x + y;
        log.info("{}", binaryOperator.apply(6, 9));
        IntBinaryOperator intBinaryOperator = (x, y) -> x + y;
        log.info("{}", intBinaryOperator.applyAsInt(6, 9));

        // 4. 接受一个String类型参数，在控制台打印，无返回，示例：(String s) -> System.out.println(s)
        Consumer<String> consumer = s -> log.info(s);
        consumer.accept("lambda 表达式");
    }

    /**
     * 方法引用
     */
    @Test
    public void test02(){
        Consumer<String> consumer = log::info;
        consumer.accept("lambda 表达式");
    }
}