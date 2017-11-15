package cn.picc.com.petdemo.util;

import android.app.Activity;
import android.os.Build;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by fengxing on 2017/11/2.
 */

public class StatusBarUtils {

    public static void setStatusBar(Activity activity, int resource) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            //设置状态栏的颜色 白色
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(activity.getResources().getColor(resource));

            //设置导航栏的颜色
            window.setNavigationBarColor(activity.getResources().getColor(resource));

        }

    }
}
