package com.android.icefire.zhihudailydagger.mvp.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by yangchj on 2016/7/11 0011.
 * email:yangchj@neusoft.com
 */
public class NewsDetail implements Parcelable{

    private String body;
    private String image_source;
    private String title;
    private String image;
    private String share_url;
    private String ga_prefix;
    private int type;
    private int id;
    private List<String> js;
    private List<String> css;

    public NewsDetail() {
    }

    protected NewsDetail(Parcel in) {
        body = in.readString();
        image_source = in.readString();
        title = in.readString();
        image = in.readString();
        share_url = in.readString();
        ga_prefix = in.readString();
        type = in.readInt();
        id = in.readInt();
        js = in.createStringArrayList();
        css = in.createStringArrayList();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(body);
        dest.writeString(image_source);
        dest.writeString(title);
        dest.writeString(image);
        dest.writeString(share_url);
        dest.writeString(ga_prefix);
        dest.writeInt(type);
        dest.writeInt(id);
        dest.writeStringList(js);
        dest.writeStringList(css);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<NewsDetail> CREATOR = new Creator<NewsDetail>() {
        @Override
        public NewsDetail createFromParcel(Parcel in) {
            return new NewsDetail(in);
        }

        @Override
        public NewsDetail[] newArray(int size) {
            return new NewsDetail[size];
        }
    };

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getImage_source() {
        return image_source;
    }

    public void setImage_source(String image_source) {
        this.image_source = image_source;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public String getGa_prefix() {
        return ga_prefix;
    }

    public void setGa_prefix(String ga_prefix) {
        this.ga_prefix = ga_prefix;
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

    public List<String> getJs() {
        return js;
    }

    public void setJs(List<String> js) {
        this.js = js;
    }

    public List<String> getCss() {
        return css;
    }

    public void setCss(List<String> css) {
        this.css = css;
    }
}
