package com.example.hometask7;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hometask7.DAO.ContactDao;
import com.example.hometask7.data_base.MyDataBase;
import com.example.hometask7.entity.Contact;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private List<Contact> contactsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar(findViewById(R.id.toolBar));

        ItemAdapter adapter = new ItemAdapter();
        adapter.setOnItemClickListener(position -> {
            Intent intent = new Intent(this, EditContact.class);
            intent.putExtra("position", position);
            startActivity(intent);
        });
        Runnable runnable = () -> {
            MyDataBase db = SingletonDB.getInstance().getDatabase();
            ContactDao contactDao = db.contactDao();
            contactsList = contactDao.getAll();
            if (contactDao.getId().size() > 0) {
                TextView tv = findViewById(R.id.noContactsTV);
                tv.setText("");
        }
    };
        Thread thread = new Thread(runnable);
        thread.start();

        FloatingActionButton floatingActionButton = findViewById(R.id.FloatingActionButton);
        floatingActionButton.setOnClickListener(this);

        RecyclerView recyclerView = findViewById(R.id.RecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        if (contactsList != null) {
            adapter.setContactList(contactsList);
            adapter.notifyDataSetChanged();
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, RegistrationUserInfoActivity.class);
        startActivity(intent);
        }
    }


