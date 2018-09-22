package com.dbmovie.fun.movie.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;

import com.dbmovie.R;
import com.dbmovie.fun.movie.Adapter.FragmentAdapter;
import com.dbmovie.view.CustomViewPager;

public class DouBanMainActivity extends AppCompatActivity {

    private TabLayout tableLayout;

    private CustomViewPager viewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_douban_movie);
        intView();
    }

    private void intView() {
        tableLayout= (TabLayout) findViewById(R.id.tl_channel_tabs);
        viewPager= (CustomViewPager) findViewById(R.id.vp_channel_pager);

        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(),this);
        viewPager.setAdapter(fragmentAdapter);
        tableLayout.setupWithViewPager(viewPager);
    }
}
