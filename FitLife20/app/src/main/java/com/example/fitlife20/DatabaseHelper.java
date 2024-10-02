package com.example.fitlife20;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.renderscript.Sampler;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "fitlife.db";
    private static final int DATABASE_VERSION = 1;


    //Table Names
    private static final String TABLE_MEALS = "meals";
    private static final String TABLE_GOALS = "goals";

    //Common column name
    private static final String KEY_ID = "id";
    private static final String KEY_DATE = "date";

    //Meals Table - column names
    private static final String KEY_MEAL_NAME = "meal_name";
    private static final String KEY_CALORIES = "calories";
    private static final String KEY_PROTEIN = "protein";

    // Goals Table - column names
    private static final String KEY_GOAL_CALORIES = "goal_calories";
    private static final String KEY_GOAL_PROTEIN = "goal_protein";

    // Table Create Statements
    private static final String CREATE_TABLE_MEALS = "CREATE TABLE " + TABLE_MEALS + "("
            + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_DATE + " TEXT,"
            + KEY_MEAL_NAME + " TEXT,"
            + KEY_CALORIES + " INTEGER,"
            + KEY_PROTEIN + " INTEGER" + ")";

    private static final String CREATE_TABLE_GOALS = "CREATE TABLE " + TABLE_GOALS + "("
            + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_DATE + " TEXT,"
            + KEY_GOAL_CALORIES + " INTEGER,"
            + KEY_GOAL_PROTEIN + " INTEGER" + ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_MEALS);
        db.execSQL(CREATE_TABLE_GOALS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEALS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GOALS);
        onCreate(db);
    }

    // CRUD operations for Meals and Goals
    public long insertMeal(String date, String mealName, int calories, int protein){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put(KEY_DATE, date);
        values.put(KEY_MEAL_NAME, mealName);
        values.put(KEY_CALORIES, calories);
        values.put(KEY_PROTEIN, protein);
        return db.insert(TABLE_MEALS, null, values);
    }

    public int deleteMeal(long id){
        SQLiteDatabase db= this.getReadableDatabase();
        return db.delete(TABLE_MEALS, KEY_ID + "=?", new String[]{String.valueOf(id)});
    }

    public Cursor getAllMeal(){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.query(TABLE_MEALS, null, null, null, null, null,
                KEY_DATE + "DESC");
    }

    //CRUD Operation For Goals
    public long setGoal(String date, int calories, int protein){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put(KEY_DATE,date);
        values.put(KEY_GOAL_CALORIES,calories);
        values.put( KEY_GOAL_PROTEIN,protein);
        return db.insert(TABLE_GOALS, null, values);
    }

    public int deleteGoal(long id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_GOALS, KEY_ID + "=?", new String[]{String.valueOf(id)});
    }

    public Cursor getAllGoals(){
        SQLiteDatabase db= this.getReadableDatabase();
        return db.query(TABLE_MEALS, null, null, null, null, null,
                KEY_DATE + "DESC");
    }


}

