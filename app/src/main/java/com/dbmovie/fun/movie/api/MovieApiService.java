package com.dbmovie.fun.movie.api;

import com.dbmovie.Constants;
import com.dbmovie.fun.movie.bean.CelebrityBean;
import com.dbmovie.fun.movie.bean.CommonBean;
import com.dbmovie.fun.movie.bean.MainInfo;
import com.dbmovie.fun.movie.bean.MovieDetailBean;
import com.dbmovie.fun.movie.bean.Top250Bean;
import com.dbmovie.fun.movie.bean.UsBoxBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * 请求接口
 * <p/>
 * 这个是请求网络的接口
 */
public interface MovieApiService {

    /**
     * 获取正在上映
     * @param city
     * @return
     */
    @GET(Constants.API.MOVIE_INFO+"in_theaters")
    Observable<MainInfo> getTheatersMovie(@Query("city") String city);

    //http://api.douban.com/v2/movie/coming_soon?start=0&count=5
    @GET(Constants.API.MOVIE_INFO+"coming_soon")
    Observable<CommonBean> getCommongSoonMovie(@Query("start") int start,
                                               @Query("count") int count
    );

    //http://api.douban.com/v2/movie/subject/1764796
    @GET(Constants.API.MOVIE_INFO+"subject/{id}")
    Observable<MovieDetailBean> getMovieDetail(@Path("id") int id);

    // http://api.douban.com/v2/movie/celebrity/105439
    @GET(Constants.API.MOVIE_INFO+"celebrity/{id}")
    Observable<CelebrityBean> getCelebrityDetail(@Path("id") int id);


    //http://api.douban.com/v2/movie/top250
    @GET(Constants.API.MOVIE_INFO+"top250")
    Observable<Top250Bean> getTop250Movie(@Query("start") int start,
                                          @Query("count") int count
    );

    //http://api.douban.com/v2/movie/us_box
    @GET(Constants.API.MOVIE_INFO+"us_box")
    Observable<UsBoxBean> getUsBoxMovie();

}  