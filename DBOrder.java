package com.example.user.hello;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.provider.BaseColumns._ID;
import static com.example.user.hello.DbConstants.GRAYC1;
import static com.example.user.hello.DbConstants.GRAYC2;
import static com.example.user.hello.DbConstants.JEANS;
import static com.example.user.hello.DbConstants.NYLON;
import static com.example.user.hello.DbConstants.TABLE_ORDER;
import static com.example.user.hello.DbConstants.TSHIRT;

/**
 * Created by user on 2014/12/28.
 */
public class DBOrder extends SQLiteOpenHelper {
    private final static String DATABASE_NAME = "order.db";
    private final static int DATABASE_VERSION = 1;
    public DBOrder(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);//建構子覆寫父類別SQLiteOpenHelper
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        final String ORDER_TABLE = "CREATE TABLE " + TABLE_ORDER + " ("+
                _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                GRAYC1 + " CHAR, "+
                GRAYC2 + " CHAR, "+
                NYLON + " CHAR, "+
                TSHIRT + " CHAR, "+
                JEANS + " CHAR);";
        db.execSQL(ORDER_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_ORDER;
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }
}
