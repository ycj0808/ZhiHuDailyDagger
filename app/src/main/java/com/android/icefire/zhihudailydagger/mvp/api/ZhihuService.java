package com.android.icefire.zhihudailydagger.mvp.api;


import com.android.icefire.zhihudailydagger.mvp.bean.NewsDetail;
import com.android.icefire.zhihudailydagger.mvp.bean.NewsList;
import com.android.icefire.zhihudailydagger.mvp.http.HttpManager;

import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by yangchj on 2016/7/11 0011.
 * email:yangchj@neusoft.com
 */
public interface ZhihuService {

    //最新消息
    @Headers(HttpManager.CACHE_CONTROL_AGE+ HttpManager.CACHE_STALE_SHORT)
    @GET("news/latest")
    Observable<NewsList> getLatestNews();

    //消息内容获取与离线下载(消息详情)
    @Headers(HttpManager.CACHE_CONTROL_AGE+ HttpManager.CACHE_STALE_LONG)
    @GET("news/{id}")
    Observable<NewsDetail> getNewsDetail(@Path("id") int id);

    //过往消息
    @Headers(HttpManager.CACHE_CONTROL_AGE+ HttpManager.CACHE_STALE_LONG)
    @GET("news/before/{date}")
    Observable<NewsList> getBeforeNews(@Path("date") String date);
}
