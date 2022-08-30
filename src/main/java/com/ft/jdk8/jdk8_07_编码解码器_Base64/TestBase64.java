package com.ft.jdk8.jdk8_07_编码解码器_Base64;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * @Description:
 * Base64工具类提供了一套静态BASE64编解码器，可以获取下面三种BASE64编解码方案：
 *     基本：输出被映射到一组字符A-Za-z0-9+/，编码不添加任何行标，输出的解码仅支持A-Za-z0-9+/。
 *     URL：输出映射到一组字符A-Za-z0-9+_，输出是URL和文件。
 *     MIME：输出隐射到MIME友好格式。输出每行不超过76字符，并且使用'\r'并跟随'\n'作为分割。编码输出最后没有行分割。
 * @Author: fangt
 * @CreateTime: 2021/6/18 9:41
 */
@Slf4j
public class TestBase64 {
    /**
     * 1、基本：输出被映射到一组字符A-Za-z0-9+/，编码不添加任何行标，输出的解码仅支持A-Za-z0-9+/。
     * 2、URL：输出映射到一组字符A-Za-z0-9+_，输出是URL和文件。
     * 3、MIME：输出隐射到MIME友好格式。输出每行不超过76字符，并且使用'\r'并跟随'\n'作为分割。编码输出最后没有行分割。
     */
    @Test
    public void main(){
        String s = "Hallo Lucky! 这里是BASE64编解码器！Hallo Show! 这里是BASE64编解码器！";

        log.info("================ BASE64编解码器 基本方案 ================ ");
        // 编码
        String encode = Base64.getEncoder().encodeToString(s.getBytes(StandardCharsets.UTF_8));
        log.info("{}", encode);
        // 解码
        String decode = new String(Base64.getDecoder().decode(encode));
        log.info("{}", decode);

        log.info("================ BASE64编解码器 URL 方案 ================ ");
        // 编码
        String urlEncode = Base64.getUrlEncoder().encodeToString(s.getBytes(StandardCharsets.UTF_8));
        log.info("{}", urlEncode);
        // 解码
        String urlDecode = new String(Base64.getUrlDecoder().decode(urlEncode));
        log.info("{}", urlDecode);

        log.info("================ BASE64编解码器 MIME方案 ================ ");
        // 编码
        String mimeEncode = Base64.getMimeEncoder().encodeToString(s.getBytes(StandardCharsets.UTF_8));
        log.info("{}", mimeEncode);
        // 解码
        String mimeDecode = new String(Base64.getMimeDecoder().decode(mimeEncode));
        log.info("{}", mimeDecode);

        log.info("================ BASE64编解码器 MIME方案（指定每行的长度 20） ================ ");
        // 编码（通过参数指定每行的长度及行的分隔符）
        String mimeEncode2 = Base64.getMimeEncoder(20, new byte[] {'\r', '\n'}).encodeToString(s.getBytes(StandardCharsets.UTF_8));
        log.info("{}", mimeEncode2);
        // 解码
        String mimeDecode2 = new String(Base64.getMimeDecoder().decode(mimeEncode2));
        log.info("{}", mimeDecode2);
    }
}
