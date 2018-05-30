package yc.com.app.biz.ndk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.facebook.stetho.Stetho;

import yc.com.app.R;
import yc.com.app.utils.ToastUtils;

public class NdkActivity extends AppCompatActivity {


    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ndk);
        textView = (TextView) findViewById(R.id.tv_msg);
        initView();
    }

    private void initView() {
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setText(JniTest.getStrFromC());
            }
        });

    }

   public static void calledByJni(String fromJni ){
       ToastUtils.showMsg(fromJni);
   }

}
