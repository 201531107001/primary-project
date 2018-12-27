package com.me.guava.strings;

import org.junit.Test;

import com.google.common.base.Strings;

public class TestStrings {
    
    @Test
    public void test() {
        String str1 = "123";
        String str2 = "1234";
        // 判断字符串是否为空或null
        Strings.isNullOrEmpty(str1);
        // 如果str为空，就返回null
        Strings.emptyToNull(str1);
        // 如果str为null，就返回空
        Strings.nullToEmpty(str1);
        
        // 为str后面填充padChar,直到长度为minLength
        System.out.println(Strings.padEnd(str1,5, '4'));
        
        // 重复str 多次
        System.out.println(Strings.repeat(str1, 2));
        
        // 返回str1、str2的最长前缀
        System.out.println(Strings.commonPrefix(str1, str2));
    }
}
