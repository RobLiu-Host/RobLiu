package com.dbmovie.base.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.dbmovie.BR;
import com.dbmovie.base.viewholder.BaseViewHolder;

import java.util.List;

/**
 * Created by liuba on 2017/8/23.
 * describe :Recyclerview的基类Adapter
 */

public abstract class BaseRecyclerAdapter<T,E extends ViewDataBinding> extends RecyclerView.Adapter<BaseViewHolder<E>>{

    //数据集合
    private List<T> mDatas;
    //布局ID
    private int layoutId;

    //用于设置Item的事件Presenter
    protected IBasePresenter mItemPresenter;

    public BaseRecyclerAdapter(List<T> mDatas, int layoutId) {
        this.mDatas = mDatas;
        this.layoutId = layoutId;
    }


    @Override
    public BaseViewHolder<E> onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        BaseViewHolder<E>itemHolder=new BaseViewHolder<>(DataBindingUtil.inflate(inflater,layoutId,parent,false));
        onCreateViewHolder(itemHolder);
        return itemHolder;
    }

    //抽象方法
    public abstract void onCreateViewHolder(BaseViewHolder<E> holder);

    @Override
    public void onBindViewHolder(BaseViewHolder<E> holder, int position) {
        holder.getBinding().setVariable(BR.data,mDatas.get(position));
        holder.getBinding().setVariable(BR.itemPresenter,mItemPresenter);
        holder.getBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mDatas==null?0:mDatas.size();
    }


    /**
     * 用于设置Item的事件Presenter
     *
     * @param itemPresenter
     * @return
     */
    public BaseRecyclerAdapter setItemPresenter(IBasePresenter itemPresenter) {
        mItemPresenter = itemPresenter;
        return this;
    }
}
