package com.dbmovie.fun.my;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.dbmovie.R;
import com.dbmovie.view.WaterRippleView;


/**
 * Created by liuba
 */

public class UserActivity extends AppCompatActivity {

    private ImageView imageView;
    private WaterRippleView waveView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_center);
        imageView = (ImageView) findViewById(R.id.image);
        waveView3 = (WaterRippleView) findViewById(R.id.wave_view);

        final FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(-2,-2);
        lp.gravity = Gravity.BOTTOM|Gravity.CENTER;

        waveView3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });

        waveView3.setRippeListenter(y -> {
            lp.setMargins(0,0,0,(int)y+2);
            imageView.setLayoutParams(lp);
        });
    }
}

