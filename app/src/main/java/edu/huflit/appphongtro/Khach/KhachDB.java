package edu.huflit.appphongtro.Khach;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.Image;

import java.util.ArrayList;

public class KhachDB {
        String dbName = "PhongTro.db";
        Context context;
        SQLiteDatabase db;

        public KhachDB(Context context) {
            this.context = context;
        }

        public SQLiteDatabase openDB(){
            return context.openOrCreateDatabase(dbName,context.MODE_PRIVATE, null);
        }

        public void createTableKhach() {
            String sql = "CREATE TABLE IF NOT EXISTS tblKhach(" +
                    "IDKhach INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "Ten TEXT," +
                    "Tuoi INTEGER," +
                    "QueQuan TEXT," +
                    "DienThoai TEXT," +
                    "ImageCus TEXT)";
            db = openDB();
            db.execSQL(sql);
            db.close();
        }

        public int countKhach(){
            String sql = "SELECT * FROM tblKhach";
            db = openDB();
            Cursor cursor = db.rawQuery(sql,null);
            int count = cursor.getCount();
            return count;
        }


    public ArrayList<Khach> getKhach() {
        ArrayList<Khach> tmp = new ArrayList<>();
        String sql = "SELECT * FROM tblKhach";
        db = openDB();
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            int idKhach = cursor.getInt(0);
            String ten = cursor.getString(1);
            int tuoi = cursor.getInt(2);
            String queQuan = cursor.getString(3);
            String dienThoai = cursor.getString(4);
            String imageCus = cursor.getString(5);
            Khach khach = new Khach(idKhach,ten,tuoi,queQuan,dienThoai,imageCus);
            tmp.add(khach);
        }
        cursor.close();
        db.close();
        return tmp;
        }



        public void insertKhach(String ten, int tuoi, String quequan, String dienthoai,String ImageCus) {
            db = openDB();
            ContentValues values = new ContentValues();
            values.put("Ten", ten);
            values.put("Tuoi", tuoi);
            values.put("QueQuan", quequan);
            values.put("DienThoai", dienthoai);
            values.put("ImageCus", ImageCus);
            db.insert("tblKhach", null, values);
            db.close();
        }

        public void updateKhach(int IDKhach ,String ten, int tuoi, String quequan, String dienthoai, String ImageCus) {
        db = openDB();

        ContentValues values = new ContentValues();
        values.put("Ten", ten);
        values.put("Tuoi", tuoi);
        values.put("QueQuan", quequan);
        values.put("DienThoai", dienthoai);
        values.put("ImageCus", ImageCus);
        db.update("tblKhach",values,"IDKHACH = '" + IDKhach + "'",null);
        db.close();
    }
    public void deleteKhach(int IDKhach) {
        db = openDB();
        db.delete("tblKhach", "IDKhach = ?", new String[]{String.valueOf(IDKhach)});
        db.close();
    }







}
