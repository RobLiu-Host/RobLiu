package com.dbmovie.fun.movie.viewmodel;

import com.dbmovie.base.base.BaseViewModel;
import com.dbmovie.fun.movie.bean.CelebrityBean;
import com.dbmovie.fun.movie.bean.UsBoxBean;
import com.dbmovie.fun.movie.manger.MovieManger;

import io.reactivex.Observable;


/**
 * Created by liuba on 2017/8/24.
 * describe :正在上映
 */
public class UsBoxViewModel extends BaseViewModel<CelebrityBean>{
    MovieManger serviceManager;
    public UsBoxViewModel() {
        serviceManager=new MovieManger();
    }


    /**
     * 通过Manger获取电影信息（top250榜单的）
     */
    public Observable<UsBoxBean> getUsBoxMovie() {
        return serviceManager.getUsBoxMovie();
    }
}
