package com.android.icefire.zhihudailydagger.mvp.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by yangchj on 2016/7/11 0011.
 * email:yangchj@neusoft.com
 */
public class NewsList implements Parcelable{

    private String date;
    private List<News> stories;
    private List<TopNews> top_stories;

    public NewsList() {
    }

    protected NewsList(Parcel in) {
        date = in.readString();
        stories = in.createTypedArrayList(News.CREATOR);
        top_stories = in.createTypedArrayList(TopNews.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(date);
        dest.writeTypedList(stories);
        dest.writeTypedList(top_stories);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<NewsList> CREATOR = new Creator<NewsList>() {
        @Override
        public NewsList createFromParcel(Parcel in) {
            return new NewsList(in);
        }

        @Override
        public NewsList[] newArray(int size) {
            return new NewsList[size];
        }
    };

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<News> getStories() {
        return stories;
    }

    public void setStories(List<News> stories) {
        this.stories = stories;
    }

    public List<TopNews> getTop_stories() {
        return top_stories;
    }

    public void setTop_stories(List<TopNews> top_stories) {
        this.top_stories = top_stories;
    }
}
