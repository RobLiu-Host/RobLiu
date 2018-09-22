package com.dbmovie.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class CustomViewPager extends ViewPager{
    private int xLastPoint;
    private int yLastPoint;

    public CustomViewPager(Context context) {
        super(context);
    }

    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent ev) {
////        return super.onInterceptTouchEvent(ev);
//        boolean isIntercept=false;
//        int xPoint= (int) ev.getX();
//        int yPoint= (int) ev.getY();
//        final int flag=ev.getAction()&MotionEvent.ACTION_MASK;
//        switch (flag){
//            case MotionEvent.ACTION_DOWN:
//                isIntercept=false;
//                super.onInterceptTouchEvent(ev);
//                break;
//            case MotionEvent.ACTION_MOVE:
//                int deltax=xLastPoint-xPoint;
//                int deltay=yLastPoint-yPoint;
//
//                if(Math.abs(deltax)>Math.abs(deltay)){
//                    isIntercept=true;
//                }else {
//                    isIntercept=false;
//                }
//                break;
//            case MotionEvent.ACTION_UP:
//                isIntercept=false;
//                break;
//
//        }
//        xLastPoint=xPoint;
//        yLastPoint=yPoint;
//        return isIntercept;
//    }
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        final int flag=ev.getAction()&MotionEvent.ACTION_MASK;
        if (flag==MotionEvent.ACTION_DOWN){
                super.onInterceptTouchEvent(ev);
                return false;
        }
        return true;
    }
}
