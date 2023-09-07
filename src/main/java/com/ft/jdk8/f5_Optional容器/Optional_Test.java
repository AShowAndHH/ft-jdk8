package com.ft.jdk8.f5_Optional容器;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Optional;

/**
 * @Description:
 *      Optional 类是个容器：它可以保存类型T的值，或者仅仅保存null
 *      Optional 类的引入很好的解决空指针异常
 * @Author: fangt
 * @CreateTime: 2021/6/15 22:59
 */
@Slf4j
public class Optional_Test {
    /**
     * Optional 初始化
     * 常用方式：empty()、of(T)、ofNullable(T)
     */
    @Test
    public void of(){
        log.info("（1）empty()：初始化空Optional类");
        Optional<String> optional = Optional.empty();
        log.info("{}", optional);
        // 当Optional.empty()，optional.get()会抛异常
        try {
            log.info("{}", optional.get());
        }catch (Exception e){
            log.error("抛出异常：{}", e.toString());
        }

        log.info("（2）of(T)：初始化非空Optional类");
        // Optional.of(null)会抛异常
        optional = Optional.of("lucky");
        log.info("{}", optional);
        log.info("{}", optional.get());

        log.info("（3）ofNullable(T)：初始化可为空Optional类");
        optional = Optional.ofNullable("lucky");
        log.info("{}", optional);
        // 当Optional.ofNullable(null)，optional.get()会抛异常
        log.info("{}", optional.get());
    }

    /**
     * Optional 取值
     * 常用方式：get()、orElse、orElseGet
     */
    @Test
    public void get(){
        log.info("（1）get()");
        log.info("{}", Optional.ofNullable("lucky").get());
        log.info("{}", Optional.ofNullable("").get());
        try {
            log.info("{}", Optional.ofNullable(null).get());
        }catch (Exception e){
            log.error("抛出异常：{}", e.toString());
        }

        log.info("（2）orElse");
        log.info("{}", Optional.ofNullable("lucky").orElse("show"));
        log.info("{}", Optional.ofNullable("").orElse("show"));
        log.info("{}", Optional.ofNullable(null).orElse("show"));

        log.info("（3）orElseGet");
        log.info("{}", Optional.ofNullable("lucky").orElseGet(() -> "show"));
        log.info("{}", Optional.ofNullable("").orElseGet(() -> getShow()));
        log.info("{}", Optional.ofNullable(null).orElseGet(this::getShow));

        log.info("（4）orElseThrow");
        log.info("{}", Optional.ofNullable("lucky").orElseThrow(() -> new RuntimeException("No value present")));
        log.info("{}", Optional.ofNullable("").orElseThrow(() -> new RuntimeException("No value present")));
        try{
            log.info("{}", Optional.ofNullable(null).orElseThrow(() -> new RuntimeException("No value present")));
        }catch (RuntimeException e){
            log.error("抛出异常：{}", e.toString());
        }
    }

    private String getShow() {
        return "show";
    }

    /**
     * Optional 判空
     * 常用方式：isPresent、ifPresent
     */
    @Test
    public void ifPresent(){
        //（1）isPresent
        log.info("isPresent：{}", Optional.ofNullable("lucky").isPresent());
        log.info("isPresent：{}", Optional.ofNullable(null).isPresent());

        //（2）ifPresent
        Optional.ofNullable("lucky").ifPresent(x -> log.info("ifPresent：{}", x));
        Optional.ofNullable(null).ifPresent(x -> log.info("ifPresent：{}", x));
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
        Integer len2 = Optional.ofNullable("lucky").flatMap(x -> Optional.of(x.length())).orElse(-1);
        log.info("{}", len2);
    }

    /**
     * toString、equals、hashCode
     */
    @Test
    public void objectFunction(){
        Optional<String> optionalNull = Optional.empty();
        Optional<String> optional = Optional.of("");
        Optional<String> optionalLucky = Optional.of("lucky");
        Optional<String> optionalLucky2 = Optional.of("lucky");

        //（1）toString
        log.info("{}", optionalNull.toString());
        log.info("{}", optional.toString());
        log.info("{}", optionalLucky.toString());

        //（2）equals
        log.info("{}", optional.equals(optionalNull));
        log.info("{}", optional.equals(""));
        log.info("{}", optionalLucky.equals(optionalLucky2));

        //（3）hashCode
        log.info("{}", optionalNull.hashCode());
        log.info("{}", optional.hashCode());
        log.info("{}", "".hashCode());
        log.info("{}", optionalLucky.hashCode());
        log.info("{}", optionalLucky2.hashCode());
        log.info("{}", "lucky".hashCode());
    }
}
