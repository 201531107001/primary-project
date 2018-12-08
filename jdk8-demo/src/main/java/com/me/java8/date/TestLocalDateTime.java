package com.me.java8.date;

import java.time.LocalDateTime;

public class TestLocalDateTime {
    public static void main(String[] args) {
        LocalDateTime dateTime = LocalDateTime.now();
        System.out.println(dateTime);
        
        // 获取日期
        System.out.println(dateTime.toLocalDate());
        // 获取时间
        System.out.println(dateTime.toLocalTime());
        
    }
}
