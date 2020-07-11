package com.example.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;


public class CustomView extends View {

    private CustomListener customListener;

    public interface onMeasureListener {
        void onSizeChanged(int width, int height);
    }
    private final int SIZE = 400;

    private Context context;
    private int centerX;
    private int centerY;
    private Paint paint = new Paint();
    private onMeasureListener listener;
    private Paint redPaint = new Paint();
    private Paint yellowPaint = new Paint();
    private Paint bluePaint = new Paint();
    private Paint greenPaint = new Paint();
    private Paint blackPaint = new Paint();
    private RectF oval = new RectF();
    private RectF smallOval = new RectF();

    public CustomView(Context context, CustomListener customListener) {
        super(context);
        this.context = context;
        yellowPaint.setColor(Color.YELLOW);
        blackPaint.setColor(Color.BLACK);
        bluePaint.setColor(Color.BLUE);
        redPaint.setColor(Color.RED);
        greenPaint.setColor(Color.GREEN);
        this.customListener = customListener;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);

        canvas.drawPaint(paint);
        oval.set(centerX-SIZE, centerY-SIZE, centerX+SIZE,
                centerY+SIZE);
        smallOval.set(centerX - 100,centerY - 100, centerX + 100,
                centerY + 100);

        drawFrom0to90(canvas, redPaint);
        canvas.drawArc(oval, 90F, 90F, true, yellowPaint);
        canvas.drawArc(oval, 180F, 90F, true, greenPaint);
        canvas.drawArc(oval, 270F, 90F, true, bluePaint);
        canvas.drawOval(smallOval, blackPaint);
    }

    public void setListener(onMeasureListener listener) {
        this.listener = listener;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        centerX = w / 2;
        centerY = h / 2;
        if (listener != null) {
            listener.onSizeChanged(w, h);
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        onPressed(x, y);
        return super.onTouchEvent(event);
    }
    private void onPressed(float x, float y) {
        customListener.getXY(x, y);
        if(x > centerX-SIZE && x < centerX - 100 && y > centerY - SIZE && y < centerY - 100){
            startTopPressed(x, y);
        } else if(x > centerX-SIZE && x < centerX -100 && y < centerY + SIZE && y > centerY + 100) {
            startBottomPressed(x, y);
        } else if(x < centerX + SIZE && x > centerX + 100 && y > centerY - SIZE && y < centerY - 100) {
            endTopPressed(x, y);
        } else if(x < centerX + SIZE && x > centerX + 100 && y > centerY  + 100 && y < centerY + SIZE) {
            endBottomPressed(x, y);
        } else if(x < centerX + 100 && x > centerX - 100 && y > centerY - 100 && y < centerY + 100) {
            onCenterOvalPressed(x, y);
        }
    }
    private void startTopPressed(float x, float y){
        Toast.makeText(context, "start top X = "+ x + " Y = " + y, Toast.LENGTH_SHORT).show();
        greenPaint.setColor(Color.BLACK);
       invalidate();
    }
    private void startBottomPressed(float x, float y) {
        Toast.makeText(context, "start bottom X = "+ x + " Y = " + y, Toast.LENGTH_SHORT).show();
        yellowPaint.setColor(Color.LTGRAY);
        invalidate();
    }
    private void endTopPressed(float x, float y) {
        Toast.makeText(context, "end top  X = "+ x + " Y = " + y, Toast.LENGTH_SHORT).show();
        bluePaint.setColor(Color.GRAY);
        invalidate();
    }

    private void endBottomPressed(float x, float y) {
        Toast.makeText(context, "end bottom  X = "+ x + " Y = " + y, Toast.LENGTH_SHORT).show();
        redPaint.setColor(Color.YELLOW);
        invalidate();
    }
    public void drawFrom0to90(Canvas canvas, Paint paint) {
        canvas.drawArc(oval, 0F, 90F, true, paint);
    }
    public void onCenterOvalPressed(float x, float y) {
        Toast.makeText(context, "Center oval pressed  X = " + " Y = "+ y, Toast.LENGTH_SHORT).show();
        yellowPaint.setColor(getResources().getColor(android.R.color.holo_green_light));
        greenPaint.setColor(getResources().getColor(android.R.color.holo_orange_light));
        redPaint.setColor((getResources().getColor(android.R.color.holo_purple)));
        bluePaint.setColor((getResources().getColor(android.R.color.background_dark)));
        invalidate();
    }
    public interface CustomListener{
        void getXY(float x, float y);
    }
}
