package com.me.jdk8.function;

import java.util.function.Function;

public class TestFunction {
    public static void main(String[] args) {
        Function<Integer, Integer> name = e -> e * 2;
        Function<Integer, Integer> square = e -> e * e;
        
        // g(f(x))
        int value = name.andThen(square).apply(3);
        System.out.println("andThen value=" + value);
        
        // f(g(x))
        int value2 = name.compose(square).apply(3);
        System.out.println("compose value2=" + value2);
        
        //返回一个执行了apply()方法之后只会返回输入参数的函数对象
        String identity = (String) Function.identity().apply("huohuo");
        System.out.println(identity);
    }
}
