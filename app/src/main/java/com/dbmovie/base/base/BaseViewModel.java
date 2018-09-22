package com.dbmovie.base.base;


import android.databinding.ObservableField;


/**
 * Created by liuba on 2017/8/24.
 * describe :
 */
public abstract class BaseViewModel<T>{
    public final ObservableField<T> info = new ObservableField<>();

}
