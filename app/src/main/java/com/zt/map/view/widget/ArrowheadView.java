package com.zt.map.view.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.zt.map.R;

//移动的箭头指向View
public class ArrowheadView extends View implements View.OnTouchListener {
    private Paint arrowheadPaint;//箭头画笔

    public void setStart(float start_X,float start_Y) {
        this.start_X = start_X;
        this.start_Y = start_Y;
    }


    private float start_X;
    private float start_Y;
    private float end_X;
    private float end_Y;
    private Canvas canvas;
    private onMovenMarker onMovenMarker;

    public ArrowheadView(Context context) {
        this(context, null);
    }

    public ArrowheadView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ArrowheadView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        arrowheadPaint = new Paint();
        arrowheadPaint.setColor(ContextCompat.getColor(getContext(), R.color.line_0091ed));
        arrowheadPaint.setStrokeWidth(2F);
//        cos30 = Math.sin(30);
        setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE: {
                // TODO: 2019/11/22 开始流程  因为起始点需外部传入   故本判断体不再检测按下
                end_X = event.getX();
                end_Y = event.getY();
                invalidate();
                break;
            }
            case MotionEvent.ACTION_UP: {
                // TODO: 2019/11/22 结束流程
                if (onMovenMarker!=null){
                    onMovenMarker.onSumbit(event.getX(),event.getY());
                    end_X = 0;
                    end_Y = 0;
                    start_X = 0;
                    start_Y = 0;
                    invalidate();
                }
                break;
            }
            default:
                break;
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.canvas = canvas;
        if (end_X==0){
            end_X = start_X;
        }
        if (end_Y==0){
            end_Y = start_Y;
        }
        drawAL(start_X,start_Y,end_X,end_Y,canvas);
    }

    public void setOnMovenMarker(ArrowheadView.onMovenMarker onMovenMarker) {
        this.onMovenMarker = onMovenMarker;
    }

    public void drawAL(float sx, float sy, float ex, float ey,Canvas myCanvas)
    {
        double H = 8; // 箭头高度
        double L = 3.5; // 底边的一半
        int x3 = 0;
        int y3 = 0;
        int x4 = 0;
        int y4 = 0;
        double awrad = Math.atan(L / H); // 箭头角度
        double arraow_len = Math.sqrt(L * L + H * H); // 箭头的长度
        double[] arrXY_1 = rotateVec((int) (ex - sx), (int)(ey - sy), awrad, true, arraow_len);
        double[] arrXY_2 = rotateVec((int) (ex - sx), (int) (ey - sy), -awrad, true, arraow_len);
        double x_3 = ex - arrXY_1[0]; // (x3,y3)是第一端点
        double y_3 = ey - arrXY_1[1];
        double x_4 = ex - arrXY_2[0]; // (x4,y4)是第二端点
        double y_4 = ey - arrXY_2[1];
        Double X3 = new Double(x_3);
        x3 = X3.intValue();
        Double Y3 = new Double(y_3);
        y3 = Y3.intValue();
        Double X4 = new Double(x_4);
        x4 = X4.intValue();
        Double Y4 = new Double(y_4);
        y4 = Y4.intValue();
        // 画线
        myCanvas.drawLine(sx, sy, ex, ey,arrowheadPaint);
        Path triangle = new Path();
        triangle.moveTo(ex, ey);
        triangle.lineTo(x3, y3);
        triangle.lineTo(x4, y4);
        triangle.close();
        myCanvas.drawPath(triangle,arrowheadPaint);
    }
    // 计算
    public double[] rotateVec(int px, int py, double ang, boolean isChLen, double newLen)
    {
        double mathstr[] = new double[2];
        // 矢量旋转函数，参数含义分别是x分量、y分量、旋转角、是否改变长度、新长度
        double vx = px * Math.cos(ang) - py * Math.sin(ang);
        double vy = px * Math.sin(ang) + py * Math.cos(ang);
        if (isChLen) {
            double d = Math.sqrt(vx * vx + vy * vy);
            vx = vx / d * newLen;
            vy = vy / d * newLen;
            mathstr[0] = vx;
            mathstr[1] = vy;
        }
        return mathstr;
    }

    public interface onMovenMarker{
        void onSumbit(float x,float y);//提交
        void onCancel();//取消
    }

}


