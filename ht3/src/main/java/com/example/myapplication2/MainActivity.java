package com.example.myapplication2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private final RecyclerView.Adapter adapter = new ItemAdapter(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar((Toolbar) findViewById(R.id.toolBar));

        if (Store.getStore().size() > 0) {
            TextView tv = findViewById(R.id.noContactsTV);
            tv.setText("");
        }
        FloatingActionButton floatingActionButton = findViewById(R.id.FloatingActionButton);
        floatingActionButton.setOnClickListener(this);

        RecyclerView recyclerView = findViewById(R.id.RecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, RegistrationUserInfo.class);
        startActivity(intent);
        }
    }


