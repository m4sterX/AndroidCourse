package com.example.ht4


import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.edit_contact.*
import kotlinx.android.synthetic.main.registration_user.*

class EditContact : AppCompatActivity(), View.OnClickListener{
    private var item: Item? = null
    var src = R.drawable.ic_contact_phone_blue_30dp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_contact)
        setSupportActionBar(toolBarEdit)

        val index = intent.getIntExtra("index", 0)
        val item: Item = Store.instance.get(index)
        contactNameEdit.setText(item.name)
        contactEmailOrPhoneEdit.setText(item.email)
        phoneNumberRadioEdit.setOnClickListener(this)
        emailRadioEdit.setOnClickListener(this)
        doneButtonEdit.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.doneButtonEdit -> {
                item?.name = contactNameEdit.toString()
                item?.email = contactEmailOrPhoneEdit.toString()
                item?.src = src
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
            R.id.emailRadioEdit -> {
                contactEmailOrPhoneEdit.hint = "Email"
                src = R.drawable.ic_contact_mail__30dp
            }
            R.id.phoneNumberRadioEdit -> {
                contactEmailOrPhoneEdit.hint = "Phone number"
                src = R.drawable.ic_contact_phone_blue_30dp
            }
        }
    }
}