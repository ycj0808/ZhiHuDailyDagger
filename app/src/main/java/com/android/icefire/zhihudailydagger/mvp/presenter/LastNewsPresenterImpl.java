package com.android.icefire.zhihudailydagger.mvp.presenter;

import com.android.icefire.zhihudailydagger.model.news.dao.NewsDao;
import com.android.icefire.zhihudailydagger.mvp.api.ZhihuService;
import com.android.icefire.zhihudailydagger.mvp.base.di.ActivityScope;
import com.android.icefire.zhihudailydagger.mvp.base.presenter.NullObjRxBasePresenter;
import com.android.icefire.zhihudailydagger.mvp.bean.NewsList;
import com.android.icefire.zhihudailydagger.mvp.view.LastNewsView;
import com.android.icefire.zhihudailydagger.support.utils.ExceptionUtils;
import com.orhanobut.logger.Logger;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by yangchj on 2016/8/9 0009.
 * email:yangchj@neusoft.com
 */
@ActivityScope
public class LastNewsPresenterImpl extends NullObjRxBasePresenter<LastNewsView> implements LastNewsPresenter {
    private final NewsDao newsDao;

    @Inject
    public LastNewsPresenterImpl(final NewsDao api) {
        super();
        newsDao = api;
    }

    @Override
    public void loadLastNews(final boolean pullToRefresh) {
        getView().showLoading(pullToRefresh);
        addSubscription(newsDao.getLatestNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        if (isViewAttached()) {
                            getView().showLoading(pullToRefresh);
                        }
                    }
                })
                .subscribe(new Action1<NewsList>() {
                    @Override
                    public void call(NewsList newsList) {
                        if (isViewAttached()) {
                            getView().hideLoading();
                            getView().endRefresh(pullToRefresh);
                            if (newsList.getStories() == null) {
                                getView().showEmptyView(true);
                            } else {
                                getView().setData(newsList.getStories());
                                getView().showEmptyView(false);
                                String curDate = newsList.getDate();
                                if (newsList.getStories().size() < 8) {
                                    getView().setCurrentDate(curDate, true);
                                } else {
                                    getView().setCurrentDate(curDate, false);
                                }
                            }
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Logger.e(throwable, ExceptionUtils.getErrorInfo(throwable));
                        getView().hideLoading();
                        getView().endRefresh(pullToRefresh);
                        getView().showError(throwable);
                    }
                })
        );
    }

    @Override
    public void loadBeforeNews(String date) {
        addSubscription(newsDao.getBeforeNews(date)
        .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<NewsList>() {
                    @Override
                    public void call(NewsList newsList) {
                        if(newsList.getStories()!=null){
                            getView().addMoreData(newsList.getStories());
                            String curDate=newsList.getDate();
                            getView().setCurrentDate(curDate,false);
                        }
                        getView().endLoadMore();
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        getView().showError(throwable);
                        getView().endLoadMore();
                    }
                })
        );
    }
}
