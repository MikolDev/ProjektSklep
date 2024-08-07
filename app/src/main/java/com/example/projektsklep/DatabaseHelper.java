package com.example.projektsklep;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.projektsklep.Account.User;
import com.example.projektsklep.Orders.Order;
import com.example.projektsklep.ProductsController.DataSource;
import com.example.projektsklep.ProductsModel.Product;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class DatabaseHelper extends SQLiteOpenHelper {
    private DataSource dataSource;
    private static final int DB_VERSION = 9;
    private static final String DB_NAME = "UserDatabase.db";
    // TABLE USER
    private static final String TABLE_USER = "user";
    // TABLE ORDER
    private static final String TABLE_ORDER = "orders";

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

    // START order table

    private String CREATE_ORDER_TABLE = "CREATE TABLE " + TABLE_ORDER + "("
            + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "total INT,"
            + "centralUnitId INT,"
            + "mouseId INT,"
            + "keyboardId INT,"
            + "monitorId INT,"
            + "id_user INT NOT NULL,"
            + "ordered TEXT"
            + ");";

    private String DROP_ORDER_TABLE  = "DROP TABLE IF EXISTS " + TABLE_ORDER;

    // END order table

    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        dataSource = new DataSource();

//        updateAllProducts();
    }

    /**
     * Metoda tworzy tabele użytkowników i zamówień.
     *
     * @param db obiekty bazy danych, w którym będą tworzone tabele
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_ORDER_TABLE);
        updateAllProducts(db);
    }

    /**
     * Metoda wywoywana przy upgradzie. Usuwa tabele użytkowników i zamówień i wywołuje ich tworzenie
     *
     * @param db obiekt bazy danych, w którym mają być nowe tabele
     * @param i
     * @param i1
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(DROP_USER_TABLE);
        db.execSQL(DROP_ORDER_TABLE);
        onCreate(db);
    }

    /**
     * Metoda pobiera produkty ze źródła danych i wywołuje metodę do zapisania tych produktów w bazie.
     *
     * @param db obiekt bazy danych, w której mają być zapisane produkty
     */
    public void updateAllProducts(SQLiteDatabase db) {
        updateProducts("pc", dataSource.getCentralUnitRepo(), db);
        updateProducts("mouse", dataSource.getMouseRepo(), db);
        updateProducts("keyboard", dataSource.getKeyboardRepo(), db);
        updateProducts("monitor", dataSource.getMonitorRepo(), db);
    }

    /**
     * Zapisuje dane produktów do bazy danych. Jeżeli tabela istnieje, usuwa ją i tworzy na nowo.
     *
     * @param tableName nazwa tabeli, do której mają trafić produkty
     * @param repo zestaw danych produktów
     * @param db obiekt bazy danych, do której mają być zapisane produkty
     */
    public void updateProducts(String tableName, ArrayList<HashMap> repo, SQLiteDatabase db) {
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

    /**
     * Metoda pobiera produkty z bazy danych.
     *
     * @param tableName nazwa tabeli (produktu)
     * @return zestaw danych produktów
     */
    public ArrayList<HashMap> getProducts(String tableName) {
        ArrayList<HashMap> repo = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT id, description, price, img FROM " + tableName, null);

        if (c.moveToFirst()){
            do {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("id", c.getString(0));
                hashMap.put("description", c.getString(1));
                hashMap.put("price", c.getString(2));
                hashMap.put("img", c.getString(3));
                repo.add(hashMap);

            } while(c.moveToNext());
        }

        c.close();

        return repo;
    }

    /**
     * Metoda pobiera zamówienia danego użytkownika z bazy danych.
     *
     * @param userId id użytkownika, dla którego chcemy pobrać zamówienia
     * @return zestaw danych zamówień
     */
    public ArrayList<Order> getOrders(int userId) {
        ArrayList<Order> orders = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT id, total, centralUnitId, mouseId, keyboardId, monitorId, id_user, ordered FROM orders WHERE id_user = " + userId, null);

        if (c.moveToFirst()) {
            do {
                Order o = new Order();
                o.setOrderId(c.getInt(0));
                o.setTotalPrice(c.getInt(1));
                o.setCentralUnitId(c.getInt(2));
                o.setMouseId(c.getInt(3));
                o.setKeyboardId(c.getInt(4));
                o.setMonitorId(c.getInt(5));
                o.setUserId(c.getInt(6));
                o.setOrdered(c.getString(7));
                orders.add(o);
            } while (c.moveToNext());
        }

        return orders;
    }

    /**
     * Metoda dodaje użytkownika do bazy danych. Nie można dodawać dwóch użytkowników o tym samym adresie email.
     *
     * @param user użytkownik do dodania
     * @return -1 jeżeli istnieje, id jeżeli dodano
     */
    public long addUser(User user) {
        if (emailExists(user.getEmail())) return -1;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_FIRST_NAME, user.getFirstName());
        values.put(COLUMN_LAST_NAME, user.getLastName());
        values.put(COLUMN_EMAIL, user.getEmail());
        values.put(COLUMN_PHONE_NUMBER, user.getPhoneNumber());
        values.put(COLUMN_PASSWORD, user.getPassword());

        return db.insert(TABLE_USER, null, values);
    }

    /**
     * Metoda używana przy logowaniu. Sprawdza, czy jest użytkownik z podanym emailem i hasłem.
     *
     * @param email email użytkownika
     * @param password hasło użytkownika
     * @return true, jeśli istnieje, w przeciwnym wypadku false
     */
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

        if (cursorCount == 1) {
            return true;
        }
        return false;
    }

    /**
     * Metoda sprawdza, czy podany email znajduje się w bazie.
     *
     * @param input email do znalezienia
     * @return true, jeśli znajduje się, w przeciwnym wypadku false
     */
    public boolean emailExists(String input) {
        SQLiteDatabase db = getReadableDatabase();

        String selection = "email = ?";
        String[] selectionArgs = {input};

        Cursor c = db.query(TABLE_USER,
                new String[]{"email"},
                selection,
                selectionArgs,
                null,
                null,
                null);

        if (c.getCount() > 0) return true;
        return false;
    }

    /**
     * Metoda pobiera dane użytkownika z bazy danych na podstawie emaila i hasła.
     *
     * @param email email użytkownika
     * @param password hasło użytkownika
     * @return obiekt użytkownika z wszystkimi danymi
     */
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
                user.setId(cursor.getInt(0));
                user.setFirstName(cursor.getString(1));
                user.setLastName(cursor.getString(2));
                user.setEmail(cursor.getString(3));
                user.setPhoneNumber(cursor.getString(4));
            } while (cursor.moveToNext());
        }

        cursor.close();

        return user;
    }

    /**
     * Metoda pobiera dane użytkownika z bazy danych na podstawie id z bazy.
     *
     * @param id id użytkownika, którego dane chcemy pobrać
     * @return obiekt użytkownika
     */
    public User getUserById(int id) {
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
        String selection = COLUMN_USER_ID + " = ?";
        String[] selectionArgs = {String.valueOf(id)};

        Cursor cursor = db.query(TABLE_USER,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);

        if (cursor.moveToFirst()) {
            do {
                user.setId(cursor.getInt(0));
                user.setFirstName(cursor.getString(1));
                user.setLastName(cursor.getString(2));
                user.setEmail(cursor.getString(3));
                user.setPhoneNumber(cursor.getString(4));
            } while (cursor.moveToNext());
        }

        cursor.close();

        return user;
    }

    /**
     * Metoda dodaje zamówienie do bazy.
     *
     * @param order obiekt zamówienia do zapisania w bazie
     * @return -1 jeśli się nie uda, w przeciwnym wypadku id zamówienia
     */
    public long addOrder(Order order) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        int mouse = -1;
        int keyboard = -1;
        int monitor = -1;

        if (order.getMouseId() != -1) mouse = order.getMouseId();
        if (order.getKeyboardId() != -1) keyboard = order.getKeyboardId();
        if (order.getMonitorId() != -1) monitor = order.getMonitorId();


        values.put("total", order.getTotalPrice());
        values.put("centralUnitId", order.getCentralUnitId());
        values.put("mouseId", mouse);
        values.put("keyboardId", keyboard);
        values.put("monitorId", monitor);
        values.put("ordered", order.getOrdered());
        values.put("id_user", order.getUserId());

        long success = db.insert(TABLE_ORDER, null, values);

        return success;
    }


    /**
     * Metoda usuwa zamówienie z bazy na podstawie id.
     *
     * @param id id zamówienia do usunięcia
     * @return 0 jeśli się nie powiodło, w przeciwnym wypadku 1 (rows affected)
     */
    public long deleteOrder(int id) {
        SQLiteDatabase db = getReadableDatabase();
        long success = db.delete("orders", "id=?", new String[]{String.valueOf(id)});
        Log.v("DELETE", "row deleted " + success);
        return success;
    }

    /**
     * Metoda zwraca dane produktu na podstawie id i rodzaju produktu.
     *
     * @param id id produktu do odszukania
     * @param table nazwa tabeli (rodzaj produktu)
     * @return lista z danymi produktu
     */
    public String[] getProductInfo(int id, String table) {
        SQLiteDatabase db = getWritableDatabase();
        String sql = "SELECT description, price, img FROM " + table + " WHERE id = " + id;
        Cursor c = db.rawQuery(sql, null);
        String[] productInfo = new String[3];

        if (c.moveToFirst()) {
            do {
                productInfo[0] = c.getString(0);
                productInfo[1] = c.getString(1);
                productInfo[2] = c.getString(2);
            } while (c.moveToNext());
        }

        return productInfo;
    }

}
