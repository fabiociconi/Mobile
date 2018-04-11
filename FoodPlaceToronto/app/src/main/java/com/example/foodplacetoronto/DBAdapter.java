package com.example.foodplacetoronto;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * DBAdapter Activity - Managed Database
 * Author : Rodrigo Geronimo & Fabio A. Ciconi
 * Dez/2017
 */

public class DBAdapter {

    // Database Name
    private static final String DATABASE_NAME = "FoodPlaceDB";

    // Logcat tag
    private static final String LOG = "DBAdapter";

    //DataBase version
    private static final int DATABASE_VERSION = 31;

    // Table Names
    private static final String TABLE_CUISINE = "tbl_cuisines";
    private static final String TABLE_RESTAURANT = "tbl_restaurants";

    //TABLE_CUISINES
    private static final String CUISINE_ID = "cuisineId";
    private static final String CUISINE_NAME = "cuisineName";
    private static final String DESCRIPTION = "description";

    //TABLE_RESTAURANTS
    private static final String RESTAURANT_ID = "restaurantId";
    private static final String RESTAURANT_NAME = "restaurantName";
    private static final String ADDRESS = "address";
    private static final String LAT = "lat";
    private static final String LNG = "lng";
    private static final String CUISINE = "cuisine";


    //TABLE_CUISINES Schema
    private static final String CREATE_TABLE_Cuisines = "CREATE TABLE "
            + TABLE_CUISINE +
            "(" + CUISINE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + CUISINE_NAME + " TEXT,"
            + DESCRIPTION + " TEXT" + ")";

    //TABLE_RESTAURANTS Schema
    private static final String CREATE_TABLE_Restaurants = "CREATE TABLE "
            + TABLE_RESTAURANT +
            "(" + RESTAURANT_ID + " INTEGER PRIMARY KEY  AUTOINCREMENT,"
            + RESTAURANT_NAME + " TEXT,"
            + ADDRESS + " TEXT,"
            + LAT + " REAL,"
            + LNG + " REAL,"
            + CUISINE + " TEXT" + ")";

    //DataBase Helper
    private static DataBaseHelper DBHelper;

    //Context
    private final Context context;

    //SQL Lite
    private SQLiteDatabase db;

    //DBHelper Instance
    public DBAdapter(Context ctx) {

        this.context = ctx;
        DBHelper = new DataBaseHelper(context);

    }

