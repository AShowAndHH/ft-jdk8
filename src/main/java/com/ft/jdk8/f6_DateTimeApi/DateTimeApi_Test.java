package com.ft.jdk8.f6_DateTimeApi;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Description:
 *      加强对日期与时间的处理（线程安全）
 * @Author: fangt
 * @CreateTime: 2021/6/16 23:56
 */
@Slf4j
public class DateTimeApi_Test {
    /**
     * （1）本地化日期时间API
     * Local(本地)：LocalDateTime、LocalDate、LocalTime
     */
    @Test
    public void local(){
        log.info("（1）LocalDateTime 初始化");
        LocalDateTime now = LocalDateTime.now();
        log.info("now = {}", now);
        log.info("{}", LocalDateTime.of(2020, 12, 2, 1, 2, 3));
        log.info("{}", LocalDateTime.parse("2020-12-02T01:02:03"));
        log.info("{}", LocalDateTime.parse("2020-12-02 01:02:03", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        log.info("（2）LocalDateTime 解析");
        log.info("LocalDateTime=【{}】", now);
        log.info("LocalDate=【{}】 LocalTime=【{}】", now.toLocalDate(), now.toLocalTime());
        log.info("Year=【{}】 Month=【{}】 DayOfMonth=【{}】 Hour=【{}】 Minute=【{}】 Second=【{}】 Nano=【{}】 ",
                now.getYear(), now.getMonthValue(), now.getDayOfMonth(), now.getHour(), now.getMinute(), now.getSecond(), now.getNano());
        log.info("Month【{}】 DayOfWeek【{}】 DayOfYear【{}】 ", now.getMonth(), now.getDayOfWeek(), now.getDayOfYear());

        log.info("（3）LocalDateTime 设置");
        log.info("{}", now.withYear(2020).withMonth(12).withDayOfMonth(2).withHour(1).withMinute(2).withSecond(3). withNano(4));
    }

    /**
     * （2）时区日期时间API
     * Zoned(时区)：ZonedDateTime、ZoneId
     */
    @Test
    public void zoned(){
        ZonedDateTime zonedDateTime = ZonedDateTime.parse("2020-12-02T01:02:03+08:00[Asia/Shanghai]");
        log.info("{}", zonedDateTime);

        // 使用默认区时
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime now = ZonedDateTime.now();
        log.info("zoneId=【{}】\t now=【{}】", zoneId, now);

        // 设置区时
        zoneId = ZoneId.of("Europe/Paris");
        now = ZonedDateTime.now(zoneId);
        log.info("zoneId=【{}】\t now=【{}】", zoneId, now);
    }

    /**
     * LocalDateTime <-> String
     */
    @Test
    public void convert(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // LocalDateTime -> String
        String string = LocalDateTime.now().format(formatter);
        log.info("{}", string);

        // String -> LocalDateTime
        LocalDateTime localDateTime = LocalDateTime.parse(string, formatter);
        log.info("{}", localDateTime);
    }

    /**
     * 求耗时 Duration
     */
    @Test
    public void timeDiff() throws InterruptedException {
        LocalDateTime start = LocalDateTime.now();
        // 休眠1.2s
        Thread.sleep(1200);
        LocalDateTime end = LocalDateTime.now();

        // 计算时间差
        Duration duration = Duration.between(start, end);
        log.info("start=【{}】", start);
        log.info("end=【{}】", end);
        log.info("耗时=【{}ms】", duration.toMillis());
        log.info("{}", duration.toString());
    }
}
