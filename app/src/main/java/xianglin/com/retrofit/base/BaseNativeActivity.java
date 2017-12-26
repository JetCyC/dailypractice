package xianglin.com.retrofit.base;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.WindowManager;

import xianglin.com.retrofit.utils.ActivityUtils;

/**
 * Created by ex-caoyanchang on 2017/12/25.
 */

public abstract class BaseNativeActivity extends BaseActivity {

    /**
     * 页面间传值key常量
     */
    public static final String BUNDLE = "bundle";

    /**
     * 布局文件Id
     *
     * @return
     */
    protected abstract int getContentViewId();

    /**
     * 获取fragment
     *
     * @return
     */
    protected abstract BaseFragment getFragment();

    /**
     * 布局中Fragment的id
     *
     * @param
     */
    protected abstract int getFragmentContentId();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initStatusBar();
        setContentView(getContentViewId());
        handleIntent(getIntent());
        addFragmentToActivity();//将fragmen添加到activity
        init();//初始化
    }

    protected void init() {

    }

    protected void addFragmentToActivity() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(getFragmentContentId());
        if (fragment == null) {
            fragment = getFragment();
            if (fragment != null) {
                ActivityUtils.addFragmenttoActivity(
                        getSupportFragmentManager(),
                        fragment,
                        getFragmentContentId()
                );
            }
        }

    }

    public void replaceFragmentToActivity() {
        Fragment fragment = getFragment();
        if (fragment != null) {
            ActivityUtils.replaceFragment(
                    getSupportFragmentManager(),
                    fragment,
                    getFragmentContentId()
            );
        }
    }

    protected void handleIntent(Intent intent) {

    }

    /**
     * 初始化系统状态栏
     * 在4.4以上版本是否设置透明状态栏
     */
    private void initStatusBar() {
        if (!setTranslucentStatusBar()) {
            return;
        }
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
    }

    /**
     * 设置透明状态栏
     *
     * @return true 设置, false 不设置, 默认为false
     */
    protected boolean setTranslucentStatusBar() {
        return false;
    }

}
