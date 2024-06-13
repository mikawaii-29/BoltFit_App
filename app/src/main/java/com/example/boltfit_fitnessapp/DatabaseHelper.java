package com.example.boltfit_fitnessapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "bolfitDB.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_USER_ACCOUNT = "useraccount";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASSWORD = "password";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE IF NOT EXISTS " + TABLE_USER_ACCOUNT +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_EMAIL + " TEXT, " +
                COLUMN_USERNAME + " TEXT, " +
                COLUMN_PASSWORD + " TEXT)";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER_ACCOUNT);
        onCreate(db);
    }

    public boolean insertUser(String name, String email, String username, String password) {
        try (SQLiteDatabase db = this.getWritableDatabase()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(COLUMN_NAME, name);
            contentValues.put(COLUMN_EMAIL, email);
            contentValues.put(COLUMN_USERNAME, username);
            contentValues.put(COLUMN_PASSWORD, password);

            long result = db.insert(TABLE_USER_ACCOUNT, null, contentValues);
            return result != -1;
        } catch (Exception e) {
            Log.e("DatabaseHelper", "Error inserting user", e);
            return false;
        }
    }

    public boolean checkUser(String username, String password) {
        String query = "SELECT * FROM " + TABLE_USER_ACCOUNT + " WHERE " +
                COLUMN_USERNAME + " = ? AND " + COLUMN_PASSWORD + " = ?";
        String[] selectionArgs = {username, password};

        try (SQLiteDatabase db = this.getReadableDatabase();
             Cursor cursor = db.rawQuery(query, selectionArgs)) {

            return cursor.getCount() > 0;
        } catch (Exception e) {
            Log.e("DatabaseHelper", "Error checking user", e);
            return false;
        }
    }

    public String[] getUserDetailsByUsername(String username) {
        String query = "SELECT " + COLUMN_NAME + ", " + COLUMN_EMAIL + " FROM " + TABLE_USER_ACCOUNT + " WHERE " + COLUMN_USERNAME + " = ?";
        String[] selectionArgs = {username};
        String[] userDetails = new String[2];

        try (SQLiteDatabase db = this.getReadableDatabase();
             Cursor cursor = db.rawQuery(query, selectionArgs)) {

            if (cursor.moveToFirst()) {
                userDetails[0] = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME));
                userDetails[1] = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EMAIL));
            }
        } catch (Exception e) {
            Log.e("DatabaseHelper", "Error retrieving user details", e);
        }
        return userDetails;
    }

    public void printAllUsers() {
        String query = "SELECT * FROM " + TABLE_USER_ACCOUNT;

        try (SQLiteDatabase db = this.getReadableDatabase();
             Cursor cursor = db.rawQuery(query, null)) {

            while (cursor.moveToNext()) {
                String username = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_USERNAME));
                String password = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PASSWORD));
                Log.d("DatabaseHelper", "User: " + username + ", Password: " + password);
            }
        } catch (Exception e) {
            Log.e("DatabaseHelper", "Error printing users", e);
        }
    }
}



