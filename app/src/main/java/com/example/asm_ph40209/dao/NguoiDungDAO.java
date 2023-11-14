package com.example.asm_ph40209.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.asm_ph40209.database.DbHelper;

public class NguoiDungDAO {
    private DbHelper dbHelper;
    public NguoiDungDAO(Context context){
        dbHelper = new DbHelper(context);
    }

    //login
    public boolean checkLogin(String username , String password){
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM NGUOIDUNG WHERE tendangnhap = ? AND matkhau = ?", new String[]{username, password});
        return cursor.getCount() > 0;

    }

    //dangki
    public boolean dangki(String username, String password, String hoten){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tendangnhap", username);
        contentValues.put("matkhau", password);
        contentValues.put("hoten", hoten);

        long check = sqLiteDatabase.insert("NGUOIDUNG", null, contentValues);
        return check != -1;





    }


}
