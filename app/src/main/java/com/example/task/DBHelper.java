package com.example.task;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.nfc.Tag;
import android.util.Log;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper( Context context) {
        super(context, "Userdata2.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("CREATE TABLE UserDetails(title TEXT primary key, description TEXT, author TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int ii) {
        DB.execSQL("drop Table if exists UserDetails");
    }

    public Boolean insertuserdata(String title, String description, String author)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", title);
        contentValues.put("description", description);
        contentValues.put("author", author);
        long result = DB.insert("UserDetails", null, contentValues);
        if (result == -1)
        {
            return  false;
        }
        else{
            return  true;
        }
    }

    public void deleteData(String title) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        int mytable = sqLiteDatabase.delete("UserDetails", "title = '" + title + "'", null);
        Log.e("delete","data : "+mytable);
    }

    public Boolean updateData(String title,String description, String author)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title",title);
        contentValues.put("description", description);
        contentValues.put("author", author);
       // DB.update("UserDetails",contentValues,"title = ?",new String[]{title});
      //  DB.close();
        return true;
    }

    public Cursor getdata()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from UserDetails", null);
        return  cursor;
    }

}
