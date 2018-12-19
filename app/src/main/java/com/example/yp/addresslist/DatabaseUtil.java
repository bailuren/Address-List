package com.example.yp.addresslist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseUtil extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "PhoneBook.db";  //数据库名
    private static final int DATABASE_VERSION = 1;               //数据库版本号

    public DatabaseUtil(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * 建立数据表
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        createTable(db);
    }
    private void createTable(SQLiteDatabase db){
        db.execSQL("create table UserInfo(" +
                "id integer primary key autoincrement," +
                "userName text," +
                "userPhone text)");
    }

    /**
     * 升级数据库
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //onUpgrade(db,oldVersion,newVersion);
    }
}
