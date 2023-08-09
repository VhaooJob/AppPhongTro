package edu.huflit.appphongtro.Khach;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import edu.huflit.appphongtro.PhongTro.DetailPhong;
import edu.huflit.appphongtro.PhongTro.EditPhong;
import edu.huflit.appphongtro.PhongTro.PhongTro;
import edu.huflit.appphongtro.PhongTro.PhongTroAdapter;
import edu.huflit.appphongtro.PhongTro.PhongTroDB;
import edu.huflit.appphongtro.R;
import edu.huflit.appphongtro.ThuePhong.ThuePhongDB;


public class DetailKhach extends AppCompatActivity {

    ImageView imgKhach;
    TextView tvNameCus,tvAgeCus,tvAddressCus,tvPhoneCus,tvRoomCus;
    Button btnEdit,btnDelete;

    Button btnThueThem, btnHuyThue, btnHuy_ThueThem;
    private boolean isListViewVisible = false;
    Context context = this;



    ListView listViewPhongTro;
    ArrayList<PhongTro> phongTroList;
    ImageButton btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_khach);

        Bundle bundle = getIntent().getExtras();
        if(bundle == null){
            return;
        }


        PhongTroDB phongTroDB = new PhongTroDB(this);

        Khach khach = (Khach) bundle.get("khach");
        imgKhach = findViewById(R.id.imgCus_detailCus);
        tvNameCus = findViewById(R.id.tvName_detailCus);
        tvAgeCus = findViewById(R.id.tvAge_detailCus);
        tvAddressCus = findViewById(R.id.tvAddress_detailCus);
        tvPhoneCus = findViewById(R.id.tvPhone_detailCus);
        tvRoomCus = findViewById(R.id.tvRoom_detailCus);
        btnBack = findViewById(R.id.btnBack_detailCus);
        btnEdit = findViewById(R.id.btnEdit_detailCus);
        btnDelete = findViewById(R.id.btnXoa_detailCus);
        btnHuy_ThueThem = findViewById(R.id.btnHuyThueThem);
        btnThueThem = findViewById(R.id.btnThueThem_detailCus);

        btnHuyThue = findViewById(R.id.btnHuythuephong_detailCus);

        btnThueThem = findViewById(R.id.btnThueThem_detailCus);

        btnThueThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Ẩn các phần tử khác
                listViewPhongTro = findViewById(R.id.lv_ThueThem);
                phongTroList = phongTroDB.getAvailableRooms(); // Lấy danh sách các phòng có flat = 0
                LVThueThemAdapter LVThueThemAdapter = new LVThueThemAdapter(context, phongTroList);
                listViewPhongTro.setAdapter(LVThueThemAdapter);

                imgKhach.setVisibility(View.GONE);
                tvNameCus.setVisibility(View.GONE);
                tvAgeCus.setVisibility(View.GONE);
                tvAddressCus.setVisibility(View.GONE);
                tvPhoneCus.setVisibility(View.GONE);
                tvRoomCus.setVisibility(View.GONE);
                btnEdit.setVisibility(View.GONE);
                btnDelete.setVisibility(View.GONE);
                btnHuyThue.setVisibility(View.GONE);
                btnThueThem.setVisibility(View.GONE);

                // Hiển thị ListView
                listViewPhongTro.setVisibility(View.VISIBLE);
                btnHuy_ThueThem.setVisibility(View.VISIBLE);

                // Đặt sự kiện click cho các phần tử trong ListView
                listViewPhongTro.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        PhongTro selectedPhong = phongTroList.get(position);

                        // Thêm dữ liệu vào bảng tblThuePhong
                        ThuePhongDB thuePhongDB = new ThuePhongDB(context);
                        thuePhongDB.insertThuePhong(khach.getIdKhach(), selectedPhong.getIdPhong());

                        // Cập nhật giá trị flat của phòng trong bảng PhongTro
                        PhongTroDB phongTroDB = new PhongTroDB(context);
                        phongTroDB.updatePhongFlatByID(String.valueOf(selectedPhong.getIdPhong()), 1);

                        // Hiển thị thông báo hoặc thực hiện các hành động khác
                        Toast.makeText(DetailKhach.this, "Thuê phòng thành công", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(DetailKhach.this, ListKhach.class);
                        startActivity(i);
                    }
                });
            }
        });

        btnHuyThue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ThuePhongDB thuePhongDB = new ThuePhongDB(context);
                listViewPhongTro = findViewById(R.id.lv_ThueThem);
                phongTroList = thuePhongDB.getRoomsByKhachID(khach.getIdKhach()); // Lấy danh sách các phòng có flat = 0
                LVThueThemAdapter LVThueThemAdapter = new LVThueThemAdapter(context, phongTroList);
                listViewPhongTro.setAdapter(LVThueThemAdapter);

                imgKhach.setVisibility(View.GONE);
                tvNameCus.setVisibility(View.GONE);
                tvAgeCus.setVisibility(View.GONE);
                tvAddressCus.setVisibility(View.GONE);
                tvPhoneCus.setVisibility(View.GONE);
                tvRoomCus.setVisibility(View.GONE);
                btnEdit.setVisibility(View.GONE);
                btnDelete.setVisibility(View.GONE);
                btnHuyThue.setVisibility(View.GONE);
                btnThueThem.setVisibility(View.GONE);

                // Hiển thị ListView
                listViewPhongTro.setVisibility(View.VISIBLE);
                btnHuy_ThueThem.setVisibility(View.VISIBLE);

                listViewPhongTro.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        // Xử lý khi người dùng ấn vào một mục trong ListView
                        // Lấy phòng đã chọn từ danh sách phòng đang thuê
                        PhongTro selectedPhong = phongTroList.get(position);

                        // Xóa dòng trong bảng tblThuePhong
                        ThuePhongDB thuePhongDB = new ThuePhongDB(context);
                        thuePhongDB.deleteThuePhong(khach.getIdKhach(), selectedPhong.getIdPhong());

                        // Cập nhật biến flat của phòng trong bảng PhongTro
                        PhongTroDB phongTroDB = new PhongTroDB(context);
                        phongTroDB.updatePhongFlatByID(String.valueOf(selectedPhong.getIdPhong()), 0);

                        Toast.makeText(context,"Xóa thành công !",Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(DetailKhach.this,ListKhach.class);
                        startActivity(i);
                    }
                });


            }
        });




        btnHuy_ThueThem = findViewById(R.id.btnHuyThueThem);
        btnHuy_ThueThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });











        KhachDB khachDB = new KhachDB(context);
        khachDB.createTableKhach();



        Glide.with(context)
                .load(khach.getImgKhach())
                .into(imgKhach);
        tvNameCus.setText(khach.getTen());
        tvAgeCus.setText(String.valueOf(khach.getTuoi()));
        tvAddressCus.setText(khach.getQueQuan());
        tvPhoneCus.setText(khach.getDienThoai());
        ArrayList<Integer> roomIDs = getRoomIDsOfCustomer(khach.getIdKhach());
        if (roomIDs.isEmpty()) {
            tvRoomCus.setText("Chưa thuê phòng nào");
        } else {
            // Nếu khách hàng thuê ít nhất một phòng, hiển thị danh sách các ID phòng cách nhau bằng dấu ","
            StringBuilder roomIDsString = new StringBuilder();
            for (int i = 0; i < roomIDs.size(); i++) {
                if (i > 0) {
                    roomIDsString.append(", ");
                }
                roomIDsString.append(roomIDs.get(i));
            }
            tvRoomCus.setText(roomIDsString.toString());
        }



        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailKhach.this, EditKhach.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("khach", khach);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(DetailKhach.this);
                alertDialogBuilder.setTitle("Xác nhận xóa");
                alertDialogBuilder.setMessage("Bạn có chắc chắn muốn xóa khách hàng này?");
                alertDialogBuilder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int idKhach = khach.getIdKhach();

                        ArrayList<Integer> roomIDs = getRoomIDsOfCustomer(idKhach);
                        PhongTroDB phongTroDB = new PhongTroDB(context);
                        for (int roomId : roomIDs) {
                            phongTroDB.updatePhongFlatByID(String.valueOf(roomId), 0);
                        }

                        ThuePhongDB thuePhongDB = new ThuePhongDB(context);
                        thuePhongDB.deleteThuePhongByKhachID(idKhach);

                        KhachDB khachDB = new KhachDB(context);
                        khachDB.deleteKhach(idKhach);

                        Toast.makeText(DetailKhach.this, "Xóa khách thành công", Toast.LENGTH_SHORT).show();

                        dialogInterface.dismiss();

                        Intent intent= new Intent(DetailKhach.this, ListKhach.class);
                        startActivity(intent);
                    }
                });
                alertDialogBuilder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Đóng hộp thoại
                        dialogInterface.dismiss();
                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });














    }
    private ArrayList<Integer> getRoomIDsOfCustomer(int idKhach) {
        ArrayList<Integer> roomIDs = new ArrayList<>();
        ThuePhongDB thuePhongDB = new ThuePhongDB(context);
        SQLiteDatabase db = thuePhongDB.openDB();

        // Truy vấn các ID phòng từ bảng "tblThuePhong" dựa vào idKhach
        String sql = "SELECT idPhong FROM tblThuePhong WHERE idKhach = " + idKhach;
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                int idPhong = cursor.getInt(0);
                roomIDs.add(idPhong);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return roomIDs;
    }
}