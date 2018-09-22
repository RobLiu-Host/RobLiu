package com.dbmovie.anim;


import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.PathMeasure;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

;



/**
 * 定义android登录动画
 */
public class LoginButton extends View {

    /**
     * 按钮平移的距离
     */
    int moveInstance=300;

    private RectF mRectF;

    private Paint mPaint;

    private Paint mTextPaint;

    private Rect textRect=new Rect();
    /**
     * 矩形的长宽
     */
    private float width;
    private float height;

    private int two_distance;

    /**
     * 默认的半径
     */

    private int  default_two_distance;


    private ValueAnimator mcircleAnimator;


    private ValueAnimator mtranslationX;


    private ObjectAnimator mtranslationY;

    private ValueAnimator animator_draw_ok;


    private int duration=2000;
    /**
     * 圆半径
     */
    private float circle_radius=0;
    /**
     * 按钮的背景色
     */
    private int bg_color=0xffbc7d53;;

    private AnimationButtonListener mButtonListener;


    AnimatorSet animationSet;


    /**
     * 路径--用来获取对勾的路径
     */
    private Path path = new Path();



    private Paint okPaint;
    /**
     * 取路径的长度
     */
    private PathMeasure pathMeasure;
    /**
     * 对路径处理实现绘制动画效果
     */
    private PathEffect effect;

    /**
     * 是否开始绘制对勾
     */
    private boolean startDrawOk = false;

    /**
     * 按钮文字字符串
     */
    private String buttonString = "确认完成";

    public LoginButton(Context context) {
        this(context,null);
    }

    public LoginButton(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);


    }
    public LoginButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();

        this.setOnClickListener(v -> {
                if(mButtonListener!=null){
                    mButtonListener.onClickListener();
                }
        });

        animationSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if(mButtonListener!=null){
                    mButtonListener.animationFinish();
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        initAnimator();
    }

    /**
     * 初始化动画
     */
    private void initAnimator() {
        setAnimation();
        translation();
        translationY();
        set_draw_ok_animation();

        animationSet.play(mtranslationX).before(mcircleAnimator).after(mtranslationY).after(animator_draw_ok);
    }

    /**
     * 开始动画
     */
    public void startAnmiation(){
        animationSet.start();
    }

    /**
     * 清空动画
     */
    public void stopAnmiation(){
        startDrawOk = false;
        circle_radius = 0;
        two_distance = 0;
        default_two_distance = (int) ((width - height) / 2);
        mTextPaint.setAlpha(255);

        setTranslationY(getTranslationY() + moveInstance);
        invalidate();
    }

    /**
     * 绑定监听器
     * @param mButtonListener
     */
    public void setAnimationButtonListener(AnimationButtonListener mButtonListener){
        this.mButtonListener=mButtonListener;
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width=w;
        height=h;

        default_two_distance=(int) (width-height) /2;

        initOk();
        initAnimator();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        draw_circle(canvas);

        drawText(canvas);
        if (startDrawOk) {
            canvas.drawPath(path, okPaint);
        }
    }

        /**
         * 绘制文字
         *
         * @param canvas 画布
         */
        private void drawText(Canvas canvas) {
            textRect.left = 0;
            textRect.top = 0;
            textRect.right = (int) width;
            textRect.bottom = (int) height;
            Paint.FontMetricsInt fontMetrics = mTextPaint.getFontMetricsInt();

            int baseline = (textRect.bottom + textRect.top - fontMetrics.bottom - fontMetrics.top) / 2;
            //文字绘制到整个布局的中心位置
            canvas.drawText(buttonString, textRect.centerX(), baseline,mTextPaint);
    }

    /**
     * 初始化画笔
     */

    private void initPaint() {
        mRectF=new RectF();

        mPaint = new Paint();
        mPaint.setStrokeWidth(4);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
        mPaint.setColor(bg_color);

        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextSize(40);
        mTextPaint.setColor(Color.WHITE);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        mTextPaint.setAntiAlias(true);

        animationSet=new AnimatorSet();

        okPaint = new Paint();
        okPaint.setStrokeWidth(10);
        okPaint.setStyle(Paint.Style.STROKE);
        okPaint.setAntiAlias(true);
        okPaint.setColor(Color.WHITE);
    }


    /**
     * 画一个圆矩形
     * @param canvas
     */
    private void draw_circle(Canvas canvas){

        mRectF.top=0;

        mRectF.left=two_distance;

        mRectF.right=width-two_distance;

        mRectF.bottom=height;

        canvas.drawRoundRect(mRectF,circle_radius,circle_radius,mPaint);
    }



    /**
     * 绘制对勾
     */
    private void initOk() {
        //对勾的路径
        path.moveTo(default_two_distance + height / 8 * 3, height / 2);
        path.lineTo(default_two_distance + height / 2, height / 5 * 3);
        path.lineTo(default_two_distance + height / 3 * 2, height / 5 * 2);

        pathMeasure = new PathMeasure(path, true);

    }

    /**
     * 设置butoon动画 变成椭圆的动画
     */

    void setAnimation(){
        mcircleAnimator=ValueAnimator.ofInt(0, (int) (height/2));
        mcircleAnimator.setDuration(duration);
        mcircleAnimator.addUpdateListener(animation -> {
            circle_radius= (float) animation.getAnimatedValue();
            invalidate();
        });
    }

    /**
     * 椭圆往中间靠拢
     */
    void translation(){
        mtranslationX=ValueAnimator.ofInt(0, default_two_distance);
        mtranslationX.setDuration(duration);
        mtranslationX.addUpdateListener(animation -> {
            two_distance= (int) animation.getAnimatedValue();

            /**
             * 平移的同时并且让文字隐藏
             */
            int alpha=(1-(two_distance/default_two_distance))*255;
            mTextPaint.setAlpha(alpha);

            invalidate();
        });

    }

    /**
     * 网Y轴上平移
     */
    private void translationY(){

       float  currentY= this.getTranslationY();

        mtranslationY=ObjectAnimator.ofFloat(this,"translationY",currentY,currentY-moveInstance);
        mtranslationY.setDuration(duration);
        mtranslationY.setInterpolator(new AccelerateDecelerateInterpolator());

    }

    /**
     * 绘制对勾的动画
     */
    private void set_draw_ok_animation() {
        animator_draw_ok = ValueAnimator.ofFloat(1, 0);
        animator_draw_ok.setDuration(duration);
        animator_draw_ok.addUpdateListener(animation -> {
            startDrawOk = true;
            float value = (Float) animation.getAnimatedValue();

            effect = new DashPathEffect(new float[]{pathMeasure.getLength(), pathMeasure.getLength()}, value * pathMeasure.getLength());
            okPaint.setPathEffect(effect);


            invalidate();
        });
    }



     private interface  AnimationButtonListener{
         /**
          * 按钮点击事件
          */
         void onClickListener();

         /**
          * 动画完成回调
          */
         void animationFinish();
     }
}
