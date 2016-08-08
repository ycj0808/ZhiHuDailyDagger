package com.android.icefire.zhihudailydagger.mvp.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by yangchj on 2016/7/11 0011.
 * email:yangchj@neusoft.com
 */
public class NewsExtra implements Parcelable{

    private int long_comments;
    private int popularity;
    private int short_comments;
    private int comments;

    public NewsExtra() {
    }

    protected NewsExtra(Parcel in) {
        long_comments = in.readInt();
        popularity = in.readInt();
        short_comments = in.readInt();
        comments = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(long_comments);
        dest.writeInt(popularity);
        dest.writeInt(short_comments);
        dest.writeInt(comments);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<NewsExtra> CREATOR = new Creator<NewsExtra>() {
        @Override
        public NewsExtra createFromParcel(Parcel in) {
            return new NewsExtra(in);
        }

        @Override
        public NewsExtra[] newArray(int size) {
            return new NewsExtra[size];
        }
    };

    public int getLong_comments() {
        return long_comments;
    }

    public void setLong_comments(int long_comments) {
        this.long_comments = long_comments;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public int getShort_comments() {
        return short_comments;
    }

    public void setShort_comments(int short_comments) {
        this.short_comments = short_comments;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }
}
