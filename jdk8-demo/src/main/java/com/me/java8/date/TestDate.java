package com.me.java8.date;

import java.time.LocalDate;

public class TestDate {
    public static void main(String[] args) {
        LocalDate date = LocalDate.now();
        System.out.println(date);

        // 获取月份
        System.out.println(date.getMonth());

        System.out.println(date.getDayOfMonth());
        System.out.println(date.getDayOfYear());
        System.out.println(date.getDayOfWeek());
        
        // 获取明天日期
        System.out.println(date.plusDays(1));
        
        // 获取昨天日期
        System.out.println(date.minusDays(1));
        
        // 获取资格月有多少天
        System.out.println(date.lengthOfMonth());
    }
}













