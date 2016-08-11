package com.android.icefire.zhihudailydagger.app;

import com.android.icefire.zhihudailydagger.model.news.NewsComponent;
import com.android.icefire.zhihudailydagger.model.news.NewsDetailComponent;
import com.android.icefire.zhihudailydagger.model.provider.ProviderModule;
import com.android.icefire.zhihudailydagger.mvp.base.di.ActivityModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by yangchj on 2016/8/9 0009.
 * email:yangchj@neusoft.com
 */
@Singleton
@Component(modules = {AppModule.class, ProviderModule.class})
public interface AppComponent {
    NewsComponent plus();

    NewsDetailComponent get();
}
