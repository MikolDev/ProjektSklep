package com.example.projektsklep.Account;
//    http://www.androidtutorialshub.com/android-login-and-register-with-sqlite-database-tutorial/

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "UserDatabase.db";
    // TABLE USER
    private static final String TABLE_USER = "user";
    // TABLE PC
    private static final String TABLE_PC = "pc";
    // TABLE MOUSE
    private static final String TABLE_MOUSE = "mouse";
    // TABLE KEYBOARD
    private static final String TABLE_KEYBOARD = "keyboard";
    // TABLE MONITOR
    private static final String TABLE_MONITOR = "monitor";


    // User table columns
    private static final String COLUMN_USER_ID = "id";
    private static final String COLUMN_FIRST_NAME = "first_name";
    private static final String COLUMN_LAST_NAME = "last_name";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PHONE_NUMBER = "phone_number";
    private static final String COLUMN_PASSWORD = "password";

    private String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
            + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_FIRST_NAME + " TEXT,"
            + COLUMN_LAST_NAME + " TEXT,"
            + COLUMN_EMAIL + " TEXT,"
            + COLUMN_PHONE_NUMBER + " TEXT,"
            + COLUMN_PASSWORD + " TEXT"
            + ");";

    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER;
    // END user table

    // PC table columns
    private static final String COLUMN_PC_ID = "id";
    private static final String COLUMN_PC_DESC = "desc";
    private static final String COLUMN_PC_PRICE = "price";
    private static final String COLUMN_PC_IMG = "img";

    private String CREATE_PC_TABLE = "CREATE TABLE " + TABLE_PC + "("
            + COLUMN_PC_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_PC_DESC + " TEXT,"
            + COLUMN_PC_PRICE + " INTEGER,"
            + COLUMN_PC_IMG + " INTEGER"
            + ")";
    // END PC table


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

    public boolean checkUser(String email, String password) {
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COLUMN_EMAIL + " = ?" + " AND " + COLUMN_PASSWORD + " = ?";
        String[] selectionArgs = {email, password};

        Cursor cursor = db.query(TABLE_USER,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }
        return false;
    }

    public User getUserData(String email, String password) {
        User user = new User();

        String[] columns = {
                COLUMN_USER_ID,
                COLUMN_FIRST_NAME,
                COLUMN_LAST_NAME,
                COLUMN_EMAIL,
                COLUMN_PHONE_NUMBER,
                COLUMN_PASSWORD
        };
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COLUMN_EMAIL + " = ?" + " AND " + COLUMN_PASSWORD + " = ?";
        String[] selectionArgs = {email, password};

        Cursor cursor = db.query(TABLE_USER,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);

        if (cursor.moveToFirst()) {
            do {
                user.setFirstName(cursor.getString(1));
                user.setLastName(cursor.getString(2));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return user;
    }

}
