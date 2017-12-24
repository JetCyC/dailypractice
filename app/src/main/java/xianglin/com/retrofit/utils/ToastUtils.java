package xianglin.com.retrofit.utils;

import android.text.TextUtils;
import android.widget.Toast;

import xianglin.com.retrofit.MyApplication;

/**
 * Created by ex-caoyanchang on 2017/12/24.
 */

public class ToastUtils {

    public static void showMsg(String msg) {
        if (TextUtils.isEmpty(msg)) {
            return;
        }
        Toast.makeText(MyApplication.getInstance(), msg, Toast.LENGTH_SHORT).show();
    }
}