package com.example.moviedb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static String DATABASE_NAME = "Movie.db";
    private static int DATABASE_VERSION = 1;
    private static String TABLE_USER = "User";
    private static String USER_COL_1 = "Name";
    private static String USER_COL_2 = "Password";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_user = "Create table " + TABLE_USER +
                "( " + USER_COL_1 + " text primary key, " + USER_COL_2 +
                " text);";
        db.execSQL(create_user);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if exists " + TABLE_USER);
    }

    public boolean insert(String name, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Name", name);
        contentValues.put("Password", password);
        long ins = db.insert("User", null, contentValues);
        if(ins == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean checkName(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from " + TABLE_USER +
                " where " + USER_COL_1 + "=?;",
                new String[]{name});
        if(cursor.getCount() > 0) {
            return false;
        } else {
            return true;
        }
    }

    public boolean checkData(String name, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from " + TABLE_USER +
                        " where " + USER_COL_1 + "=? and " + USER_COL_2 +"=?;",
                new String[]{name, password});
        if(cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }
}
