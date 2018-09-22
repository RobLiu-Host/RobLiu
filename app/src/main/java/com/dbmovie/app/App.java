package com.dbmovie.app;

import android.app.Application;

import com.dbmovie.Constants;
import com.dbmovie.base.mvvm.AppComponent;
import com.dbmovie.base.mvvm.AppModule;
import com.dbmovie.base.mvvm.ComponentHolder;
import com.dbmovie.base.mvvm.DaggerAppComponent;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by liuba on 2017/8/23.
 * describe :
 */

public class App extends Application{

    public static App app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        inject();
    }

    private void inject() {
        AppComponent component =DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
        ComponentHolder.setComponent(component);
    }

    public Retrofit getNet(){
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        //用于做网络请求的拦截
//        if (Constants.DEBUG) {
//            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//            builder.addInterceptor(interceptor);
//        }
        OkHttpClient client = builder.build();

        return  new Retrofit.Builder()
                .baseUrl(Constants.API.BASE_DOUBAN)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();
    }
}
