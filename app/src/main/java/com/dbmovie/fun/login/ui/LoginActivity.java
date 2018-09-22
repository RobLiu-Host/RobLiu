package com.dbmovie.fun.login.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.dbmovie.R;

/**
 * Created by Administrator on 2017/8/22.
 * 登录界面
 */

public class LoginActivity extends AppCompatActivity{


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataBinding();
    }

    private void initDataBinding() {
        //现在我们通过DataBindingUtil设置布局文件
        setContentView(R.layout.login);

    }

}