    //Open Database
    public DBAdapter Open() throws SQLException {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    //Close Database
    public void Close() throws SQLException {
        DBHelper.close();

    }

    //******************************************************** \\
    //  INSERTS                                                ||
    //*********************************************************//

    /**
     * Create Cuisine
     */
    public long CreateCuisine(Cuisine cuisine) {

        ContentValues values = new ContentValues();

        values.put(CUISINE_ID, cuisine.getId());
        values.put(CUISINE_NAME, cuisine.getName());
        values.put(DESCRIPTION, cuisine.getDescription());

        return db.insert(TABLE_CUISINE, null, values);

    }

    /**
     * Create Restaurant
     */
    public long CreateRestaurant(Restaurant restaurant) {

        ContentValues values = new ContentValues();

        values.put(RESTAURANT_ID, restaurant.getId());
        values.put(RESTAURANT_NAME, restaurant.getName());
        values.put(ADDRESS, restaurant.getAddress());
        values.put(LAT, restaurant.getLat());
        values.put(LNG, restaurant.getLng());
        values.put(CUISINE, restaurant.getCuisine());

        return db.insert(TABLE_RESTAURANT, null, values);

    }

    //******************************************************** \\
    //  Select - one row                                       ||
    //*********************************************************//

    /**
     * GET A CUISINE by ID and Name
     *
     * @param id
     * @param name
     * @return
     */
    public Cuisine GetCuisine(int id, String name) {

        String selectQueryCuisine = "SELECT  * FROM " + TABLE_CUISINE + " WHERE "
                + CUISINE_ID + " = " + id + " and " + CUISINE_NAME + " = '" + name + "' ;";

        Cursor c = db.rawQuery(selectQueryCuisine, null);

        if (c.moveToFirst()) {

            Cuisine td = new Cuisine();
            td.setId(c.getInt(c.getColumnIndex(CUISINE_ID)));
            td.setName((c.getString(c.getColumnIndex(CUISINE_NAME))));
            td.setDescription(c.getString(c.getColumnIndex(DESCRIPTION)));

            return td;
        }
        return null;
    }

    /**
     * GET A CUISINE by ID
     *
     * @param id
     * @return
     */
    public Cuisine GetCuisine(int id) {

        String selectQueryCuisine = "SELECT  * FROM " + TABLE_CUISINE + " WHERE "
                + CUISINE_ID + " = " + id + "' ;";

        Cursor c = db.rawQuery(selectQueryCuisine, null);

        if (c.moveToFirst()) {

            Cuisine td = new Cuisine();
            td.setId(c.getInt(c.getColumnIndex(CUISINE_ID)));
            td.setName((c.getString(c.getColumnIndex(CUISINE_NAME))));
            td.setDescription(c.getString(c.getColumnIndex(DESCRIPTION)));

            return td;
        }
        return null;
    }

    /**
     * GET A CUISINE by Name
     *
     * @param name
     * @return
     */
    public Cuisine GetCuisine(String name) {

        String selectQueryCuisine = "SELECT  * FROM " + TABLE_CUISINE + " WHERE "
                + CUISINE_NAME + " = '" + name + "' ;";

        Cursor c = db.rawQuery(selectQueryCuisine, null);

        if (c.moveToFirst()) {

            Cuisine td = new Cuisine();
            td.setId(c.getInt(c.getColumnIndex(CUISINE_ID)));
            td.setName((c.getString(c.getColumnIndex(CUISINE_NAME))));
            td.setDescription(c.getString(c.getColumnIndex(DESCRIPTION)));

            return td;
        }
        return null;
    }

    /**
     * GET A RESTAURANT by ID and Name
     *
     * @param id
     * @param name
     * @return
     */
    public Restaurant GetRestaurant(int id, String name) {

        String selectQueryRestaurant = "SELECT  * FROM " + TABLE_RESTAURANT + " WHERE "
                + RESTAURANT_ID + " = " + id + " and " + RESTAURANT_NAME + " = '" + name + "' ;";

        Cursor c = db.rawQuery(selectQueryRestaurant, null);

        if (c.moveToFirst()) {

            Restaurant td = new Restaurant();
            td.setId(c.getInt(c.getColumnIndex(RESTAURANT_ID)));
            td.setName((c.getString(c.getColumnIndex(RESTAURANT_NAME))));
            td.setAddress(c.getString(c.getColumnIndex(ADDRESS)));
            td.setLat(c.getDouble(c.getColumnIndex(LAT)));
            td.setLng(c.getDouble(c.getColumnIndex(LNG)));
            td.setCuisine(c.getString(c.getColumnIndex(CUISINE)));

            return td;
        }
        return null;
    }

    /**
     * GET A RESTAURANT by ID
     *
     * @param id
     * @return
     */
    public Restaurant GetRestaurant(int id) {

        String selectQueryRestaurant = "SELECT  * FROM " + TABLE_RESTAURANT + " WHERE "
                + RESTAURANT_ID + " = " + id + "' ;";

        Cursor c = db.rawQuery(selectQueryRestaurant, null);

        if (c.moveToFirst()) {

            Restaurant td = new Restaurant();
            td.setId(c.getInt(c.getColumnIndex(RESTAURANT_ID)));
            td.setName((c.getString(c.getColumnIndex(RESTAURANT_NAME))));
            td.setAddress(c.getString(c.getColumnIndex(ADDRESS)));
            td.setLat(c.getDouble(c.getColumnIndex(LAT)));
            td.setLng(c.getDouble(c.getColumnIndex(LNG)));
            td.setCuisine(c.getString(c.getColumnIndex(CUISINE)));

            return td;
        }
        return null;
    }

    /**
     * GET A RESTAURANT by Name
     *
     * @param name
     * @return
     */
    public Restaurant GetRestaurant(String name) {

        String selectQueryRestaurant = "SELECT  * FROM " + TABLE_RESTAURANT + " WHERE "
                + RESTAURANT_NAME + " = '" + name + "' ;";

        Cursor c = db.rawQuery(selectQueryRestaurant, null);

        if (c.moveToFirst()) {

            Restaurant td = new Restaurant();
            td.setId(c.getInt(c.getColumnIndex(RESTAURANT_ID)));
            td.setName((c.getString(c.getColumnIndex(RESTAURANT_NAME))));
            td.setAddress(c.getString(c.getColumnIndex(ADDRESS)));
            td.setLat(c.getDouble(c.getColumnIndex(LAT)));
            td.setLng(c.getDouble(c.getColumnIndex(LNG)));
            td.setCuisine(c.getString(c.getColumnIndex(CUISINE)));

            return td;
        }
        return null;
    }

    //******************************************************** \\
    //  Select - Cursors - WHERE                               ||
    //*********************************************************//

    /**
     * GET ALL CUISINES
     *
     * @return
     */
    public List<Cuisine> GetAllCuisines() {
        List<Cuisine> array = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_CUISINE + " ;";
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor == null) {
            return null;
        } else {
            cursor.moveToFirst();
            do {
                Cuisine td = new Cuisine();
                td.setId(cursor.getInt(cursor.getColumnIndex(CUISINE_ID)));
                td.setName((cursor.getString(cursor.getColumnIndex(CUISINE_NAME))));
                td.setDescription(cursor.getString(cursor.getColumnIndex(DESCRIPTION)));
                array.add(td);
            } while (cursor.moveToNext());
        }
        return array;

    }

