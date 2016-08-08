package com.android.icefire.zhihudailydagger.mvp.view;

import com.android.icefire.zhihudailydagger.mvp.base.view.BaseMvpView;
import com.android.icefire.zhihudailydagger.mvp.bean.News;
import java.util.List;

/**
 * Created by yangchj on 2016/8/8 0008.
 * email:yangchj@neusoft.com
 */
public interface LastNewsView extends BaseMvpView<List<News>>{

    public void setCurrentDate(String curDate,boolean loadMore);

    public void endRefresh(boolean flag);

    public void addMoreData(List<News> newses);

    public void endLoadMore();
}
