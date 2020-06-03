package com.example.datacollection;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseBackend extends SQLiteOpenHelper {

    // Storing the table constants in global variables

    public static final String Db_name = "Info_DB.db";
    public static final String Table_name = "BioData_Table";
    public static final String Id = "Id";
    public static final String Name_Col = "Name";
    public static final String Age_Col = "Age";
    public static final int Db_version = 1;       // Initial database version must be 1

    public DatabaseBackend(Context context) {
        super(context, Db_name, null, Db_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + Table_name + "(" + Id + " INTEGER PRIMARY KEY AUTOINCREMENT, " + Name_Col + " TEXT, " + Age_Col + " INTEGER);");
    }

    // onUpgrade is called when the version of the database changes. It contains code to decide what to do with the current database.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Table_name);
        onCreate(db);
    }

    //Defining insertData() function which inserts a new row
    public boolean insertData(String name, int age){
        SQLiteDatabase db = this.getWritableDatabase(); // Creating an instance of SQLiteDatabase. The method getWritableDatabase() creates an editable database.
        ContentValues conVal  =new ContentValues();     // Using ContentValues class to put values into each row
        conVal.put(Name_Col,name);
        conVal.put(Age_Col,age);
        long in_result = db.insert(Table_name,null,conVal);  // db.insert() returns -1 if insert op fails. Else it returns the id of the new row
        return (in_result != -1);
    }

    //Defining getData() function which returns all the data stored in the database in a Cursor object.
    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + Table_name, null);
        return res;
    }
}
