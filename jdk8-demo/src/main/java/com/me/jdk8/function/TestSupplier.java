package com.me.jdk8.function;

import java.util.function.Supplier;

/**
 * 数据提供器
 * @author 清明
 *
 */
public class TestSupplier {
    public static void main(String[] args) {
        Supplier<Student> supplier = Student::new;
        System.out.println(supplier.get());
        
        Supplier<Student> supplier3 = () -> {
            return new Student("gqm", 19);
        };
        System.out.println(supplier3.get());
    }
}
