package com.me.jdk8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;


/**
 * 流的并行
 * @author 清明
 *
 */
public class TestStreamParallel {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        numbers.parallelStream()
               .forEach(System.out::print); 
        System.out.println();
        
        // 并行输出，所以是无序的
        Stream.of(1,2,3,4,5,6,7,8,9)
            .parallel()
            .forEach(System.out::print);
        System.out.println();
        
        // 并行计算
        int[] list = new int[50000000];
        Random random = new Random();
        for(int i=0;i<50000000;i++) {
            list[i] = (random.nextInt()%100);
        }
        int sum1 = 0;
        long s1 = System.currentTimeMillis();
        for(int i=0;i<50000000;i++)
            sum1 = (sum1 + list[i]) % 50000000;
        long s2 = System.currentTimeMillis();
        
        int sum2 = IntStream.of(list)
                .parallel()
                .reduce((a,b)->(a + b)% 50000000)
                .getAsInt();
        long s3 = System.currentTimeMillis();
        System.out.println(sum1);
        System.out.println(sum2);
        
        //213 81
        System.out.println((s2-s1) +" "+ (s3-s2));
    }
    
}
