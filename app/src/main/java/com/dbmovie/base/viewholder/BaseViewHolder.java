package com.dbmovie.base.viewholder;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

/**
 * Created by liuba on 2017/8/23.
 * describe :
 */

public class BaseViewHolder<T extends ViewDataBinding> extends RecyclerView.ViewHolder {

    protected final T binding;

    public BaseViewHolder(T t) {
        super(t.getRoot());
        this.binding = t;
    }

    public T getBinding() {
        return binding;
    }
}
