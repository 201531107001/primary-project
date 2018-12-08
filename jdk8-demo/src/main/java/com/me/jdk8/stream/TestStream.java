package com.me.jdk8.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestStream {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 5, 2, 3, 6, 4, 7, 8, 9);
        List<Integer> list2 = new ArrayList<>();

        Stream.of("one", "two", "three", "four").filter(e -> e.length() > 3)
                .peek(e -> System.out.println("Filtered value: " + e))
                .map(String::toUpperCase)
                .peek(e -> System.out.println("Mapped value: " + e))
                .collect(Collectors.toList());

        Stream<List<Integer>> inputStream = 
                Stream.of(Arrays.asList(1), 
                        Arrays.asList(2, 3), 
                        Arrays.asList(4, 5, 6));
        inputStream.flatMap((childList) -> childList.stream())
                .forEach(System.out::print);
        System.out.println();
        Stream<Integer> integers = Stream.of(1,2,3,4,5);
        Integer sum1 = integers.reduce(0, (a, b) -> a+b);
        //Integer sum2 = integers.reduce(0, Integer::sum);
        System.out.println(sum1);
        
        // 字符串连接，concat = "ABCD"
        String concat = Stream.of("A", "B", "C", "D")
                .reduce("", String::concat);
        // 求最小值，minValue = -3.0
        double minValue = Stream.of(-1.5, 1.0, -3.0, -2.0)
                .reduce(Double.MAX_VALUE, Double::min);
        // 求和，sumValue = 10, 有起始值
        int sumValue = Stream.of(1, 2, 3, 4)
                .reduce(0, Integer::sum);
        // 求和，sumValue = 10, 无起始值
        sumValue = Stream.of(1, 2, 3, 4)
                .reduce(Integer::sum).get();
        // 过滤，字符串连接，concat = "ace"
        concat = Stream.of("a", "B", "c", "D", "e", "F")
                .filter(x -> x.compareTo("Z") > 0)
                .reduce("", String::concat);
    }
}
