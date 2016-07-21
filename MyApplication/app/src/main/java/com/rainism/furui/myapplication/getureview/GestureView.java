package com.rainism.furui.myapplication.getureview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.rainism.furui.myapplication.R;

import java.util.ArrayList;

/**
 * Created by furui on 16/7/20.
 */

public class GestureView extends View {
    ArrayList<GesturePointer> pointerArrayList;//所有点的集合
    ArrayList<GesturePointer> selectPointerList = new ArrayList<GesturePointer>();//选中点的集合
    ArrayList<GesturePointer> linePoinerList = new ArrayList<GesturePointer>();//线经过的点的集合
    int lineWith = 5;//线的粗度

    int radius;//圆的半径
    int padding;//圆的宽度
    boolean uncheckPoiner=true;//当处于未点击任何点的时候重置视图

    GesturePointer startPointer = new GesturePointer();//起点
    GesturePointer middlePointer = new GesturePointer();//中间点


    public GestureView(Context context) {
        super(context);
    }

    public GestureView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GestureView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public GestureView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        initData(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(getResources().getColor(R.color.colorPrimary));
        drawCircle(canvas);
        drawLine(canvas);
    }

    /**
     * 初始化数据
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    public void initData(int widthMeasureSpec, int heightMeasureSpec) {
        pointerArrayList = new ArrayList<GesturePointer>();
        padding = 30;//距离
        radius = (getWidth() - padding * 4) / 6;//半径
        for (int i = 0; i < 9; i++) {
            GesturePointer gesturePointer = new GesturePointer();
            gesturePointer.setX(padding + radius + (i / 3) * (padding + radius + radius));
            Log.i("esturePointer.getX", "" + gesturePointer.getX());
            gesturePointer.setY(padding + radius + (i % 3) * (padding + radius + radius));
            Log.i("esturePointer.getY", "" + gesturePointer.getY());
            pointerArrayList.add(gesturePointer);
        }
    }
    //画圆
    public void drawCircle(Canvas canvas) {
        Paint defaultPaint = new Paint();
        defaultPaint.setAntiAlias(true);
        defaultPaint.setColor(getResources().getColor(R.color.colorAccent));
        Paint selectPaint = new Paint();
        selectPaint.setAntiAlias(true);
        selectPaint.setColor(getResources().getColor(R.color.colorPrimaryDark));
        for (GesturePointer pointer : pointerArrayList) {
            if (pointer.isChecked()) {
                canvas.drawCircle(pointer.getX(), pointer.getY(), radius, selectPaint);
            } else {
                canvas.drawCircle(pointer.getX(), pointer.getY(), radius, defaultPaint);
            }
        }
    }
    //画所有点的线以及移动中的线
    public void drawLine(Canvas canvas) {
        Paint defaultPaint = new Paint();
        defaultPaint.setAntiAlias(true);
        defaultPaint.setStrokeWidth(lineWith);
        defaultPaint.setColor(getResources().getColor(R.color.colorGreen));
        if (linePoinerList.size() >= 2) {
            for (int i = 0; i < linePoinerList.size(); i++) {
                if (i + 2 <= linePoinerList.size()) {
                    GesturePointer gesturePointer1 = linePoinerList.get(i);
                    GesturePointer gesturePointer2 = linePoinerList.get(i + 1);
                    canvas.drawLine(gesturePointer1.getX(), gesturePointer1.getY(),
                            gesturePointer2.getX(), gesturePointer2.getY(), defaultPaint);
                }

            }
        }

        if (middlePointer != null && startPointer!=null) {
            canvas.drawLine(startPointer.getX(), startPointer.getY(),
                    middlePointer.getX(), middlePointer.getY(), defaultPaint);
        }


    }
//选中按钮
    public void selectPointer(MotionEvent event) {

        for (GesturePointer pointer : pointerArrayList) {
            double width = Math.pow(Math.abs(event.getX() - pointer.getX()), 2);
            double height = Math.pow(Math.abs(event.getY() - pointer.getY()), 2);
            if (Math.sqrt(width + height) <= radius) {
                pointer.setChecked(true);
                startPointer = pointer;
                selectPointerList.add(pointer);
                linePoinerList.add(pointer);
                uncheckPoiner=false;
            }
        }
        if (uncheckPoiner){
            for (GesturePointer pointer : pointerArrayList) {
                pointer.setChecked(false);
            }
            selectPointerList.clear();
            linePoinerList.clear();
        }

        invalidate();
    }
    String str;
    //选中点经过的线
    public void selectLine(MotionEvent event) {
        if (middlePointer == null){
            middlePointer=new GesturePointer();
        }
        middlePointer.setX((int) event.getX());
        middlePointer.setY((int) event.getY());
        for (GesturePointer pointer : pointerArrayList) {
            double width = Math.pow(Math.abs(event.getX() - pointer.getX()), 2);
            double height = Math.pow(Math.abs(event.getY() - pointer.getY()), 2);
            if (Math.sqrt(width + height) <= radius) {
                pointer.setChecked(true);
                selectPointerList.add(pointer);
                if (!pointer.equals(startPointer)) {
                    if(!linePoinerList.contains(pointer)){
                        linePoinerList.add(pointer);
                        startPointer = pointer;
                    }
                }
            }
        }

        invalidate();
    }
    //结束绘制
    public void upPointer(MotionEvent event) {
        middlePointer = null;
        uncheckPoiner=true;
        invalidate();
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                selectPointer(event);
                break;
            case MotionEvent.ACTION_MOVE:
                selectLine(event);
                break;
            case MotionEvent.ACTION_UP:
                upPointer(event);
                break;

        }
        return true;
    }
}
