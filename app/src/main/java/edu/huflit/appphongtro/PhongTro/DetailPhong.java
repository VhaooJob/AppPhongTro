package edu.huflit.appphongtro.PhongTro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import edu.huflit.appphongtro.Khach.KhachDB;
import edu.huflit.appphongtro.R;
import edu.huflit.appphongtro.ThuePhong.ThuePhongDB;
import androidx.appcompat.app.AlertDialog;

public class DetailPhong extends AppCompatActivity {


    TextView tvidPhong, tvMota,tvDienTich, tvGiaTien,tvTinhTrang,tvKhach;
    ImageView imgRoom;

    ImageButton btnBack;

    ThuePhongDB thuePhongDB = new ThuePhongDB(this);

    Button btnEdit, btnDelete;
    Context context = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_phong);

        Bundle bundle = getIntent().getExtras();
        if(bundle == null){
            return;
        }
        PhongTro phongTro = (PhongTro) bundle.get("phongTro");


        thuePhongDB.openDB();

        tvidPhong = findViewById(R.id.tvIDPhong_DetailRoom);
        tvMota = findViewById(R.id.tvMoTa_detail);
        tvDienTich = findViewById(R.id.tvDienTich_detailRoom);
        tvGiaTien = findViewById(R.id.tvGiaTien_detailRoom);
        tvTinhTrang = findViewById(R.id.tvTinhTrang_DetailRoom);
        tvKhach = findViewById(R.id.tvCusName_DetailRoom);
        imgRoom = findViewById(R.id.imgRoom_detail);

        btnEdit = findViewById(R.id.btnChinhSua_DetailRoom);
        btnDelete = findViewById(R.id.btnXoaPhong_detailRoom);



        tvidPhong.setText(String.valueOf(phongTro.getIdPhong()));
        tvMota.setText(phongTro.getMota());
        tvDienTich.setText(String.valueOf(phongTro.getDienTich()));
        tvGiaTien.setText(String.valueOf(phongTro.getGiatien()));

        int flat = phongTro.getFlat();
        if(flat == 1){
            tvTinhTrang.setText("Có người thuê");
            tvTinhTrang.setTextColor(ContextCompat.getColor(this,R.color.flat1));
            tvKhach.setText(thuePhongDB.getTenKhachThuePhong(phongTro.getIdPhong()));
        }
        else if(flat == 0){
            tvTinhTrang.setText("Chưa có người thuê");
            tvTinhTrang.setTextColor(ContextCompat.getColor(this,R.color.flat0));
            tvKhach.setText("Chưa có");
        }

        String img = phongTro.getImage();
        Glide.with(getApplicationContext())
                .load(img)
                .into(imgRoom);

        btnBack = findViewById(R.id.btnBack_detailRoom);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailPhong.this, EditPhong.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("phongtro", phongTro);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Xác nhận xóa");
                builder.setMessage("Bạn có chắc chắn muốn xóa phòng này?");

                // Nút xác nhận xóa
                builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int id = phongTro.getIdPhong();
                        int flat = phongTro.getFlat();
                        PhongTroDB phongTroDB = new PhongTroDB(context);
                        phongTroDB.createTable();

                        if (flat == 0) {
                            // Xóa phòng có ID tương ứng
                            phongTroDB.delete(String.valueOf(id));

                            // Lấy danh sách toàn bộ các phòng
                            ArrayList<PhongTro> listPhongTro = phongTroDB.getPhongTro();

                            // Cập nhật lại ID của các phòng
                            int newId = 1;
                            for (PhongTro pt : listPhongTro) {
                                phongTroDB.updatePhong(newId, pt.getDienTich(), pt.getGiatien(), pt.getMota(), pt.getImage(), pt.getFlat());
                                newId++;
                            }

                            Toast.makeText(context, "Xóa phòng thành công !", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(context, MainActivity.class);
                            startActivity(intent);
                        }
                        if (flat == 1) {
                            Toast.makeText(context,"Phòng đang có người thuê! Không được xóa!",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                // Nút hủy
                builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Đóng hộp thoại
                        dialogInterface.dismiss();
                    }
                });

                // Tạo và hiển thị hộp thoại xác nhận
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });


    }
}