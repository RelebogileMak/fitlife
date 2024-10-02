package com.example.fitlife20;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitlife20.dialogs.AddMealDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class DietaryPlannerFragment extends Fragment {
    //required empty public contrustor

    private DatabaseHelper db;
    private RecyclerView recyclerView;
    private MealAdapter mealAdapter;
    private List<Meal> mealList;
    private FragmentManager childFragmentManger;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater iterator, @Nullable ViewGroup container,
                             @Nullable Bundle saveInstanceState) {
        //infalate the layout for this fragment
        View view = iterator.inflate(R.layout.fragment_dietary_planner, container, false);
//
        //initialize RecycleView
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        db = new DatabaseHelper(getContext());


        mealList = new ArrayList<>();
        mealAdapter = new MealAdapter(mealList);
        recyclerView.setAdapter(mealAdapter);

        FloatingActionButton fab = view.findViewById(R.id.fab_add_meal);
        fab.setOnClickListener(new View.OnClickListener() {
            public FragmentManager getChildFragmentManger() {
                return childFragmentManger;
            }

            @Override
            public void onClick(View v) {
                AddMealDialog dialog = new AddMealDialog();
                dialog.show(getChildFragmentManger(), "AddMealDialog");
            }
        });
        LoadMeals();

        return view;
    }

    private FragmentManager getChildFragmentManger(FragmentManager childFragmentManger) {
        this.childFragmentManger = childFragmentManger;
        return childFragmentManger;
    }

    private void LoadMeals() {
        mealList.clear();
        Cursor cursor = db.getAllMeal();
        if (cursor.moveToFirst()) {
            do {
                Meal meal = new Meal(
                        cursor.getLong(cursor.getColumnIndexOrThrow("id")),
                        cursor.getString(cursor.getColumnIndexOrThrow("date")),
                        cursor.getString(cursor.getColumnIndexOrThrow("meal_name")),
                        cursor.getInt(cursor.getColumnIndexOrThrow("calories")),
                        cursor.getInt(cursor.getColumnIndexOrThrow("proteins"))
                        );
                mealList.add(meal);
            } while (cursor.moveToNext());
        }
        cursor.close();
        mealAdapter.notifyDataSetChanged();
    }

}
