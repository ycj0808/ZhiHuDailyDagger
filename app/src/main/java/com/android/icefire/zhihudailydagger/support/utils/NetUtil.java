package com.android.icefire.zhihudailydagger.support.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.android.icefire.zhihudailydagger.app.ZhihuApp;

/**
 * Created by yangchj on 2016/7/7 0007.
 * email:yangchj@neusoft.com
 */
public class NetUtil {

    public enum NetWorkType {
        none, mobile, wifi
    }

    /**
     * 获取连接的网络类型
     * @return
     */
    public static NetWorkType getNetworkType() {
        ConnectivityManager connMgr = (ConnectivityManager) ZhihuApp.get().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null) {
            switch (networkInfo.getType()) {
                case ConnectivityManager.TYPE_MOBILE:
                    return NetWorkType.mobile;
                case ConnectivityManager.TYPE_WIFI:
                    return NetWorkType.wifi;
            }
        }
        return NetWorkType.none;
    }

    /**
     * 是否连接网络
     * @return
     */
    public static boolean isNetworkConnected(){
        ConnectivityManager mConnectivityManager= (ConnectivityManager)ZhihuApp.get().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mNetworkInfo=mConnectivityManager.getActiveNetworkInfo();
        if(mNetworkInfo!=null){
            return mNetworkInfo.isAvailable();
        }
        return false;
    }

    /**
     * Wifi是否连接
     * @return
     */
    public static boolean isWifiConnected(){
        ConnectivityManager mConnectivityManager= (ConnectivityManager) ZhihuApp.get().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mWiFiNetworkInfo=mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if(mWiFiNetworkInfo!=null){
            return mWiFiNetworkInfo.isAvailable();
        }
        return false;
    }
}
