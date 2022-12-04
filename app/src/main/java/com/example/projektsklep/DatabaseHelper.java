package com.example.projektsklep;
//    http://www.androidtutorialshub.com/android-login-and-register-with-sqlite-database-tutorial/

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.projektsklep.Account.User;
import com.example.projektsklep.Products.CentralUnit;
import com.example.projektsklep.Products.DataSource;
import com.example.projektsklep.R;

import java.util.ArrayList;
import java.util.HashMap;

public class DatabaseHelper extends SQLiteOpenHelper {
    private DataSource dataSource;
    private static final int DB_VERSION = 3;
    private static final String DB_NAME = "UserDatabase.db";
    // TABLE USER
    private static final String TABLE_USER = "user";

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


    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        dataSource = new DataSource();

        updateAllProducts();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
        updateAllProducts();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(DROP_USER_TABLE);
        onCreate(db);
    }

    public void updateAllProducts() {
        updateProducts("pc", dataSource.getCentralUnitRepo());
        updateProducts("mouse", dataSource.getMouseRepo());
        updateProducts("keyboard", dataSource.getKeyboardRepo());
        updateProducts("monitor", dataSource.getMonitorRepo());
    }

    public void updateProducts(String tableName, ArrayList<HashMap> repo) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + tableName);
        String CREATE_TABLE = "CREATE TABLE " + tableName + "("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "description TEXT,"
                + "price INTEGER,"
                + "img INTEGER"
                + ");";

        db.execSQL(CREATE_TABLE);

        ArrayList<ContentValues> contentValuesList = new ArrayList<>();

        for (int i = 0; i < repo.size(); i++) {
            ContentValues cv = new ContentValues();
            HashMap<String, String> product = repo.get(i);

            cv.put("description", product.get("description"));
            cv.put("price", product.get("price"));
            cv.put("img", product.get("img"));

            contentValuesList.add(cv);
        }

        contentValuesList.forEach((contentValues -> {
            db.insert(tableName, null, contentValues);
        }));

    }

    public ArrayList<HashMap> getProducts(String tableName) {
        ArrayList<HashMap> repo = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT description, price, img FROM " + tableName, null);

        if (c.moveToFirst()){
            do {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("description", c.getString(0));
                hashMap.put("price", c.getString(1));
                hashMap.put("img", c.getString(2));
                repo.add(hashMap);

            } while(c.moveToNext());
        }

        c.close();

        return repo;
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

        return user;
    }

}
