package com.dbmovie.fun.movie.bean;

import android.os.Parcel;
import android.os.Parcelable;

public  class RatingInfo implements Parcelable{
            /**
             * max : 10
             * average : 9.6
             * stars : 50
             * min : 0
             */
            //最大分
            public float max;
            //平均分
            public float average;
            //星数
            public String stars;
            //最低分
            public float min;


    protected RatingInfo(Parcel in) {
        max = in.readFloat();
        average = in.readFloat();
        stars = in.readString();
        min = in.readFloat();
    }

    public static final Creator<RatingInfo> CREATOR = new Creator<RatingInfo>() {
        @Override
        public RatingInfo createFromParcel(Parcel in) {
            return new RatingInfo(in);
        }

        @Override
        public RatingInfo[] newArray(int size) {
            return new RatingInfo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeFloat(max);
        parcel.writeFloat(average);
        parcel.writeString(stars);
        parcel.writeFloat(min);
    }


}
   