package com.me.jdk8.function;

import java.util.function.Consumer;

/**
 * 数据消费器
 * @author 清明
 *
 */
public class TestConsumer {
    public static void main(String[] args) {
        Consumer<String> consumer = s -> {s = s.substring(1, s.length()-1);
                                    System.out.println(s);
                                };
        
        consumer.andThen(s -> System.out.println(s)).accept("hello");
    }
}
