package com.dbmovie.fun.movie.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by liuba on 2017/8/23.
 * describe :
 */

public class MovieInfo implements Parcelable{
        /**
         * rating : {"max":10,"average":9.6,"stars":"50","min":0}
         * genres : ["犯罪","剧情"]
         * title : 肖申克的救赎
         * casts : [{"alt":"https://movie.douban.com/celebrity/1054521/","avatars":{"small":"https://img3.doubanio.com/img/celebrity/small/17525.jpg","large":"https://img3.doubanio.com/img/celebrity/large/17525.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/17525.jpg"},"name":"蒂姆·罗宾斯","id":"1054521"},{"alt":"https://movie.douban.com/celebrity/1054534/","avatars":{"small":"https://img3.doubanio.com/img/celebrity/small/34642.jpg","large":"https://img3.doubanio.com/img/celebrity/large/34642.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/34642.jpg"},"name":"摩根·弗里曼","id":"1054534"},{"alt":"https://movie.douban.com/celebrity/1041179/","avatars":{"small":"https://img1.doubanio.com/img/celebrity/small/5837.jpg","large":"https://img1.doubanio.com/img/celebrity/large/5837.jpg","medium":"https://img1.doubanio.com/img/celebrity/medium/5837.jpg"},"name":"鲍勃·冈顿","id":"1041179"}]
         * collect_count : 1108395
         * original_title : The Shawshank Redemption
         * subtype : movie
         * directors : [{"alt":"https://movie.douban.com/celebrity/1047973/","avatars":{"small":"https://img3.doubanio.com/img/celebrity/small/230.jpg","large":"https://img3.doubanio.com/img/celebrity/large/230.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/230.jpg"},"name":"弗兰克·德拉邦特","id":"1047973"}]
         * year : 1994
         * images : {"small":"https://img3.doubanio.com/view/movie_poster_cover/ipst/public/p480747492.webp","large":"https://img3.doubanio.com/view/movie_poster_cover/lpst/public/p480747492.webp","medium":"https://img3.doubanio.com/view/movie_poster_cover/spst/public/p480747492.webp"}
         * alt : https://movie.douban.com/subject/1292052/
         * id : 1292052
         */
        //星星 以及评分对象
        public RatingInfo rating;

        //标题
        public String title;

        //多少人看过
        public int collect_count;
        //英文名
        public String original_title;


        public String subtype;

        //电影年份
        public String year;

        //电影的路径
        public ImagePathInfo images;

        //浏览器链接路径
        public String alt;
        //电影ID
        public String id;

        //类型
        public List<String> genres;

        //主演信息
        public List<CastsInfo> casts;

        //导演信息集合
        public List<DirectorsInfo> directors;


        protected MovieInfo(Parcel in) {
                title = in.readString();
                collect_count = in.readInt();
                original_title = in.readString();
                subtype = in.readString();
                year = in.readString();
                alt = in.readString();
                id = in.readString();
                genres = in.createStringArrayList();
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(title);
                dest.writeInt(collect_count);
                dest.writeString(original_title);
                dest.writeString(subtype);
                dest.writeString(year);
                dest.writeString(alt);
                dest.writeString(id);
                dest.writeStringList(genres);
        }

        @Override
        public int describeContents() {
                return 0;
        }

        public static final Creator<MovieInfo> CREATOR = new Creator<MovieInfo>() {
                @Override
                public MovieInfo createFromParcel(Parcel in) {
                        return new MovieInfo(in);
                }

                @Override
                public MovieInfo[] newArray(int size) {
                        return new MovieInfo[size];
                }
        };

        public RatingInfo getRating() {
                return rating;
        }

        public void setRating(RatingInfo rating) {
                this.rating = rating;
        }

        public String getTitle() {
                return title;
        }

        public void setTitle(String title) {
                this.title = title;
        }

        public int getCollect_count() {
                return collect_count;
        }

        public void setCollect_count(int collect_count) {
                this.collect_count = collect_count;
        }

        public String getOriginal_title() {
                return original_title;
        }

        public void setOriginal_title(String original_title) {
                this.original_title = original_title;
        }

        public String getSubtype() {
                return subtype;
        }

        public void setSubtype(String subtype) {
                this.subtype = subtype;
        }

        public String getYear() {
                return year;
        }

        public void setYear(String year) {
                this.year = year;
        }

        public ImagePathInfo getImages() {
                return images;
        }

        public void setImages(ImagePathInfo images) {
                this.images = images;
        }

        public String getAlt() {
                return alt;
        }

        public void setAlt(String alt) {
                this.alt = alt;
        }

        public String getId() {
                return id;
        }

        public void setId(String id) {
                this.id = id;
        }

        public List<String> getGenres() {
                return genres;
        }

        public void setGenres(List<String> genres) {
                this.genres = genres;
        }

        public List<CastsInfo> getCasts() {
                return casts;
        }

        public void setCasts(List<CastsInfo> casts) {
                this.casts = casts;
        }

        public List<DirectorsInfo> getDirectors() {
                return directors;
        }

        public void setDirectors(List<DirectorsInfo> directors) {
                this.directors = directors;
        }
}
