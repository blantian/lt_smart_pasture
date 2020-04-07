package com.lantian.lt_smart_pasture.view.remakepass;

import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import com.lantian.lt_smart_pasture.R;

public class CountDownTimer extends android.os.CountDownTimer {

    private TextView textView;
    /**
     * @param millisInFuture    The number of millis in the future from the call
     *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
     *                          is called.
     * @param countDownInterval The interval along the way to receive
     *                          {@link #onTick(long)} callbacks.
     */
    public CountDownTimer(TextView textView, long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
        this.textView = textView;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        //设置不可点击
        textView.setClickable(false);
        //设置倒计时时间
        textView.setText(millisUntilFinished / 1000 + "秒后可重新发送");
        textView.setTextSize(10);
        textView.setBackgroundResource(R.color.afterClick);
        SpannableString spannableString = new SpannableString(textView.getText().toString());
        ForegroundColorSpan span = new ForegroundColorSpan(Color.WHITE);
        spannableString.setSpan(span, 0, 2, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        textView.setText(spannableString);
    }

    @Override
    public void onFinish() {
        textView.setText("重新获取验证码");
        textView.setTextSize(10);
        //重新获得点击
        textView.setClickable(true);
        textView.setBackgroundResource(R.color.afterSelector);
    }
}
