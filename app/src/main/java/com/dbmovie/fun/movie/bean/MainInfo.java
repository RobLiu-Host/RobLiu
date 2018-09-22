package com.dbmovie.fun.movie.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by liuba on 2017/8/23.
 * describe :电影所有的信息
 */

public class MainInfo implements Parcelable{

    public int count;
    //从那一条开始
    public int start;
    public int total;
    //数目
    public String title;

    //所有的电影信息
    public List<MovieInfo> subjects;

    protected MainInfo(Parcel in) {
        count = in.readInt();
        start = in.readInt();
        total = in.readInt();
        title = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(count);
        dest.writeInt(start);
        dest.writeInt(total);
        dest.writeString(title);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MainInfo> CREATOR = new Creator<MainInfo>() {
        @Override
        public MainInfo createFromParcel(Parcel in) {
            return new MainInfo(in);
        }

        @Override
        public MainInfo[] newArray(int size) {
            return new MainInfo[size];
        }
    };

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<MovieInfo> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<MovieInfo> subjects) {
        this.subjects = subjects;
    }
}
