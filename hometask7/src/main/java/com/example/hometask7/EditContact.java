package com.example.hometask7;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hometask7.DAO.ContactDao;
import com.example.hometask7.data_base.MyDataBase;
import com.example.hometask7.entity.Contact;

import java.util.List;


public class EditContact extends AppCompatActivity implements View.OnClickListener {
    private ContactDao contactDaoP;
    private Contact contactP;
    private int position;
    private int contactsSize;
    private int src = R.drawable.ic_contact_phone_blue_30dp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_contact);

        int index = getIntent().getIntExtra("position", 0);
        this.position = index;

        EditText contNameEdit = findViewById(R.id.contactNameEdit);
        EditText contEmailEdit = findViewById(R.id.contactEmailOrPhoneEdit);
        RadioButton rb1 = findViewById(R.id.phoneNumberRadioEdit);
        RadioButton rb2 = findViewById(R.id.emailRadioEdit);
        ImageButton deleteBTN = findViewById(R.id.deleteContact);

        deleteBTN.setOnClickListener(this);
        rb1.setOnClickListener(this);
        rb2.setOnClickListener(this);
        Runnable runnable = () -> {
            MyDataBase db = SingletonDB.getInstance().getDatabase();
            ContactDao contactDao = db.contactDao();
            List<Contact> contacts = contactDao.getAll();
            contactsSize = contacts.size();
            Contact contact = contacts.get(index);
            contactP = contact;
            contactDaoP = contactDao;
            contNameEdit.post(() -> contNameEdit.setText(contact.getName()));
                contEmailEdit.post(new Runnable() {
                    @Override
                    public void run() {
                        contEmailEdit.setText(contact.getEmailOrPhone());
                    }
                });
            };
        Thread thread = new Thread(runnable);
        thread.start();
        ImageButton doneButton = findViewById(R.id.doneButtonEdit);
        doneButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.doneButtonEdit:

                EditText contNameEdit = findViewById((R.id.contactNameEdit));
                EditText contEmailEdit = findViewById(R.id.contactEmailOrPhoneEdit);
                contactP.setName(contNameEdit.getText().toString());
                contactP.setEmailOrPhone(contEmailEdit.getText().toString());
                contactP.setSrc(src);

                contactDaoP.update(contactP);
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
            case R.id.deleteContact:
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
                dialogBuilder.setPositiveButton("Yes", (dialog, which) -> {
                    ItemAdapter adapter = new ItemAdapter();
                    adapter.deleteItem(contactP, contactDaoP, position, contactsSize);
                    startActivity(new Intent(EditContact.this, MainActivity.class));
                    finish();
                });
                dialogBuilder.setNegativeButton("Cancel", (dialog, which) -> finish());
                AlertDialog alertDialog = dialogBuilder.create();
                alertDialog.show();
                break;
        }
    }
}
