package yc.com.app.biz.handler;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import yc.com.app.R;

//功能：每隔5秒去服务器查询最新的数据
public class HandlerActivity extends AppCompatActivity {

    private TextView mTextView;
    private HandlerThread mCheckMsgThread;
    private Handler mCheckMsgHandler;
    private boolean isUpdateInfo;//是否查询
    //与UI线程管理的handler
    private Handler mHandler = new Handler();
    private static final int MSG_UPDATE_INFO = 0x110;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);
        initBackThread();
        mTextView = (TextView) findViewById(R.id.tv_query);
    }

    /**
     * 开启后台线程
     */
    private void initBackThread() {
        mCheckMsgThread = new HandlerThread("check-message-coming");
        mCheckMsgThread.start();
        mCheckMsgHandler = new Handler(mCheckMsgThread.getLooper()) {
            @Override
            public void handleMessage(Message msg) {//子线程
                checkForUpdate();//从服务器解析数据

                if (isUpdateInfo) {
   // mCheckMsgHandler.sendEmptyMessageDelayed()
                }
            }
        };
    }

    private void checkForUpdate() {//子线程

        try {
            Thread.sleep(1000);
            mHandler.post(new Runnable() {
                @Override
                public void run() {//主线程

                    mTextView.setText("更新完成");

                }
            });

        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void onResume() {
        super.onResume();
        //开始查询
        isUpdateInfo = true;
        mCheckMsgHandler.sendEmptyMessage(MSG_UPDATE_INFO);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //停止查询
        isUpdateInfo = false;
        mCheckMsgHandler.removeMessages(MSG_UPDATE_INFO);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCheckMsgThread.quit();
    }
}
