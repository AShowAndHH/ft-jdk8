package com.ft.jdk8.jdk8_05_Optional容器;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Optional;

/**
 * @Description:
 * Optional 类是个容器：它可以保存类型T的值，或者仅仅保存null
 * Optional 类的引入很好的解决空指针异常
 * @Author: fangt
 * @CreateTime: 2021/6/15 22:59
 */
@Slf4j
public class TestOptional {
    /**
     * 初始化 Optional ：empty()、of(T)、ofNullable(T)
     */
    @Test
    public void of(){
        // （1）empty()：初始化空Optional类
        log.info("---------- empty() ----------");
        Optional<Object> optional1 = Optional.empty();
        log.info("{}", optional1);
        try {
            log.info("{}", optional1.get());     // 报异常
        }catch (Exception e){
            log.error("error", e);
        }

        // （2）of(T)：初始化非空Optional类
        log.info("---------- of(T) ----------");
        Optional<String> optional2 = Optional.of("lucky");   // 传nul报异常
        log.info("{}", optional2);
        log.info("{}", optional2.get());

        // （3）ofNullable(T)：初始化可为空Optional类
        log.info("---------- ofNullable(T) ----------");
        Optional<String> optional3 = Optional.ofNullable("lucky");
        log.info("{}", optional3);
        log.info("{}", optional3.get());     // 为null报异常
    }

    /**
     * 获取Optional类值的方法：get()、orElse、orElseGet
     */
    @Test
    public void get(){
        // （1）get()
        log.info("---------- get() ----------");
        log.info("{}", Optional.ofNullable("lucky").get());
        log.info("{}", Optional.ofNullable("").get());
        try {
            log.info("{}", Optional.ofNullable(null).get());    // 报异常
        }catch (Exception e){
            log.error("error", e);
        }

        // （2）orElse
        log.info("---------- orElse ----------");
        log.info("{}", Optional.ofNullable("lucky").orElse("show"));
        log.info("{}", Optional.ofNullable("").orElse("show"));
        log.info("{}", Optional.ofNullable(null).orElse("show"));

        // （3）orElseGet
        log.info("---------- orElseGet ----------");
        log.info("{}", Optional.ofNullable("lucky").orElseGet(() -> "show"));
        log.info("{}", Optional.ofNullable("").orElseGet(() -> getShow()));
        log.info("{}", Optional.ofNullable(null).orElseGet(this::getShow));

        // （4）orElseThrow
        log.info("---------- orElseThrow ----------");
        try{
            log.info("{}", Optional.ofNullable("lucky").orElseThrow(() -> new RuntimeException("当前值为null")));
        }catch (RuntimeException e){
            log.error("error", e);
        }
        try{
            log.info("{}", Optional.ofNullable("").orElseThrow(() -> new RuntimeException("当前值为null")));
        }catch (RuntimeException e){
            log.error("error", e);
        }
        try{
            log.info("{}", Optional.ofNullable(null).orElseThrow(() -> new RuntimeException("当前值为null")));
        }catch (RuntimeException e){
            log.error("error", e);
        }
    }

    private String getShow() {
        return "show";
    }

    /**
     * 判断是否为空：isPresent、ifPresent
     */
    @Test
    public void ifPresent(){
        //（1）isPresent
        log.info("{}", Optional.ofNullable("lucky").isPresent());
        log.info("{}", Optional.ofNullable(null).isPresent());

        //（2）ifPresent
        Optional.ofNullable("lucky").ifPresent(x -> log.info("{} Present 1", x));
        Optional.ofNullable(null).ifPresent(x -> log.info("{} Present 2", x));
    }

    /**
     * filter 过滤
     */
    @Test
    public void filter(){
        // 这里与三段式功能相同
        String s1 = Optional.ofNullable("lucky").filter(x -> x.length() > 20).orElse("show");
        log.info("{}", s1);
        String s2 = Optional.ofNullable("lucky").filter(x -> x.length() > 2).orElse("show");
        log.info("{}", s2);
    }

    /**
     * map、flatMap 映射
     */
    @Test
    public void map(){
        Integer len1 = Optional.ofNullable("lucky").map(x -> x.length()).orElse(-1);
        log.info("{}", len1);
        Integer len2 = Optional.ofNullable("lucky").flatMap(x -> Optional.ofNullable(x.length())).orElse(-1);
        log.info("{}", len2);
    }

    /**
     * toString、equals、hashCode
     */
    @Test
    public void otherFunction(){
        Optional<String> optional1 = Optional.of("lucky");
        Optional<String> optional2 = Optional.empty();
        Optional<String> optional3 = Optional.of("lucky");

        //（1）toString
        log.info("{}", optional1.toString());
        log.info("{}", optional2.toString());

        //（2）equals
        log.info("{}", optional1.equals(optional1));
        log.info("{}", optional1.equals(optional2));
        log.info("{}", optional1.equals(optional3));

        //（3）hashCode
        log.info("{}", optional1.hashCode());
        log.info("{}", optional2.hashCode());
        log.info("{}", optional3.hashCode());
    }
}
