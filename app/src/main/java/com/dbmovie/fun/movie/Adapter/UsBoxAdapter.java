package com.dbmovie.fun.movie.Adapter;

import android.content.Context;

import com.dbmovie.R;
import com.dbmovie.fun.movie.bean.UsBoxBean;
import com.dbmovie.fun.movie.ui.MovieDetailActivity;
import com.util.supportlibrary.CommonViewHolder;
import com.util.supportlibrary.RecyclerViewCommonAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 北美排行榜
 * <p/>
 * Created  on 16/7/12.
 */
public class UsBoxAdapter extends RecyclerViewCommonAdapter<UsBoxBean.SubjectsBean> {
    /**
     * @param mContext 上下文对象
     * @param list     数据List
     */
    public UsBoxAdapter(Context mContext, List<UsBoxBean.SubjectsBean> list) {
        super(mContext, list, R.layout.view_rank_item);
    }
    public void update(List<UsBoxBean.SubjectsBean> list) {
        addList((ArrayList<UsBoxBean.SubjectsBean>) list);
    }
    @Override
    public void onListBindViewHolder(CommonViewHolder holder, int position) {
        final UsBoxBean.SubjectsBean subjectsBean = getItem(position);
        holder.setText(R.id.tv_rank, String.valueOf(subjectsBean.getRank()));
        holder.setText(R.id.tv_movie_name, String.valueOf(subjectsBean.getSubject().getTitle()));
        holder.setText(R.id.tv_movie_score, String.format(mContext.getString(R.string.tip_score), String.valueOf(subjectsBean.getSubject().getRating().getAverage())));
        holder.itemView.setOnClickListener(v -> mContext.startActivity(MovieDetailActivity.getCallingIntent(mContext, subjectsBean.getSubject().getId())));

    }
}