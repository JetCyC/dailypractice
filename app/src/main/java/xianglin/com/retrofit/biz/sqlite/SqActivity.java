package xianglin.com.retrofit.biz.sqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import xianglin.com.retrofit.R;

public class SqActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sq);
        init();
    }

    private void init() {
        //基数
        int baseNum = 3 * 11;
        //公钥
        int keyE = 3;
        //密钥，私钥
        int keyD = 7;
        //未加密的值
        long msg = 24L;
        //加密后
        long encode = Rsa.rsa(baseNum, keyD, msg);
        //解密后的数据
        long decode = Rsa.rsa(baseNum, keyE, encode);
        System.out.println("TAG加密前：" + msg);
        System.out.println("TAG加密后：" + encode);
        System.out.println("TAG解密后：" + decode);
    }
}
