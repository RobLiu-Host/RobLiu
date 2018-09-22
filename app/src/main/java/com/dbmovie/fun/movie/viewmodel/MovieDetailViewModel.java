package com.dbmovie.fun.movie.viewmodel;

import com.dbmovie.base.base.BaseViewModel;
import com.dbmovie.fun.movie.bean.MainInfo;
import com.dbmovie.fun.movie.bean.MovieDetailBean;
import com.dbmovie.fun.movie.manger.MovieManger;

import io.reactivex.Observable;


/**
 * Created by liuba on 2017/8/24.
 * describe :正在上映
 */
public class MovieDetailViewModel extends BaseViewModel<MainInfo>{
    MovieManger serviceManager;
    public MovieDetailViewModel() {
        serviceManager=new MovieManger();
    }


    /**
     * 通过Manger获取电影信息（top250榜单的）
     */
    public Observable<MovieDetailBean> getMovieDetail(int id) {
        return serviceManager.getMovieDetail(id);
    }
}
