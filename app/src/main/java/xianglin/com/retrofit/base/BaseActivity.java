package xianglin.com.retrofit.base;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import org.greenrobot.eventbus.EventBus;

import xianglin.com.retrofit.utils.ActivityManagerTool;

/**
 * Created by ex-caoyanchang on 2017/12/25.
 */

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityManagerTool.getActivityManager().add(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!EventBus.getDefault().isRegistered(BaseActivity.this)) {
            EventBus.getDefault().register(BaseActivity.this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManagerTool.getActivityManager().removeActivity(this);
        EventBus.getDefault().unregister(BaseActivity.this);
    }

    // 重写这个方法，避免因为用户手机设置字体大小，而导致的屏幕适配问题
    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        Configuration config = new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config, res.getDisplayMetrics());
        return res;
    }
}
