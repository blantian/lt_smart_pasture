package com.lantian.lib_commin_ui.dialog;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.lantian.lib_base.utils.BaseUtils;
import com.lantian.lib_commin_ui.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Sherlock·Holmes on 2020-03-06
 */
public class AlertDialogUtil {

    public static AlertDialog alertDialog = null;
    public static AlertDialog.Builder dialog = null;
    public static boolean cmClick = true;
    public static boolean cuClick = true;
    private static boolean otClick = true;
    /**
     * 显示提示消息的对话框：仅提示
     * @param activity
     * @param title
     * @param message
     */
    public static void warningDialog(Activity activity, String title, String message) {
            alertDialog = new AlertDialog.Builder(activity, R.style.Theme_AppCompat_Light_Dialog_Alert)
                    .setTitle(title)
                    .setIcon(R.drawable.ic_action_network_erro)
                    .setMessage(message)
                    .setPositiveButton(R.string.button_sure, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    }).show();
        if (!activity.isFinishing()) {
            alertDialog.show();
        }
    }

    /**
     * 弹出对话框并有2个按钮可选择处理
     * @param activity
     * @param title
     * @param message
     */
    public static void TwoChoiceDialog(Activity activity,String title, String message,String[] items,final TwoChoiceHandle choiceHandle) {
        if (items.length != 2) {
            return;
        }
        alertDialog = new AlertDialog.Builder(activity, R.style.Theme_AppCompat_Light_Dialog_Alert)
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
        if (!activity.isFinishing()) {
            alertDialog.show();
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

        if(alertDialog.isShowing()){

        }else{
            if (!activity.isFinishing()){
                alertDialog.show();
            }
        }

    }

    public interface ThreeChoiceHandle {
        void onPositiveButtonHandle();
        void onNegativeButtonHandle();
        void onNeutralButtonButtonHandle();
    }

    /**
     * 弹出对话框并有2个按钮可选择处理
     * @param activity
     */
    public static void addcevdialog(final Context activity, final Buttons buttons) {

        dialog = new AlertDialog.Builder(activity);
        final View view = View.inflate(activity, R.layout.dialog_activity, null);
        final TextView sure_add = view.findViewById(R.id.sure);
        final TextView cancel = view.findViewById(R.id.cancel);
        final TextView dev_cm =(TextView)view.findViewById(R.id.dev_cm);
        final TextView dev_fen = (TextView)view.findViewById(R.id.dev_fen);
        final TextView dev_others = (TextView)view.findViewById(R.id.dev_other);

        final EditText et_name = view.findViewById(R.id.dev_name);
        final EditText et_ip =(EditText)view.findViewById(R.id.dev_ip);
        final EditText et_port = (EditText)view.findViewById(R.id.dev_port);
        final EditText et_username = (EditText)view.findViewById(R.id.dev_user_name);
        final EditText et_pass = (EditText)view.findViewById(R.id.dev_pass);
        alertDialog = dialog.create();
        alertDialog.setView(view);
        List<TextView> views = Arrays.asList(dev_cm,dev_fen,dev_others);
        final List<String> list = new ArrayList<>();
        for (final TextView textView: views){
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (v.getId() == R.id.dev_cm) {
                        list.clear();
                        buttons.VideoCamera();
                        list.add("VideoCamera");
                        textView.setBackground(BaseUtils.getContext().getResources().getDrawable(R.drawable.select));
                        dev_fen.setBackground(BaseUtils.getContext().getResources().getDrawable(R.drawable.bttn_select_dev));
                        dev_others.setBackground(BaseUtils.getContext().getResources().getDrawable(R.drawable.bttn_select_dev));
                    }else if (v.getId() == R.id.dev_fen){
                        buttons.Columns();
                        list.clear();
                        list.add("Columns");
                        textView.setBackground(BaseUtils.getContext().getResources().getDrawable(R.drawable.select));
                        dev_cm.setBackground(BaseUtils.getContext().getResources().getDrawable(R.drawable.bttn_select_dev));
                        dev_others.setBackground(BaseUtils.getContext().getResources().getDrawable(R.drawable.bttn_select_dev));
                    }else if (v.getId() ==R.id.dev_other){
                        buttons.Others();
                        list.clear();
                        list.add("Others");
                        textView.setBackground(BaseUtils.getContext().getResources().getDrawable(R.drawable.select));
                        dev_cm.setBackground(BaseUtils.getContext().getResources().getDrawable(R.drawable.bttn_select_dev));
                        dev_fen.setBackground(BaseUtils.getContext().getResources().getDrawable(R.drawable.bttn_select_dev));
                    }
                }
            });
        }
        sure_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (list.size()==0){
                    buttons.sendMsgg("null");

                }else if (et_name.getText().toString().isEmpty()) {
                    buttons.sendMsgg("name_null");
                }else if (et_ip.getText().toString().isEmpty()) {
                    buttons.sendMsgg("ip_null");
                }else if (et_port.getText().toString().isEmpty()){
                    buttons.sendMsgg("port_null");
                }else if (list.get(0).equals("VideoCamera")){
                    if (et_username.getText().toString().isEmpty()){
                        buttons.sendMsgg("username_null");
                    }else if (et_pass.getText().toString().isEmpty()){
                        buttons.sendMsgg("userpass_null");
                    }
                }else {
                    buttons.senddata(et_name.getText().toString(),et_ip.getText().toString(),et_port.getText().toString()
                            ,et_username.getText().toString(),et_pass.getText().toString());
                    buttons.Add();
                    alertDialog.dismiss();
                }
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttons.Cancel();
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
//        Window window = alertDialog.getWindow();
//        WindowManager windowManager = window.getWindowManager();
//        Display display = windowManager.getDefaultDisplay();
//        WindowManager.LayoutParams layoutParams = window.getAttributes();
//        layoutParams.width = (int) display.getWidth();
//        window.setAttributes(layoutParams);
//        window.getDecorView().setPadding(0,0,0,0);
//        window.setGravity(Gravity.BOTTOM);
        //window.setWindowAnimations(R.style.TimePickDialog);
    }


    public interface Buttons{
        void sendMsgg(String msg);
        void senddata(String devname,String ip,String port,String username,String pass);
        void VideoCamera();
        void Columns();
        void Others();
        void Add();
        void Cancel();
    }
}
