package com.example.ht4


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.registration_user.*


class RegistrationUserInfo : AppCompatActivity(), View.OnClickListener {

   var src = R.drawable.ic_contact_phone_blue_30dp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registration_user)
        setSupportActionBar(toolBarRegistration)

        doneButton.setOnClickListener(this)
        phoneNumberRadio.setOnClickListener(this)
        emailRadio.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.doneButton -> {
                (Store.instance.add(Item(contactName.text.toString(), contactEmailOrPhone.text.toString(), src)))
                 val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
            R.id.phoneNumberRadio ->{
                contactEmailOrPhone.hint = "Phone number"
            src = R.drawable.ic_contact_phone_blue_30dp
            }
            R.id.emailRadio ->{
                contactEmailOrPhone.hint = "Email"
                src = R.drawable.ic_contact_mail__30dp
                }
            }
        }
    }
