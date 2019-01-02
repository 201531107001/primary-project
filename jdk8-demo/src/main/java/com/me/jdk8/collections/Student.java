package com.me.jdk8.collections;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Student {
    private String name;
    private String password;
    private Integer age;
}
