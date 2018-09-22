package com.dbmovie.fun.movie.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dbmovie.R;
import com.dbmovie.base.base.CustomObserver;
import com.dbmovie.fun.movie.Adapter.InTheatersAdapter;
import com.dbmovie.fun.movie.bean.MainInfo;
import com.dbmovie.fun.movie.bean.MovieInfo;
import com.dbmovie.fun.movie.viewmodel.MovieScreeningViewModel;
import com.dbmovie.util.GridMarginDecoration;
import com.util.supportlibrary.GridRecyclerView;


import java.util.List;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 正在热映
 * <p/>
 * Created on 16/6/27.
 */
public class InTheaterFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    GridRecyclerView mRecyclerView;
    GridLayoutManager mGridLayoutManager;
    InTheatersAdapter mTheaterAdapter;
    SwipeRefreshLayout mSwipeRefreshLayout;

    List<MovieInfo> mSubjectsEntities;

    MovieScreeningViewModel mViewModel;

    public static InTheaterFragment newInstance() {
        Bundle args = new Bundle();
        InTheaterFragment fragment = new InTheaterFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel=new MovieScreeningViewModel();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_intheater,null);
        mSwipeRefreshLayout =  view.findViewById(R.id.srl_refresh_layout);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mRecyclerView = view.findViewById(R.id.recyclerview);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mGridLayoutManager = new GridLayoutManager(getActivity(), 3);
        mGridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                switch (position % 6) {
                    case 5:
                        return 3;
                    case 3:
                        return 2;
                    default:
                        return 1;
                }
            }
        });
        mRecyclerView.setLayoutManager(mGridLayoutManager);
        mRecyclerView.addItemDecoration(new GridMarginDecoration(getResources().getDimensionPixelSize(R.dimen.grid_item_spacing)));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setOnLoadMoreListener(recyclerView->{

        });

        setPreLoadData();
    }

    @Override
    public void onRefresh() {
        requestDefaultData();
    }

    public void setPreLoadData() {
        mSwipeRefreshLayout.post(()->{
                    mSwipeRefreshLayout.setRefreshing(true);
                    requestDefaultData();
                });
    }

    /**
     * 请求数据
     */

    private void requestDefaultData() {
        mViewModel.getScreenInfo("杭州")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CustomObserver<MainInfo>() {
                    @Override
                    public void onSuccess(MainInfo response) {
                            mSubjectsEntities =response.subjects;
                            if (mTheaterAdapter == null) {
                                mTheaterAdapter = new InTheatersAdapter(mSubjectsEntities, getActivity());
                                mRecyclerView.setAdapter(mTheaterAdapter);
                            } else {
                                mTheaterAdapter.update(mSubjectsEntities);
                            }
                    }

                    @Override
                    protected void onFinish() {
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                });
    }
}
