package com.example.msi.countdown;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

/**
 * Created by MSI on 2017/10/24.
 */

public class CountdownButton extends Button implements View.OnClickListener {
    int background;
    int textColor;
    String textStr = "发送验证码";
    int totalTime = 60 * 1000;
    int intervalsTime = 1000;

    public CountdownButton(Context context) {
        super(context);

    }

    public CountdownButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        final TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CountdownButton);
        background = typedArray.getColor(R.styleable.CountdownButton_btn_background, getResources().getColor(R.color.colorAccent));
        textColor = typedArray.getColor(R.styleable.CountdownButton_btn_Color, getResources().getColor(R.color.white));
        textStr = typedArray.getString(R.styleable.CountdownButton_btn_text);
        setTextColor(textColor);
//        setBackgroundColor(background);
        setText(textStr);
        setOnClickListener(this);
    }

    public void setTime(int totalTime, int intervalsTime) {
        this.totalTime = totalTime;
        this.intervalsTime = intervalsTime;
    }

    @Override
    public void onClick(View view) {
        CountDownTimer countDownTimer = new NumberTimer(totalTime, intervalsTime);
        countDownTimer.start();
    }

    public class NumberTimer extends CountDownTimer {
        long millisInFuture;

        public NumberTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
            this.millisInFuture = millisInFuture;
        }

        @Override
        public void onTick(long time) {
            setEnabled(false);
            if (time == totalTime) {
                time = totalTime - intervalsTime;
            }
            //倒计时每秒的回调
            setText(time / intervalsTime + "s");
        }

        @Override
        public void onFinish() {
            setEnabled(true);
            //倒计时结束
            setText(textStr);
        }
    }

}

