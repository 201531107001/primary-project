package com.me.swagger.demo.entity;

import lombok.Data;

@Data
public class ApiResult<T> {
	private Integer code;
	private String message;
	private T data;

	public ApiResult(Integer code,String message){
		this.code = code;
		this.message = message;
	}
}
