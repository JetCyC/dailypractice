package xianglin.com.retrofit;

import android.app.Application;

/**
 * Created by ex-caoyanchang on 2017/12/24.
 */

public class MyApplication extends Application {

    private static MyApplication instance;

    public static MyApplication getInstance() {
        return instance;
    }

    @Override
    public synchronized void onCreate() {
        super.onCreate();
        instance = this;
    }
}
