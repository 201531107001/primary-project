package com.me.io;

import org.apache.commons.io.Charsets;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.StringBuilderWriter;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;

public class IOUtilsTest {
	// 这个方法可以拷贝流，算是这个工具类中使用最多的方法了。支持多种数据间的拷贝.copy内部使用的其实还是copyLarge方法
	@Test
	public void testCopy(){
		try {
			InputStream inputStream = FileUtils.openInputStream(FileUtils.getFile("data/temp.txt"));
			Writer writer = new StringBuilderWriter();
			IOUtils.copy(inputStream,writer);
			System.out.println(writer.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 这个方法会用一个固定大小的Buffer，持续不断的读取数据，然后写入到输出流中。
	@Test
	public void testRead(){
		try {
			InputStream inputStream = IOUtils.toInputStream("hello world");
			byte[] buffer =  new byte[1000];
			IOUtils.read(inputStream,buffer);
			System.out.println(String.valueOf(new String(buffer,Charsets.UTF_8)));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 把数据写入到输出流中
	@Test
	public void testWrite(){
		OutputStream out = System.out;
		try {
			IOUtils.write("hello world",out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 用于跳过指定长度的流
	@Test
	public void skipTest(){
		InputStream is = IOUtils.toInputStream("hello world");
		try {
			IOUtils.skip(is,4);
			System.out.println(IOUtils.toString(is,"utf-8"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
