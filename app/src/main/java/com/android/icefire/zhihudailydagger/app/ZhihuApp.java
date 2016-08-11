package com.android.icefire.zhihudailydagger.app;

import android.app.Application;
import android.support.annotation.NonNull;

import com.android.icefire.zhihudailydagger.model.provider.ProviderModule;
import com.android.icefire.zhihudailydagger.mvp.http.HttpManager;
import com.orhanobut.logger.Logger;

/**
 * Created by yangchj on 2016/8/9 0009.
 * email:yangchj@neusoft.com
 */
public class ZhihuApp extends Application implements IApplication{
    private static final String TAG="ZhihuApp";
    private static ZhihuApp sInstance;
    private AppComponent mAppComponent;

    public static ZhihuApp get(){
        return sInstance;
    }

    private static void setInstance(final ZhihuApp instance){
        sInstance=instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        setInstance(this);
        mAppComponent=createComponent();
        Logger.init(TAG);
    }

    protected AppComponent createComponent(){
        return DaggerAppComponent.builder()
                .providerModule(new ProviderModule(HttpManager.BASE_URL))
                .build();
    }

    @NonNull
    @Override
    public AppComponent appComponent() {
        return mAppComponent;
    }
}
