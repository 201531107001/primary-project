package com.me.lang3;

import org.apache.commons.lang3.SystemUtils;
import org.junit.Test;

/**
 * SystemUtils操作系统工具类
 * 获取系统参数信息
 */
public class SystemUtilsTest {

	@Test
	public void test() {
		System.out.println(SystemUtils.getUserDir());
		System.out.println(SystemUtils.getUserHome());
		System.out.println(SystemUtils.getJavaHome());
		System.out.println(SystemUtils.getJavaIoTmpDir());
		System.out.println(SystemUtils.FILE_ENCODING);
		System.out.println(SystemUtils.JAVA_CLASS_PATH);
	}
}
