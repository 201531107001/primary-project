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
        
        Stream.of(12,3,4,5,6,7,8,9)
            .parallel()
            .forEach(System.out::print);
        
        System.out.println();
        
        int[] list = new int[50000000];
        int sum = 0;
        Random random = new Random();
        for(int i=0;i<50000000;i++) {
            list[i] = (random.nextInt()%10000);
        }
        
        long s1 = System.currentTimeMillis();
        for(int i=0;i<50000000;i++)
            sum = sum+list[i];
        System.out.println(sum);
        
        long s2 = System.currentTimeMillis();
        
        sum = IntStream.of(list)
                .parallel()
                .reduce((a,b)->a+b)
                .getAsInt();
        System.out.println(sum);
        
        long s3 = System.currentTimeMillis();
        // 因为执行时间太短，所以并行没有效果
        System.out.println((s2-s1) +" "+ (s3-s2));
    }
}
