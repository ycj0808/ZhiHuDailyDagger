package com.android.icefire.zhihudailydagger.model.news;

import com.android.icefire.zhihudailydagger.mvp.base.di.ActivityModule;
import com.android.icefire.zhihudailydagger.mvp.base.di.ActivityScope;
import com.android.icefire.zhihudailydagger.mvp.base.di.BaseMvpComponent;
import com.android.icefire.zhihudailydagger.mvp.presenter.LastNewsPresenter;
import com.android.icefire.zhihudailydagger.mvp.view.LastNewsView;
import com.android.icefire.zhihudailydagger.ui.MainActivity;

import dagger.Subcomponent;

/**
 * Created by yangchj on 2016/8/9 0009.
 * email:yangchj@neusoft.com
 */
@ActivityScope
@Subcomponent(modules = {ActivityModule.class,NewsModule.class})
public interface NewsComponent extends BaseMvpComponent<LastNewsView,LastNewsPresenter>{
    void inject(MainActivity mainActivity);
}
