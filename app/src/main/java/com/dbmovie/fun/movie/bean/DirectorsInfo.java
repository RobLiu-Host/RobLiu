package com.dbmovie.fun.movie.bean;

import android.os.Parcel;
import android.os.Parcelable;

//导演的信息
public  class DirectorsInfo implements Parcelable{
            /**
             * alt : https://movie.douban.com/celebrity/1047973/
             * avatars : {"small":"https://img3.doubanio.com/img/celebrity/small/230.jpg","large":"https://img3.doubanio.com/img/celebrity/large/230.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/230.jpg"}
             * name : 弗兰克·德拉邦特
             * id : 1047973
             */

            public String alt;
            public ImagePathInfo avatars;
            public String name;
            public String id;

    protected DirectorsInfo(Parcel in) {
        alt = in.readString();
        name = in.readString();
        id = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(alt);
        dest.writeString(name);
        dest.writeString(id);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DirectorsInfo> CREATOR = new Creator<DirectorsInfo>() {
        @Override
        public DirectorsInfo createFromParcel(Parcel in) {
            return new DirectorsInfo(in);
        }

        @Override
        public DirectorsInfo[] newArray(int size) {
            return new DirectorsInfo[size];
        }
    };

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public ImagePathInfo getAvatars() {
        return avatars;
    }

    public void setAvatars(ImagePathInfo avatars) {
        this.avatars = avatars;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}