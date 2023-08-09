package edu.huflit.appphongtro.ThuePhong;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import edu.huflit.appphongtro.PhongTro.PhongTro;

public class ThuePhongDB {
    String dbName = "PhongTro.db";
    Context context;
    SQLiteDatabase db;

    public ThuePhongDB(Context context) {
        this.context = context;
    }

    public SQLiteDatabase openDB(){
        return context.openOrCreateDatabase(dbName,context.MODE_PRIVATE, null);
    }
    public void createTableThuePhong() {
        String sql = "CREATE TABLE IF NOT EXISTS tblThuePhong(" +
                "idKhach INTEGER," +
                "idPhong INTEGER)";

        db = openDB();
        db.execSQL(sql);
        db.close();
    }


    public int countThuePhong(){
        String sql = "SELECT * FROM tblThuePhong";
        db = openDB();
        Cursor cursor = db.rawQuery(sql,null);
        int count = cursor.getCount();
        return count;
    }

    public void insertThuePhong(int idKhach, int idPhong) {
        db = openDB();
        ContentValues values = new ContentValues();
        values.put("idKhach", idKhach);
        values.put("idPhong", idPhong);
        db.insert("tblThuePhong", null, values);
        db.close();
    }
    public String getTenKhachThuePhong(int idPhong) {
        SQLiteDatabase db = context.openOrCreateDatabase(dbName, Context.MODE_PRIVATE, null);
        String tenKhach = null;
        String idroom = String.valueOf(idPhong);
        String sql = "SELECT idKhach FROM tblThuePhong WHERE idPhong = " + idroom;

        Cursor cursor = db.rawQuery(sql,null);
        if (cursor.moveToFirst()) {
            int idKhach = cursor.getInt(0);
            String idCus =String.valueOf(idKhach);
            // Truy vấn lấy tên của khách hàng từ tblKhach dựa vào idKhach
            Cursor khachCursor = db.rawQuery("SELECT Ten FROM tblKhach WHERE IDKhach = " + idCus,null );
            if (khachCursor.moveToFirst()) {
                tenKhach = khachCursor.getString(0);
            }
            khachCursor.close();
        }

        cursor.close();
        db.close();

        return tenKhach;
    }

    public void deleteThuePhongByKhachID(int idKhach) {
        db = openDB();
        db.delete("tblThuePhong", "idKhach = ?", new String[]{String.valueOf(idKhach)});
        db.close();
    }




    public ArrayList<PhongTro> getRoomsByKhachID(int idKhach) {
        ArrayList<PhongTro> rooms = new ArrayList<>();
        db = openDB();

        String sql = "SELECT * FROM tblPhong WHERE idPhong IN (SELECT idPhong FROM tblThuePhong WHERE idKhach = " + idKhach + ")";
        Cursor cursor = db.rawQuery(sql, null);

        while (cursor.moveToNext()) {
            int maph = cursor.getInt(0);
            int dientich = cursor.getInt(1);
            int giatien = cursor.getInt(2);
            String mota = cursor.getString(3);
            String anh = cursor.getString(4);
            int flat = cursor.getInt(5);
            PhongTro phongTro = new PhongTro(maph, dientich, giatien, mota, anh, flat);
            rooms.add(phongTro);
        }

        cursor.close();
        db.close();

        return rooms;
    }


    public void deleteThuePhong(int idKhach, int idPhong) {
        db = openDB();
        db.delete("tblThuePhong", "idKhach = ? AND idPhong = ?", new String[]{String.valueOf(idKhach), String.valueOf(idPhong)});
        db.close();
    }






}
