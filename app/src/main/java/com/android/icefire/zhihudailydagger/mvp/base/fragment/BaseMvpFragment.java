package com.android.icefire.zhihudailydagger.mvp.base.fragment;

import android.os.Bundle;

import com.hannesdorfmann.mosby.mvp.MvpFragment;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.MvpView;

/**
 * Created by yangchj on 2016/7/8 0008.
 * email:yangchj@neusoft.com
 */
public abstract class BaseMvpFragment<V extends MvpView, P extends MvpPresenter<V>> extends MvpFragment<V,P> {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
