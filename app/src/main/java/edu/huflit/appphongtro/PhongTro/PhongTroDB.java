package edu.huflit.appphongtro.PhongTro;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class PhongTroDB {
    String dbName = "PhongTro.db";
    Context context;
    SQLiteDatabase db;

    public PhongTroDB(Context context) {
        this.context = context;
    }
    public SQLiteDatabase openDB(){
        return context.openOrCreateDatabase(dbName,context.MODE_PRIVATE, null);
    }
    public void createTable(){
        String sql = "CREATE TABLE IF NOT EXISTS tblPhong(" +
                "IDPHONG INTEGER PRIMARY KEY AUTOINCREMENT," +
                "DIENTICH INTEGER," +
                "GIATIEN INTEGER," +
                "MOTA TEXT," +
                "ANH TEXT," +
                "FLAT INTEGER)";

        db = openDB();
        db.execSQL(sql);
        db.close();
    }

    public int countPhong(){
        String sql = "SELECT * FROM tblPhong";
        db = openDB();
        Cursor cursor = db.rawQuery(sql,null);
        int count = cursor.getCount();
        return count;
    }
    public void insertPhong(int dienTich, int giaTien, String mota, String anh, int flat){
        db = openDB();
        ContentValues cv = new ContentValues();
        cv.put("DIENTICH", dienTich);
        cv.put("GIATIEN", giaTien);
        cv.put("MOTA", mota);
        cv.put("ANH", anh);
        cv.put("FLAT", flat);
        db.insert("tblPhong",null,cv);
        db.close();
    }

    //Select From Where
    public ArrayList<PhongTro> getPhongTroWhere(String idPhong){
        ArrayList<PhongTro> tmp = new ArrayList<>();
        String sql = "SELECT * FROM tblPhong WHERE IDPHONG = '" + idPhong + "'";
        db = openDB();
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()){
            int maph = cursor.getInt(0);
            int dientich = cursor.getInt(1);
            int giatien = cursor.getInt(2);
            String mota = cursor.getString(3);
            String anh = cursor.getString(4);
            int flat = cursor.getInt(5);
            PhongTro phongTro= new PhongTro(maph,dientich,giatien,mota,anh,flat);
            tmp.add(phongTro);
        }
        db.close();
        return tmp;
    }

    //Select * from where
    public ArrayList<PhongTro> getPhongTro(){
        ArrayList<PhongTro> tmp = new ArrayList<>();
        String sql = "SELECT * FROM tblPhong";
        db = openDB();
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()){
            int maph = cursor.getInt(0);
            int dientich = cursor.getInt(1);
            int giatien = cursor.getInt(2);
            String mota = cursor.getString(3);
            String anh = cursor.getString(4);
            int flat = cursor.getInt(5);
            PhongTro phongtro = new PhongTro(maph, dientich, giatien, mota, anh, flat);
            tmp.add(phongtro);
        }
        db.close();
        return tmp;
    }

    //DELETE
    public void delete(String idPhong) {
        db = openDB();
        db.delete("tblPhong", "IDPHONG='" + idPhong + "'", null);
        db.close();
    }

    //UpdatePhong
    public void updatePhong(int idPhong, int dientich, int giatien, String mota, String anh, int flat){
        db = openDB();
        ContentValues cv = new ContentValues();
        cv.put("IDPHONG",idPhong);
        cv.put("DIENTICH", dientich);
        cv.put("GIATIEN", giatien);
        cv.put("MOTA", mota);
        cv.put("ANH", anh);
        cv.put("FLAT", flat);
        db.update("tblPhong",cv,"IDPHONG = '" + idPhong + "'",null);
        db.close();
    }

    public void updatePhongFlatByID(String idPhong, int flat) {
        db = openDB();
        ContentValues cv = new ContentValues();
        cv.put("FLAT", flat);
        db.update("tblPhong", cv, "IDPHONG = ?", new String[]{idPhong});
        db.close();
    }






    public ArrayList<PhongTro> getAvailableRooms() {
        ArrayList<PhongTro> availableRooms = new ArrayList<>();
        db = openDB();

        String sql = "SELECT * FROM tblPhong WHERE FLAT = 0";
        Cursor cursor = db.rawQuery(sql, null);

        while (cursor.moveToNext()) {
            int maph = cursor.getInt(0);
            int dientich = cursor.getInt(1);
            int giatien = cursor.getInt(2);
            String mota = cursor.getString(3);
            String anh = cursor.getString(4);
            int flat = cursor.getInt(5);
            PhongTro phongTro = new PhongTro(maph, dientich, giatien, mota, anh, flat);
            availableRooms.add(phongTro);
        }

        cursor.close();
        db.close();

        return availableRooms;
    }


}
