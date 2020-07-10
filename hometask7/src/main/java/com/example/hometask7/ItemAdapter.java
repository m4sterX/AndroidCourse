package com.example.hometask7;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hometask7.DAO.ContactDao;
import com.example.hometask7.data_base.MyDataBase;
import com.example.hometask7.entity.Contact;

import java.util.List;


public final class ItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private OnItemClickListener onItemClickListener;
    private Handler handler;


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
        holder.itemView.setOnClickListener(v -> onItemClickListener.onClick(position));
        handler = new Handler();

        Runnable runnable = () -> {
            MyDataBase db = SingletonDB.getInstance().getDatabase();
            ContactDao contactDao = db.contactDao();
            List<Contact> contacts = contactDao.getAll();
            Contact contact = contacts.get(position);

            TextView name = holder.itemView.findViewById(R.id.contactNameReady);
            TextView emailOrNumber = holder.itemView.findViewById(R.id.contactEmailOrPhoneReady);
            ImageView imgV = holder.itemView.findViewById(R.id.imgButtonFromItem);

            name.post(() -> name.setText(contact.getName()));
            emailOrNumber.post(() -> emailOrNumber.setText(contact.getEmailOrPhone()));
            imgV.post(() -> imgV.setImageResource(contact.getSrc()));
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    @Override
    public int getItemCount() {
       final int[] contactDaoV = new int[1];
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
        MyDataBase db = SingletonDB.getInstance().getDatabase();
        ContactDao contactDao = db.contactDao();
        contactDaoV[0] = contactDao.getId().size();
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();

        return contactDaoV[0];
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
