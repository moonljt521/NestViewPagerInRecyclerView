package com.ht.testlist.vp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

import java.lang.reflect.Field;
import java.util.Objects;

/**
 * author: jiangtao.liang
 * date:   On 2019/5/10 17:30
 */
public class NestVp extends ViewPager {

    private boolean hasActivityDestroy;

    public NestVp(@NonNull Context context) {
        super(context);
    }

    public NestVp(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    //是否可以进行滑动
    private boolean isSlide = false;

    public void setSlide(boolean slide) {
        isSlide = slide;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return isSlide;
    }

//    @Override
//    protected void onAttachedToWindow() {
//        super.onAttachedToWindow();
//        try {
//            Field mFirstLayout = ViewPager.class.getDeclaredField("mFirstLayout");
//            mFirstLayout.setAccessible(true);
//            mFirstLayout.set(this, false);
//            Objects.requireNonNull(getAdapter()).notifyDataSetChanged();
//            setCurrentItem(getCurrentItem());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

//    @Override
//    protected void onDetachedFromWindow() {
//        if (hasActivityDestroy) {
//            super.onDetachedFromWindow();
//        }
//    }

    public void setHasDestroy(boolean hasDestroy) {
        hasActivityDestroy = hasDestroy;
    }
}
