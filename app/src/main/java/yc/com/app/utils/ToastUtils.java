package yc.com.app.utils;

import android.text.TextUtils;
import android.widget.Toast;

import yc.com.app.biz.MyApplication;


/**
 * Created by ex-caoyanchang on 2017/12/24.
 */

public class ToastUtils {

    private static Toast mToast;

    public static void showMsg(String msg) {
        if (TextUtils.isEmpty(msg)) {
            return;
        }
        if (mToast == null) {
            mToast = Toast.makeText(MyApplication.getInstance(), msg, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(msg);
        }
        mToast.show();
    }
}
