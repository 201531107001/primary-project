package com.me.lang3;

import org.apache.commons.lang3.ObjectUtils;
import org.junit.Test;

/**
 * Object工具类
 *  在jdk7中的java.util.Objects中有好多关于Object的处理
 */
public class ObjectUtilsTest {
	// 此方法返回提供的值不变。
	// 这可以防止Java编译器内联一个常量字段
	public static final  int NUM_5 = ObjectUtils.CONST(5);

	@Test
	public void test(){
		Object obj = new Object();

		// 全部都不为空返回true
		ObjectUtils.allNotNull();

		// 只要有一个不为空就返回true
		ObjectUtils.anyNotNull();

		// 克隆一个对象（可以为数组）
		ObjectUtils.clone(obj);

		String s1 = "1",s2 = "2";
		// 比较两个对象（可以防止null）Null safe comparison of Comparables
		ObjectUtils.compare(s1,s2,false);

		// 如果可以克隆就克隆，否则返回原对象
		ObjectUtils.cloneIfPossible(obj);

		// 如果对象为null则返回默认值
        ObjectUtils.defaultIfNull(s1,s2);

	}
}
