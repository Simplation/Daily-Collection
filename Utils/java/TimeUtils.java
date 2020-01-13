package com.example.testutils.utils;

import android.app.Activity;
import android.os.CountDownTimer;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;


public class TimeUtils {

    private static CountDownTimer timer;
    private static long time = 0;//倒计时

    /**
     * 倒计时
     *
     * @param times 时间
     */
    public static void countDown(long times, final TextView textView, final Activity activity) {
        timer = new CountDownTimer(times, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                time = millisUntilFinished / 1000;
                // textView.setText(time + "秒");
                textView.setText(String.format("%s 秒", time));
                textView.setEnabled(false);
//                textView.setTextColor(activity.getResources().getColor(R.color.white));
//                textView.setBackgroundResource(R.color.code_color);

            }

            @Override
            public void onFinish() {
                textView.setText("重新获取验证码");
                textView.setEnabled(true);
//                textView.setTextColor(activity.getResources().getColor(R.color.white));
//                textView.setBackgroundResource(R.color.colorPrimaryDark);
            }
        }.start();
    }

    public static void finsh() {
        if (timer != null) {
            timer.cancel();
        }
    }

    /**
     * 返回年
     *
     * @return year
     */
    public static int getYear() {
        Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        return c.get(Calendar.YEAR);
    }

    /**
     * 返回月
     *
     * @return 月
     */
    public static int getMonth() {
        Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        return c.get(Calendar.MONTH);
    }

    /**
     * 返回日
     *
     * @return 日
     */
    public static int getDay() {
        Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        return c.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 返回时
     *
     * @return 时
     */
    public static int getHour() {
        Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        return c.get(Calendar.HOUR);

    }

    /**
     * 返回分
     *
     * @return 分
     */
    public static int getMinute() {
        Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        return c.get(Calendar.MINUTE);
    }

    /**
     * 格式化时间戳
     * ---输入所要转换的时间戳例如（1402733340）输出（"2014年06月14日16时09分00秒"）
     *
     * @param timeStamp 时间戳
     * @return YYYY.MM.dd HH:mm
     */
    public static String year_month_day(long timeStamp) {
        Date d = new Date(timeStamp);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return sf.format(d);

    }

    /**
     * 格式化时间戳
     * ---输入所要转换的时间戳例如（1402733340）输出（"2014年06月14日16时09分00秒"）
     *
     * @param timeStamp 时间戳
     * @return YYYY.MM.dd HH:mm
     */
    public static String Year_month_day_hour_minute(long timeStamp) {
        Date d = new Date(timeStamp);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return sf.format(d);

    }

    /**
     * date 转 string
     *
     * @param date date
     * @return string
     */
    public static String getStringTime(Date date) {// 可根据需要自行截取数据显示
        // SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  // 格式化年月日时分秒
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());   // 格式化年月日
        return format.format(date);
    }

    /**
     * date 转 string
     *
     * @param date date
     * @return string
     */
    public static String getStringTimes(Date date) {// 可根据需要自行截取数据显示
         SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());  // 格式化年月日时分秒
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");   // 格式化年月日
        return format.format(date);
    }
}
