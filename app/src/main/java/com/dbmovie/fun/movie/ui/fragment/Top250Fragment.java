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
import com.dbmovie.fun.movie.Adapter.Top250Adapter;
import com.dbmovie.fun.movie.bean.Top250Bean;
import com.dbmovie.fun.movie.viewmodel.Top250ViewModel;
import com.dbmovie.util.GridMarginDecoration;
import com.util.supportlibrary.LinearRecyclerView;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * top 250 fragement
 * <p/>
 * Created  on 16/7/12.
 */
public class Top250Fragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    LinearRecyclerView recyclerView;
    SwipeRefreshLayout mSwipeRefreshLayout;
    Top250Adapter top250Adapter;

    List<Top250Bean.SubjectsBean> mList;
    Top250Bean mTop250Bean;


    Top250ViewModel top250ViewModel;
    public static Top250Fragment newInstance() {
        Bundle args = new Bundle();
        Top250Fragment fragment = new Top250Fragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_top250, null);
        recyclerView =  view.findViewById(R.id.rv_recyclerview);
        mSwipeRefreshLayout =view.findViewById(R.id.srl_refresh_layout);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new GridMarginDecoration(
                getResources().getDimensionPixelSize(R.dimen.grid_item_spacing)));
        recyclerView.setHasFixedSize(true);
        recyclerView.setOnLoadMoreListener(recyclerView -> {
            if (mTop250Bean != null && mTop250Bean.isHasNext()) {
                recyclerView.showFootProgress();
                requestData(mTop250Bean.getNextIndex(), 20);
            } else {
                recyclerView.showFootProgressEnd();
            }
        });
        setPreLoadData();
    }


    @Override
    public void onRefresh() {
        if (top250Adapter != null) {
            top250Adapter.clear();
        }
        requestData(0, 20);
    }

    public void setPreLoadData() {
        mSwipeRefreshLayout.post(() -> {
            mSwipeRefreshLayout.setRefreshing(true);
            onRefresh();
        });
    }

    /**
     * 请求数据
     * @param start
     * @param count
     */
    private void requestData(int start, int count) {
        top250ViewModel.getTop250Movie(start, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CustomObserver<Top250Bean>() {
                    @Override
                    public void onSuccess(Top250Bean top250Bean) {
                        if (top250Bean != null) {
                            recyclerView.showFootProgressEnd();

                            mTop250Bean = top250Bean;
                            mList = top250Bean.getSubjects();
                            if (top250Adapter == null) {
                                top250Adapter = new Top250Adapter(getContext(), mList);
                                recyclerView.setAdapter(top250Adapter);
                            } else {
                                top250Adapter.update(mList);
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


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        top250ViewModel=new Top250ViewModel();
    }
}