    /**
     * GET ALL RESTAURANTS
     *
     * @return
     */
    public List<Restaurant> GetAllRestaurants() {
        List<Restaurant> array = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_RESTAURANT + ";";
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor == null) {
            return null;
        } else {
            cursor.moveToFirst();
            do {
                Restaurant td = new Restaurant();
                td.setId(cursor.getInt(cursor.getColumnIndex(RESTAURANT_ID)));
                td.setName((cursor.getString(cursor.getColumnIndex(RESTAURANT_NAME))));
                td.setAddress(cursor.getString(cursor.getColumnIndex(ADDRESS)));
                td.setLat(cursor.getDouble(cursor.getColumnIndex(LAT)));
                td.setLng(cursor.getDouble(cursor.getColumnIndex(LNG)));
                td.setCuisine(cursor.getString(cursor.getColumnIndex(CUISINE)));

                array.add(td);
            } while (cursor.moveToNext());
        }
        return array;

    }

    /**
     * GET ALL RESTAURANTS by CUISINE
     *
     * @param cuisine
     * @return
     */
    public List<Restaurant> GetAllRestaurants(String cuisine) {
        List<Restaurant> array = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_RESTAURANT + " WHERE "
                + CUISINE + " = '" + cuisine + "' ;";
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor == null) {
            return null;
        } else {
            cursor.moveToFirst();
            do {
                Restaurant td = new Restaurant();
                td.setId(cursor.getInt(cursor.getColumnIndex(RESTAURANT_ID)));
                td.setName((cursor.getString(cursor.getColumnIndex(RESTAURANT_NAME))));
                td.setAddress(cursor.getString(cursor.getColumnIndex(ADDRESS)));
                td.setLat(cursor.getDouble(cursor.getColumnIndex(LAT)));
                td.setLng(cursor.getDouble(cursor.getColumnIndex(LNG)));
                td.setCuisine(cursor.getString(cursor.getColumnIndex(CUISINE)));

                array.add(td);
            } while (cursor.moveToNext());
        }
        return array;

    }



    private static class DataBaseHelper extends SQLiteOpenHelper {

        /**
         * Take the COntext and Database
         *
         * @param context
         */
        public DataBaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        /**
         * CREATE TABLES
         *
         * @param db
         */
        @Override
        public void onCreate(SQLiteDatabase db) {

            // creating required tables
            db.execSQL(CREATE_TABLE_Cuisines);
            db.execSQL(CREATE_TABLE_Restaurants);
        }

        /**
         * DATABASE VERSION
         * DROP EXISTENT TABLES
         *
         * @param db
         * @param oldVersion
         * @param newVersion
         */
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            Log.w(LOG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");

            // on upgrade drop older tables
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_CUISINE);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_RESTAURANT);

            // create new tables
            onCreate(db);
        }
    }


}
