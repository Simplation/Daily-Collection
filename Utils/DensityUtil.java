package com.example.testutils.util;

import android.content.Context;

/**
 * dp与px的转换工具类
 */
public class DensityUtil {

    /**
     * dip转px
     *
     * @param context  上下文对象
     * @param dipValue dip值
     * @return px
     */
    public static int dip2px(Context context, float dipValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * px转dip
     *
     * @param context 上下文对象
     * @param pxValue px值
     * @return dip
     */
    public static int px2dip(Context context, float pxValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
