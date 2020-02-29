package com.example.cleaningthecity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "CleaningTheCity.db";
    public static final String TABLE_NAME = "records";
    public static final String COL_1 = "score";
    public static final String COL_2 = "name";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
       // SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table "+TABLE_NAME+" (score INTEGER, name TEXT)");
        //sqLiteDatabase.execSQL("create table "+TABLE_NAME_2+" (idNumber INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, age INTEGER, range INTEGER, latitude REAL, longitude REAL, outOfBounds INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        //sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        //sqLiteDatabase.execSQL("ALTER TABLE " + TABLE_NAME + " ADD COLUMN range INTEGER DEFAULT 0");
        //sqLiteDatabase.execSQL("ALTER TABLE " + TABLE_NAME + " ADD COLUMN location TEXT DEFAULT 0");
        //sqLiteDatabase.execSQL("ALTER TABLE " + TABLE_NAME + " ADD COLUMN outOfBounds INTEGER DEFAULT 0");
        //onCreate(sqLiteDatabase);
    }

    public boolean insertDataRecords(int i_score, String i_name) {
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, i_score);
        contentValues.put(COL_2,i_name);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    /*public boolean updateDataRecords(int i_score, String i_name){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, i_score);
        contentValues.put(COL_2, i_name);

        db.update(DatabaseHelper.TABLE_NAME, contentValues, "name = ?", new String[]{i_name});
        return true;
    }*/

    /*public Cursor getDataRecords(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME, null);
        return res;
    }*/

    public ArrayList<Player> getAllData()
    {
        ArrayList<Player> arrayList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from "+TABLE_NAME,null);
        while(cursor.moveToNext())
        {
            int score = cursor.getInt(0);
            String name = cursor.getString(1);
            Player player = new Player(score,name);

            arrayList.add(player);
        }
        Collections.sort(arrayList);
        return arrayList;
    }

    /*public int getOldRecord() {
        int oldScore;
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT Min(score) FROM users;", null);
        oldScore = c.getInt(c.getColumnIndex("score"));
        return oldScore;
    }*/

}