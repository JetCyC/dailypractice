package yc.com.retrofit.biz.sqlite;

import android.os.Message;
import android.renderscript.Script;

/**
 * Created by ex-caoyanchang on 2017/12/26.
 */

public class Rsa {

    //加密，解密
    public static long rsa(int baseNum, int key, long message) {
        if (baseNum < 1 || key < 1) {
            return 0L;
        }

        long rsaMessage = Math.round(Math.pow(message, key)) % baseNum;
        return rsaMessage;
    }

}
