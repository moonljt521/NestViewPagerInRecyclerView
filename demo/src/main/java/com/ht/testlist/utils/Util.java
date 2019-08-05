package com.ht.testlist.utils;

import android.content.res.Resources;
import android.util.TypedValue;

/**
 * author: jiangtao.liang
 * date:   On 2019/8/5 15:46
 */
public class Util {

    public static int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                Resources.getSystem().getDisplayMetrics());
    }

}
