package com.dbmovie.base.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by liuba on 2017/8/23.
 * describe :
 */

public abstract class BaseActivity<T extends ViewDataBinding,M extends BaseViewModel> extends AppCompatActivity{
    protected  T mBinding;
    protected M mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataBind();
        //初始化实体
        inject();
    }
    public void initDataBind(){
        int layoutId = getLayoutRes();
        mBinding =DataBindingUtil.setContentView(this,layoutId);
    }

    //传入布局
    protected abstract int getLayoutRes();

    public abstract void inject();

    @Override
    protected void onDestroy() {
        if (mBinding != null)
            mBinding.unbind();
        super.onDestroy();
    }
}
