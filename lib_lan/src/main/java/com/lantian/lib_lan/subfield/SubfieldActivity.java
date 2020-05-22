package com.lantian.lib_lan.subfield;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lantian.lib_base.MyApp;
import com.lantian.lib_base.database.greendao.DevsDao;
import com.lantian.lib_base.entity.items.Devs;
import com.lantian.lib_base.file.sharedpreferences.SharedPreferencesHelper;
import com.lantian.lib_base.utils.BaseUtils;
import com.lantian.lib_commin_ui.base.BaseActivity;
import com.lantian.lib_lan.R;
import com.lantian.lib_lan.device.model.HandShakeBean;
import com.lantian.lib_lan.device.model.LogBean;
import com.lantian.lib_lan.device.model.PostMessage;
import com.lantian.lib_lan.net.tcp.LocalIP;
import com.lantian.lib_lan.net.tcp.service.TCPServer;
import com.lantian.lib_lan.subfield.adapter.LogAdapter;
import com.lantian.lib_network.receiver.NetStateChangeReceiver;
import com.lantian.lib_network.receiver.interfaces.NetStateChangeObserver;
import com.lantian.lib_network.utils.NetworkType;
import com.xuhao.didi.core.iocore.interfaces.IPulseSendable;
import com.xuhao.didi.core.iocore.interfaces.ISendable;
import com.xuhao.didi.core.pojo.OriginalData;
import com.xuhao.didi.core.utils.SLog;
import com.xuhao.didi.socket.client.impl.client.action.ActionDispatcher;
import com.xuhao.didi.socket.client.sdk.OkSocket;
import com.xuhao.didi.socket.client.sdk.client.ConnectionInfo;
import com.xuhao.didi.socket.client.sdk.client.OkSocketOptions;
import com.xuhao.didi.socket.client.sdk.client.action.SocketActionAdapter;
import com.xuhao.didi.socket.client.sdk.client.connection.IConnectionManager;
import com.xuhao.didi.socket.client.sdk.client.connection.NoneReconnect;
import com.xuhao.didi.socket.common.interfaces.common_interfacies.server.IServerManager;
import com.xuhao.didi.socket.server.impl.OkServerOptions;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sherlock·Holmes on 2020/5/18
 */
public class SubfieldActivity extends BaseActivity implements View.OnClickListener, NetStateChangeObserver {

    public static final String GET_DATA = "get_data";

    private RecyclerView loglist;
    private TextView mShow;
    private TextView showIP;
    private Button mBind;
    private Bundle bundle;
    private LogAdapter logAdapter;
    private IServerManager mServerManager;
    private IConnectionManager mManager;
    private ConnectionInfo mInfo;
    private OkSocketOptions mOkOptions;
    private String userid;
    private DevsDao devsDao;
    private String IP;
    private List<LogBean> logBeans;

    private SocketActionAdapter adapter = new SocketActionAdapter() {
        @Override
        public void onSocketConnectionSuccess(ConnectionInfo info, String action) {
            mManager.send(new HandShakeBean());

        }

        @Override
        public void onSocketDisconnection(ConnectionInfo info, String action, Exception e) {
            if (e != null) {
                logSend("异常断开(Disconnected with exception):" + e.getMessage());
            } else {
                logSend("正常断开(Disconnect Manually)");
            }
        }

        @Override
        public void onSocketConnectionFailed(ConnectionInfo info, String action, Exception e) {
            logSend("连接失败(Connecting Failed)");

        }

        @Override
        public void onSocketReadResponse(ConnectionInfo info, String action, OriginalData data) {
            String str = new String(data.getBodyBytes(), Charset.forName("utf-8"));
            //logRece(str);
        }

        @Override
        public void onSocketWriteResponse(ConnectionInfo info, String action, ISendable data) {
            String str = new String(data.parse(), Charset.forName("utf-8"));
            logSend(str);
        }

        @Override
        public void onPulseSend(ConnectionInfo info, IPulseSendable data) {
            String str = new String(data.parse(), Charset.forName("utf-8"));
            logSend(str);
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.activity_subfield;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
    }

    private void initView() {
        EventBus.getDefault().register(this);
        NetStateChangeReceiver.registerReceiver(this);
        devsDao = ((MyApp) BaseUtils.getContext()).getDaoSession().getDevsDao();
        userid = String.valueOf(SharedPreferencesHelper.get(SubfieldActivity.this,"user_name",""));
        showIP = findViewById(R.id.show_ip);
        loglist = findViewById(R.id.msg);
        mShow = (TextView) findViewById(R.id.show);
        mShow.setOnClickListener(this);
        mBind = (Button) findViewById(R.id.bind);
        mBind.setOnClickListener(this);
        initadapter();
        OkServerOptions.setIsDebug(true);
        OkSocketOptions.setIsDebug(true);
        SLog.setIsDebug(true);

    }

    private void initManager(String ip,int port) {
        final Handler handler = new Handler();
        mInfo = new ConnectionInfo(ip, port);
        mOkOptions = new OkSocketOptions.Builder()
                .setReconnectionManager(new NoneReconnect())
                .setConnectTimeoutSecond(10)
                .setCallbackThreadModeToken(new OkSocketOptions.ThreadModeToken() {
                    @Override
                    public void handleCallbackEvent(ActionDispatcher.ActionRunnable runnable) {
                        handler.post(runnable);
                    }
                })
                .build();
        mManager = OkSocket.open(mInfo).option(mOkOptions);
        mManager.registerReceiver(adapter);
    }

    private void initadapter() {
        LinearLayoutManager manager2 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        loglist.setLayoutManager(manager2);
        logBeans = new ArrayList<>();
        logAdapter = new LogAdapter(R.layout.log_item,logBeans);
        loglist.setAdapter(logAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        showIP.setText(LocalIP.getIPAddress());
        NetStateChangeReceiver.registerObserver(this);

    }

    @Override
    protected void onPause() {
        super.onPause();
        NetStateChangeReceiver.unRegisterObserver(this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        NetStateChangeReceiver.unRegisterReceiver(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void getData(PostMessage postMessage){
        if (postMessage.getMessage().equals(GET_DATA)){
            Log.e("GET",postMessage.getLogBean().mLog);
            initManager("192.168.8.21",8000);
            logBeans.add(postMessage.getLogBean());
            logAdapter.setData(0,postMessage.getLogBean());
            logAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.bind) {
            bundle = new Bundle();
            IP = bundle.getString("IP","");
            Devs devs = devsDao.queryBuilder().where(devsDao.queryBuilder().and(DevsDao.Properties.Userid.eq(userid), DevsDao.Properties.Ip.eq(IP))).unique();
            Intent intent = new Intent(SubfieldActivity.this, TCPServer.class);
            intent.setAction(TCPServer.START);
            intent.putExtra("port",8000);
            startService(intent);
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

    /**网络实时监听**/
    @Override
    public void onNetDisconnected() {

    }

    @Override
    public void onNetConnected(NetworkType networkType) {
        if (networkType==NetworkType.NETWORK_WIFI||networkType ==NetworkType.NETWORK_4G||networkType==NetworkType.NETWORK_2G
        ||networkType == NetworkType.NETWORK_3G){
            Log.e("NOLAN","不在局域网");
        }else {
            Log.e("LAN","局域网模式");
        }
    }
}
