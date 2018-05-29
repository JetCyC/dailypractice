package yc.com.app.utils;

import android.app.Activity;

import java.util.LinkedList;
import java.util.List;

/**
 * activity管理类
 * Created by ex-caoyanchang on 2017/12/25.
 */

public class ActivityManagerTool {
    private static ActivityManagerTool instance;
    private final List<Activity> activities = new LinkedList<>();

    //获得管理对象
    public static ActivityManagerTool getActivityManager() {
        if (instance == null) {
            instance = new ActivityManagerTool();
        }
        return instance;
    }

    public boolean add(final Activity activity) {
        if (!activities.add(activity)) {
            return false;
        }
        return true;
    }

    public void exit() {
        for (Activity activity : activities) {
            if (activity != null) {
                activity.finish();
            }
        }
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }

    public Activity getCurrentActivity() {

        if (activities == null || activities.isEmpty()) {
            return null;
        }
        Activity activity = activities.get(activities.size() - 1);
        return activity;
    }

    /*
    * 是否存在指定的Activity
    * */
    public boolean isExistActivity(final Class<? extends Activity> targetclazz) {
        Activity activity;
        if (activities == null) {
            return false;
        }
        for (int i = activities.size() - 1; i >= 0; i--) {
            activity = activities.get(i);
            if (activity.getClass() == targetclazz) {
                return true;
            }
        }
        return false;
    }

    /**
     * 删除已经finish的activity
     *
     * @param activity
     */
    public void removeActivity(final Activity activity) {
        if (activity != null) {
            activities.remove(activity);
        }
    }

}
