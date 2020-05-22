package com.lantian.lib_commin_ui.utils;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import com.lantian.lib_commin_ui.R;

/**
 * Created by Sherlock·Holmes on 2020/4/18
 */
public class NotificationUtils {

    // start notification id
    protected static int notifyID = 0525;
    //当前app是不是在用户可是界面
    protected static int foregroundNotifyID = 0555;

    public static void send(String string, Context context, Class activity) {
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context);

        Intent intent = new Intent(context, activity);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(
                context,
                notifyID,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        // 设置通知栏点击意图
        mBuilder.setContentIntent(pendingIntent);
        // 设置通知栏标题
        mBuilder.setContentTitle("这是标题");
        mBuilder.setContentText(string);
        // 设置通知小ICON（5.0必须采用白色透明图片）
        mBuilder.setSmallIcon(R.drawable.ic_action_network_erro);
        // 通知首次出现在通知栏，带上升动画效果的
        mBuilder.setTicker(string + "有警报！");

        //悬挂式Notification，5.0后显示
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mBuilder.setFullScreenIntent(pendingIntent, true);
            mBuilder.setCategory(NotificationCompat.CATEGORY_MESSAGE);
            mBuilder.setVisibility(NotificationCompat.VISIBILITY_PRIVATE);
        }

        // 设置通知大ICON
        mBuilder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_action_network_erro));
        // 通知产生的时间，会在通知信息里显示，一般是系统获取到的时间
        mBuilder.setWhen(System.currentTimeMillis());
        // 设置该通知优先级
        mBuilder.setPriority(NotificationCompat.PRIORITY_MAX);
        //在任何情况下都显示，不受锁屏影响。
        mBuilder.setVisibility(NotificationCompat.VISIBILITY_PUBLIC);
        // 设置这个标志当用户单击面板就可以让通知将自动取消
        mBuilder.setAutoCancel(true);
        // ture，设置他为一个正在进行的通知。他们通常是用来表示一个后台任务,用户积极参与(如播放音乐)或以某种方式正在等待,因此占用设备(如一个文件下载,同步操作,主动网络连接)
        // 向通知添加声音、闪灯和振动效果的最简单、最一致的方式是使用当前的用户默认设置，使用NotificationCompat.DEFAULT_ALL属性，可以组合
        mBuilder.setOngoing(false);
        //振动效果需要振动权限
        mBuilder.setVibrate(new long[]{0, 100, 500, 100});

        Uri defaultSoundUrlUri = RingtoneManager
                //声音
                .getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        mBuilder.setSound(defaultSoundUrlUri);
        //闪灯
        mBuilder.setDefaults(NotificationCompat.DEFAULT_LIGHTS);

        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Activity.NOTIFICATION_SERVICE);
        //Notification notification = mBuilder.getNotification();//API 11
        //API 16
        Notification notification = mBuilder.build();

        mNotificationManager.notify(1, notification);
    }


    public static void sendNotification(Context context, String message, boolean isForeground) {
        NotificationManager notificationManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        try {
            String notifyText = message;
            PackageManager packageManager = context.getPackageManager();
            String appName = (String) packageManager
                    .getApplicationLabel(context.getApplicationInfo());
            // notification title
            String contentTitle = appName;
            String packageName = context.getApplicationInfo().packageName;
            Uri defaultSoundUrlUri = RingtoneManager
                    .getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            // create and send notification
            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
                    .setSmallIcon(context.getApplicationInfo().icon)
                    .setSound(defaultSoundUrlUri)
                    .setWhen(System.currentTimeMillis())
                    .setAutoCancel(true);
            Intent msgIntent = context.getPackageManager()
                    .getLaunchIntentForPackage(packageName);
            PendingIntent pendingIntent = PendingIntent.getActivity(
                    context,
                    notifyID,
                    msgIntent,
                    PendingIntent.FLAG_UPDATE_CURRENT);
            // 设置该通知优先级
            mBuilder.setPriority(NotificationCompat.PRIORITY_MAX);
            //在任何情况下都显示，不受锁屏影响。
            mBuilder.setVisibility(NotificationCompat.VISIBILITY_PUBLIC);
            mBuilder.setContentTitle(contentTitle);
            mBuilder.setTicker(notifyText);
            mBuilder.setContentText(notifyText);
            mBuilder.setContentIntent(pendingIntent);
            mBuilder.setFullScreenIntent(pendingIntent, true);
            mBuilder.setOnlyAlertOnce(false);
            //闪灯
            mBuilder.setDefaults(NotificationCompat.DEFAULT_LIGHTS);
            Notification notification = mBuilder.build();

            if (isForeground) {
                notificationManager.notify(foregroundNotifyID, notification);
                notificationManager.cancel(foregroundNotifyID);
            } else {
                notificationManager.notify(notifyID, notification);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}
