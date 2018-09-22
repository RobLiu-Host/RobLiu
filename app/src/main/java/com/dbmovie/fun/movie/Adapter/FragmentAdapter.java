package com.dbmovie.fun.movie.Adapter;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.dbmovie.R;
import com.dbmovie.fun.movie.ui.fragment.CommingSoonFragment;
import com.dbmovie.fun.movie.ui.fragment.InTheaterFragment;
import com.dbmovie.fun.movie.ui.fragment.Top250Fragment;
import com.dbmovie.fun.movie.ui.fragment.UsBoxFragment;

public  class FragmentAdapter extends FragmentStatePagerAdapter {
        private Context context;

        public FragmentAdapter(FragmentManager fm, Context context) {
            super(fm);
            this.context = context;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return context.getString(R.string.tab_title_intheaters);
                case 1:
                    return context.getString(R.string.tab_title_comming_soon);
                case 2:
                    return context.getString(R.string.tab_title_top250);
                case 3:
                    return context.getString(R.string.tab_title_us_box);
                default:
                    return context.getString(R.string.tab_title_intheaters);
            }
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return InTheaterFragment.newInstance();
                case 1:
                    return CommingSoonFragment.newInstance();
                case 2:
                    return Top250Fragment.newInstance();
                case 3:
                    return UsBoxFragment.newInstance();
                default:
                    return InTheaterFragment.newInstance();
            }
        }

        @Override
        public int getCount() {
            return 4;
        }
    }