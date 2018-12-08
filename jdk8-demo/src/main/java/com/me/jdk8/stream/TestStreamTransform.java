package com.me.jdk8.stream;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 流的转换
 * @author 清明
 *
 */
public class TestStreamTransform {
    public static void main(String[] args) {
        List<String> list = Stream.of("12","34","56","78")
                                    .collect(Collectors.toList());
        System.out.println(list);
        
        Object[] objs = Stream.of("12","34","56","78")
                .toArray();
        Stream.of(objs).forEach(System.out::print);
        System.out.println();
        
        String[] strs = Stream.of("12","34","56","78")
                .toArray(String[]::new);
        Stream.of(strs).forEach(System.out::print);
        System.out.println();
    }
}
