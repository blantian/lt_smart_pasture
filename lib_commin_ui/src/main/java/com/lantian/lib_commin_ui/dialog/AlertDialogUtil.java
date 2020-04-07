package com.lantian.lib_commin_ui.dialog;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;

import com.lantian.lib_commin_ui.R;

/**
 * Created by Sherlock·Holmes on 2020-03-06
 */
public class AlertDialogUtil {

    public static AlertDialog alertDialog = null;
    /**
     * 显示提示消息的对话框：仅提示
     * @param context
     * @param title
     * @param message
     */
    public static void warningDialog(Context context, String title, String message) {
        if(alertDialog == null){
            alertDialog = new AlertDialog.Builder(context, R.style.Theme_AppCompat_Light_Dialog_Alert)
                    .setTitle(title)
                    .setIcon(R.drawable.ic_action_network_erro)
                    .setMessage(message)
                    .setPositiveButton(R.string.button_sure, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    }).show();
        }else{
            if(alertDialog.isShowing()){

            }else{
                alertDialog.show();
            }
        }
    }

    /**
     * 弹出对话框并有2个按钮可选择处理
     * @param context
     * @param title
     * @param message
     */
    public static void TwoChoiceDialog(Context context,String title, String message,String[] items,final TwoChoiceHandle choiceHandle) {
        if(items.length!=2){
            return;
        }
        if(alertDialog == null){
            alertDialog = new AlertDialog.Builder(context, R.style.Theme_AppCompat_Light_Dialog_Alert)
                    .setTitle(title)
                    .setMessage(message)
                    .setIcon(R.drawable.ic_action_network_erro)
                    .setPositiveButton(items[0], new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            choiceHandle.onPositiveButtonHandle();
                        }
                    })
                    .setNegativeButton(items[1], new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            choiceHandle.onNegativeButtonHandle();
                        }
                    }).show();
        }else{
            if(alertDialog.isShowing()){

            }else{
                alertDialog.show();
            }
        }
    }


    public interface TwoChoiceHandle {
        void onPositiveButtonHandle();
        void onNegativeButtonHandle();
    }

    /**
     * 弹出对话框并有3个按钮可选择处理
     * @param activity
     * @param title
     * @param message
     */
    public static void ThreeChoiceDialog(Activity activity, String title, String message, String[] items, final ThreeChoiceHandle choiceHandle) {
        if(items.length!=3){
            return;
        }
        /*if(alertDialog == null){*/
        alertDialog = new AlertDialog.Builder(activity, R.style.Theme_AppCompat_Light_Dialog_Alert)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(items[0], new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        choiceHandle.onPositiveButtonHandle();
                    }
                })
                .setNegativeButton(items[1], new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        choiceHandle.onNegativeButtonHandle();
                    }
                })
                .setNeutralButton(items[2], new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        choiceHandle.onNeutralButtonButtonHandle();
                    }
                }).show();
        /*}else{*/
        if(alertDialog.isShowing()){

        }else{
            if (!activity.isFinishing()){
                alertDialog.show();
            }
        }
        /* }*/
    }

    public interface ThreeChoiceHandle {
        void onPositiveButtonHandle();
        void onNegativeButtonHandle();
        void onNeutralButtonButtonHandle();
    }
}
