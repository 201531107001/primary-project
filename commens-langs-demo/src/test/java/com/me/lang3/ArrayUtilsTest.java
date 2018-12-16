package com.me.lang3;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;

/**
 * 数组工具类
 */
public class ArrayUtilsTest {
	@Test
	public void test(){
		int[] list = {1,2,3,4,5};
		// 每次添加一个元素，每次都新建一个长度加一的数组去储存
		ArrayUtils.add(list,1);

		// 克隆数组
		ArrayUtils.clone(list);

		// 判断数组是否包含元素
		ArrayUtils.contains(list,2);

		// 返回元素的下标，如果没有找到，返回-1
		ArrayUtils.indexOf(list,5);

		// 为null或长度为0时返回true
		ArrayUtils.isEmpty(list);

		// 数组是否按 自然顺序排序
		ArrayUtils.isSorted(list);

		// 为null则返回一个长度为0的数组
		ArrayUtils.nullToEmpty(list);

		// 反转数组
		ArrayUtils.reverse(list);

		// 将数组移位
		ArrayUtils.shift(list,1);
		System.out.println(ArrayUtils.toString(list));

		// 交换两个位置的元素
		ArrayUtils.swap(list,1,2);

		// 以两个位置为起点，依次交换长度为n次元素，每次加一
		ArrayUtils.swap(list,0,2,2);
		System.out.println(ArrayUtils.toString(list));

		// 剪切数组
		ArrayUtils.subarray(list,2,5);

	}
}
