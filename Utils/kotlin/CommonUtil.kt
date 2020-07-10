package com.example.utils

import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.text.TextUtils
import android.util.TypedValue
import android.view.View
import android.view.inputmethod.InputMethodManager
import java.io.BufferedReader
import java.io.FileReader
import java.io.IOException
import java.lang.reflect.Field
import java.util.*


object CommonUtil {

    fun dp2px(context: Context, dpValue: Float): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dpValue,
            context.resources.displayMetrics
        ).toInt()
    }

    fun sp2px(context: Context, spValue: Float): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_SP,
            spValue,
            context.resources.displayMetrics
        ).toInt()
    }

    fun px2dp(pxValue: Int): Int {
        val scale = Resources.getSystem().displayMetrics.density
        return (pxValue / scale + 0.5F).toInt()
    }

    fun px2sp(pxValue: Int): Int {
        val fontScale = Resources.getSystem().displayMetrics.density
        return (pxValue / fontScale + 0.5F).toInt()
    }


    /**
     * 获取状态栏高度
     *
     * @param context context
     * @return 状态栏高度
     */
    fun getStatusBarHeight(context: Context): Int {
        // 获得状态栏高度
        val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")
        return context.resources.getDimensionPixelSize(resourceId)
    }

    /**
     * 获取随机 rgb 颜色值
     */
    fun randomColor(): Int {
        val random = Random()
        // 0-190, 如果颜色值过大,就越接近白色,就看不清了,所以需要限定范围
        var red = random.nextInt(190)
        var green = random.nextInt(190)
        var blue = random.nextInt(190)
        if (SettingUtil.getIsNightMode()) {
            // 150-255
            red = random.nextInt(105) + 150
            green = random.nextInt(105) + 150
            blue = random.nextInt(105) + 150
        }
        // 使用 rgb 混合生成一种新的颜色, Color.rgb 生成的是一个 int 数
        return Color.rgb(red, green, blue)
    }

    /**
     * 解决 InputMethodManager 引起的内存泄漏
     * 在 Activity 的 onDestroy 方法里调用
     */
    @Suppress("DEPRECATED_IDENTITY_EQUALS")
    fun fixInputMethodManagerLeak(context: Context) {

        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        val arr = arrayOf("mCurRootView", "mServedView", "mNextServedView")
        var field: Field?
        var objGet: Any?
        for (i in arr.indices) {
            val param = arr[i]
            try {
                field = imm.javaClass.getDeclaredField(param)
                if (field.isAccessible === false) {
                    field.isAccessible = true
                }
                objGet = field.get(imm)
                if (objGet != null && objGet is View) {
                    val view = objGet
                    if (view.context === context) {
                        // 被 InputMethodManager 持有引用的 context 是想要目标销毁的
                        field.set(imm, null) // 置空，破坏掉 path to gc 节点
                    } else {
                        // 不是想要目标销毁的，即为又进了另一层界面了，不要处理，避免影响原逻辑,也就不用继续 for 循环了
                        break
                    }
                }
            } catch (t: Throwable) {
                t.printStackTrace()
            }

        }

    }

    /**
     * 获取当前进程名
     */
    fun getProcessName(pid: Int): String {
        var reader: BufferedReader? = null
        try {
            reader = BufferedReader(FileReader("/proc/$pid/cmdline"))
            var processName = reader.readLine()
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim { it <= ' ' }
            }
            return processName
        } catch (throwable: Throwable) {
            throwable.printStackTrace()
        } finally {
            try {
                reader?.close()
            } catch (exception: IOException) {
                exception.printStackTrace()
            }
        }
        return ""
    }

}
