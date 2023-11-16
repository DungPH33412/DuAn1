package com.example.duan1.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan1.DBhelper.DpHelper;


public class UserDao {
    private DpHelper dpHelper;

    public UserDao(Context context){
        dpHelper = new DpHelper(context);
    }
    public boolean LoginCheck(String username, String password){
        SQLiteDatabase sqLiteDatabase = dpHelper.getReadableDatabase();// get read được sử dụng khi đọc dữ liệu không ảnh hưởng đến database: truy vấn thông tin

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM NGUOIDUNG WHERE tendangnhap = ? AND matkhau =?",new String[]{username,password});
        if (cursor.getCount()>0){
            return true;
        }
        return false;
    }
    public  boolean Dangki(String username,String password,String hoten) {
        SQLiteDatabase database = dpHelper.getWritableDatabase();// getwri được sử dụng khi thêm dữ liệu
        ContentValues values = new ContentValues();
        values.put("tendangnhap", username);
        values.put("matkhau", password);
        values.put("hoten",hoten);
        long check = database.insert("NGUOIDUNG", null, values);
        if (check != -1) {
            return true;
        }
        return false;
    }
    public  String laymatkhau(String email){
        SQLiteDatabase database = dpHelper.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT matkhau FROM NGUOIDUNG WHERE tendangnhap= ? ",new String[]{email});
        if (cursor.getCount()>0){//ko lấy bất kì thông tin nào trong database
            cursor.moveToFirst();//di chuyển con trỏ lên hàng đầu
            return  cursor.getString(0);//
        }else {
            return "";
        }
    }

}
