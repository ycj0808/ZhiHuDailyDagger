package com.android.icefire.zhihudailydagger.mvp.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by yangchj on 2016/7/11 0011.
 * email:yangchj@neusoft.com
 */
public class NewsComment implements Parcelable{
    private String author;
    private int id;
    private String content;
    private int likes;
    private long time;
    private String avatar;

    public NewsComment() {
    }

    protected NewsComment(Parcel in) {
        author = in.readString();
        id = in.readInt();
        content = in.readString();
        likes = in.readInt();
        time = in.readLong();
        avatar = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(author);
        dest.writeInt(id);
        dest.writeString(content);
        dest.writeInt(likes);
        dest.writeLong(time);
        dest.writeString(avatar);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<NewsComment> CREATOR = new Creator<NewsComment>() {
        @Override
        public NewsComment createFromParcel(Parcel in) {
            return new NewsComment(in);
        }

        @Override
        public NewsComment[] newArray(int size) {
            return new NewsComment[size];
        }
    };

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
