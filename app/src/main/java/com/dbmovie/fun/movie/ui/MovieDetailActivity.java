package com.dbmovie.fun.movie.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dbmovie.R;
import com.dbmovie.base.base.BaseActivity;
import com.dbmovie.base.base.CustomObserver;
import com.dbmovie.fun.movie.Adapter.ArtAdapter;
import com.dbmovie.fun.movie.bean.DirectorsInfo;
import com.dbmovie.fun.movie.bean.MovieDetailBean;
import com.dbmovie.fun.movie.bean.RatingInfo;
import com.dbmovie.fun.movie.viewmodel.MovieDetailViewModel;
import com.dbmovie.util.GridMarginDecoration;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * Created on 16-6-6.
 * <p/>
 * 电影详情页面
 */
public class MovieDetailActivity extends BaseActivity {

    private static final String INTENT_KEY_MOVIE_ID = "id";
    private ImageView bgImageView;
    private CollapsingToolbarLayout collapsingToolbar;
    private RecyclerView rvList;
    private TextView tvSummary;

    private RatingBar rbMovieLevel;
    private TextView tvMovieLevelInfo;
    private TextView tvMovieInfo;


    MovieDetailViewModel detailViewModel;
    public static Intent getCallingIntent(Context context, String id) {
        Intent intent = new Intent(context, MovieDetailActivity.class);
        intent.putExtra(INTENT_KEY_MOVIE_ID, id);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        initActionBar();

        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);

        bgImageView = (ImageView) findViewById(R.id.backdrop);
        tvSummary = (TextView) findViewById(R.id.tv_summary);
        tvMovieInfo = (TextView) findViewById(R.id.tv_movie_info);
        rbMovieLevel = (RatingBar) findViewById(R.id.rb_movie_level);
        tvMovieLevelInfo = (TextView) findViewById(R.id.tv_movie_level_info);
        rvList = (RecyclerView) findViewById(R.id.rv_horizontal_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rvList.setLayoutManager(layoutManager);
        rvList.addItemDecoration(new GridMarginDecoration(getResources().getDimensionPixelSize(R.dimen.grid_item_spacing)));
        getMovieDetail(getIntent());
    }
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_movie_detail;
    }
    @Override
    public void inject() {

    }
    void initActionBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        detailViewModel=new MovieDetailViewModel();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        getMovieDetail(intent);
    }

    /**
     * 获取电影咨询 详情
     */
    private void getMovieDetail(Intent intent) {
        String movieId = intent.getStringExtra(INTENT_KEY_MOVIE_ID);
        if (!TextUtils.isEmpty(movieId)) {
            detailViewModel.getMovieDetail(Integer.parseInt(movieId))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new CustomObserver<MovieDetailBean>() {
                        @Override
                        public void onSuccess(MovieDetailBean movieDetailBean) {
                            if (movieDetailBean != null) {
                                loadBackdrop(movieDetailBean.images.getLarge());
                                RatingInfo ratingBean = movieDetailBean.rating;
                                if (ratingBean != null) {
                                    float averageLevel =movieDetailBean.rating.average;
                                    rbMovieLevel.setRating(averageLevel / 2);
                                    tvMovieLevelInfo.setText(String.format(" %s 分", String.valueOf(averageLevel)));
                                }
                                collapsingToolbar.setTitle(movieDetailBean.getTitle());
                                //collapsingToolbar.setExpandedTitleColor(MovieDetailActivity.this.getResources().getColor(R.color.colorPrimary));
                                tvSummary.setText(movieDetailBean.getSummary());

                                loadBaseInfo(movieDetailBean);

                                ArtAdapter artAdapter = new ArtAdapter(MovieDetailActivity.this,movieDetailBean.directors);
                                rvList.setAdapter(artAdapter);

                            }
                        }

                        @Override
                        protected void onFinish() {

                        }
                    });
            
                    

        }
    }
    /**
     * 加载影片信息
     *
     * @param movieDetailBean
     */
    private void loadBaseInfo(MovieDetailBean movieDetailBean) {
        StringBuilder stringBuilder = new StringBuilder();
        List<DirectorsInfo> directorsBeanList = movieDetailBean.directors;

        if (directorsBeanList != null && directorsBeanList.size() > 0) {
            stringBuilder.append("导演:");
            boolean isFirst = true;
            for (DirectorsInfo directorsBean : directorsBeanList) {
                if (!isFirst) {
                    stringBuilder.append("/");
                }
                stringBuilder.append(directorsBean.getName());
                isFirst = false;
            }
            stringBuilder.append("\n");
        }

        List<String> genres = movieDetailBean.genres;
        stringBuilder = listToString("类型:", genres, stringBuilder);

        stringBuilder.append("上映日期:");
        stringBuilder.append(movieDetailBean.year);
        stringBuilder.append("\n");

        List<String> countries = movieDetailBean.getCountries();
        stringBuilder = listToString("制片国家/地区:", countries, stringBuilder);

        List<String> akrs = movieDetailBean.aka;
        if (akrs != null && akrs.size() > 3) {
            akrs = akrs.subList(0, 2);
        }
        stringBuilder = listToString("又名:", akrs, stringBuilder);

        tvMovieInfo.setText(stringBuilder.toString());
    }

    private StringBuilder listToString(String title, List<String> list, StringBuilder stringBuilder) {
        if (list != null && list.size() > 0) {
            stringBuilder.append(title);
            boolean isFirst = true;
            for (String string : list) {
                if (!isFirst) {
                    stringBuilder.append("/");
                }
                stringBuilder.append(string);
                isFirst = false;
            }

            stringBuilder.append("\n");
        }
        return stringBuilder;
    }

    private void loadBackdrop(String url) {
        Glide.with(this).load(url).centerCrop().into(bgImageView);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                break;
        }
        return true;
    }
}
