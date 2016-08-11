package com.android.icefire.zhihudailydagger.mvp.presenter;

import com.android.icefire.zhihudailydagger.model.news.dao.NewsDao;
import com.android.icefire.zhihudailydagger.mvp.base.di.ActivityScope;
import com.android.icefire.zhihudailydagger.mvp.base.presenter.NullObjRxBasePresenter;
import com.android.icefire.zhihudailydagger.mvp.bean.NewsDetail;
import com.android.icefire.zhihudailydagger.mvp.view.NewsDetailView;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by yangchj on 2016/8/11 0011.
 * email:yangchj@neusoft.com
 */
@ActivityScope
public class NewsDetailPresenterImpl extends NullObjRxBasePresenter<NewsDetailView> implements NewsDetailPresenter{

    private final NewsDao newsDao;

    @Inject
    public NewsDetailPresenterImpl(final NewsDao dao){
        super();
        this.newsDao=dao;
    }

    @Override
    public void loadNewsDetail(int id) {
        addSubscription(newsDao.getNewsDetail(id)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        getView().showLoading(false);
                    }
                })
                .subscribe(new Action1<NewsDetail>() {
                    @Override
                    public void call(NewsDetail newsDetail) {
                        getView().hideLoading();
                        if (newsDetail == null) {
                            getView().showEmptyView(true);
                        } else {
                            getView().setData(newsDetail);
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        getView().hideLoading();
                        getView().showError(throwable);
                    }
                })
        );
    }
}
