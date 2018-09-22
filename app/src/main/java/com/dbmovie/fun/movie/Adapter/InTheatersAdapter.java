package com.dbmovie.fun.movie.Adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;



import java.util.List;

import com.bumptech.glide.Glide;
import com.dbmovie.R;
import com.dbmovie.fun.movie.bean.MovieInfo;
import com.dbmovie.fun.movie.ui.MovieDetailActivity;
import com.util.supportlibrary.CommonViewHolder;
import com.util.supportlibrary.RecyclerViewCommonAdapter;

/**
 * Created  on 16-6-6.
 * <p/>
 * 正在上映电影 - 数据适配器
 */
public class InTheatersAdapter extends RecyclerViewCommonAdapter<MovieInfo> {

    public InTheatersAdapter(List<MovieInfo> list, Context context) {
        super(context, list,R.layout.view_list_item_home);
    }

    public void update(List<MovieInfo> list) {
        mList = list;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public void onListBindViewHolder(CommonViewHolder holder, int position) {
        final MovieInfo entity = mList.get(position);
        Glide.with(mContext)
                .load(mList.get(position).getImages().getLarge())
                .placeholder(android.R.color.white)
                .into((ImageView) holder.getView(R.id.photo));
        holder.setText(R.id.tv_movie_name, entity.getTitle());
        holder.itemView.setOnClickListener(
                view -> mContext.startActivity(MovieDetailActivity.getCallingIntent(mContext,entity.getId()))
        );
    }

}
