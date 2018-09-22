package com.dbmovie.fun.movie.Adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.dbmovie.R;
import com.dbmovie.fun.movie.bean.DirectorsInfo;
import com.dbmovie.fun.movie.ui.CelebrityActivity;
import com.util.supportlibrary.CommonViewHolder;
import com.util.supportlibrary.RecyclerViewCommonAdapter;

import java.util.List;

public class ArtAdapter extends RecyclerViewCommonAdapter<DirectorsInfo> {

        public ArtAdapter(Context context, List<DirectorsInfo> data) {
            super(context, data, R.layout.view_list_cast_item);
        }

        @Override
        public void onListBindViewHolder(CommonViewHolder holder, int position) {
            final DirectorsInfo castsBean = getItem(position);
            if (castsBean != null) {
                if (castsBean.getAvatars() != null) {
                    ImageView castImageView = holder.getView(R.id.iv_cast_image);
                    Glide.with(mContext).load(castsBean.getAvatars().getLarge()).placeholder(android.R.color.white).into(castImageView);
                    //holder.loadImageUrl(mContext, R.id.iv_cast_image, castsBean.getAvatars().getLarge());
                } else {
                    holder.setImageRes(R.id.iv_cast_image, R.drawable.default_large);
                }
                holder.setText(R.id.tv_cast_name, castsBean.getName());
                holder.itemView.setOnClickListener(view -> CelebrityActivity.start(mContext, castsBean.getId()));
            }
        }
    }
