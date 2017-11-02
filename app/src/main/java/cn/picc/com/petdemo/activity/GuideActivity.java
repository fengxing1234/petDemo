package cn.picc.com.petdemo.activity;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhy.autolayout.AutoLinearLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.picc.com.petdemo.Config;
import cn.picc.com.petdemo.R;
import cn.picc.com.petdemo.adapter.GuideAdapter;
import cn.picc.com.petdemo.util.StatusBarUtils;

/**
 * Created by fengxing on 2017/10/30.
 */

public class GuideActivity extends AppCompatActivity {

    private static final String TAG = GuideActivity.class.getSimpleName();

    @BindView(R.id.btn_register)
    Button btn_register;

    @BindView(R.id.btn_login)
    Button btn_login;

    @BindView(R.id.vp_guide)
    ViewPager viewPager;

    @BindView(R.id.shape_red_circle)
    ImageView red_circle;

    @BindView(R.id.father_circle)
    AutoLinearLayout father_circle;


    private ArrayList<View> views;
    private TextView tv_title;
    private TextView tv_des;

    private int left;
    private ValueAnimator va;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtils.setStatusBar(this, R.color.colorWhite);
        setContentView(R.layout.activity_guide);
        ButterKnife.bind(this);
        views = new ArrayList<>();
        initData();
        GuideAdapter adapter = new GuideAdapter(views);
        viewPager.addOnPageChangeListener(new ViewPagerChangeListener());
        red_circle.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int left1 = father_circle.getChildAt(1).getLeft();
                int left0 = father_circle.getChildAt(0).getLeft();
                left = left1 - left0;
                Log.d(TAG, "left1 : " + left1 + "  left0 : " + left0 + " left :" + left);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    red_circle.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                } else {
                    Log.d(TAG, "版本太低 不能移除全局监听");
                }

            }
        });
        viewPager.setAdapter(adapter);
        startAnimation(0);
    }

    //初始化数据
    private void initData() {
        for (int i = 0; i < Config.LEADER_TOP.length; i++) {
            View view = getLayoutInflater().inflate(R.layout.guide_viewpager, null);
            tv_title = view.findViewById(R.id.tv_guide_title);
            tv_des = view.findViewById(R.id.tv_guide_description);

            tv_title.setText(Config.LEADER_TOP[i]);
            tv_des.setText(Config.LEADER_SECONG[i]);
            views.add(view);
        }

    }

    @OnClick(R.id.btn_login)
    public void login() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.btn_register)
    public void register() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
        finish();
    }

    private int index;

    public void startAnimation(final int position) {
        final ImageView picture = views.get(position).findViewById(R.id.iv_guide_picture);
        va = ValueAnimator.ofFloat(0, 1.0f);
        va.setDuration(100);
        va.setRepeatCount(-1);
        va.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                Log.d(TAG, "onAnimationStart: ");
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                Log.d(TAG, "onAnimationEnd: ");
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {
                switch (position) {
                    case 0:
                        setImages(Config.IMG_ONE);
                        break;
                    case 1:
                        setImages(Config.IMG_TWO);
                        break;
                    case 2:
                        setImages(Config.IMG_THREE);
                        break;
                    case 3:
                        setImages(Config.IMG_FOUR);
                        break;
                }
                index++;
            }

            private void setImages(int[] resource) {
                Log.d(TAG, "index : " + index);
                picture.setImageResource(resource[index]);
                if (index == resource.length - 1) {
                    va.end();
                }
            }
        });
        va.start();
    }

    private class ViewPagerChangeListener implements ViewPager.OnPageChangeListener {


        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            //Log.d(TAG, "position : " + position + "positionOffset:" + positionOffset + "positionOffsetPixels:" + positionOffsetPixels);
            float pixels = left * (position + positionOffset);
            int round = Math.round(pixels);
            //父控件 的布局参数
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) red_circle.getLayoutParams();
            layoutParams.leftMargin = round;
            red_circle.setLayoutParams(layoutParams);
        }

        @Override
        public void onPageSelected(int position) {
            startAnimation(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            if (va != null && state > 0) {
                index = 0;
                va.end();
            }
        }
    }
}
