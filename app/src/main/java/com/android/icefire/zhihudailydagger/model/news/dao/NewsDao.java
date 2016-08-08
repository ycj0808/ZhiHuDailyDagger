package com.android.icefire.zhihudailydagger.model.news.dao;

import android.support.annotation.NonNull;

import com.android.icefire.zhihudailydagger.mvp.bean.NewsList;

import rx.Observable;

/**
 * Created by yangchj on 2016/8/8 0008.
 * email:yangchj@neusoft.com
 */
public interface NewsDao {

    @NonNull
    Observable<NewsList> getLatestNews();
}
