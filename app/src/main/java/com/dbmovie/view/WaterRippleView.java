package com.dbmovie.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DrawFilter;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by liuba on 2017/8/26.
 * describe :自定义一个水波纹类
 */

public class WaterRippleView extends View{

    private  WaterRippeListenter rippeListenter;
    /**
     *   定义两条路径
     */
    private Path mAbovePath,mBelowWavePath;

    private Paint mAbovePaint,mBelowWavePaint;

    private DrawFilter drawFilter;

    //角度
    private float Θ;

    public WaterRippleView(Context context) {
        this(context,null);
    }

    public WaterRippleView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public WaterRippleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    void initView(){
        mAbovePath=new Path();
        mBelowWavePath=new Path();

        //抗锯齿
        mAbovePaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        mAbovePaint.setAntiAlias(true);
        mAbovePaint.setStyle(Paint.Style.FILL);
        mAbovePaint.setColor(Color.WHITE);

        mBelowWavePaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        mBelowWavePaint.setAntiAlias(true);
        mBelowWavePaint.setStyle(Paint.Style.FILL);
        mBelowWavePaint.setColor(Color.WHITE);
        mBelowWavePaint.setAlpha(80);

        //画布抗锯齿
        drawFilter = new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //测量逻辑
        //由于位置固定所以高度不用测量

    }

    /**
     *  y=Asin(ωx+φ)+k
    *  A—振幅越大，波形在y轴上最大与最小值的差值越大
    *  ω—角速度， 控制正弦周期(单位角度内震动的次数)
     *  φ—初相，反映在坐标系上则为图像的左右移动。这里通过不断改变φ,达到波浪移动效果
    *  k—偏距，反映在坐标系上则为图像的上移或下移。
    */

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.setDrawFilter(drawFilter);

        //绘画逻辑
        mAbovePath.reset();
        mBelowWavePath.reset();
    /**
     *   moveTo	移动下一次操作的起点位置
        连接直线	lineTo	添加上一个点到当前点之间的直线到Path
        重置路径	reset, rewind
        清除Path中的内容reset不保留内部数据结构，但会保留FillType
        **rewind会保留内部的数据结构，但不保留FillType**
    */
        Θ-=0.1f;
        float y,y2;
        double ω = 2*Math.PI / getWidth();

        mAbovePath.moveTo(getLeft(),getBottom());
        mBelowWavePath.moveTo(getLeft(),getBottom());
        for (float x = 0; x <= getWidth(); x += 20) {
            /**
             *  y=Asin(ωx+φ)+k
             *  A—振幅越大，波形在y轴上最大与最小值的差值越大
             *  ω—角速度， 控制正弦周期(单位角度内震动的次数)
             *  φ—初相，反映在坐标系上则为图像的左右移动。这里通过不断改变φ,达到波浪移动效果
             *  k—偏距，反映在坐标系上则为图像的上移或下移。
             */
            y = (float) (8 * Math.cos(ω*x+Θ) +8);
            y2 = (float) (8 * Math.sin(ω*x+Θ));
            mAbovePath.lineTo(x, y);
            mBelowWavePath.lineTo(x, y2);
            //回调 把y坐标的值传出去(在activity里面接收让小机器人随波浪一起摇摆)
            rippeListenter.Listenter(y);
        }
        mAbovePath.lineTo(getRight(),getBottom());
        mBelowWavePath.lineTo(getRight(),getBottom());

        canvas.drawPath(mAbovePath,mAbovePaint);
        canvas.drawPath(mBelowWavePath,mBelowWavePaint);

        postInvalidateDelayed(20);
    }

    public void setRippeListenter(WaterRippeListenter rippeListenter) {
        this.rippeListenter = rippeListenter;
    }
    /**
     * 监听器
     */
    public interface WaterRippeListenter{
        void Listenter(float y);
    }
}
