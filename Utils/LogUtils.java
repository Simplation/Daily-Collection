package com.zw.materialdesigndemo;

import android.util.Log;

/**
 * ========================================================
 * <p>
 * Author：Simplation
 * Time  ：2017/8/24 12:14
 * Desc  ：日志的工具类
 * <p>
 * ========================================================
 */
public class LogUtils {

    private static final int VERBOSE = 1;
    private static final int DEBUG = 2;
    private static final int INFO = 3;
    private static final int WARM = 4;
    private static final int ERROR = 5;
    // 屏蔽所有日志
    private static final int NOTHING = 6;

    /**
     * 修改日志等级，就可以有选择的筛选日志
     */
    private static final int level = VERBOSE;

    public static void v(String tag, String msg) {
        if (level == VERBOSE) {
            Log.v(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (level == DEBUG) {
            Log.d(tag, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (level == INFO) {
            Log.i(tag, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (level == WARM) {
            Log.w(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (level == ERROR) {
            Log.e(tag, msg);
        }
    }
}
