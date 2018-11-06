package com.caiomcg.es.Utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class ApplicationDatabase extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "agrotec.db";
    private static final String TABLE = "user";
    private static final String ID = "_id";
    private static final String SERVER_ADDRESS = "server_addr";
    private static int DATABASE_VERSION = 1;

    public ApplicationDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE "+ TABLE + "("
                + ID + " integer primary key autoincrement,"
                + SERVER_ADDRESS + " text,"
                +")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }
}
