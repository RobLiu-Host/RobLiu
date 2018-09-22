package com.dbmovie.test;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dbmovie.R;

public class AnimationActivity extends AppCompatActivity implements View.OnClickListener{
    private Button button1,button2,button3,button4,button5;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.animation);
        initView();
    }

    /**
     *
     */
    private void initView() {
        TextView textView= (TextView) findViewById(R.id.text1);
        SpannableString spannableString = new SpannableString("默认颜色红颜色");
        ForegroundColorSpan foregroundColorSpan=new ForegroundColorSpan(Color.parseColor("#FF0000"));
        spannableString.setSpan(foregroundColorSpan,4,spannableString.length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.append(spannableString);


        spannableString = new SpannableString("级大咖道具卡佳都科技阿德");
        ForegroundColorSpan foregroundColorSpan1=new ForegroundColorSpan(Color.parseColor("#FF0000"));
        spannableString.setSpan(foregroundColorSpan1, 4,spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.append(spannableString);
//        button1= (Button) findViewById(R.id.id1);
//        button2= (Button) findViewById(R.id.id2);
//        button3= (Button) findViewById(R.id.id3);
//        button4= (Button) findViewById(R.id.id4);
//        button5= (Button) findViewById(R.id.id5);
//
//        button1.setOnClickListener(this);
//        button2.setOnClickListener(this);
//        button3.setOnClickListener(this);
//        button4.setOnClickListener(this);
//        button5.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }
}
