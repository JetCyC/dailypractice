package xianglin.com.retrofit.biz.sqlite;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

import xianglin.com.retrofit.R;

public class SqActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sq);
        //init();
        Date date = new Date();
        DateFormat dateFormat=new DateFormat() {
            @Override
            public StringBuffer format(Date date, StringBuffer toAppendTo, FieldPosition fieldPosition) {
                return null;
            }

            @Override
            public Date parse(String source, ParsePosition pos) {
                return null;
            }
        };

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
