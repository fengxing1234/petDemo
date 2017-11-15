package cn.picc.com.petdemo.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

/**
 * Created by fengxing on 2017/11/14.
 */

public class AgreeDetialActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WebView webView = new WebView(this);
        webView.loadUrl("www.baidu.com");
    }


    public static Intent createIntent(Context context){
        return  new Intent(context,AgreeDetialActivity.class);
    }
}
