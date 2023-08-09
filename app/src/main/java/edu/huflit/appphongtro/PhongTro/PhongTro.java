package edu.huflit.appphongtro.PhongTro;

import java.io.Serializable;

public class PhongTro implements Serializable {
    private int idPhong;
    private int dienTich;
    private int giatien;
    private String mota;
    private String image;
    private int flat;


    public PhongTro(int idPhong, int dienTich, int giatien, String mota, String image, int flat) {
        this.idPhong = idPhong;
        this.dienTich = dienTich;
        this.giatien = giatien;
        this.mota = mota;
        this.image = image;
        this.flat = flat;
    }

    public int getIdPhong() {
        return idPhong;
    }

    public int getDienTich() {
        return dienTich;
    }

    public int getGiatien() {
        return giatien;
    }

    public String getMota() {
        return mota;
    }

    public String getImage() {
        return image;
    }

    public int getFlat() {
        return flat;
    }


    public void setIdPhong(int idPhong) {
        this.idPhong = idPhong;
    }

    public void setDienTich(int dienTich) {
        this.dienTich = dienTich;
    }

    public void setGiatien(int giatien) {
        this.giatien = giatien;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setFlat(int flat) {
        this.flat = flat;
    }

}
