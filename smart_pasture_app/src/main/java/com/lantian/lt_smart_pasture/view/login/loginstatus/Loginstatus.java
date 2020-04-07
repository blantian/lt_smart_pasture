package com.lantian.lt_smart_pasture.view.login.loginstatus;

/**
 * 网络状态
 * Created by Sherlock·Holmes on 2020-03-12
 */
public interface Loginstatus {

    /**
     * 有网状态
     */
    public void online();

    /**
     * 无网络状态
     */
    public void offline();
}
