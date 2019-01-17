package com.me.io;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FileUtilsTest {

	@Test
	public void testWriteLines(){
		List<String> list = new ArrayList();
		list.add("123");
		list.add("456");
		list.add("789");
		try {
			FileUtils.writeLines(new File("data/temp.txt"),list);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testReadFileToString(){
		try {
			String str = FileUtils.readFileToString(new File("data/temp.txt"));
			System.out.println(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testReadLines(){
		try {
			List<String> list = FileUtils.readLines(new File("data/temp.txt"));
			System.out.println(list);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testCopyFile(){
		try {
			FileUtils.copyFile(new File("data/temp.txt"),new File("data/temp1.txt"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testCopyURLToFile(){
		URL url = null;
		try {
			url = new URL("https://gss2.bdstatic.com/-fo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike116%2C5%2C5%2C116%2C38/sign=2c2d89717b8b4710da22f59ea2a7a898/962bd40735fae6cded05da3106b30f2442a70f28.jpg");
			File file = new File("data/gyy.jpg");
			FileUtils.copyURLToFile(url, file);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
