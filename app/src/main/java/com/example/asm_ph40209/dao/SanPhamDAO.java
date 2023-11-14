package com.example.asm_ph40209.dao;

import static android.content.ContentValues.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.asm_ph40209.database.DbHelper;
import com.example.asm_ph40209.model.trangchu;

import java.util.ArrayList;

public class SanPhamDAO {
    private DbHelper dbHelper;
    public SanPhamDAO(Context context){
        dbHelper = new DbHelper(context);
    }

    //lay sp
    public ArrayList<trangchu> selectALL(){
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        ArrayList<trangchu> list = new ArrayList<>();

        try{
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM SANPHAM", null);
            if(cursor.getCount()>0){
                cursor.moveToFirst(); //dua con tro ve dong dau tien
                while (!cursor.isAfterLast()){
                    trangchu home = new trangchu();//tao doi tuong
                    //nhap thong tin cho doi tuong
                    home.setMasp(cursor.getInt(0));
                    home.setTensp(cursor.getString(1));
                    home.setGiaban(cursor.getString(2));
                    home.setSoluong(cursor.getString(3));
                    list.add(home);
                    cursor.moveToNext();//next con tro dong tiep theo
                }
            }
        }catch (Exception e){
            Log.i(TAG, "Lỗi", e);
        }

        return list;
    }

    //add du lieu vao bang SANPHAM
    public boolean add(trangchu home){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tensp",home.getTensp());
        values.put("giaban", home.getGiaban());
        values.put("soluong", home.getSoluong());
        long row = sqLiteDatabase.insert("SANPHAM", null, values);
        return row != -1;
    }


    //update du lieu vao bang SANPHAM
    public boolean update(trangchu home){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tensp",home.getTensp());
        values.put("giaban", home.getGiaban());
        values.put("soluong", home.getSoluong());
        long row = sqLiteDatabase.update("SANPHAM", values, "masp = ?", new String[]{String.valueOf(home.getMasp())});
        if (row <= 0){
            return false;
        }
        return true;
    }

    //xoa du lieu trong bang SANPHAM
    public boolean delete(int masp){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        long row = sqLiteDatabase.delete("SANPHAM","masp=?",new String[]{String.valueOf(masp)});
        if (row <= 0){
            return false;
        }
        return true;
    }


}
