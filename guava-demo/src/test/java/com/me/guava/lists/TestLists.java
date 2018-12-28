package com.me.guava.lists;

import java.util.List;

import org.junit.Test;

import com.google.common.collect.Lists;
import com.google.common.primitives.Ints;

public class TestLists {
    @Test
    public void test() {
        List<String> list = Lists.newArrayList();
        List<Integer> list2 = Ints.asList(1,2,3,4,5,6,7);
        
        // 将list按size长度切开
        List<List<Integer>> list3 = Lists.partition(list2, 3);
        System.out.println(list3);
    }
}
