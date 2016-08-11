package com.android.icefire.zhihudailydagger.mvp.presenter;

import com.android.icefire.zhihudailydagger.mvp.view.NewsDetailView;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;

/**
 * Created by yangchj on 2016/8/11 0011.
 * email:yangchj@neusoft.com
 */
public interface NewsDetailPresenter extends MvpPresenter<NewsDetailView>{
    public void loadNewsDetail(int id);
}
