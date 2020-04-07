package com.lantian.lib_commin_ui.utils;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * 控件焦点批量处理
 * Created by Sherlock·Holmes on 2020-03-06
 */
public class SubmitControler {
    /**用于存储 组件的list集合**/
    private List<View> listViews = null;
    /**edittext以及textview是否有输入的监听**/
    private SubmitControler.MyTextWatcher myWatcher = null;
    /**checkbox的选中状态监听**/
    private SubmitControler.MyOnCheckedChangedListener myCheckedListener;
    /**提交“按钮”**/
    private TextView submitButton;
    private static SubmitControler myControl;


    /**
     * 获取 单例实例
     */
    public static SubmitControler getInstance() {
        if(myControl == null){
            myControl = new SubmitControler();
        }
        return myControl;
    }

    /**
     * 初始化数据 这个是必须得首先调用的，否则会出现混乱错误
     */
    public void initConroller() {
        if (listViews == null) {
            listViews = new ArrayList<View>();
        } else {
            listViews.clear();
        }
        myWatcher = new SubmitControler.MyTextWatcher();
        myCheckedListener = new SubmitControler.MyOnCheckedChangedListener();
    }

    /**
     * 添加view组件（这个view可以是textview或者是edittext或者是checkbox了）
     */
    public void addView(View... views) {
        for (View viewTemp : views) {
            if (!listViews.contains(viewTemp)) {
                if(viewTemp instanceof CheckBox){
                    ((CheckBox)viewTemp).setOnCheckedChangeListener(myCheckedListener);
                }else if(viewTemp instanceof TextView){
                    ((TextView)viewTemp).addTextChangedListener(myWatcher);
                }
                listViews.add(viewTemp);
            }
        }
    }

    /**将某个组件从监控状态中移除**/
    public void remove(View... views) {
        for (View view : views) {
            /**如果list中有 要移除的元素的话**/
            if(listViews.contains(view)){
                if (view instanceof CheckBox) {
                    ((CheckBox) view).setOnCheckedChangeListener(null);
                }else if (view instanceof TextView) {
                    ((EditText) view).removeTextChangedListener(myWatcher);
                }
                listViews.remove(view);
            }
        }
    }

    /**
     * checkbox的监听
     */
    private class MyOnCheckedChangedListener implements CompoundButton.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            changeSubmitStatus();
        }
    }

    /**
     * edittext的监听
     */
    private class MyTextWatcher implements TextWatcher {

        @Override
        public void afterTextChanged(Editable s) {
            changeSubmitStatus();
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

    }

    /**
     * 根据 各种组件状态的改变，来设置提交按钮是否可点击
     * 貌似避免不了遍历
     */
    private void changeSubmitStatus() {
        int count = 0;
        for (int i = 0; i < listViews.size(); i++) {
            if (listViews.get(i) instanceof CheckBox) {
                if (((CheckBox) listViews.get(i)).isChecked()) {
                    count++;
                } else {
                    break;
                }
            }else if (listViews.get(i) instanceof TextView) {
                if (!TextUtils.isEmpty(((TextView) listViews.get(i)).getText().toString())) {
                    count++;
                } else {
                    break;
                }
            }
        }
        if (submitButton == null) {
            throw new NullPointerException("异常");
        }
        submitButton.setEnabled((count == listViews.size()));
    }

    /**
     * 设置提交按钮
     * 并且提交按钮的状态改变是通过 textview或者edittext或者checkbox的对应赋值方法调用时候才会改变
     */
    public void setSubmitButton(TextView button) {
        this.submitButton = button;
    }
}
