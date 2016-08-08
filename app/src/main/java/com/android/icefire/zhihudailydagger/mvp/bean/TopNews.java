package com.android.icefire.zhihudailydagger.mvp.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by yangchj on 2016/7/11 0011.
 * email:yangchj@neusoft.com
 */
public class TopNews implements Parcelable {

    private String image;
    private int type;
    private int id;
    private String ga_prefix;
    private String title;

    public TopNews() {
    }

    protected TopNews(Parcel in) {
        image = in.readString();
        type = in.readInt();
        id = in.readInt();
        ga_prefix = in.readString();
        title = in.readString();
    }

    public static final Creator<TopNews> CREATOR = new Creator<TopNews>() {
        @Override
        public TopNews createFromParcel(Parcel in) {
            return new TopNews(in);
        }

        @Override
        public TopNews[] newArray(int size) {
            return new TopNews[size];
        }
    };

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(image);
        parcel.writeInt(type);
        parcel.writeInt(id);
        parcel.writeString(ga_prefix);
        parcel.writeString(title);
    }
}
