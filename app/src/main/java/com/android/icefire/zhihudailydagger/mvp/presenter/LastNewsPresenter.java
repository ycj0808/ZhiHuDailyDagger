package com.android.icefire.zhihudailydagger.mvp.presenter;

import com.android.icefire.zhihudailydagger.mvp.view.LastNewsView;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;

/**
 * 加载最新的消息
 * Created by yangchj on 2016/8/8 0008.
 * email:yangchj@neusoft.com
 */
public interface LastNewsPresenter extends MvpPresenter<LastNewsView>{
    public void loadLastNews(final boolean pullToRefresh);
    public void loadBeforeNews(String date);
}
