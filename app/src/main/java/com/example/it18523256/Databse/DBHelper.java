package com.example.it18523256.Databse;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "GOOD.db";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + UserProfile.Users.TABLE_NAME + " (" +
                    UserProfile.Users._ID + " INTEGER PRIMARY KEY," +
                    UserProfile.Users.Col_1 + " TEXT," +
                    UserProfile.Users.Col_2 + " TEXT,"+
                    UserProfile.Users.Col_3 + " TEXT,"+
                    UserProfile.Users.Col_4 + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + UserProfile.Users.TABLE_NAME;


    public long addInfo(String UserName, String dob, String gender, String pswd){
        // Gets the data repository in write mode
        SQLiteDatabase db = getWritableDatabase();

// Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(UserProfile.Users.Col_1,UserName);
        values.put(UserProfile.Users.Col_2, dob);
        values.put(UserProfile.Users.Col_3, gender);
        values.put(UserProfile.Users.Col_4, pswd);

// Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(UserProfile.Users.TABLE_NAME, null, values);
        return newRowId;
    }

    public boolean updateInfo(String UserName, String dob, String gender,String pswd){
        SQLiteDatabase db = getWritableDatabase();

// New value for one column
        String title = "MyNewTitle";
        ContentValues values = new ContentValues();
        values.put(UserProfile.Users.Col_2 ,dob);
        values.put(UserProfile.Users.Col_3 ,gender);
        values.put(UserProfile.Users.Col_4 ,pswd);

// Which row to update, based on the title
        String selection = UserProfile.Users.Col_1 + " LIKE ?";
        String[] selectionArgs = { UserName };

        int count = db.update(
              UserProfile.Users.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        if (count >= 1){
            return true;
        }
        else
            return false;
    }
    public List readAllInfo(){
        String UserName = "Usn";
        SQLiteDatabase db = getReadableDatabase();

// Define a projection that specifies which columns from the database
// you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                UserProfile.Users.Col_1,
                UserProfile.Users.Col_2,
                UserProfile.Users.Col_3,
                UserProfile.Users.Col_4,
        };

// Filter results WHERE "title" = 'My Title'
        String selection = UserProfile.Users.Col_1+ " = ?";
        String[] selectionArgs = { UserName };

// How you want the results sorted in the resulting Cursor
        String sortOrder =
                UserProfile.Users.Col_1+ " DESC";

        Cursor cursor = db.query(
                UserProfile.Users.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );
        List userNames = new ArrayList<>();
        while(cursor.moveToNext()) {
            long itemId = cursor.getLong(
                    cursor.getColumnIndexOrThrow(UserProfile.Users.Col_1));
            userNames.add(itemId);
        }
        cursor.close();
        return userNames;
    } public List readAllInfo(String UserName){

        SQLiteDatabase db = getReadableDatabase();

// Define a projection that specifies which columns from the database
// you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                UserProfile.Users.Col_1,
                UserProfile.Users.Col_2,
                UserProfile.Users.Col_3,
                UserProfile.Users.Col_4,
        };

// Filter results WHERE "title" = 'My Title'
        String selection = UserProfile.Users.Col_1+ " = ?";
        String[] selectionArgs = { UserName };

// How you want the results sorted in the resulting Cursor
        String sortOrder =
                UserProfile.Users.Col_1+ " DESC";

        Cursor cursor = db.query(
                UserProfile.Users.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );
        List UserDetails = new ArrayList<>();
        while(cursor.moveToNext()) {

                    String user = cursor.getString(cursor.getColumnIndexOrThrow(UserProfile.Users.Col_1));
                    String dob = cursor.getString(cursor.getColumnIndexOrThrow(UserProfile.Users.Col_2));
                    String gender = cursor.getString(cursor.getColumnIndexOrThrow(UserProfile.Users.Col_3));
                    String pswd = cursor.getString(cursor.getColumnIndexOrThrow(UserProfile.Users.Col_4));


            UserDetails.add(user); //0
            UserDetails.add(dob); //1
            UserDetails.add(gender); //2
            UserDetails.add(pswd);//3
        }
        cursor.close();
        return UserDetails;
    }
    public void DeleteInfo(String UserName){
        SQLiteDatabase db = getReadableDatabase();

        // Define 'where' part of query.
        String selection = UserProfile.Users.Col_1+ " LIKE ?";
// Specify arguments in placeholder order.
        String[] selectionArgs = {UserName };
// Issue SQL statement.
        int deletedRows = db.delete(UserProfile.Users.TABLE_NAME, selection, selectionArgs);
    }

}

