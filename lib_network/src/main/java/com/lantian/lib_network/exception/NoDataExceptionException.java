package com.lantian.lib_network.exception;

/**
 * 服务器返回异常
 * Created by Sherlock·Holmes on 2020-03-12
 */
public class NoDataExceptionException extends RuntimeException{
    public NoDataExceptionException() {
        super("服务器没有返回对应的Data数据", new Throwable("Server error"));
    }
}
