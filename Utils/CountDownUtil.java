package com.example.testutils.util;

import android.os.CountDownTimer;
import android.text.Spannable;
import android.text.SpannableString;
import android.widget.Button;
import com.example.testutils.MainActivity;

/**
 * 倒计时的工具类
 */
public class CountDownUtil extends CountDownTimer {

    private MainActivity mActivity;   // 更改为注册的Activity
    private Button mButton;       // 更改为发送验证码的Button

    /**
     * 构造方法
     *
     * @param millisInFuture    总的时间millisInFuture
     * @param countDownInterval countDownInterval
     * @param activity          Activity
     * @param button            Button
     */
    public CountDownUtil(long millisInFuture, long countDownInterval, MainActivity activity, Button button) {
        super(millisInFuture, countDownInterval);

        this.mActivity = activity;
        this.mButton = button;
    }

    @Override
    public void onTick(long l) {
        // 设置不可点击
        mButton.setClickable(false);
        // 设置倒计时时间
        mButton.setText(l / 1000 +"秒后重新发送");

        // 获取按钮上的文字
        Spannable span = new SpannableString(mButton.getText().toString());
        mButton.setText(span);
    }

    @Override
    public void onFinish() {
        mButton.setText("重新获取验证码");
        // 重新获得点击
        mButton.setClickable(true);
    }
}
