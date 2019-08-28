package com.example.testutils.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Environment;
import android.util.DisplayMetrics;

import java.io.File;

public class DisplayUtils {

    /**
     * dp2px
     *
     * @param dp      要转换的dp数值
     * @param context 上下文对象
     * @return 转换后的px
     */
    public static float convertDpToPixel(float dp, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        return dp * (metrics.densityDpi / 160f);
    }

    /**
     * convert px to its equivalent dp
     * 将px转换为与之相等的dp
     */
    public static int px2dp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * convert dp to its equivalent px
     * 将dp转换为与之相等的px
     */
    public static int dp2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * convert px to its equivalent sp
     * 将px转换为sp
     */
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * convert sp to its equivalent px
     * 将sp转换为px
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * 获取 App 的版本号
     * @param context 上下文对象
     * @return double codeVersion
     */
    public static double getVersionCode(Context context) {
        PackageManager packageManager = context.getPackageManager();
        PackageInfo info;
        try {
            info = packageManager.getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return 1.0;
        }

        return info.versionCode;
    }

    /**
     * 获取文件存储的路径
     *
     * @param context 上下文对象
     * @return path
     */
    public static String getApkPath(Context context) {
        String directoryPath = "";

        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {  //判断外部存储是否能用
            directoryPath = context.getExternalFilesDir("apk").getAbsolutePath();
        } else {
            directoryPath = context.getFilesDir() + File.separator + "apk";
        }

        File file = new File(directoryPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        return directoryPath;
    }
}
