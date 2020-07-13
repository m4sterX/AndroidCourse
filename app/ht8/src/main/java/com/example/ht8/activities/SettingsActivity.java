package com.example.ht8.activities;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import com.example.ht8.R;

public class SettingsActivity extends AppCompatActivity {
    private SwitchCompat switchCompat;
    private SharedPreferences sharedPreferences;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setSupportActionBar(findViewById(R.id.settingsToolbar));

        SwitchCompat switchCompat = findViewById(R.id.switchTemperature);


        sharedPreferences = getSharedPreferences("Previous_state", MODE_PRIVATE);
        if (sharedPreferences.getBoolean("isChecked",false)){
            switchCompat.setChecked(true);
            switchCompat.setText("Fahrenheit");
        }
        this.switchCompat = switchCompat;
        switchCompat.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                switchCompat.setText("Fahrenheit");
                SharedPreferences sharedPreferences = getSharedPreferences("Settings",  MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("isCelsius", false);
                editor.apply();
            } else {
                switchCompat.setText("Celsius");
                SharedPreferences sharedPreferences = getSharedPreferences("Settings",  MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("isCelsius", true);
                editor.apply();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences sharedPreferences = getSharedPreferences("Previous_state", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        boolean answer;
        answer = switchCompat.isChecked();
        editor.putBoolean("isChecked", answer).apply();
        finish();
    }
}
