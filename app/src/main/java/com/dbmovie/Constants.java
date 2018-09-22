package com.dbmovie;

/**
 * Created by liuba on 2017/8/23.
 * describe :
 */

public class Constants {

    boolean DEBUG = BuildConfig.DEBUG;

    public interface SP{

        String DEFAULT_SP_NAME = "preferences";

    }

     public interface API {

        String BASE_DOUBAN = "https://api.douban.com";
        // GET 电影信息
        String MOVIE_INFO = "/v2/movie/";

    }
}
