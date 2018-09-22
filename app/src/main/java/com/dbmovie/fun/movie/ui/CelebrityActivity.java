package com.dbmovie.fun.movie.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dbmovie.R;
import com.dbmovie.base.base.BaseActivity;
import com.dbmovie.base.base.CustomObserver;
import com.dbmovie.fun.movie.Adapter.CelebrityAdapter;
import com.dbmovie.fun.movie.bean.CelebrityBean;
import com.dbmovie.fun.movie.viewmodel.CelebrityViewModel;
import com.dbmovie.util.GridMarginDecoration;
import com.dbmovie.util.SpConfig;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 演员详情
 * Created  on 16/7/4.
 */
public class CelebrityActivity extends BaseActivity {
    private static final String INTENT_KEY_CELEBRITY_ID="id";
    RecyclerView rvCelebrity;
    TextView tvInfo, tvTitle;

    ImageView ivAvatar;
    CelebrityBean mCelebrityBean;

    CelebrityViewModel celebrityViewModel;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_celebrity);
        initActionBar();
        rvCelebrity = (RecyclerView) findViewById(R.id.rv_view);
        tvInfo = (TextView) findViewById(R.id.tv_celebrity_info);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        ivAvatar = (ImageView) findViewById(R.id.iv_avatar);

        rvCelebrity.addItemDecoration(new GridMarginDecoration(getResources().getDimensionPixelSize(R.dimen.grid_item_spacing)));
        rvCelebrity.setHasFixedSize(true);


        getCelebrityDetail(getIntent());
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_celebrity;
    }

    @Override
    public void inject() {
        celebrityViewModel=new CelebrityViewModel();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return super.onCreateOptionsMenu(menu);
    }

    void initActionBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        getCelebrityDetail(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                break;
            case R.id.menu_title_view:
                if (mCelebrityBean != null) {
                   WebViewActivity.start(CelebrityActivity.this, mCelebrityBean.getMobile_url(), mCelebrityBean.getName());
                }
                break;
        }
        return true;
    }

    /**
     * 获取 演员详情
     */
    private void getCelebrityDetail(Intent intent) {
        String id = intent.getStringExtra(INTENT_KEY_CELEBRITY_ID);
        if (!TextUtils.isEmpty(id)) {
            celebrityViewModel.getCelebrityDetail(Integer.parseInt(id))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new CustomObserver<CelebrityBean>() {
                        @Override
                        public void onSuccess(CelebrityBean celebrityBean) {
                            if (celebrityBean != null) {
                                mCelebrityBean = celebrityBean;
                                String gender = celebrityBean.getGender();
                                if (!TextUtils.isEmpty(gender) && gender.equals("男")) {
                                    //如果设置 只看女明星
                                    if (SpConfig.getIsSee(CelebrityActivity.this)) {
                                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(CelebrityActivity.this);
                                        alertDialogBuilder.setTitle("提示")
                                                .setMessage("这位是男明星,根据您个人偏好设置,即将退出此页面。")
                                                .setCancelable(false)
                                                .setPositiveButton("知道了", (dialog,which) -> {
                                                    dialog.dismiss();
                                                    finish();
                                                }).show();
                                    } else {
                                        updateView(celebrityBean);
                                    }
                                } else {
                                    updateView(celebrityBean);
                                    //ToastUtil.showShortMessage(CelebrityActivity.this, "啊!!!女人");
                                }
                            }
                        }
                        @Override
                        protected void onFinish() {

                        }
               });
        }
    }

    private void updateView(CelebrityBean celebrityBean) {
        Glide.with(this).load(celebrityBean.getAvatars().getLarge()).into(ivAvatar);
        tvTitle.setText(celebrityBean.getName());
        StringBuilder stringBuilder = new StringBuilder();
        if (!TextUtils.isEmpty(celebrityBean.getGender())) {
            stringBuilder.append("性别:" + celebrityBean.getGender());
        }
        if (!TextUtils.isEmpty(celebrityBean.getName_en())) {
            stringBuilder.append("\n");
            stringBuilder.append("英文名:" + celebrityBean.getName_en());
        }
        if (celebrityBean.getAka() != null && celebrityBean.getAka().size() > 0) {
            stringBuilder.append("\n");
            stringBuilder.append("曾用名:" + celebrityBean.getAka().get(0));
        }
        if (!TextUtils.isEmpty(celebrityBean.getBorn_place())) {
            stringBuilder.append("\n");
            stringBuilder.append("出生地:" + celebrityBean.getBorn_place());
        }
        tvInfo.setText(stringBuilder.toString());
        CelebrityAdapter celebrityAdapter = new CelebrityAdapter(celebrityBean.getWorks(), CelebrityActivity.this);
        rvCelebrity.setAdapter(celebrityAdapter);
    }

    public static void start(Context context,String id) {
        Intent starter = new Intent(context, CelebrityActivity.class);
        starter.putExtra(INTENT_KEY_CELEBRITY_ID, id);
        context.startActivity(starter);
    }
}
