package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        CustomView customview = new CustomView(getApplicationContext(), new CustomView.CustomListener() {
            @Override
            public void getXY(float x, float y) {
                Toast.makeText(getApplicationContext(), "X = " + x + " Y = " + y, Toast.LENGTH_SHORT).show();
            }
        });

        setContentView(customview);

    }
}
