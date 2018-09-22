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
import com.dbmovie.fun.movie.Adapter.CommingSoonAdapter;
import com.dbmovie.fun.movie.bean.CommonBean;
import com.dbmovie.fun.movie.viewmodel.CommingSoonViewModel;
import com.dbmovie.util.GridMarginDecoration;
import com.util.supportlibrary.GridRecyclerView;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
/**
 * 即将上映
 * <p/>
 * Created on 16/6/27.
 */
public class CommingSoonFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    GridRecyclerView mRecyclerView;
    GridLayoutManager mGridLayoutManager;
    CommingSoonAdapter mCommingSoonAdapter;
    SwipeRefreshLayout mSwipeRefreshLayout;

    List<CommonBean.SubjectsBean> mSubjectsEntities;
    CommonBean mCommonBean;
    CommingSoonViewModel commingSoonViewModel ;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        commingSoonViewModel=new CommingSoonViewModel();
    }

    public static CommingSoonFragment newInstance() {
        Bundle args = new Bundle();
        CommingSoonFragment fragment = new CommingSoonFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_commingsoon, null);
        mSwipeRefreshLayout =  view.findViewById(R.id.srl_refresh_layout);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mRecyclerView =view.findViewById(R.id.recyclerview);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mGridLayoutManager = new GridLayoutManager(getContext(), 3);
        mGridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                switch (position % 4) {
                    case 0:
                        return 3;
                    default:
                        return 1;
                }
            }
        });
        mRecyclerView.setLayoutManager(mGridLayoutManager);
        mRecyclerView.addItemDecoration(new GridMarginDecoration(
                getResources().getDimensionPixelSize(R.dimen.grid_item_spacing)));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setOnLoadMoreListener(recyclerView -> {
            if (mCommonBean != null) {
                if (mCommonBean.isHasNext()) {
                    recyclerView.showFootProgress();
                    requestDefaultData(mCommonBean.getNextIndex());
                } else {
                    recyclerView.showFootProgressEnd();
                }
            }
        });
        setPreLoadData();
    }

    @Override
    public void onRefresh() {
        if (mCommingSoonAdapter != null) {
            mCommingSoonAdapter.clear();
        }
        requestDefaultData(0);
    }

    public void setPreLoadData() {
        mSwipeRefreshLayout.post(() -> {
            mSwipeRefreshLayout.setRefreshing(true);
            onRefresh();
        });
    }

    private void requestDefaultData(int pageIndex) {
        commingSoonViewModel.getCommingSoonMovie(pageIndex,20)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CustomObserver<CommonBean>() {
                    @Override
                    public void onSuccess(CommonBean commonBean) {
                        mCommonBean = commonBean;
                        mSubjectsEntities = commonBean.getSubjects();
                        if (mCommingSoonAdapter == null) {
                            mCommingSoonAdapter = new CommingSoonAdapter(mSubjectsEntities, getActivity());
                            mRecyclerView.setAdapter(mCommingSoonAdapter);
                        } else {
                            mCommingSoonAdapter.update(mSubjectsEntities);
                        }
                    }
                    @Override
                    protected void onFinish() {
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                });
    }


    protected int getLayoutRes() {
        return R.layout.fragment_commingsoon;
    }



}
