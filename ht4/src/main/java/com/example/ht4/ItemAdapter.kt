package com.example.ht4

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item.view.*

class ItemAdapter(private var context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            return object : RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false))
            {}
        }

        override fun getItemCount(): Int {
            return Store.instance.size()
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, index: Int) {
            holder.itemView.setOnClickListener{v: View? ->
                val intent = Intent(context, EditContact::class.java)
                intent.putExtra("index", index)
                context.startActivity(intent)
            }
            val item = Store.instance.get(index)
            holder.itemView.imgButtonFromItem.setImageResource(item.src)
            holder.itemView.contactNameReady.text = item.name
            holder.itemView.contactEmailOrPhoneReady.text = item.email
        }
    }