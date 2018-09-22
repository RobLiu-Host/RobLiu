package com.dbmovie.base.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.dbmovie.fun.movie.manger.MovieManger;

/**
 * fragment 基类
 * <p/>
 * Created on 16/6/27.
 */
public abstract class BaseFragment<T extends ViewDataBinding,M extends BaseViewModel> extends Fragment {

    protected  T mBinding;
    protected M mViewModel;

    MovieManger movieManger;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataBind();
        //初始化实体
        inject();
    }

    //传入布局
    protected abstract int getLayoutRes();

    public abstract void inject();

    public void initDataBind(){
        int layoutId = getLayoutRes();
        mBinding = DataBindingUtil.setContentView(getActivity(),layoutId);
    }

    @Override
    public void onDestroy() {
        if (mBinding != null)
            mBinding.unbind();
        super.onDestroy();
    }
}
