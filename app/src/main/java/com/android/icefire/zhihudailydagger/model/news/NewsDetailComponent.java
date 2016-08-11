package com.android.icefire.zhihudailydagger.model.news;

import com.android.icefire.zhihudailydagger.mvp.base.di.ActivityModule;
import com.android.icefire.zhihudailydagger.mvp.base.di.ActivityScope;
import com.android.icefire.zhihudailydagger.mvp.base.di.BaseMvpComponent;
import com.android.icefire.zhihudailydagger.mvp.presenter.NewsDetailPresenter;
import com.android.icefire.zhihudailydagger.mvp.view.NewsDetailView;
import com.android.icefire.zhihudailydagger.ui.NewsDetailActivity;
import dagger.Subcomponent;

/**
 * Created by yangchj on 2016/8/11 0011.
 * email:yangchj@neusoft.com
 */
@ActivityScope
@Subcomponent(modules = {ActivityModule.class,NewsModule.class})
public interface NewsDetailComponent extends BaseMvpComponent<NewsDetailView,NewsDetailPresenter>{
    void inject(NewsDetailActivity newsDetailActivity);
}
