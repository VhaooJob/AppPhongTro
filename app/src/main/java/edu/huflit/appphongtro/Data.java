package edu.huflit.appphongtro;

import android.app.Application;

import java.util.ArrayList;

import edu.huflit.appphongtro.HoaDon.HoaDon;
import edu.huflit.appphongtro.HoaDon.HoaDonDB;
import edu.huflit.appphongtro.Khach.KhachDB;
import edu.huflit.appphongtro.PhongTro.PhongTro;
import edu.huflit.appphongtro.PhongTro.PhongTroDB;
import edu.huflit.appphongtro.ThuePhong.ThuePhongDB;

public class Data extends Application {
    PhongTroDB phongTroDB;
    KhachDB khachDB;
    HoaDonDB hoaDonDB;
    ThuePhongDB thuePhongDB;
    ArrayList<PhongTro> lstPhong;

    @Override
    public void onCreate() {
        super.onCreate();
        phongTroDB = new PhongTroDB(this);
        phongTroDB.createTable();
        if(phongTroDB.countPhong() == 0){
            phongTroDB.insertPhong(30,3000000,"Phòng đẹp, thoáng mát, có ban công, có cửa sổ","https://timescityminhkhai.com/wp-content/uploads/sites/4/2020/10/phong-tro-cho-thue.jpg",1);
            phongTroDB.insertPhong(30,3000000,"Phòng đẹp, thoáng mát, có ban công, có cửa sổ","https://timescityminhkhai.com/wp-content/uploads/sites/4/2020/10/phong-tro-cho-thue.jpg",1);
            phongTroDB.insertPhong(30,3000000,"Phòng đẹp, thoáng mát, có ban công, có cửa sổ","https://timescityminhkhai.com/wp-content/uploads/sites/4/2020/10/phong-tro-cho-thue.jpg",1);
            phongTroDB.insertPhong(32,3500000,"Phòng đẹp,full nội thất, rộng, thoáng mát, có ban công, có cửa sổ","https://timescityminhkhai.com/wp-content/uploads/sites/4/2020/10/phong-tro-cho-thue.jpg",1);
            phongTroDB.insertPhong(32,3500000,"Phòng đẹp,full nội thất, rộng, thoáng mát, có ban công, có cửa sổ","https://timescityminhkhai.com/wp-content/uploads/sites/4/2020/10/phong-tro-cho-thue.jpg",1);
            phongTroDB.insertPhong(32,3500000,"Phòng đẹp,full nội thất, rộng, thoáng mát, có ban công, có cửa sổ","https://timescityminhkhai.com/wp-content/uploads/sites/4/2020/10/phong-tro-cho-thue.jpg",0);
            phongTroDB.insertPhong(32,3500000,"Phòng đẹp,full nội thất, rộng, thoáng mát, có ban công, có cửa sổ","https://timescityminhkhai.com/wp-content/uploads/sites/4/2020/10/phong-tro-cho-thue.jpg",1);

        }


        khachDB = new KhachDB(this);
        khachDB.createTableKhach();
        if(khachDB.countKhach() == 0){
            khachDB.insertKhach("Trịnh Kiết Tường",20,"Đắk Lắk", "0967402772","https://scontent.fsgn5-8.fna.fbcdn.net/v/t1.6435-9/118664749_392522491717471_3032720511942260065_n.jpg?_nc_cat=109&cb=99be929b-59f725be&ccb=1-7&_nc_sid=ad2b24&_nc_ohc=AAMWG2oMJl4AX_Wgb8n&_nc_ht=scontent.fsgn5-8.fna&oh=00_AfDoMNSiTtMgK9aDSw-DmOf4IQqjZKkD6J9PyEyrdpcR5A&oe=64EEEEA4");
            khachDB.insertKhach("Dương Văn Đức",20,"Biên Hòa, Đồng Nai", "0853535353","https://scontent.fsgn5-8.fna.fbcdn.net/v/t1.6435-9/118664749_392522491717471_3032720511942260065_n.jpg?_nc_cat=109&cb=99be929b-59f725be&ccb=1-7&_nc_sid=ad2b24&_nc_ohc=AAMWG2oMJl4AX_Wgb8n&_nc_ht=scontent.fsgn5-8.fna&oh=00_AfDoMNSiTtMgK9aDSw-DmOf4IQqjZKkD6J9PyEyrdpcR5A&oe=64EEEEA4");
            khachDB.insertKhach("Lê Long Tân",20,"Nha Trang", "0912345678","https://scontent.fsgn5-8.fna.fbcdn.net/v/t1.6435-9/118664749_392522491717471_3032720511942260065_n.jpg?_nc_cat=109&cb=99be929b-59f725be&ccb=1-7&_nc_sid=ad2b24&_nc_ohc=AAMWG2oMJl4AX_Wgb8n&_nc_ht=scontent.fsgn5-8.fna&oh=00_AfDoMNSiTtMgK9aDSw-DmOf4IQqjZKkD6J9PyEyrdpcR5A&oe=64EEEEA4");
            khachDB.insertKhach("Nguyễn Hoàng Anh",20,"Tp.Hồ Chí Minh", "0987696969","https://scontent.fsgn5-8.fna.fbcdn.net/v/t1.6435-9/118664749_392522491717471_3032720511942260065_n.jpg?_nc_cat=109&cb=99be929b-59f725be&ccb=1-7&_nc_sid=ad2b24&_nc_ohc=AAMWG2oMJl4AX_Wgb8n&_nc_ht=scontent.fsgn5-8.fna&oh=00_AfDoMNSiTtMgK9aDSw-DmOf4IQqjZKkD6J9PyEyrdpcR5A&oe=64EEEEA4");
            khachDB.insertKhach("Phạm Công Thịnh",20,"Tp.Hồ Chí Minh", "0987654321","https://scontent.fsgn5-8.fna.fbcdn.net/v/t1.6435-9/118664749_392522491717471_3032720511942260065_n.jpg?_nc_cat=109&cb=99be929b-59f725be&ccb=1-7&_nc_sid=ad2b24&_nc_ohc=AAMWG2oMJl4AX_Wgb8n&_nc_ht=scontent.fsgn5-8.fna&oh=00_AfDoMNSiTtMgK9aDSw-DmOf4IQqjZKkD6J9PyEyrdpcR5A&oe=64EEEEA4");
            khachDB.insertKhach("Spider Test",20,"XXXX","XXXXXX","https://assetsio.reedpopcdn.com/Spider-Banner_AVVWjOb.jpg?width=1200&height=630&fit=crop&enable=upscale&auto=webp");
        }

        thuePhongDB = new ThuePhongDB(this);
        thuePhongDB.createTableThuePhong();
        if(thuePhongDB.countThuePhong() == 0){
            thuePhongDB.insertThuePhong(1,1);
            thuePhongDB.insertThuePhong(1,2);
            thuePhongDB.insertThuePhong(2,3);
            thuePhongDB.insertThuePhong(3,4);
            thuePhongDB.insertThuePhong(4,5);
            thuePhongDB.insertThuePhong(5,7);
        }


        hoaDonDB = new HoaDonDB(this);
        hoaDonDB.createTableHoaDon();
        if(hoaDonDB.countHoaDon() == 0){
            hoaDonDB.insertHoaDon(1,1,132,100000,3000000,3496000d);
            hoaDonDB.insertHoaDon(2,1,120,100000,3000000,3460000d);
            hoaDonDB.insertHoaDon(3,2,140,100000,3000000,3520000d);
        }



    }


}
