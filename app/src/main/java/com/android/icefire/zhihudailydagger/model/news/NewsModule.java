package com.android.icefire.zhihudailydagger.model.news;

import com.android.icefire.zhihudailydagger.model.news.dao.NewsDao;
import com.android.icefire.zhihudailydagger.model.news.dao.NewsDaoImpl;
import com.android.icefire.zhihudailydagger.mvp.api.ZhihuService;
import com.android.icefire.zhihudailydagger.mvp.base.di.ActivityScope;
import com.android.icefire.zhihudailydagger.mvp.presenter.LastNewsPresenter;
import com.android.icefire.zhihudailydagger.mvp.presenter.LastNewsPresenterImpl;
import com.android.icefire.zhihudailydagger.mvp.presenter.NewsDetailPresenter;
import com.android.icefire.zhihudailydagger.mvp.presenter.NewsDetailPresenterImpl;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by yangchj on 2016/8/8 0008.
 * email:yangchj@neusoft.com
 */
@Module
public class NewsModule {
    @Provides
    LastNewsPresenter provideLastNewsPresenter(final LastNewsPresenterImpl presenter){
        return presenter;
    }

    @Provides
    NewsDetailPresenter provideNewsDetailPresenter(final NewsDetailPresenterImpl presenter){
        return presenter;
    }

    @ActivityScope
    @Provides
    NewsDao provideNewsDao(final NewsDaoImpl newsDao){
        return newsDao;
    }

    @ActivityScope
    @Provides
    ZhihuService provideZhihuService(final Retrofit retrofit){
        return retrofit.create(ZhihuService.class);
    }
}
