package com.example.projektsklep.Account;
//    http://www.androidtutorialshub.com/android-login-and-register-with-sqlite-database-tutorial/

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "UserDatabase.db";
    private static final String TABLE_USER = "user";

    // User table columns
    private static final String COLUMN_USER_ID = "id";
    private static final String COLUMN_FIRST_NAME = "first_name";
    private static final String COLUMN_LAST_NAME = "last_name";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PHONE_NUMBER = "phone_number";
    private static final String COLUMN_PASSWORD = "password";

    private String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
            + COLUMN_USER_ID + "INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_FIRST_NAME + "TEXT,"
            + COLUMN_LAST_NAME + "TEXT,"
            + COLUMN_EMAIL + "TEXT,"
            + COLUMN_PHONE_NUMBER + "TEXT,"
            + COLUMN_PASSWORD + "TEXT"
            + ");";

    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER;

    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(DROP_USER_TABLE);
        onCreate(db);
    }

    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_FIRST_NAME, user.getFirstName());
        values.put(COLUMN_LAST_NAME, user.getLastName());
        values.put(COLUMN_EMAIL, user.getEmail());
        values.put(COLUMN_PHONE_NUMBER, user.getPhoneNumber());
        values.put(COLUMN_PASSWORD, user.getPassword());

        db.insert(TABLE_USER, null, values);
        db.close();
    }

}
