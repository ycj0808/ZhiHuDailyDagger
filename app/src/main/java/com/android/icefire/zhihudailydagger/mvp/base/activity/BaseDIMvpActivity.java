package com.android.icefire.zhihudailydagger.mvp.base.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.android.icefire.zhihudailydagger.mvp.base.di.ActivityModule;
import com.android.icefire.zhihudailydagger.mvp.base.di.BaseMvpComponent;
import com.android.icefire.zhihudailydagger.mvp.base.di.HasComponent;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.MvpView;
import com.hannesdorfmann.mosby.mvp.delegate.ActivityMvpDelegate;
import com.hannesdorfmann.mosby.mvp.delegate.ActivityMvpDelegateCallback;
import com.hannesdorfmann.mosby.mvp.delegate.ActivityMvpDelegateImpl;

/**
 * Created by yangchj on 2016/8/8 0008.
 * email:yangchj@neusoft.com
 */
public abstract class BaseDIMvpActivity<V extends MvpView, P extends MvpPresenter<V>,C extends BaseMvpComponent<V,P>>
        extends AppCompatActivity implements HasComponent<C>,ActivityMvpDelegateCallback<V, P>, MvpView {

    protected ActivityMvpDelegate mvpDelegate;
    protected P presenter;
    protected boolean retainInstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initializeInjector();
        super.onCreate(savedInstanceState);
        getMvpDelegate().onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getMvpDelegate().onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getMvpDelegate().onSaveInstanceState(outState);
    }

    @Override
    protected void onPause() {
        super.onPause();
        getMvpDelegate().onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getMvpDelegate().onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        getMvpDelegate().onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        getMvpDelegate().onStop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        getMvpDelegate().onRestart();
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        getMvpDelegate().onContentChanged();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        getMvpDelegate().onPostCreate(savedInstanceState);
    }

    @NonNull
    protected ActivityMvpDelegate<V, P> getMvpDelegate() {
        if (mvpDelegate == null) {
            mvpDelegate = new ActivityMvpDelegateImpl(this);
        }
        return mvpDelegate;
    }

    @NonNull
    @Override
    public final P createPresenter() {
        presenter=getComponent().presenter();
        return presenter;
    }

    @NonNull
    @Override
    public final P getPresenter() {
        return presenter;
    }

    @Override
    public final void setPresenter(@NonNull final P presenter) {
    }

    @NonNull
    @Override
    public final V getMvpView() {
        return (V) this;
    }

    @Override
    public boolean isRetainInstance() {
        return retainInstance;
    }

    @Override
    public boolean shouldInstanceBeRetained() {
        return retainInstance && isChangingConfigurations();
    }

    @Override
    public void setRetainInstance(boolean retainInstance) {
        this.retainInstance = retainInstance;
    }

    @Override
    public Object onRetainNonMosbyCustomNonConfigurationInstance() {
        return null;
    }

    /**
     * Internally used by Mosby. Use {@link #onRetainNonMosbyCustomNonConfigurationInstance()} and
     * {@link #getNonMosbyLastCustomNonConfigurationInstance()}
     */
    @Override
    public final Object onRetainCustomNonConfigurationInstance() {
        return getMvpDelegate().onRetainCustomNonConfigurationInstance();
    }

    @Override
    public final Object getNonMosbyLastCustomNonConfigurationInstance() {
        return getMvpDelegate().getNonMosbyLastCustomNonConfigurationInstance();
    }

    protected abstract void initializeInjector();

    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }
}