package com.lantian.lib_commin_ui.utils;

import android.content.Context;
import android.text.Selection;
import android.text.Spannable;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ToggleButton;

import com.lantian.lib_commin_ui.R;

/**
 * Created by Sherlock·Holmes on 2020-03-17
 */
public class ViewUtils implements View.OnClickListener {

    private ToggleButton toggleButton;
    private EditText editText;
    /**
     * 隐藏密码/显示密码
     * @param editText
     */
    public void ShowHidePass(EditText editText,ToggleButton toggleButton) {
        this.editText =editText;
        this.toggleButton = toggleButton;
    }

    @Override
    public void onClick(View v) {
        TransformationMethod method = editText.getTransformationMethod();
        if (method == HideReturnsTransformationMethod.getInstance()) {
            editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
        } else {
            editText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        }
        // 保证切换后光标位于文本末尾
        Spannable spanText = editText.getText();
        if (spanText != null) {
            Selection.setSelection(spanText, spanText.length());
        }
    }
}
