package cn.picc.com.petdemo.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import cn.picc.com.petdemo.R;

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
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            Window window = getWindow();
            //设置状态栏的颜色 白色
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.WHITE);
            Log.d(TAG, "沉浸");


            //设置导航栏的颜色
            window.setNavigationBarColor(getResources().getColor(R.color.colorWhite));

        }

        setContentView(R.layout.activity_splash);
        handler.sendEmptyMessageDelayed(HANDLER_CODE, 1500);
    }
}
