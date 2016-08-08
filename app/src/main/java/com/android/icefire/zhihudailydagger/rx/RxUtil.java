package com.android.icefire.zhihudailydagger.rx;

import java.util.concurrent.Callable;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by yangchj on 2016/7/6 0006.
 * email:yangchj@neusoft.com
 */
public class RxUtil {
    /**
     * 转化成Observable
     * @param func
     * @param <T>
     * @return
     */
    public static <T> Observable<T> makeObservable(final Callable<T> func){
        return Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                try{
                    subscriber.onNext(func.call());
                }catch (Exception e){
                    subscriber.onError(e);
                }
                subscriber.onCompleted();
            }
        });
    }

    /**
     * 带有Throwable的转化
     * @param func
     * @param throwable
     * @param <T>
     * @return
     */
    public static <T> Observable<T> makeObservableWithError(final Callable<T> func, final Throwable throwable){
        return Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                try{
                    subscriber.onNext(func.call());
                    subscriber.onError(throwable);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
}
