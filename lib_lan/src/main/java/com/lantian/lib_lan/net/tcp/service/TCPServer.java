package com.lantian.lib_lan.net.tcp.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.lantian.lib_lan.device.model.LogBean;
import com.lantian.lib_lan.device.model.PeplyData;
import com.lantian.lib_lan.device.model.PostMessage;
import com.lantian.lib_lan.subfield.SubfieldActivity;
import com.xuhao.didi.core.iocore.interfaces.ISendable;
import com.xuhao.didi.core.pojo.OriginalData;
import com.xuhao.didi.core.utils.SLog;
import com.xuhao.didi.socket.client.sdk.OkSocket;
import com.xuhao.didi.socket.client.sdk.client.OkSocketOptions;
import com.xuhao.didi.socket.common.interfaces.common_interfacies.server.IClient;
import com.xuhao.didi.socket.common.interfaces.common_interfacies.server.IClientIOCallback;
import com.xuhao.didi.socket.common.interfaces.common_interfacies.server.IClientPool;
import com.xuhao.didi.socket.common.interfaces.common_interfacies.server.IServerManager;
import com.xuhao.didi.socket.common.interfaces.common_interfacies.server.IServerShutdown;
import com.xuhao.didi.socket.server.action.ServerActionAdapter;
import com.xuhao.didi.socket.server.impl.OkServerOptions;

import org.greenrobot.eventbus.EventBus;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * Created by Sherlock·Holmes on 2020/5/15
 */
public class TCPServer extends Service implements IClientIOCallback {

    public static final String START = "start_server";
    public static final String SHUT_DOWN = "shut_server";
    private IServerManager serverManager;


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("start", "服务启动");
        if (intent.getAction().equals(START)) {
            if (!serverManager.isLive()) {
                serverManager.listen();
            } else {
                serverManager.shutdown();
            }
            Log.e("start", "服务器启动" + intent.getExtras().get("port"));
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        OkServerOptions.setIsDebug(true);
        OkSocketOptions.setIsDebug(true);
        SLog.setIsDebug(true);
        startServer(8000);
    }

    public void startServer(int port){
        serverManager = OkSocket.server(port).registerReceiver(new ServerActionAdapter() {
            @Override
            public void onServerListening(int serverPort) {
                super.onServerListening(serverPort);
                Log.i("ServerCallback", Thread.currentThread().getName() + " onServerListening,serverPort:" + serverPort);
            }

            @Override
            public void onClientConnected(IClient client, int serverPort, IClientPool clientPool) {
                super.onClientConnected(client, serverPort, clientPool);
                Log.i("ServerCallback", Thread.currentThread().getName() + " onClientConnected,serverPort:" + serverPort + "--ClientNums:" + clientPool.size() + "--ClientTag:" + client.getUniqueTag());
                //initManager(client.getHostIp(),8000);
                client.addIOCallback(TCPServer.this);

            }

            @Override
            public void onClientDisconnected(IClient client, int serverPort, IClientPool clientPool) {
                super.onClientDisconnected(client, serverPort, clientPool);
                Log.i("ServerCallback", Thread.currentThread().getName() + " onClientDisconnected,serverPort:" + serverPort + "--ClientNums:" + clientPool.size() + "--ClientTag:" + client.getUniqueTag());
                client.removeIOCallback(TCPServer.this);
            }

            @Override
            public void onServerWillBeShutdown(int serverPort, IServerShutdown shutdown, IClientPool clientPool, Throwable throwable) {
                super.onServerWillBeShutdown(serverPort, shutdown, clientPool, throwable);
                Log.i("ServerCallback", Thread.currentThread().getName() + " onServerWillBeShutdown,serverPort:" + serverPort + "--ClientNums:" + clientPool
                        .size());
                shutdown.shutdown();

            }

            @Override
            public void onServerAlreadyShutdown(int serverPort) {
                super.onServerAlreadyShutdown(serverPort);
                Log.i("ServerCallback", Thread.currentThread().getName() + " onServerAlreadyShutdown,serverPort:" + serverPort);
            }
        });
    }

    /****/
    @Override
    public void onClientRead(OriginalData originalData, IClient client, IClientPool<IClient, String> clientPool) {
        String str = new String(originalData.getBodyBytes(), StandardCharsets.UTF_8);
        if (str != null){
            client.send(new PeplyData());
        }
        Log.e("LANMSG",str +client.getUniqueTag());
        logRece(str,client);
    }

    /****/
    @Override
    public void onClientWrite(ISendable sendable, IClient client, IClientPool<IClient, String> clientPool) {
        byte[] bytes = sendable.parse();
        bytes = Arrays.copyOfRange(bytes, 4, bytes.length);
        String str = new String(bytes, StandardCharsets.UTF_8);
        JsonObject jsonObject = null;
        try {
            jsonObject = new JsonParser().parse(str).getAsJsonObject();
            int cmd = jsonObject.get("cmd").getAsInt();
            switch (cmd) {
                case 54: {
                    String handshake = jsonObject.get("handshake").getAsString();
                    Log.i("onClientIOServer", Thread.currentThread().getName() + " 发送给:" + client.getHostIp() + " 握手数据:" + handshake);
                    break;
                }
                default:
                    Log.i("onClientIOServer", Thread.currentThread().getName() + " 发送给:" + client.getHostIp() + " " + str);
            }
        } catch (Exception e) {
            Log.i("onClientIOServer", Thread.currentThread().getName() + " 发送给:" + client.getHostIp() + " " + str);
        }
    }

    private void logRece(final String log, final IClient client) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            LogBean logBean = new LogBean(System.currentTimeMillis(), log);
            EventBus.getDefault().postSticky(new PostMessage(SubfieldActivity.GET_DATA,logBean,client));
        } else {
            final String threadName = Thread.currentThread().getName();
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    logRece(log,client);
                }
            });
        }
    }

    private void logSend(final String log) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            LogBean logBean = new LogBean(System.currentTimeMillis(), log);
        } else {
            final String threadName = Thread.currentThread().getName();
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    logSend(threadName + " 线程打印(In Thread):" + log);
                }
            });
        }
    }


}
