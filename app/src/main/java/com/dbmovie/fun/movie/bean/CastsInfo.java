package com.dbmovie.fun.movie.bean;

import android.os.Parcel;
import android.os.Parcelable;

//主演的信息
public  class CastsInfo implements Parcelable{
            /**
             * alt : https://movie.douban.com/celebrity/1054521/
             * avatars : {"small":"https://img3.doubanio.com/img/celebrity/small/17525.jpg","large":"https://img3.doubanio.com/img/celebrity/large/17525.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/17525.jpg"}
             * name : 蒂姆·罗宾斯
             * id : 1054521
             */

            public String alt;
            //主演的头像路径
            public ImagePathInfo avatars;
            //主演的名字
            public String name;

            public String id;

    protected CastsInfo(Parcel in) {
        alt = in.readString();
        name = in.readString();
        id = in.readString();
    }

    public static final Creator<CastsInfo> CREATOR = new Creator<CastsInfo>() {
        @Override
        public CastsInfo createFromParcel(Parcel in) {
            return new CastsInfo(in);
        }

        @Override
        public CastsInfo[] newArray(int size) {
            return new CastsInfo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(alt);
        parcel.writeString(name);
        parcel.writeString(id);
    }

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