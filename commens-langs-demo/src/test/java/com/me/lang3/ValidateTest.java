package com.me.lang3;

import org.apache.commons.lang3.Validate;
import org.junit.Test;

public class ValidateTest {

	@Test
	public void test() {
		// 判断值是否在一个范围内(不可以为边界值)
		Validate.exclusiveBetween(1,5,3);

		// 判断值是否在一个范围内(可以为边界值)
		Validate.inclusiveBetween(1,5,3);

		// 判断条件是否成立
		Validate.isTrue(1 == 2);

		// 判断对象是否为空
		String s = "123";
		Validate.notNull(s);

		// 判断下标是否合法
		Validate.validIndex(s, 1);
	}
}
