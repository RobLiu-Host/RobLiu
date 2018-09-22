package com.dbmovie.test;


import android.os.Handler;

public class SingleTon {
    private SingleTon() {}

    private static  SingleTon single;

    //静态工厂方法
    public static SingleTon getInstance() {
        if(single==null){
            synchronized (SingleTon.class){
                single=new SingleTon();
            }
        }
        return single;
    }
   private static Handler handler;


    private void test(){

         handler=new Handler(message -> false);
        handler.post(() -> {

        });
    }


}
