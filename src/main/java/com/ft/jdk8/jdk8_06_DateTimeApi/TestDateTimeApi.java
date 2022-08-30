package com.ft.jdk8.jdk8_06_DateTimeApi;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Description: java8 日期时间 API 测试
 * @Author: fangt
 * @CreateTime: 2021/6/16 23:56
 */
@Slf4j
public class TestDateTimeApi {
    /**
     * （1）本地化日期时间API
     * Local(本地)：LocalDateTime、LocalDate、LocalTime
     */
    @Test
    public void local(){
        // 初始化日期时间
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime localDateTime01 = LocalDateTime.of(2020,12,2,1,2,3);
        LocalDateTime localDateTime02 = LocalDateTime.parse("2020-12-02T01:02:03");
        LocalDateTime localDateTime03 = LocalDateTime.parse("20201202010203", DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        // 获取日期时间参数
        log.info("LocalDateTime【{}】 LocalDate【{}】 LocalTime【{}】 ", now, now.toLocalDate(), now.toLocalTime());
        log.info("Year【{}】 Month【{}】 DayOfMonth【{}】 Hour【{}】 Minute【{}】 Second【{}】 Nano【{}】 ", now.getYear(), now.getMonthValue(),
                now.getDayOfMonth(), now.getHour(), now.getMinute(), now.getSecond(), now.getNano());
        log.info("Month【{}】 DayOfWeek【{}】 DayOfYear【{}】 ", now.getMonth(), now.getDayOfWeek(), now.getDayOfYear());
        // 设置日期时间参数
        LocalDateTime localDateTime04 = now.withYear(2020).withMonth(12).withDayOfMonth(2).withHour(1).withMinute(2).withSecond(3). withNano(4);
        log.info("{}", localDateTime04);
    }

    /**
     * （2）时区日期时间API
     * Zoned(时区)：ZonedDateTime、ZoneId
     */
    @Test
    public void zoned(){
        ZonedDateTime zonedDateTime = ZonedDateTime.parse("2015-12-03T10:15:30+06:30[Asia/Shanghai]");
        log.info("{}", zonedDateTime);

        // 使用默认区时
        ZoneId zoneId01 = ZoneId.systemDefault();
        ZonedDateTime now01 = ZonedDateTime.now();
        log.info("{} --- {}", zoneId01, now01);

        // 设置区时
        ZoneId zoneId02 = ZoneId.of("Europe/Paris");
        ZonedDateTime now02 = ZonedDateTime.now(zoneId02);
        log.info("{} --- {}", zoneId02, now02);
    }
}
