package com.example.myapplication2;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class RegistrationUserInfo extends AppCompatActivity implements View.OnClickListener {

    int src = R.drawable.ic_contact_phone_blue_30dp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_user);
        setSupportActionBar((Toolbar) findViewById(R.id.toolBarRegistration));

        final ImageButton done = findViewById(R.id.doneButton);

        done.setOnClickListener(this);
        RadioButton radioName = findViewById(R.id.phoneNumberRadio);
        RadioButton radioEmail = findViewById(R.id.emailRadio);
        radioName.setOnClickListener(this);
        radioEmail.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.doneButton:
                EditText name = findViewById(R.id.contactName);
                EditText emailOrPhone = findViewById(R.id.contactEmailOrPhone);
                Store.getStore().add(new Item(name.getText().toString(), emailOrPhone.getText().toString(), src));
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;

            case R.id.phoneNumberRadio:
                EditText email = findViewById(R.id.contactEmailOrPhone);
                email.setHint("Phone number");
                src = R.drawable.ic_contact_phone_blue_30dp;
                break;
            case R.id.emailRadio:
                EditText email2 = findViewById(R.id.contactEmailOrPhone);
                email2.setHint("Email");
                src = R.drawable.ic_contact_mail__30dp;
                break;
        }

    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}

