package com.dbmovie.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class WaterView extends View {


    Paint paint;
    Paint textPaint;

    float mRingWidth;

    int mRingNormalColor=0xffbc7d53;

    public WaterView(Context context) {
        this(context,null);
    }

    public WaterView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public WaterView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        Paint ringNormalPaint = new Paint();
        ringNormalPaint.setStyle(Paint.Style.STROKE);
        ringNormalPaint.setStrokeWidth(mRingWidth);
        ringNormalPaint.setColor(mRingNormalColor);


        textPaint=new Paint();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(0,0,50,paint);

        drawCiecle(canvas);
    }

    /**
     * 画圆
     * @param canvas
     */
    private void drawCiecle(Canvas canvas) {


    }
}
