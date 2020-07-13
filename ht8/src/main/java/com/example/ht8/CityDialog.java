package com.example.ht8;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class CityDialog extends AppCompatDialogFragment {

    private EditText newCity;
    private CityDialogListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.cities_dialog, null);
        dialog.setView(view)
                .setTitle("Enter new city")
                .setNegativeButton("cancel", (dialog1, which) -> {

                })
                .setPositiveButton("add", (dialog12, which) -> {
                    String cityName = newCity.getText().toString();
                    listener.applyCity(cityName);
                });
        newCity = view.findViewById(R.id.edit_new_city);
        return dialog.create();
    }
    public interface CityDialogListener{
        void applyCity(String cityName);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (CityDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement CityDialogListener");
        }
    }
}
