package com.android.icefire.zhihudailydagger.mvp.base.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.android.icefire.zhihudailydagger.mvp.base.di.BaseComponent;

/**
 * Created by yangchj on 2016/8/8 0008.
 * email:yangchj@neusoft.com
 */
public abstract class BaseDIActivity<C extends BaseComponent> extends AppCompatActivity{
    private C mComponent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDependencies();
    }

    private void injectDependencies() {
        mComponent =getComponent();
        inject();
    }

    protected C getComponent() {
        return mComponent;
    }
    protected abstract void inject();
}
