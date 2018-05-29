package yc.com.app.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by ex-caoyanchang on 2017/12/25.
 */

public abstract class BaseFragment extends Fragment {

    BaseNativeActivity mActivity;
    /**
     * butterknife  解绑对象
     *
     * @param savedInstanceState
     */
    private Unbinder unbinder;

    /**
     * 获取fragment layoutId
     *
     * @return
     */
    protected abstract int getLayoutId();

    protected abstract void initView(View view, Bundle savedInstanceState);


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseNativeActivity) {
            this.mActivity = (BaseNativeActivity) context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        //butterknife
        unbinder = ButterKnife.bind(this, view);
        initView(view, savedInstanceState);
        return view;
    }

    @Override
    public void onDestroy() {
        mActivity = null;//???
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }
}

