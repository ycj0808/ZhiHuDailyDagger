package com.android.icefire.zhihudailydagger.model.errors;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.functions.Action1;

/**
 * Created by yangchj on 2016/8/8 0008.
 * email:yangchj@neusoft.com
 */
@Singleton
public class RxNetErrorProcessor implements Action1<Throwable> {

    private final Gson mGson;

    @Inject
    RxNetErrorProcessor(final Gson gson){
        mGson=gson;
    }

    @Override
    public void call(final Throwable throwable) {
        Logger.e(throwable,"RxNetErrorProcessor");
    }

//    public boolean tryWithApiError(final Throwable throwable,final Action1<>){
//
//    }
}
