package com.dbmovie.fun.login.bean;

import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.os.Parcel;
import android.os.Parcelable;

import static android.R.attr.name;
import static android.R.attr.password;

/**
 * Created by liuba on 2017/8/22.
 * describe 用户信息
 */

public class UserInfo extends BaseObservable implements Parcelable{

    /**
     * ObservableFields 实现数据绑定（推荐）
     */
    public String  name;

    public String   password;

    public UserInfo(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public UserInfo() {
    }

    protected UserInfo(Parcel in) {
        name = in.readString();
        password = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(password);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<UserInfo> CREATOR = new Creator<UserInfo>() {
        @Override
        public UserInfo createFromParcel(Parcel in) {
            return new UserInfo(in);
        }

        @Override
        public UserInfo[] newArray(int size) {
            return new UserInfo[size];
        }
    };
}
