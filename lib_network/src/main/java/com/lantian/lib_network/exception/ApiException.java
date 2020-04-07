package com.lantian.lib_network.exception;

import com.lantian.lib_network.common.ErroCode;

/**
 * Created by Sherlock·Holmes on 2020-03-12
 */
public class ApiException extends RuntimeException {
    private int mErrorCode;

    public ApiException(int errorCode, String errorMessage) {
        super(errorMessage);
        mErrorCode = errorCode;
    }

    /**
     * 判断是否是token失效
     * @return 失效返回true, 否则返回false;
     */
    public boolean isTokenExpried() {
        return mErrorCode == ErroCode.TOKEN_EXPRIED;
    }
}
