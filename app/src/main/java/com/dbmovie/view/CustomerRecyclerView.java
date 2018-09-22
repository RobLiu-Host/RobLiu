package com.dbmovie.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.util.supportlibrary.LinearRecyclerView;

public class CustomerRecyclerView extends LinearRecyclerView {

    private int xLastPoint;
    private int yLastPoint;
    public CustomerRecyclerView(Context context) {
        super(context);
    }

    public CustomerRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomerRecyclerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
//        return super.dispatchTouchEvent(ev);
        int xPoint= (int) ev.getX();
        int yPoint= (int) ev.getY();
        final int flag=ev.getAction()&MotionEvent.ACTION_MASK;
        switch (flag){
            case MotionEvent.ACTION_DOWN:
                getParent().requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_MOVE:
                int deltax=xLastPoint-xPoint;
                int deltay=yLastPoint-yPoint;

                if(Math.abs(deltax)>Math.abs(deltay)){
                   getParent().requestDisallowInterceptTouchEvent(false);
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        xLastPoint=xPoint;
        yLastPoint=yPoint;
        return super.dispatchTouchEvent(ev);
    }
}
