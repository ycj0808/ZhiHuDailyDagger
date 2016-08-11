package com.android.icefire.zhihudailydagger.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import com.android.icefire.zhihudailydagger.R;
import com.android.icefire.zhihudailydagger.mvp.bean.News;
import com.bumptech.glide.Glide;
import java.util.List;
import cn.bingoogolapple.androidcommon.adapter.BGARecyclerViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * 最新消息
 * Created by yangchj on 16/7/11.
 */
public class LastNewsAdapter extends BGARecyclerViewAdapter<News> {

    public LastNewsAdapter(RecyclerView recyclerView) {
        super(recyclerView, R.layout.item_last_news);
    }

    @Override
    protected void fillData(BGAViewHolderHelper bgaViewHolderHelper, int i, News news) {
        ImageView ivNews=bgaViewHolderHelper.getView(R.id.ivNews);
        List<String> imgs=news.getImages();
        if(imgs!=null&&imgs.size()>0){
            Glide.with(mContext).load(imgs.get(0)).into(ivNews);
        }
        bgaViewHolderHelper.setText(R.id.tvNews,news.getTitle());
    }

}
