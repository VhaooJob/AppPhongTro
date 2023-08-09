package edu.huflit.appphongtro.Khach;

import java.io.Serializable;

public class Khach implements Serializable {
    private int idKhach;
    private String ten;
    private int tuoi;
    private String queQuan;
    private String dienThoai;

    private String imgKhach;

    public Khach(int idKhach, String ten, int tuoi, String queQuan, String dienThoai, String imgKhach) {
        this.idKhach = idKhach;
        this.ten = ten;
        this.tuoi = tuoi;
        this.queQuan = queQuan;
        this.dienThoai = dienThoai;
        this.imgKhach = imgKhach;
    }

    public int getIdKhach() {
        return idKhach;
    }

    public String getTen() {
        return ten;
    }

    public int getTuoi() {
        return tuoi;
    }

    public String getQueQuan() {
        return queQuan;
    }

    public String getDienThoai() {
        return dienThoai;
    }

    public String getImgKhach() {
        return imgKhach;
    }

    public void setIdKhach(int idKhach) {
        this.idKhach = idKhach;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public void setTuoi(int tuoi) {
        this.tuoi = tuoi;
    }

    public void setQueQuan(String queQuan) {
        this.queQuan = queQuan;
    }

    public void setDienThoai(String dienThoai) {
        this.dienThoai = dienThoai;
    }

    public void setImgKhach(String imgKhach) {
        this.imgKhach = imgKhach;
    }
}
