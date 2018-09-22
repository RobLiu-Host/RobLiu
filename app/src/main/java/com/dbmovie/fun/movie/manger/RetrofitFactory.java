package com.dbmovie.fun.movie.manger;

import android.content.Context;
import android.util.Log;

import com.dbmovie.Constants;
import com.dbmovie.fun.movie.api.MovieApiService;
import com.dbmovie.util.AppUtil;
import com.dbmovie.util.SpConfig;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *    //region @description Retrofit 初始化配置相关
 */
public class RetrofitFactory {

        private static String TAG = "factory";

        public static MovieApiService createService(final Context context) {
            //日志拦截器
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            /**
             * 获取缓存
             */
            Interceptor baseInterceptor = chain -> {
                Request request = chain.request();
                if (!AppUtil.isNetWorkAvailable(context)) {
                    /**
                     * 离线缓存控制  总的缓存时间=在线缓存时间+设置离线缓存时间
                     */
                    int maxStale = 60 * 60 * 24 * 28; // 离线时缓存保存4周,单位:秒
                    CacheControl tempCacheControl = new CacheControl.Builder()
                            .onlyIfCached()
                               .maxStale(maxStale, TimeUnit.SECONDS)
                            .build();
                    request = request.newBuilder()
                            .cacheControl(tempCacheControl)
                            .build();
                    Log.i(TAG,"intercept:no network ");
                }
                return chain.proceed(request);
            };
            //只有 网络拦截器环节 才会写入缓存写入缓存,在有网络的时候 设置缓存时间
            Interceptor rewriteCacheControlInterceptor = (Interceptor.Chain chain) -> {
                Request request = chain.request();
                Response originalResponse = chain.proceed(request);
                int maxAge = 1 * 60; // 在线缓存在1分钟内可读取 单位:秒
                return originalResponse.newBuilder()
                        .removeHeader("Pragma")// 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                        .removeHeader("Cache-Control")
                        .header("Cache-Control","public, max-age=" + maxAge)
                        .build();
            };
            //设置缓存路径 内置存储
            //File httpCacheDirectory = new File(context.getCacheDir(), "responses");
            //外部存储
            File httpCacheDirectory = new File(context.getExternalCacheDir(), "responses");
            //设置缓存 10M
            int cacheSize = 10 * 1024 * 1024;
            Cache cache = new Cache(httpCacheDirectory, cacheSize);

            OkHttpClient client;
            //如果默认为 缓存数据
            if (SpConfig.getIsCache(context)) {
                client = new OkHttpClient.Builder()
                        .cache(cache)
                        .addInterceptor(logging)
                        .addInterceptor(baseInterceptor)
                        .addNetworkInterceptor(rewriteCacheControlInterceptor)
                        .connectTimeout(15, TimeUnit.SECONDS)
                        .build();
            } else {
                client = new OkHttpClient.Builder()
                        .addInterceptor(logging)
                        .connectTimeout(15, TimeUnit.SECONDS)
                        .build();
            }
            return new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl(Constants.API.BASE_DOUBAN)
                    .client(client)
                    .build()
                    .create(MovieApiService.class);
        }
}
