package com.me.jdk8.collections;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class TestMap {
    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        
        students.add(new Student("gqa", "123", 15));
        students.add(new Student("gqb", "123", 15));
        students.add(new Student("gqc", "123", 12));
        students.add(new Student("gqd", "123", 13));
        students.add(new Student("gqe", "123", 15));
        students.add(new Student("gqf", "123", 15));
        students.add(new Student("gqg", "123", 15));
        students.add(new Student("gqh", "123", 12));
        students.add(new Student("gqi", "123", 13));
        students.add(new Student("gqj", "123", 15));
        
        Map<Integer, List<Student>> groupByAge = students.stream()
                .collect(Collectors.groupingBy(s->s.getAge()));
//        Map<Integer, List<Student>> groupByAge = students.stream()
//                .collect(Collectors.groupingBy(Student::getAge));
        
        Set<Integer> set = groupByAge.keySet();
        for (Integer age : set) {
            List<Student> list = groupByAge.get(age);
            System.out.println(list);
        }
    }
}
