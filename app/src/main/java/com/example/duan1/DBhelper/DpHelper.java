package com.example.duan1.DBhelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DpHelper extends SQLiteOpenHelper {


    public DpHelper(@Nullable Context context) {
        super(context, "ASS_ADR2", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String NguoiDung="CREATE TABLE NGUOIDUNG(tendangnhap TEXT PRIMARY KEY," +
                "matkhau TEXT," +
                "hoten TEXT)";
       db.execSQL(NguoiDung);
        String CongViec = "CREATE TABLE CONGVIEC(macv INTEGER PRIMARY KEY AUTOINCREMENT," +
                "tencv TEXT," +
                "content TEXT," +
                "status TEXT)";
       db.execSQL(CongViec);
        String dNguoiDung = "INSERT INTO NGUOIDUNG VALUES('Dung','123','Nguyen Trung Dung')," +
                "('Giap','699','Do Quang Giap')";
       db.execSQL(dNguoiDung);
        String dCongViec = "INSERT INTO CONGVIEC VALUES(1,'Sản phẩm mới','Nike AirForce 1','500.000VND')," +
                "(2,'Sản phẩm mới','Nike','1.000.000 VND')";
       db.execSQL(dCongViec);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion!=newVersion){
            db.execSQL("DROP TABLE IF EXISTS NGUOIDUNG");
            db.execSQL("DROP TABLE IF EXISTS CONGVIEC");
        }
        onCreate(db);
    }
}
