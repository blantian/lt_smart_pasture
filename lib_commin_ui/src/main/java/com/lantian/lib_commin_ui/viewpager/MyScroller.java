package com.lantian.lib_commin_ui.viewpager;

import android.content.Context;
import android.view.animation.Interpolator;
import android.widget.Scroller;

/**
 * Created by Sherlock·Holmes on 2020-03-31
 */
public class MyScroller extends Scroller {

    private static final Interpolator sInterpolator = new Interpolator() {
        @Override
        public float getInterpolation(float t) {
            t -= 1.0f;
            return t * t * t * t * t + 1.0f;
        }
    };
    public boolean noDuration;

    public MyScroller(Context context) {
        super(context);
    }

    public void setNoDuration(boolean noDuration) {
        this.noDuration = noDuration;
    }

    public MyScroller(Context context, Interpolator interpolator) {
        super(context, interpolator);
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy, int duration) {
        if (noDuration){
            //界面滑动不需要时间间隔
            super.startScroll(startX, startY, dx, dy, 0);
        }

        else{
            super.startScroll(startX, startY, dx, dy, duration);
        }
    }
}