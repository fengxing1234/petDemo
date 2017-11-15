package cn.picc.com.petdemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.picc.com.petdemo.R;
import cn.picc.com.petdemo.util.StatusBarUtils;

/**
 * Created by fengxing on 2017/11/2.
 */

public class RegisterActivity extends AppCompatActivity implements TextWatcher {


    @BindView(R.id.tv_title)
    TextView tv_title;


    @BindView(R.id.register_agree)
    TextView register_agree;

    @BindView(R.id.register_btn)
    TextView register_btn;

    @BindView(R.id.et_register_phone)
    EditText et_register_phone;

    @BindView(R.id.et_register_pass)
    EditText et_register_pass;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtils.setStatusBar(this, R.color.colorPet);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        tv_title.setText("注册");
        et_register_pass.addTextChangedListener(this);
        et_register_phone.addTextChangedListener(this);
    }

    @OnClick({R.id.register_btn, R.id.register_agree})
    public void onClickView(View view) {
        switch (view.getId()) {
            case R.id.toolbar_back:
                finish();
                break;
            case R.id.register_agree:
                startActivity(AgreeDetialActivity.createIntent(this));
                break;
            case R.id.register_btn:
                boolean b = et_register_pass.getText().length() > 0;
                boolean b1 = et_register_phone.getText().length() > 0;
                if (b && b1) {
                    Intent intent = new Intent(this, VerificationCode.class);
                    intent.putExtra(VerificationCode.REGISTER_PHONE, String.valueOf(et_register_phone.getText()));
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "请输入账号密码", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (et_register_pass.getText().equals("") && et_register_phone.getText().equals("")) {
            register_btn.setBackgroundResource(R.drawable.shape_register_btn_32);
        } else {
            register_btn.setBackgroundResource(R.drawable.shape_register_btn_pet);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
