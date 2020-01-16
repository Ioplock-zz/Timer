package com.example.reversetimer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class Timer extends AppCompatActivity {

    static public float s;
    timer t = new timer();
    static public float milisecs;
    TimerView tv;

    class timer extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            tv.invalidate();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            while (milisecs > 0 & !isCancelled()) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                publishProgress();
                milisecs-=1;
                //float ang = (Timer.s - Timer.milisecs) * (360f / Timer.s);
                //Log.d("mygag", ang + " " + Timer.milisecs + " " + Timer.s);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            if(!isCancelled()) System.exit(0);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        tv = findViewById(R.id.tv);
        Intent end = getIntent();
        milisecs = end.getExtras().getFloat(("milisecs"));
        s = milisecs;
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t.execute();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        t.cancel(true);
    }

    public void onClick(View v) {
        tv.isTextVisible = !tv.isTextVisible;
        tv.invalidate();
    }
}
