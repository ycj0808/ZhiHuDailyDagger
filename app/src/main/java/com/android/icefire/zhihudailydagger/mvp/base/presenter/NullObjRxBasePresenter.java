package com.android.icefire.zhihudailydagger.mvp.base.presenter;

import android.support.annotation.NonNull;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.MvpView;

import java.lang.ref.WeakReference;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by yangchj on 2016/6/8 0008.
 * email:yangchj@neusoft.com
 */
public abstract class NullObjRxBasePresenter<V extends MvpView> implements MvpPresenter<V> {
    private CompositeSubscription mSubscription;
    private WeakReference<V> mViewRef;

    /**
     * add a {@link Subscription} to the CompositeSubscription.
     *
     * @param subscription {@link Subscription} to add.
     */
    protected void addSubscription(final Subscription subscription) {
        if (mSubscription == null || mSubscription.isUnsubscribed()) {
            mSubscription = new CompositeSubscription();
        }
        mSubscription.add(subscription);
    }

    /**
     * unsubscribe all {@link Subscription}.
     */
    protected void unSubscribeAll() {
        if (mSubscription != null && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }

    @Override
    public void attachView(final V view) {
        mViewRef = new WeakReference<>(view);
    }

    @Override
    public void detachView(final boolean retainInstance) {
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef=null;
        }

        if (!retainInstance) {
            unSubscribeAll();
        }
    }

    /**
     * Get the attached view.
     *
     * @return the concrete view instance
     * @throws NullPointerException, if view is not attached
     */
    @NonNull
    public V getView() {
        if (mViewRef == null || mViewRef.get() == null) {
            throw new IllegalStateException(
                    "MvpView reference is null. Have you called attachView()?");
        }
        return mViewRef.get();
    }

    public boolean isViewAttached() {
        return mViewRef != null && mViewRef.get() != null;
    }
}
