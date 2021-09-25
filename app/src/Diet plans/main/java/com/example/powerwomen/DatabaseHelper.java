package com.example.powerwomen;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper extends SQLiteOpenHelper {  //for databese
    public static final String DATABASE_NAME = "Dietlist.db";
    public static final String TABLE_NAME = "dietlist_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "DATE";
    public static final String COL_3 = "BREAKFAST";
    public static final String COL_4 = "LUNCH";
    public static final String COL_5 = "DINNER";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {       //create the database
        db.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT, DATE TEXT, BREAKFAST TEXT, LUNCH TEXT, DINNER TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);

    }

    public boolean insertData(String date, String breakfast, String lunch, String dinner) { // insert data to the database
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, date);
        contentValues.put(COL_3, breakfast);
        contentValues.put(COL_4, lunch);
        contentValues.put(COL_5, dinner);
        long result = db.insert(TABLE_NAME,null ,contentValues);
        if (result == -1)         //for return values
            return false;
        else
            return true;
    }
    public Cursor getAllData() {              //Getting all the data list (read)
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }
    public boolean updateData(String id, String date, String breakfast, String lunch, String dinner) {   //Update the data in the database
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,date);
        contentValues.put(COL_3,breakfast);
        contentValues.put(COL_4,lunch);
        contentValues.put(COL_5,dinner);
        db.update(TABLE_NAME, contentValues, "ID = ?",new String[] { id });
        return true;
    }public Integer deleteData(String id) {          //Delete the data in the database
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?",new String[] { id });
    }
}