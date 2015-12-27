package com.android.jianhua.hidenwindows.Tools;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import static android.provider.BaseColumns._ID;

/**
 * Created by Chienhua on 2015/12/27.
 */
public class SQLite extends SQLiteOpenHelper {
    final static String database = "localdata.db";
    final static int version = 3;

    // 內建的建構子，用來建立資料庫，但要傳入的參數有點多，所以我們改用自己的
    public SQLite(Context context, String name, SQLiteDatabase.CursorFactory factory,
                      int version) {
        super(context, name, factory, version);
        // TODO Auto-generated constructor stub
    }

    // 自建的建構子，只需傳入一個Context物件即可
    public SQLite(Context context) {
        super(context, database, null, version);
    }
    //建立資料表
    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL("CREATE TABLE api(_id integer primary key autoincrement," +
                "api text no null," +
                "json text no null)");
        db.execSQL("CREATE TABLE chat(_id integer primary key autoincrement," +
                "chat_id text no null," +
                "json text no null)");
        db.execSQL("CREATE TABLE record(_id integer primary key autoincrement," +
                "chat_id text no null," +
                "json text no null)");

    }

    //資料庫更新，刪除資料表，再次呼叫onCreate()重建資料表
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldversion, int newversion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS api");
        db.execSQL("DROP TABLE IF EXISTS chat");
        db.execSQL("DROP TABLE IF EXISTS record");
        onCreate(db);
    }

}