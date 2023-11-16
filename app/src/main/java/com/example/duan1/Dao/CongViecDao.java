package com.example.duan1.Dao;

import static android.content.ContentValues.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import com.example.duan1.DBhelper.DpHelper;
import com.example.duan1.DTO.CongViec;

import java.util.ArrayList;


public class CongViecDao {
    private DpHelper dpHelper;
    public CongViecDao(Context context){
        dpHelper = new DpHelper(context);
    }
    public ArrayList<CongViec> getALL(){
        ArrayList<CongViec> list = new ArrayList<>();
        SQLiteDatabase database = dpHelper.getReadableDatabase();
        try{
            Cursor cursor = database.rawQuery("select*from CONGVIEC",null);
            if (cursor.getCount()>0){
                cursor.moveToFirst();
                while (!cursor.isAfterLast()){
                    CongViec congViec = new CongViec();
                    congViec.setId(cursor.getInt(0));
                    congViec.setTenCV(cursor.getString(1));
                    congViec.setContent(cursor.getString(2));
                    congViec.setStatus(cursor.getString(3));
                     list.add(congViec);
                     cursor.moveToNext();
                }
            }
        }catch (Exception e){
            Log.i(TAG,"loi",e);
        }
        return  list;
    }
    public boolean insertdata(CongViec cv){
        SQLiteDatabase database = dpHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tencv",cv.getTenCV());
        values.put("content",cv.getContent());
        values.put("status",cv.getStatus());
        long row = database.insert("CONGVIEC",null,values);
        return (row>0);
    }
    public boolean updatedata(CongViec cv){
        SQLiteDatabase database = dpHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tencv",cv.getTenCV());
        values.put("content",cv.getContent());
        values.put("status",cv.getStatus());
        long row = database.update("CONGVIEC",values,"macv=?",
                new String[]{String.valueOf(cv.getId())});
        return (row>0);
    }
    public boolean deletedata(int macv){
        SQLiteDatabase database = dpHelper.getWritableDatabase();
        long row = database.delete("CONGVIEC","macv=?",new String[]{String.valueOf(macv)});
        return (row>0);
    }
}
