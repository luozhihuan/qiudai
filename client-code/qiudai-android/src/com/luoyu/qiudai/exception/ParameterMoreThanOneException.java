package com.luoyu.qiudai.exception;

public class ParameterMoreThanOneException extends Exception{
	public ParameterMoreThanOneException(String msg){
		super(msg);
	}
	public ParameterMoreThanOneException() {
		super("参数超过了一个，无法进行调用");
	}

}
