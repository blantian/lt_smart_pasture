package com.lantian.lib_network.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;

import com.lantian.lib_base.utils.BaseUtils;
import com.lantian.lib_network.receiver.interfaces.NetStateChangeObserver;
import com.lantian.lib_network.utils.NetworkType;
import com.lantian.lib_network.utils.NetworkUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sherlock·Holmes on 2020/4/20
 */
public class NetStateChangeReceiver extends BroadcastReceiver {

    private NetworkType mType = NetworkUtil.getNetworkType(BaseUtils.getContext());

    private static class InstanceHolder{
        private static final NetStateChangeReceiver INSTANCE = new NetStateChangeReceiver();
    }

    private List<NetStateChangeObserver> mObservers = new ArrayList<>();

    @Override
    public void onReceive(Context context, Intent intent) {
        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())){
            NetworkType networkType = NetworkUtil.getNetworkType(context);
            notifyObservers(networkType);
        }
    }

    public static void registerReceiver(Context context){
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        context.registerReceiver( InstanceHolder.INSTANCE,intentFilter);
    }

    public static void unRegisterReceiver(Context context){
        context.unregisterReceiver( InstanceHolder.INSTANCE);
    }

    public static void registerObserver(NetStateChangeObserver observer){
        if (observer == null) {
            return;
        }
        if (!InstanceHolder.INSTANCE.mObservers.contains(observer)){
            InstanceHolder.INSTANCE.mObservers.add(observer);
        }
    }

    public static void unRegisterObserver(NetStateChangeObserver observer){
        if (observer == null) {
            return;
        }
        if (InstanceHolder.INSTANCE.mObservers == null) {
            return;
        }
        InstanceHolder.INSTANCE.mObservers.remove(observer);
    }

    private void notifyObservers(NetworkType networkType){
        if (mType == networkType) {
            return;
        }
        mType = networkType;
        if (networkType == NetworkType.NETWORK_NO){
            for (NetStateChangeObserver observer : mObservers){
                observer.onNetDisconnected();
            }
        }else {
            for (NetStateChangeObserver observer : mObservers){
                observer.onNetConnected(networkType);
            }
        }
    }
}
