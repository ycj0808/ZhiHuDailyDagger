package com.android.icefire.zhihudailydagger.model.news.dao;

import android.support.annotation.NonNull;

import com.android.icefire.zhihudailydagger.mvp.api.ZhihuService;
import com.android.icefire.zhihudailydagger.mvp.base.di.ActivityScope;
import com.android.icefire.zhihudailydagger.mvp.bean.NewsList;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by yangchj on 2016/8/8 0008.
 * email:yangchj@neusoft.com
 */
@ActivityScope
public class NewsDaoImpl implements NewsDao{

    private final ZhihuService mZhihuApi;
    @Inject
    public NewsDaoImpl(final ZhihuService api){
        mZhihuApi=api;
    }

    @NonNull
    @Override
    public Observable<NewsList> getLatestNews() {
        return mZhihuApi.getLatestNews();
    }
}
