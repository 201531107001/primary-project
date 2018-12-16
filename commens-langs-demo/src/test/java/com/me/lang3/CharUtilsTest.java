package com.me.lang3;

import org.apache.commons.lang3.CharUtils;
import org.junit.Test;

public class CharUtilsTest {

	@Test
	public void test() {
		char a = 'a',b = 'b';
		String str = "a";

		// 比较两个字符
		CharUtils.compare(a,b);

		// 单个字符转字符串
		CharUtils.toString(a);

		// 获取字符串的第一个字符
		CharUtils.toChar(str);

		// 判断字符是否为数字字符
		CharUtils.isAsciiNumeric(a);

		// 将数字字符转换为对应的int
		//CharUtils.toIntValue(a);

		System.out.println(Integer.toHexString(b));
		// 将字符编码转为unicode(java的编码)
		System.out.println(CharUtils.unicodeEscaped(a));
	}
}
