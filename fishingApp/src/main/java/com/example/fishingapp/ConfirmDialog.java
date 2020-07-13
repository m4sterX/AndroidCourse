package com.example.fishingapp;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.fishingapp.activity.MainActivity;

public class ConfirmDialog extends AppCompatDialogFragment {
    private ConfirmListener confirmListener;
    private String[] strings;

    public ConfirmDialog(String[] strings) {
        this.strings = strings;
    }


    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.confirm_order, null);
        TextView textView = view.findViewById(R.id.info);
        String housePrice = "0p";
        String equipPrice = "0p";
        String name = strings[0];
        String h_count = strings[1];
        String house = strings[2];
        String equip = strings[3];
        if (house.equals("Да")){
            housePrice = "20р";
        }
        if (equip.equals("Да")){
            equipPrice = "10р";
        }
        String format = "Озеро: %s\nЧасы аренды: %sp\nБеседка: %s - %s\nСнасти : %s - %s";
        String result = String.format(format, name, h_count, house, housePrice, equip, equipPrice);
        textView.setText(result);
        dialog.setView(view)
                .setTitle("Подтверждение оплаты")
                .setNegativeButton("Отмена", (dialog1, which) -> {
        })
                .setPositiveButton("Подтвердить", (dialog12, which) -> {
                    Intent intent = new Intent(getContext(), MainActivity.class);
                    startActivity(intent);
                    confirmListener.confirmOrder(strings);
                });
        return dialog.create();
    }
    public interface ConfirmListener{
        void confirmOrder(String[] strings);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            confirmListener = (ConfirmListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement ConfirmListener");
        }
    }
}
