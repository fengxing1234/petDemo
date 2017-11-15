package cn.picc.com.petdemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.picc.com.petdemo.R;

/**
 * Created by fengxing on 2017/11/14.
 */

public class VerificationCode extends AppCompatActivity {
    public static final String REGISTER_PHONE = "register_phone";

    @BindView(R.id.tx_register_phone)
    TextView tv_register_phone;

    @BindView(R.id.code_btn)
    TextView btn_code;

    @BindView(R.id.code_getnums)
    EditText code_getnums;

    @BindView(R.id.code_nums)
    TextView code_nums;

    @BindView(R.id.register_agree)
    TextView register_agree;

    @BindView(R.id.tv_title)
    TextView tv_title;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            boolean stop = msg.what == 0;
            if (stop) {
                isStart = false;
                code_nums.setText("重新获取");
                code_nums.setClickable(true);
                code_nums.setBackgroundResource(R.drawable.shape_msg_btn_pet);
            } else {
                code_nums.setClickable(false);
                code_nums.setBackgroundResource(R.drawable.shape_btn_white);
                code_nums.setText(msg.what + "  s");
            }
        }
    };

    private boolean isStart = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);
        ButterKnife.bind(this);
        tv_title.setText("注册");
        Intent intent = getIntent();
        String stringExtra = intent.getStringExtra(REGISTER_PHONE);
        tv_register_phone.setText(stringExtra);
        sendMsg();
        code_getnums.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d("fengxing", "onTextChanged: " + s);
                if(s.length()>0){
                    btn_code.setBackgroundResource(R.drawable.shape_msg_btn_pet);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void sendMsg() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                int position = 60;
                while (isStart) {
                    handler.sendEmptyMessage(position--);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    @OnClick({R.id.code_btn, R.id.toolbar_back, R.id.code_nums, R.id.register_agree})
    public void onclick(View v) {
        switch (v.getId()) {
            case R.id.toolbar_back:
                onBackPressed();
                break;
            case R.id.code_nums:
                isStart = true;
                sendMsg();
                break;
            case R.id.code_btn:
                if (code_getnums.getText().length() > 0) {
                    startActivity(AddPetActivity.createIntent(this));
                } else {
                    Toast.makeText(this, "请输入验证码", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.register_agree:
                startActivity(AgreeDetialActivity.createIntent(this));
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
