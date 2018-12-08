package com.me.jdk8.stream;

import java.util.stream.IntStream;

public class TestIntStream {
    public static void main(String[] args) {
        IntStream.of(new int[]{1, 2, 3}).forEach(System.out::print);
        System.out.println();
        IntStream.range(1, 3).forEach(System.out::print);
        System.out.println();
        IntStream.rangeClosed(1, 3).forEach(System.out::print);
        System.out.println();
        System.out.println(IntStream.rangeClosed(1, 5).sum());
    }
}
