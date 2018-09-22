package com.dbmovie.fun.movie.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by liuba on 2017/8/23.
 * describe :
 */

public class ImagePathInfo implements Parcelable{
    public String small;
    public String large;
    public String medium;

    protected ImagePathInfo(Parcel in) {
        small = in.readString();
        large = in.readString();
        medium = in.readString();
    }

    public static final Creator<ImagePathInfo> CREATOR = new Creator<ImagePathInfo>() {
        @Override
        public ImagePathInfo createFromParcel(Parcel in) {
            return new ImagePathInfo(in);
        }

        @Override
        public ImagePathInfo[] newArray(int size) {
            return new ImagePathInfo[size];
        }
    };

    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small;
    }

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(small);
        parcel.writeString(large);
        parcel.writeString(medium);
    }
}
