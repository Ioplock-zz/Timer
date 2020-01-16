package com.example.reversetimer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class TimerView extends View {

    private int Weight, Height;
    boolean isTextVisible = true;
    Paint White_Stroke, Black_Fill, Black_Stroke, White_Transpered;

    public TimerView(Context context) {
        super(context);
    }

    public TimerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    private void InitStuff() {
        White_Stroke = new Paint();
        White_Transpered = new Paint();
        Black_Fill = new Paint();
        Black_Stroke = new Paint();

        White_Stroke.setAntiAlias(true);
        White_Transpered.setAntiAlias(true);
        Black_Fill.setAntiAlias(true);
        Black_Stroke.setAntiAlias(true);

        White_Stroke.setColor(Color.WHITE);
        White_Stroke.setStyle(Paint.Style.STROKE);
        White_Stroke.setStrokeWidth(15);

        Black_Fill.setColor(Color.BLACK);
        Black_Fill.setTextSize(500);
        Black_Fill.setTextAlign(Paint.Align.CENTER);

        Black_Stroke.setColor(Color.BLACK);
        Black_Stroke.setStyle(Paint.Style.STROKE);
        Black_Stroke.setStrokeWidth(7);

        White_Transpered.setColor(Color.argb(60, 255, 255, 255));
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        Weight = w;
        Height = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        InitStuff();
        InitBackGround(canvas);
        RectF circle =new RectF((float) (Weight/2 - Weight/2/1.2), (float) (Height/2 - Weight/2/1.2), (float)(Weight/2 + Weight/2/1.2), (float)(Height/2 + Weight/2/1.2));
        float angel = (Timer.s - Timer.milisecs) * (360 / Timer.s);
        canvas.drawArc(circle,-90,Timer.s == Timer.milisecs ? 0 : angel,true,White_Transpered);
        canvas.drawArc(circle,-90,Timer.s == Timer.milisecs ? 0 : angel,true,Black_Stroke);
        String str = String.valueOf(Timer.milisecs).substring(0,String.valueOf(Timer.milisecs).length()- 2);
        if(isTextVisible) canvas.drawText(str, Timer.milisecs == 4 ? Weight/2 - 30 : Weight/2 - 15, Height/2 + 180, Black_Fill);
        Log.d("magag", "angel: " + angel + Timer.milisecs + " " + Timer.s);
    }

    private void InitBackGround(Canvas canvas) {
        canvas.drawColor(Color.parseColor("#2B2B2B"));
        canvas.drawCircle(Weight/2, Height/2, (float) (Weight/2/1.2), White_Stroke);
        canvas.drawCircle(Weight/2, Height/2, (float) (Weight/2/1.3), White_Stroke);
        canvas.drawLine(Weight/2, 0, Weight/2, Height, Black_Stroke);
        canvas.drawLine(0, Height/2, Weight, Height/2, Black_Stroke);
    }
}
