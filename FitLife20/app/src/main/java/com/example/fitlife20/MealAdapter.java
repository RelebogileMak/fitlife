package com.example.fitlife20;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


public class MealAdapter extends RecyclerView.Adapter<MealAdapter.MealViewHolder> {

    private List<Meal> mealList;

    public MealAdapter(List<Meal> mealList) {
        this.mealList = mealList;
    }
    @NonNull
    @Override
    public MealViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_meal, parent, false);
        return new MealViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull MealViewHolder holder, int position) {
        Meal meal = mealList.get(position);
        holder.mealName.setText(meal.getMealName());
        holder.calories.setText(String.valueOf(meal.getCalories()));
        holder.protein.setText(String.valueOf(meal.getProtein()));
    }
    @Override
    public int getItemCount() {
        return mealList.size();
    }
    public static class MealViewHolder extends RecyclerView.ViewHolder {
        public TextView mealName, calories, protein, mealType;
        public MealViewHolder(View view) {
            super(view);
            mealType = view.findViewById(R.id.meal_type);
            mealName = view.findViewById(R.id.meal_name);
            calories = view.findViewById(R.id.calories);
            protein = view.findViewById(R.id.protein);
        }
    }
}