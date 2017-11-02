package cn.picc.com.petdemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import cn.picc.com.petdemo.R;
import cn.picc.com.petdemo.util.StatusBarUtils;

public class SplashActivity extends AppCompatActivity {

    private static final int HANDLER_CODE = 0x123;
    private static final String TAG = SplashActivity.class.getSimpleName();

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Intent intent = new Intent(SplashActivity.this, GuideActivity.class);
            startActivity(intent);
            finish();
            super.handleMessage(msg);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtils.setStatusBar(this, R.color.colorWhite);
        setContentView(R.layout.activity_splash);
        handler.sendEmptyMessageDelayed(HANDLER_CODE, 1500);
    }
}
