package com.example.fitlife20;

public class Meal {
    private long id;
    private String date;
    private String mealName;
    private int calories;
    private int protein;
    private String mealType;

    public Meal(long id, String date, String mealName, int calories, int protein) {
        this.id = id;
        this.date = date;
        this.mealName = mealName;
        this.calories = calories;
        this.protein = protein;
        this.mealType = mealType;
    }
    // Getters and setters
    public long getId() { return id; }
    public String getDate() { return date; }
    public String getMealName() { return mealName; }
    public int getCalories() { return calories; }
    public int getProtein() { return protein; }

}

