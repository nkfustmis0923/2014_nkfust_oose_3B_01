package com.example.user.hello;

/**
 * Created by user on 2014/12/26.
 */

import static com.example.user.hello.DbConstants.TABLE_NAME;
import static android.provider.BaseColumns._ID;
import static com.example.user.hello.DbConstants.NAME;

import static com.example.user.hello.DbConstants.TEL;
import static com.example.user.hello.DbConstants.ACC;
import static com.example.user.hello.DbConstants.PWD;
import static com.example.user.hello.DbConstants.ADR;



import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private final static String DATABASE_NAME = "demo.db";
    private final static int DATABASE_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);//建構子覆寫父類別SQLiteOpenHelper
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String INIT_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NAME + " CHAR, " +
                 ACC+ " CHAR, " +
                PWD + " CHAR, " +
                TEL + " CHAR, " +
                ADR + " CHAR"+
                ");";
        db.execSQL(INIT_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(DROP_TABLE);
        onCreate(db);


    }



}

