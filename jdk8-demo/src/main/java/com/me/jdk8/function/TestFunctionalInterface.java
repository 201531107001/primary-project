package com.me.jdk8.function;

public class TestFunctionalInterface {
    public static void main(String[] args) {
        // 实力话实现接口
        test(new MyFunction());
        
        // 匿名内部类
        test(new IFunction() {
            @Override
            public void test() {
                System.out.println("test function");
            }
        });
        
        // lambad表达式
        test(()->System.out.println("test function"));
    }
    
    public static void test(IFunction function) {
        function.test();
    }
}

@FunctionalInterface
interface IFunction{
    void test();
    
    default void helo() {
        
    }
}

class MyFunction implements IFunction{
    @Override
    public void test() {
        System.out.println("test function");
    }
}