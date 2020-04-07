package com.lantian.lib_network.exception;

import com.lantian.lib_network.common.ErroCode;

/**
 * 服务器响应数据错误
 * Created by Sherlock·Holmes on 2020-03-12
 */
public class ServerResponseException extends RuntimeException{
    private int errorCode;
    public ServerResponseException(int errorCode, String cause) {
        super(ErroCode.getErroMessage(errorCode, cause), new Throwable(cause));
        this.errorCode = errorCode;
    }
    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}
