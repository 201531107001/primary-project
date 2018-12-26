package com.me.guava.ints;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.google.common.primitives.Ints;

public class IntsTest {

    @Test
    public void test() {
        List<Integer> list = Ints.asList(1,2,3,4,5);
        System.out.println(list);
        System.out.println(Ints.compare(2, 3));
    }
    
    @Test
    public void testConcat() {
        int[] a = {1,2};
        int[] b = {3,4};
        int[] c = Ints.concat(a,b);
        System.out.println(Arrays.toString(c));
    }
}
