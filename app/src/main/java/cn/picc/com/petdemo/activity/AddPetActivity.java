package cn.picc.com.petdemo.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by fengxing on 2017/11/15.
 */

public class AddPetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static Intent createIntent(Context context) {
        return new Intent(context, AddPetActivity.class);
    }

}
