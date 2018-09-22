package com.dbmovie.fun.movie.viewmodel;

import com.dbmovie.base.base.BaseViewModel;
import com.dbmovie.fun.movie.bean.CelebrityBean;
import com.dbmovie.fun.movie.bean.Top250Bean;
import com.dbmovie.fun.movie.manger.MovieManger;

import io.reactivex.Observable;


/**
 * Created by liuba on 2017/8/24.
 * describe :正在上映
 */
public class Top250ViewModel extends BaseViewModel<CelebrityBean>{
    MovieManger serviceManager;
    public Top250ViewModel() {
        serviceManager=new MovieManger();
    }


    /**
     * 通过Manger获取电影信息（top250榜单的）
     */
    public Observable<Top250Bean> getTop250Movie(int start, int count) {
        return serviceManager.getTop250Movie(start,count);
    }
}
