package com.mycompany.databaseclasses;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by my on 8/26/2015.
 */
public class CreateTable extends SQLiteOpenHelper {

    private static final String TAG = "GenieDatabase";
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "genie_db";

    public static final String TABLE_USERS = "users";
    public static final String USER_ID = "user_id";
    public static final String USER_NAME = "user_name";
    public static final String USER_PHONE = "user_phone";

    private static final String CREATE_TABLE_USERS =
            "CREATE TABLE" + TABLE_USERS
            + "(" +USER_ID + "INTEGER PRIMARY KEY AUTOINCREMENT,"
            + USER_NAME + "TEXT NOT NULL"
            + USER_PHONE + "TEXT NOT NULL );";

    public CreateTable(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USERS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_USERS);
        onCreate(db);

    }

    public void insertUser(String userName, String userPhone){
        SQLiteDatabase sqLiteDatabase = null;
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_USERS + " VALUES('" + userName + "', '" + userPhone + "');");
    }

    public String selectUser(String userName){
        String uName = "";
        SQLiteDatabase sqLiteDatabase = null;
       Cursor cursor = sqLiteDatabase.rawQuery("SELECT " + USER_ID + " FROM " + TABLE_USERS + " WHERE " + USER_NAME + " =" + userName,null);

        if(cursor!=null){
            if(cursor.moveToFirst()){
                do{
                    uName = cursor.getString(cursor.getColumnIndex("USER_NAME"));
                }while (cursor.moveToNext());
            }
        }
        return  uName;
    }
}
