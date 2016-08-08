package com.android.icefire.zhihudailydagger.mvp.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by yangchj on 2016/7/11 0011.
 * email:yangchj@neusoft.com
 */
public class News implements Parcelable{

    private int type;
    private int id;
    private String ga_prefix;
    private String title;
    private List<String> images;
    private boolean isRead=false;
    private String date;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGa_prefix() {
        return ga_prefix;
    }

    public void setGa_prefix(String ga_prefix) {
        this.ga_prefix = ga_prefix;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public News(){}

    protected News(Parcel in) {
        this.type=in.readInt();
        this.id=in.readInt();
        this.ga_prefix=in.readString();
        this.title=in.readString();
        this.images=in.createStringArrayList();
        this.isRead=in.readByte()!=0;
    }

    public static final Creator<News> CREATOR = new Creator<News>() {
        @Override
        public News createFromParcel(Parcel in) {
            return new News(in);
        }

        @Override
        public News[] newArray(int size) {
            return new News[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.type);
        parcel.writeInt(this.id);
        parcel.writeString(this.ga_prefix);
        parcel.writeString(this.title);
        parcel.writeStringList(this.images);
        parcel.writeByte(isRead?(byte) 1:(byte) 0);
    }
}
