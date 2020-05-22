package com.lantian.lib_network.receiver.interfaces;
import com.lantian.lib_network.utils.NetworkType;

/**
 * Created by Sherlock·Holmes on 2020/4/20
 */
public interface NetStateChangeObserver {
    void onNetDisconnected();
    void onNetConnected(NetworkType networkType);
}
