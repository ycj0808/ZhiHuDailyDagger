package com.android.icefire.zhihudailydagger.ui;

import android.app.ActivityOptions;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.icefire.zhihudailydagger.R;
import com.android.icefire.zhihudailydagger.app.ZhihuApp;
import com.android.icefire.zhihudailydagger.model.news.NewsComponent;
import com.android.icefire.zhihudailydagger.mvp.base.activity.BaseMvpActivity;
import com.android.icefire.zhihudailydagger.mvp.bean.News;
import com.android.icefire.zhihudailydagger.mvp.presenter.LastNewsPresenter;
import com.android.icefire.zhihudailydagger.mvp.view.LastNewsView;
import com.android.icefire.zhihudailydagger.support.utils.Utils;
import com.android.icefire.zhihudailydagger.support.widget.RecycleViewDivider;
import com.android.icefire.zhihudailydagger.ui.adapter.LastNewsAdapter;
import com.orhanobut.logger.Logger;

import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bingoogolapple.androidcommon.adapter.BGAOnRVItemClickListener;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

public class MainActivity extends BaseMvpActivity<LastNewsView, LastNewsPresenter, NewsComponent> implements LastNewsView, BGARefreshLayout.BGARefreshLayoutDelegate, BGAOnRVItemClickListener {

    @BindView(R.id.rvNews)
    RecyclerView rvNews;
    @BindView(R.id.refreshLayout)
    BGARefreshLayout refreshLayout;

    LastNewsAdapter newsAdapter;
    String curDate;

    private NewsComponent newsComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCanBack(false);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        if(savedInstanceState!=null&&savedInstanceState.containsKey("curDate")){
            curDate=savedInstanceState.getString("curDate");
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(curDate!=null){
            outState.putString("curDate",curDate);
        }
    }

    @Override
    protected void initializeInjector() {
        newsComponent= ZhihuApp.get().appComponent().plus();
        newsComponent.inject(this);
    }

    protected void init() {
        rvNews.setLayoutManager(new LinearLayoutManager(mContext));
        RecycleViewDivider divider=new RecycleViewDivider(mContext,LinearLayoutManager.VERTICAL, Utils.dip2px(10),R.color.line_divider);
        rvNews.addItemDecoration(divider);
        newsAdapter=new LastNewsAdapter(rvNews);
        BGANormalRefreshViewHolder viewHolder=new BGANormalRefreshViewHolder(mContext,true);
        refreshLayout.setRefreshViewHolder(viewHolder);
        rvNews.setAdapter(newsAdapter);
        refreshLayout.setDelegate(this);
        newsAdapter.setOnRVItemClickListener(this);
    }
    @Override
    protected void requestData() {
        getComponent().presenter().loadLastNews(false);
    }

    @Override
    public void setCurrentDate(String curDate, boolean loadMore) {
        this.curDate=curDate;
        if(loadMore){
            loadMoreData();
        }
    }

    @Override
    public void endRefresh(boolean flag) {
        if(flag){
            refreshLayout.endRefreshing();
        }
    }

    @Override
    public void addMoreData(List<News> newses) {
        newsAdapter.addMoreDatas(newses);
    }

    @Override
    public void endLoadMore() {
        refreshLayout.endLoadingMore();
    }

    @Override
    public void showLoading(boolean pullToRefresh) {
        if(!pullToRefresh){
            showLoadingDialog("数据加载中,请稍候...");
        }
    }

    @Override
    public void hideLoading() {
        dismissDialog();
    }

    @Override
    public void showError(Throwable e) {
        dismissDialog();
    }

    @Override
    public void setData(List<News> data) {
        newsAdapter.setDatas(data);
        dismissDialog();
    }

    @Override
    public void showEmptyView(boolean flag) {

    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        loadData(true);
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        Logger.d("--------%s--------",curDate);
        loadMoreData();
        return true;
    }

    private void loadData(boolean flag){
        if(presenter!=null){
            presenter.loadLastNews(flag);
        }
    }

    private void loadMoreData(){
        if(presenter!=null){
            presenter.loadBeforeNews(curDate);
        }
    }

    @Override
    public void onRVItemClick(ViewGroup viewGroup, View view, int i) {
        News news=newsAdapter.getItem(i);
        Bundle bundle=new Bundle();
        bundle.putParcelable("news",news);
        if(Build.VERSION.SDK_INT>=21){
            ImageView shareView= (ImageView) view.findViewById(R.id.ivNews);
            if(shareView!=null){
                ActivityOptions options = ActivityOptions
                        .makeSceneTransitionAnimation(MainActivity.this, shareView, "robot");
                readGoWithOptions(NewsDetailActivity.class,bundle,options);
            }else{
                readyGo(NewsDetailActivity.class,bundle);
            }
        }else{
           readyGo(NewsDetailActivity.class,bundle);
        }
    }

    @Override
    public NewsComponent getComponent() {
        return newsComponent;
    }
}
