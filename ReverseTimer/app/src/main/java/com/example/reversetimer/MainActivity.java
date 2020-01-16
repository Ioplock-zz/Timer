package com.example.reversetimer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText ed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ed = findViewById(R.id.ed);
    }

    public void onClibck(View v) {
        Intent start = new Intent(getApplicationContext(), Timer.class);
        try {
            start.putExtra("milisecs", ed.getText().toString().equals("") ? 10 : Float.parseFloat(ed.getText().toString()));
        } catch (Exception e) {
            Log.e("mytag", "Enter a secs");
        }
        startActivity(start);
    }
}
