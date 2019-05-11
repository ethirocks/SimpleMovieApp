package com.example.movieapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHandler extends SQLiteOpenHelper {
    //information of database
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "userDB.db";
    public static final String TABLE_NAME = "User";
    public static final String COLUMN_NAME = "UserEmail";
    public static final String COLUMN_NAME1 = "UserNumber";
    public static final String COLUMN_NAME2 = "UserBdate";
    public static final String COLUMN_NAME3 = "UserPassword";

    //initialize the database
    public MyDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" +
                COLUMN_NAME+" Text PRIMARY KEY," + COLUMN_NAME1 +" Text NOT NULL,"+ COLUMN_NAME2 +" Text NOT NULL," +COLUMN_NAME3 + " TEXT NOT NULL"+")";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {}
    public String loadHandler() {
        String result = "";
        String query = "Select * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            int result_0 = cursor.getInt(0);
            String result_1 = cursor.getString(1);
            result += String.valueOf(result_0) + " " + result_1 +
                    System.getProperty("line.separator");
        }
        cursor.close();
        db.close();
        return result;
    }
    public void addHandler(User user) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, user.getEmail());
        values.put(COLUMN_NAME1, user.getBdate());
        values.put(COLUMN_NAME2, user.getNumber());
        values.put(COLUMN_NAME3, user.getPassword());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_NAME, null, values);
        db.close();
    }
    public User findHandler(String useremail) {
        String query = "Select * FROM " + TABLE_NAME + " WHERE " + COLUMN_NAME + " = " + "'" + useremail + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        User user = new User();
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            user.setEmail(cursor.getString(0));
            user.setPassword(cursor.getString(3));
            cursor.close();
        } else {
            user = null;
        }
        db.close();
        return user;
    }
    public boolean deleteHandler(String email) {
        boolean result = false;
        String query = "Select * FROM " + TABLE_NAME + " WHERE " + COLUMN_NAME + " = '" + email + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        User user = new User();
        if (cursor.moveToFirst()) {
            user.setEmail(cursor.getString(0));
            db.delete(TABLE_NAME, COLUMN_NAME + "=?",
                    new String[] {
                String.valueOf(user.getEmail())
            });
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }
    public boolean updateHandler(String email, int number, String bdate, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues args = new ContentValues();
        args.put(COLUMN_NAME, email);
        args.put(COLUMN_NAME1, number);
        args.put(COLUMN_NAME2, bdate);
        args.put(COLUMN_NAME3, password);

        return db.update(TABLE_NAME, args, COLUMN_NAME + "=" + email, null) > 0;
    }
}
