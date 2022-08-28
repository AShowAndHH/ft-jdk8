package com.ft.jdk8.jdk8_01_Lambda表达式;

import com.ft.jdk8.common.Student;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.*;

/**
 * @Description:
 * Lambda 允许把函数作为一个方法的参数（函数作为参数传递到方法中）
 * Lambda 表达式免去了使用匿名方法的麻烦，并且给予Java简单但是强大的函数化的编程能力。
 * @Author: fangt
 * @CreateTime: 2021/6/9 21:40
 */
@Slf4j
public class TestLambda {
    /**
     * 函数式编程
     */
    @Test
    public void test01(){
        // 1. 不需要参数,返回值为 5：() -> 5
        IntSupplier a1 = () -> 5;
        Supplier<Integer> a2 = () -> 5;
        log.info("{}", a1.getAsInt());
        log.info("{}", a2.get());

        // 2. 接收一个参数(数字类型),返回其2倍的值：x -> 2 * x
        IntUnaryOperator b1 = x -> 2 * x;
        UnaryOperator<Integer> b2 = x -> 2 * x;
        log.info("{}", b1.applyAsInt(5));
        log.info("{}", b2.apply(5));

        // 3. 接受2个参数(数字),并返回他们的差值：(x, y) -> x – y
        BinaryOperator<Integer> c1 = (x, y) -> x - y;
        log.info("{}", c1.apply(8, 2));

        // 4. 接收2个int型整数,返回他们的和：(int x, int y) -> x + y
        BinaryOperator<Integer> d1 =  (x, y) -> x + y;
        log.info("{}", d1.apply(1, 2));

        // 5. 接受一个 string 对象,并在控制台打印,不返回任何值(看起来像是返回 void)：(String s) -> System.out.println(s)
        Consumer<String> e1 = s -> log.info(s);
        Consumer<String> e2 = log::info;
        e1.accept("lambda 表达式");
        e2.accept("lambda 表达式");
    }

    /**
     * 方法引用
     */
    @Test
    public void test02(){
        List<Student> studentList = Arrays.asList(new Student("Lucky", 18), new Student("Show", 25));
        studentList.forEach(x -> x.print());
        studentList.forEach(Student::print);  // 方法引用
    }
}

