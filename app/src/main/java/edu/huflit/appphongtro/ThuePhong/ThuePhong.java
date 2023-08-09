package edu.huflit.appphongtro.ThuePhong;

import java.io.Serializable;

public class ThuePhong implements Serializable {
    private int idKhach;
    private int idPhong;

    public ThuePhong(int idKhach, int idPhong) {
        this.idKhach = idKhach;
        this.idPhong = idPhong;
    }

    public int getIdKhach() {
        return idKhach;
    }

    public int getIdPhong() {
        return idPhong;
    }

    public void setIdKhach(int idKhach) {
        this.idKhach = idKhach;
    }

    public void setIdPhong(int idPhong) {
        this.idPhong = idPhong;
    }
}
