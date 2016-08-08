package com.android.icefire.zhihudailydagger.rx;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * Created by yangchj on 2016/7/6 0006.
 * email:yangchj@neusoft.com
 */
public class RxBus<T,R> {
    private final Subject<T,R> rxBus;

    private RxBus(){
        rxBus=new SerializedSubject(PublishSubject.<T>create());
    }

    private static class SingleonHolder{
        private static RxBus instance=new RxBus();
    }

    public static RxBus getInstance(){
        return SingleonHolder.instance;
    }

    public void send(T msg){
        rxBus.onNext(msg);
    }

    /**
     * 转化成Observable
     * @return
     */
    public Observable<R> toObservable(){
        return rxBus.asObservable().onBackpressureBuffer();
    }

    /**
     * 检查是否已经订阅
     * @return
     */
    public boolean hasObservers(){
        return rxBus.hasObservers();
    }
}
