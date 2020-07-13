package com.example.fishingapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.fishingapp.R;
import com.example.fishingapp.fragment.OrderFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class LakeActivity extends AppCompatActivity {

    private FloatingActionButton makeOrderButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lake_description_layout);
        makeOrderButton = findViewById(R.id.floatingActionButton_setOrder);
        Intent intent = getIntent();
        double lat = intent.getDoubleExtra("lat", 0);
        double lon = intent.getDoubleExtra("lon", 0);
        int photo = intent.getIntExtra("photoFile", 0);
        String name = intent.getStringExtra("name");
        int description = intent.getIntExtra("description", 0);

        makeOrderButton.setOnClickListener(v ->
        {
           Intent intent1 = new Intent(this, OrderActivity.class);
           intent1.putExtra("name", name);
           startActivity(intent1);
        });


        ImageView imageView = findViewById(R.id.imageView_lakeDescription);
        imageView.setImageResource(photo);

        TextView lakeName = findViewById(R.id.title_lakeDescription);
        lakeName.setText(name);

        TextView lakeDescription = findViewById(R.id.textView_lakeDescription_main);
        lakeDescription.setText(description);

        ImageButton showMap = findViewById(R.id.imageButton_showMap);
        showMap.setOnClickListener(v -> {
            Intent intent1 = new Intent(getApplicationContext(), MapActivity.class);
            intent1.putExtra("lat", lat);
            intent1.putExtra("lon", lon);
            startActivity(intent1);
        });
    }
}
