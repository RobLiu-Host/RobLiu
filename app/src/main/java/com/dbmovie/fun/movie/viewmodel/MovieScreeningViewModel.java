package com.dbmovie.fun.movie.viewmodel;

import com.dbmovie.base.base.BaseViewModel;
import com.dbmovie.fun.movie.bean.MainInfo;
import com.dbmovie.fun.movie.bean.MovieInfo;
import com.dbmovie.fun.movie.manger.MovieManger;

import java.util.List;

import io.reactivex.Observable;


/**
 * Created by liuba on 2017/8/24.
 * describe :正在上映
 */
public class MovieScreeningViewModel extends BaseViewModel<MainInfo>{
    MovieManger serviceManager;
    public MovieScreeningViewModel() {
        serviceManager=new MovieManger();
    }


    /**
     * 通过Manger获取电影信息（top250榜单的）
     */
    public Observable<MainInfo> getScreenInfo(String city) {
        return serviceManager.gettheatersMovie(city);
    }
}
