package com.example.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;
import android.widget.Toast;


public class CustomView extends View implements View.OnClickListener {
    @Override
    public void onClick(View v) {
        Toast.makeText(getContext(), "Mock", Toast.LENGTH_SHORT).show();
    }
    public interface onMeasureListener {
        void onSizeChanged(int width, int height);
    }
    RectF oval = new RectF();
    private int centerX;
    private int centerY;
    private final int SIZE = 400;
    private Paint paint = new Paint();
    private onMeasureListener listener;
    private Paint redPaint = new Paint();
    private Paint yellowPaint = new Paint();
    private Paint bluePaint = new Paint();
    private Paint greenPaint = new Paint();
    public CustomView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        canvas.drawPaint(paint);
        redPaint.setColor(Color.RED);
        yellowPaint.setColor(Color.YELLOW);
        greenPaint.setColor(Color.GREEN);
        bluePaint.setColor(Color.BLUE);
        oval.set(centerX-SIZE, centerY-SIZE, centerX+SIZE,
                centerY+SIZE);
        canvas.drawArc(oval, 0F, 90F, true, redPaint);
        canvas.drawArc(oval, 90F, 90F, true, yellowPaint);
        canvas.drawArc(oval, 180F, 90F, true, greenPaint);
        canvas.drawArc(oval, 270F, 90F, true, bluePaint);
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
}
