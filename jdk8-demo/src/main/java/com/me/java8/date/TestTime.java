package com.me.java8.date;

import java.time.LocalTime;

public class TestTime {
    public static void main(String[] args) {
        LocalTime time = LocalTime.now();
        System.out.println(time);
        
        System.out.println(time.getHour()+" "+time.getMinute()+" "+time.getSecond()+" "+time.getNano());
        
        // time比较大小
        System.out.println(time.isAfter(LocalTime.of(10, 20)));
        
    }
}
