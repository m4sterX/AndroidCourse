package com.example.fishingapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.fishingapp.ConfirmDialog;
import com.example.fishingapp.R;


public class OrderFragment extends Fragment {

    private TextView title;
    private EditText editText_addHoursCount;
    private ImageButton continueImgButton;
    private CheckBox check_adding_house;
    private CheckBox check_adding_equipment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.order_fragment_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        continueImgButton = view.findViewById(R.id.continueImageButton);
        editText_addHoursCount = view.findViewById(R.id.ET_addHoursCount);
        check_adding_house = view.findViewById(R.id.check_adding_house);
        title = view.findViewById(R.id.LakeInformation);
        check_adding_equipment = view.findViewById(R.id.check_adding_equipment);

        String temp = "Аренда озера: %S";
        Bundle bundle = getArguments();
        String name = bundle.getString("name");
        String formatted = String.format(temp,name);

        title.setText(formatted);
        continueImgButton.setOnClickListener(v -> {
            String[] strings = new String[]{
                    name,
                    editText_addHoursCount.getText().toString(),
                    answer(check_adding_house.isChecked()),
                    answer(check_adding_equipment.isChecked()),
            };
            ConfirmDialog dialog = new ConfirmDialog(strings);
            dialog.show(getActivity().getSupportFragmentManager(),"TAG");
        });
    }
    private String answer(boolean b) {
        if(b) {
            return "Да";
        } else {
            return "Нет";
        }
    }

}
