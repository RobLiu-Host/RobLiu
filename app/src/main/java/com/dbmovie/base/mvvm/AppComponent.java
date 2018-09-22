package com.dbmovie.base.mvvm;

import android.content.Context;
import android.content.SharedPreferences;



import com.dbmovie.fun.movie.manger.MovieManger;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by fcn-mq on 2017/5/31.
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    SharedPreferences sharedPreferences();

    Context context();

    MovieManger serviceManager();

}

