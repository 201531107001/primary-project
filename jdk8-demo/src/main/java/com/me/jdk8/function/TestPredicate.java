package com.me.jdk8.function;

import java.util.function.Predicate;
/**
 * 条件测试器
 * @author 清明
 *
 */
public class TestPredicate {
    public static void main(String[] args) {
        Predicate<String> blankString1 = s -> s != null;
        Predicate<String> blankString2 = s -> s.length()>0;
        
        System.out.println(blankString1.test(""));
        System.out.println(blankString1.and(blankString2).test("123"));
    }
}
