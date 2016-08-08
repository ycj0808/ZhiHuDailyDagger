package com.android.icefire.zhihudailydagger.mvp.base.view;

import com.hannesdorfmann.mosby.mvp.MvpView;

/**
 * Created by yangchj on 2016/7/12 0012.
 * email:yangchj@neusoft.com
 */
public interface BaseMvpView<M> extends MvpView{

    public void showLoading(boolean pullToRefresh);

    public void hideLoading();

    public void showError(Throwable e);

    public void setData(M data);

    public void showEmptyView(boolean flag);
}
