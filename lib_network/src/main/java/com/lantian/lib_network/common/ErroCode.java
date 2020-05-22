package com.lantian.lib_network.common;

import androidx.annotation.StringRes;

import com.lantian.lib_base.utils.BaseUtils;
import com.lantian.lib_network.R;

/**
 * Created by Sherlock·Holmes on 2020-03-12
 */
public class ErroCode {

    /**
     * 登录成功
     */
    public static final int SUCCESS = 200;
    /**
     * 账号密码错误
     */
    public static final int ERRO_PASS_USNAME=402;

    public static final int BREED_ID = 422;

    public static final  int TOKEN_EXPRIED = -1;
    public static final int REMOTE_LOGIN = 91011;

    public static String getErroMessage(int erroCode,String erroMessage){
        String message = null;
        switch (erroCode){
            case ERRO_PASS_USNAME:
                message = getString(R.string.user_pass_erro);
                break;
                default:
        }
        return message;
    }
    private static String getString(@StringRes int resId) {
        return BaseUtils.getContext().getString(resId);
    }
}
