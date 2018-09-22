package com.dbmovie.fun.movie.api;

import com.dbmovie.fun.movie.bean.CelebrityBean;
import com.dbmovie.fun.movie.bean.CommonBean;
import com.dbmovie.fun.movie.bean.MainInfo;
import com.dbmovie.fun.movie.bean.MovieDetailBean;
import com.dbmovie.fun.movie.bean.Top250Bean;
import com.dbmovie.fun.movie.bean.UsBoxBean;

import io.reactivex.Observable;



/**
 * Created by liuba on 2017/8/23.
 * describe : 定义接口函数和返回值
 *  这个接口提供给后台这边访问
 */

public interface UrlInterface {
    /**
     * 获取Top250
     * @return
     */
   Observable<Top250Bean> getTop250Movie(int start, int count);

    /**
     * 获取正在上映
     *
     * @param city
     * @return
     */
    Observable<MainInfo> gettheatersMovie(String city);


    /**
     * 获取即将上映的电影
     *
     * @param start
     * @param count
     * @return
     */
    Observable<CommonBean> getCommingSoonMovie(int start, int count);

    /**
     * 获取文章详情
     *
     * @param id id
     * @return
     */
    Observable<MovieDetailBean> getMovieDetail(int id);

    /**
     * 获取演员详情
     *
     * @param id
     * @return
     */
    Observable<CelebrityBean> getCelebrityDetail(int id);


    /**
     * 北美排行榜
     *
     * @return
     */
    Observable<UsBoxBean> getUsBoxMovie();

}
