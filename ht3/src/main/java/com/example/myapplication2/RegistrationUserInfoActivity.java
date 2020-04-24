package com.example.myapplication2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication2.dao.ContactDao;
import com.example.myapplication2.data_base.MyDataBase;
import com.example.myapplication2.entity.Contact;

public class RegistrationUserInfoActivity extends AppCompatActivity implements View.OnClickListener {

    private int src = R.drawable.ic_contact_phone_blue_30dp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_user);
        setSupportActionBar(findViewById(R.id.toolBarRegistration));

        final ImageButton done = findViewById(R.id.doneButton);
        RadioButton radioName = findViewById(R.id.phoneNumberRadio);
        RadioButton radioEmail = findViewById(R.id.emailRadio);

        done.setOnClickListener(this);
        radioName.setOnClickListener(this);
        radioEmail.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.doneButton:
                EditText name = findViewById(R.id.contactName);
                EditText emailOrPhone = findViewById(R.id.contactEmailOrPhone);

                MyDataBase db = SingletonDB.getInstance().getDatabase();
                ContactDao contactDao = db.contactDao();
                Contact contact = new Contact(name.getText().toString(), emailOrPhone.getText().toString(),src);
                contactDao.insert(contact);
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();

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

}

