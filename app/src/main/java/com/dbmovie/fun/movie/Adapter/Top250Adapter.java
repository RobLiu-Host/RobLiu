package com.dbmovie.fun.movie.Adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.dbmovie.R;
import com.dbmovie.fun.movie.bean.Top250Bean;
import com.dbmovie.fun.movie.ui.MovieDetailActivity;
import com.util.supportlibrary.CommonViewHolder;
import com.util.supportlibrary.RecyclerViewCommonAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * top 250
 * <p/>
 * Created on 16/7/12.
 */
public class Top250Adapter extends RecyclerViewCommonAdapter<Top250Bean.SubjectsBean> {
    /**
     * @param mContext 上下文对象
     * @param list     数据List
     */
    public Top250Adapter(Context mContext, List<Top250Bean.SubjectsBean> list) {
        super(mContext, list, R.layout.view_top_250);
    }

    public void update(List<Top250Bean.SubjectsBean> list) {
        addList((ArrayList<Top250Bean.SubjectsBean>) list);
    }

    @Override
    public void onListBindViewHolder(CommonViewHolder holder, int position) {
        final Top250Bean.SubjectsBean subjectsBean = getItem(position);
        holder.setText(R.id.tv_movie_name, subjectsBean.getTitle());
        holder.setText(R.id.tv_movie_score, String.format(mContext.getString(R.string.tip_score), String.valueOf(subjectsBean.getRating().getAverage())));
        holder.setText(R.id.tv_movie_date, String.format(mContext.getString(R.string.tip_movie_date), subjectsBean.getYear()));
        ImageView imageView =holder.itemView.findViewById(R.id.iv_movie_icon);
        Glide.with(mContext)
                .load(mList.get(position).getImages().getLarge())
                .placeholder(android.R.color.white)
                .into(imageView);
        holder.itemView.setOnClickListener(v -> mContext.startActivity(MovieDetailActivity.getCallingIntent(mContext, subjectsBean.getId())));

    }

}
