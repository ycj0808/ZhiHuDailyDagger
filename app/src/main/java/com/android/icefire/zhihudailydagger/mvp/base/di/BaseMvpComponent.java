package com.android.icefire.zhihudailydagger.mvp.base.di;

import android.support.annotation.NonNull;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.MvpView;

/**
 * Created by yangchj on 2016/8/8 0008.
 * email:yangchj@neusoft.com
 */
public interface BaseMvpComponent <V extends MvpView,P extends MvpPresenter<V>> extends BaseComponent{

    @NonNull
    P presenter();
}
