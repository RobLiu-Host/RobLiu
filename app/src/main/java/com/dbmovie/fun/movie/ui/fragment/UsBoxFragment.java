package com.dbmovie.fun.movie.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dbmovie.R;
import com.dbmovie.base.base.CustomObserver;
import com.dbmovie.fun.movie.Adapter.UsBoxAdapter;
import com.dbmovie.fun.movie.bean.UsBoxBean;
import com.dbmovie.fun.movie.viewmodel.UsBoxViewModel;
import com.dbmovie.util.GridMarginDecoration;
import com.dbmovie.view.CustomerRecyclerView;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 北美排行榜
 * <p/>
 * Created on 16/7/12.
 */
public class UsBoxFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    List<UsBoxBean.SubjectsBean> mList;
    CustomerRecyclerView recyclerView;
    SwipeRefreshLayout mSwipeRefreshLayout;
    UsBoxAdapter usBoxAdapter;

    UsBoxViewModel usBoxViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        usBoxViewModel=new UsBoxViewModel();
    }

    public static UsBoxFragment newInstance() {
        Bundle args = new Bundle();
        UsBoxFragment fragment = new UsBoxFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_top250, null);
        recyclerView =  view.findViewById(R.id.rv_recyclerview);
        mSwipeRefreshLayout = view.findViewById(R.id.srl_refresh_layout);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new GridMarginDecoration(
                getResources().getDimensionPixelSize(R.dimen.grid_item_spacing_1)));
        recyclerView.setHasFixedSize(true);
        setPreLoadData();
    }


    @Override
    public void onRefresh() {
        if (usBoxAdapter != null) {
            usBoxAdapter.clear();
        }
        requestData();
    }

    public void setPreLoadData() {
        mSwipeRefreshLayout.post(() -> {
            mSwipeRefreshLayout.setRefreshing(true);
            onRefresh();
        });
    }


    private void requestData() {
        usBoxViewModel.getUsBoxMovie()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CustomObserver<UsBoxBean>() {
                    @Override
                    public void onSuccess(UsBoxBean usBoxBean) {
                        if (usBoxBean != null) {
                            mList = usBoxBean.getSubjects();
                            if (usBoxAdapter == null) {
                                usBoxAdapter = new UsBoxAdapter(getContext(), mList);
                                recyclerView.setAdapter(usBoxAdapter);
                            } else {
                                usBoxAdapter.update(mList);
                            }
                        }
                    }

                    @Override
                    protected void onFinish() {
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                });
    }


    protected int getLayoutRes() {
        return R.layout.fragment_top250;
    }



}
