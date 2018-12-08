package com.me.java8.date;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TestDateTimeFormatter {
    public static void main(String[] args) {
        
        LocalDateTime dateTime = LocalDateTime.now();
        /**
         * mm：分
         * MM:月
         * hh:小时12进制
         * HH:小时24进制
         */
        System.out.println(DateTimeFormatter.ofPattern("YYYY年MM月dd日 hh时mm分ss秒").format(dateTime));
        System.out.println(DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH时mm分s秒").format(dateTime));
    }
}
