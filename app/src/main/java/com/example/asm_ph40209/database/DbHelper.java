package com.example.asm_ph40209.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    public DbHelper(Context context){
        super(context, "AND102", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String qNguoiDung = "CREATE TABLE NGUOIDUNG(tendangnhap TEXT PRIMARY KEY, matkhau TEXT, hoten TEXT)";
        db.execSQL(qNguoiDung);
        String qSanPham = "CREATE TABLE SANPHAM(masp INTEGER PRIMARY KEY AUTOINCREMENT, tensp TEXT, giaban INTEGER, soluong INTEGER)";
        db.execSQL(qSanPham);

        String dNguoiDung = "INSERT INTO NGUOIDUNG VALUES('truongtdph40209','40209', 'Trinh Dinh Truong')";
        db.execSQL(dNguoiDung);
        String dSanPham = "INSERT INTO SANPHAM VALUES(1,'Bánh quy bơ', 45000, 10)," +
                                                    "(2,'Snack mực lăn', 8000, 52)," +
                                                    "(3,'Snack khoai tây', 12000, 38)," +
                                                    "(4,'Bánh gạo one one', 30000, 12)";
        db.execSQL(dSanPham);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion != newVersion) {
            db.execSQL("drop table if exists NGUOIDUNG");
            db.execSQL("drop table if exists SANPHAM");
            onCreate(db);
        }
    }
}
