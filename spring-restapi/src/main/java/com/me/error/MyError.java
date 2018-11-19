package com.me.error;

/**
 * 自定义错误类
 * @author gqm
 *
 */
public class MyError {
	private int code;
	private String message;

	public MyError(int code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	} 

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
