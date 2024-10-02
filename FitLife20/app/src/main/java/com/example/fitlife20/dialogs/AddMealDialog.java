package com.example.fitlife20.dialogs;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.fitlife20.DatabaseHelper;
import com.example.fitlife20.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class AddMealDialog extends DialogFragment {
    private EditText mealName, calories, protein;
    private Button saveButton;
    private DatabaseHelper db;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add_meal, null);
        mealName = view.findViewById(R.id.meal_name);
        calories = view.findViewById(R.id.calories);
        protein = view.findViewById(R.id.protein);
        saveButton = view.findViewById(R.id.save_button);
        db = new DatabaseHelper(getContext());
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mealName.getText().toString();
                int cal = Integer.parseInt(calories.getText().toString());
                int pro = Integer.parseInt(protein.getText().toString());
                db.insertMeal("today", name, cal, pro); // Replace "today" with actual date
                dismiss();
            }
        });
        builder.setView(view);
        return builder.create();
    }
}