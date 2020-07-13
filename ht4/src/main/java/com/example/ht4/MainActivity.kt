package com.example.ht4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener{

   private val adapter = ItemAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolBar)
        if(Store.instance.size() > 0) {
             noContactsTV.text =""
        }
        FloatingActionButton.setOnClickListener(this)
        RecyclerView.setHasFixedSize(true)
        RecyclerView.adapter = this.adapter
        RecyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun onClick(v: View?) {
        startActivity(Intent(this, RegistrationUserInfo::class.java))
    }
}
