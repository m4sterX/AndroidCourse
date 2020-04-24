package com.example.myapplication2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication2.dao.ContactDao;
import com.example.myapplication2.data_base.MyDataBase;
import com.example.myapplication2.entity.Contact;

import java.util.List;

public final class ItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private OnItemClickListener onItemClickListener;
    private RecyclerView.ViewHolder holder;
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecyclerView.ViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false)) {
        };
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        holder.itemView.setOnClickListener((View.OnClickListener) v -> {
            onItemClickListener.onClick(position);
        });

        MyDataBase db = SingletonDB.getInstance().getDatabase();
        ContactDao contactDao = db.contactDao();
        List<Contact> contacts = contactDao.getAll();
        Contact contact = contacts.get(position);

        TextView name = holder.itemView.findViewById(R.id.contactNameReady);
        TextView emailOrNumber = holder.itemView.findViewById(R.id.contactEmailOrPhoneReady);
        ImageView imgV = holder.itemView.findViewById(R.id.imgButtonFromItem);

        name.setText(contact.getName());
        emailOrNumber.setText(contact.getEmailOrPhone());
        imgV.setImageResource(contact.getSrc());
    }

    @Override
    public int getItemCount() {
        MyDataBase db = SingletonDB.getInstance().getDatabase();
        ContactDao contactDao = db.contactDao();
        return contactDao.getId().size();
    }

    public interface OnItemClickListener {
        void onClick(int position);
    }
    public void deleteItem(Contact contact, ContactDao contactDao, int position, int contactsSize) {
        contactDao.delete(contact);
        notifyItemRemoved(position);
        notifyItemRangeRemoved(position, contactsSize);
    }

}
