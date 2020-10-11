package com.avipras.customviews.scrollableViewPager;

import android.app.Activity;

import androidx.viewpager2.widget.ViewPager2;

import java.util.TimerTask;

public class SliderTimer extends TimerTask {
    private ViewPager2 viewPager;
    private int size;
    private Activity activity;

    public SliderTimer(ViewPager2 viewPager, int size, Activity activity) {
        this.viewPager = viewPager;
        this.size = size;
        this.activity = activity;
    }

    @Override
    public void run() {
        activity.runOnUiThread(() -> {
            if (viewPager.getCurrentItem() < size - 1) {
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1, true);
            } else {
                viewPager.setCurrentItem(0, true);
            }
        });
    }
}