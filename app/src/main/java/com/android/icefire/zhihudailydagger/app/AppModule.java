package com.android.icefire.zhihudailydagger.app;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by yangchj on 2016/8/9 0009.
 * email:yangchj@neusoft.com
 */
@Module
public class AppModule {
    private final Application mApplication;

    public AppModule(final Application application){
        mApplication=application;
    }
    @Singleton
    @Provides
    Application provideApplication(){
        return mApplication;
    }

    @Singleton
    @Provides
    Resources provideResources(){
        return mApplication.getResources();
    }

    @Singleton
    @Provides
    Context provideContext(){
        return mApplication;
    }
}
