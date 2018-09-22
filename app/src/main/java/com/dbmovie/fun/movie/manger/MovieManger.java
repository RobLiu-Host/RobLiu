package com.dbmovie.fun.movie.manger;


import com.dbmovie.app.App;
import com.dbmovie.fun.movie.api.MovieApiService;
import com.dbmovie.fun.movie.api.UrlInterface;
import com.dbmovie.fun.movie.bean.CelebrityBean;
import com.dbmovie.fun.movie.bean.CommonBean;
import com.dbmovie.fun.movie.bean.MainInfo;
import com.dbmovie.fun.movie.bean.MovieDetailBean;
import com.dbmovie.fun.movie.bean.Top250Bean;
import com.dbmovie.fun.movie.bean.UsBoxBean;

import io.reactivex.Observable;


/**
 * Created by liuba on 2017/8/23.
 * describe :
 */

public class MovieManger implements UrlInterface{


    MovieApiService apiService;


    public MovieManger() {
        init();
    }

    private void init() {
        apiService =RetrofitFactory.createService(App.app);
    }

    @Override
    public Observable<Top250Bean> getTop250Movie(int start, int count) {
        return apiService.getTop250Movie(start,count);
    }

    @Override
    public Observable<MainInfo> gettheatersMovie(String city) {
        return apiService.getTheatersMovie(city);
    }

    @Override
    public Observable<CommonBean> getCommingSoonMovie(int start, int count) {
        return apiService.getCommongSoonMovie(start,count);
    }

    @Override
    public Observable<MovieDetailBean> getMovieDetail(int id) {
        return apiService.getMovieDetail(id);
    }

    @Override
    public Observable<CelebrityBean> getCelebrityDetail(int id) {
        return apiService.getCelebrityDetail(id);
    }

    @Override
    public Observable<UsBoxBean> getUsBoxMovie() {
        return apiService.getUsBoxMovie();
    }
}
