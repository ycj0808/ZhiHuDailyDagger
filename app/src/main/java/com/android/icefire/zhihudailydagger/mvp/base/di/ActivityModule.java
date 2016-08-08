package com.android.icefire.zhihudailydagger.mvp.base.di;

import android.support.v7.app.AppCompatActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by yangchj on 2016/8/8 0008.
 * email:yangchj@neusoft.com
 */
@Module
public class ActivityModule {
    private final AppCompatActivity mActivity;
    public ActivityModule(final AppCompatActivity activity){
        mActivity=activity;
    }

    @ActivityScope
    @Provides
    AppCompatActivity provideActivity(){
        return mActivity;
    }
}
