package com.dbmovie.fun.movie.Adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.dbmovie.R;
import com.dbmovie.fun.movie.bean.CommonBean;
import com.dbmovie.fun.movie.ui.MovieDetailActivity;
import com.util.supportlibrary.CommonViewHolder;
import com.util.supportlibrary.RecyclerViewCommonAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created  on 16-6-6.
 * <p/>
 * 即将上映电影
 */
public class CommingSoonAdapter extends RecyclerViewCommonAdapter<CommonBean.SubjectsBean> {


    public CommingSoonAdapter(List<CommonBean.SubjectsBean> list, Context context) {
        super(context, list, R.layout.view_list_item_home);
    }

    public void update(List<CommonBean.SubjectsBean> list) {
        addList((ArrayList<CommonBean.SubjectsBean>) list);
    }

    @Override
    public void onListBindViewHolder(CommonViewHolder holder, int position) {
        final CommonBean.SubjectsBean entity = mList.get(position);
        Glide.with(mContext)
                .load(mList.get(position).getImages().getLarge())
                .placeholder(android.R.color.white)
                .into((ImageView) holder.getView(R.id.photo));
        holder.setText(R.id.tv_movie_name, entity.getTitle());
        holder.itemView.setOnClickListener(v -> mContext.startActivity(MovieDetailActivity.getCallingIntent(mContext, entity.getId())));
    }

   /* public void refreshData(ArrayList<T> data) {
        ArrayList<T> newData = new ArrayList<>();
        newData.addAll(data);

        mData.clear();
        mData.addAll(newData);
        notifyDataSetChanged();
    }*/


}
