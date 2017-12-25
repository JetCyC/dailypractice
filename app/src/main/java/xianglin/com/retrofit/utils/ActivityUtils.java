package xianglin.com.retrofit.utils;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by ex-caoyanchang on 2017/12/25.
 */

public class ActivityUtils {

    public static void addFragmenttoActivity(
            @NonNull FragmentManager fragmentManager,
            @NonNull Fragment fragment,
            int frameId,
            String tag
    ) {
        if (fragmentManager == null || fragment == null) {
            return;
        }
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(frameId, fragment, tag);
        transaction.commitNowAllowingStateLoss();
    }


    public static void addFragmenttoActivity(
            @NonNull FragmentManager fragmentManager,
            @NonNull Fragment fragment,
            int frameId
    ) {
        addFragmenttoActivity(
                fragmentManager,
                fragment,
                frameId,
                null
        );
    }

    /**
     * 替换fragment
     *
     * @param fragmentManager
     * @param fragment
     * @param frameId
     */
    public static void replaceFragment(
            @NonNull FragmentManager fragmentManager,
            @NonNull Fragment fragment,
            int frameId
    ) {
        if (fragment == null || fragmentManager == null) {
            return;
        }
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(frameId, fragment);
        transaction.commitNowAllowingStateLoss();
    }


}
