package com.android.icefire.zhihudailydagger.mvp.base.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.android.icefire.zhihudailydagger.mvp.base.di.BaseComponent;
import com.android.icefire.zhihudailydagger.mvp.base.di.HasComponent;

/**
 * Created by yangchj on 2016/8/8 0008.
 * email:yangchj@neusoft.com
 */
public abstract class BaseDIFragment<C extends BaseComponent> extends Fragment {
    private C mComponent;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDependencies();
    }

    private void injectDependencies() {
        mComponent =((HasComponent<C>) getActivity()).getComponent();
        inject();
    }

    protected C getComponent() {
        return mComponent;
    }
    protected abstract void inject();
}
