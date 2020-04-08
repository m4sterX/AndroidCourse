package com.example.myapplication2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

public class EditContact extends AppCompatActivity implements View.OnClickListener {
    private Item item;
    int src = R.drawable.ic_contact_phone_blue_30dp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_contact);

        int index = getIntent().getIntExtra("index", 0);
        Item item = Store.getStore().get(index);
        this.item = item;
        EditText contNameEdit = findViewById(R.id.contactNameEdit);
        EditText contEmailEdit = findViewById(R.id.contactEmailOrPhoneEdit);
        RadioButton rb1 = findViewById(R.id.phoneNumberRadioEdit);
        RadioButton rb2 = findViewById(R.id.emailRadioEdit);

        rb1.setOnClickListener(this);
        rb2.setOnClickListener(this);
        contNameEdit.setText(item.getName());
        contEmailEdit.setText(item.getEmail());

        ImageButton doneButton = findViewById(R.id.doneButtonEdit);
        doneButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.doneButtonEdit:
                EditText contNameEdit = findViewById((R.id.contactNameEdit));
                EditText contEmailEdit = findViewById(R.id.contactEmailOrPhoneEdit);
                item.setName(contNameEdit.getText().toString());
                item.setEmail(contEmailEdit.getText().toString());
                item.setSetSRC(src);
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.phoneNumberRadioEdit:
                EditText email2 = findViewById(R.id.contactEmailOrPhoneEdit);
                email2.setHint("Phone number");
                src = R.drawable.ic_contact_phone_blue_30dp;
                break;
            case R.id.emailRadioEdit:
                EditText email = findViewById(R.id.contactEmailOrPhoneEdit);
                email.setHint("Email");
                src = R.drawable.ic_contact_mail__30dp;
                break;
        }
    }
}
