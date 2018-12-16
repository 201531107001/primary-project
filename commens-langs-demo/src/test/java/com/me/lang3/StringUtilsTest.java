package com.me.lang3;

import org.junit.Test;
import org.apache.commons.lang3.StringUtils;

public class StringUtilsTest {

	// 判断某字符串是否为空，为空的标准是 str==null 或 str.length()==0
	@Test
	public void testIsEmpty() {
		StringUtils.isEmpty(null);// = true
		StringUtils.isEmpty("");// = true
		StringUtils.isEmpty(" ");// = false //注意在 StringUtils 中空格作非空处理
		StringUtils.isEmpty("   ");// = false
		StringUtils.isEmpty("bob");// = false
		StringUtils.isEmpty(" bob ");// = false

	}

	// 判断某字符串是否为空或长度为0或由空白符(whitespace) 构成
	@Test
	public void testIsBlank() {
		StringUtils.isBlank(null); // 均识为空白符
		StringUtils.isBlank("\b");// = false //"\b"为单词边界符
		StringUtils.isBlank("bob");// = false
		StringUtils.isBlank(" bob ");// = false
		StringUtils.isBlank(" ");// = true
		StringUtils.isBlank("\t \n \f \r");// = true 对于制表符、换行符、换页符和回车符

		System.out.println(StringUtils.isBlank(null));
	}

	/**
	 * 去掉字符串两端的控制符(control characters, char <= 32) , 如果输入为 null则返回null
	 * 控制符就是用于打印和显示控制、数据结构化、传输控制、以及其他零散用途的特殊符号。如/t /f whitespace
	 * 等制表符、换行符、换页符和回车符
	 */
	@Test
	public void testTrim() {
		StringUtils.trim(null);// = null
		StringUtils.trim("");// = ""
		StringUtils.trim(" ");// = ""
		StringUtils.trim("  \b \t \n \f \r		");// = ""
		StringUtils.trim("     \n\tss   \b");// = "ss"
		StringUtils.trim(" d   d dd     ");// = "d d dd"
		StringUtils.trim("dd     ");// = "dd"
		StringUtils.trim("     dd       ");// = "dd"

		System.out.println(StringUtils.trim(" d   d dd		"));
		System.out.println(StringUtils.trim("  \b \t \n \f \r		"));
		System.out.println(StringUtils.trim("     \n\tss   \b"));

	}

	// 去掉字符串两端的控制符(control characters, char <= 32) ,如果变为 null 或""，则返回 null
	@Test
	public void testTrimToNull() {
		StringUtils.trimToNull(null);// = null
		StringUtils.trimToNull("");// = null
		StringUtils.trimToNull(" ");// = null
		StringUtils.trimToNull("     \b \t \n \f \r    ");// = null
		StringUtils.trimToNull("     \n\tss   \b");// = "ss"
		StringUtils.trimToNull(" d   d dd     ");// = "d d dd"
		StringUtils.trimToNull("dd     ");// = "dd"
		StringUtils.trimToNull("     dd       ");// = "dd"

		System.out.println(StringUtils.trimToNull(" "));
		System.out.println(StringUtils.trimToNull("     dd       "));

	}

	// 去掉字符串两端的空白符(whitespace) ，如果输入为 null 则返回 null下面是示例(注意和 trim() 的区别)
	@Test
	public void testStrip() {
		StringUtils.strip(null);// = null
		StringUtils.strip("");// = ""
		StringUtils.strip(" ");// = ""
		StringUtils.strip("    \b \t \n \f \r    ");// = "\b"
		StringUtils.strip("     \n\tss   \b");// = "ss \b"
		StringUtils.strip(" d   d dd     ");// = "d d dd"
		StringUtils.strip("dd     ");// = "dd"
		StringUtils.strip("     dd       ");// = "dd"

		System.out.println((int) '\b');
		System.out.println(StringUtils.strip("     \b \t \n \f \r    "));
		System.out.println(StringUtils.strip("     dd       "));
	}

	// 比较两个字符串是否相等，如果两个均为空则也认为相等。
	@Test
	public void testEquals() {
		StringUtils.equals(null, null);// = true
		StringUtils.equals(null, "abc");// = false
		StringUtils.equals("abc", null);// = false
		StringUtils.equals("abc", "abc");// = true
		StringUtils.equals("abc", "ABC");// = false
		System.out.println(StringUtils.equals(null, null));
	}

	/**
	 * 回字符 searchChar 在字符串 str 中第一次出现的位置。如果 searchChar 没有在 str 中出现则返回-1， 如果 str
	 * 为 null 或 "" ，则也返回-1
	 */
	@Test
	public void testIndexOf() {
		// StringUtils.indexOf(null, *);// = -1
		// StringUtils.indexOf("", *);// = -1
		StringUtils.indexOf("aabaabaa", 'a');// = 0
		StringUtils.indexOf("aabaabaa", 'b');// = 2
		System.out.println(StringUtils.indexOf("cbadf", 'a'));
	}

	//字符串拼接
	@Test
	public void testJoin() {
		System.out.println(StringUtils.join(new String[]{"a", "b", "c"}, ","));
	}

	//判断字符串是否为数字
	@Test
	public void testIsNumeric() {

		System.out.println(StringUtils.isNumeric("123"));
	}

	@Test
	public void testSplic() {

		String str = "a b c";
		System.out.println(StringUtils.split(str)[1]);

		str = "a,b,c";
		System.out.println(StringUtils.split(str, ",")[1]);
	}

	@Test
	public void testContains() {
		System.out.println(StringUtils.contains("abc", "a"));
	}

}
