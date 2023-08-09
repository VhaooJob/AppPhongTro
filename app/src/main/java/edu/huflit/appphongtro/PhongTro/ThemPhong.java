package edu.huflit.appphongtro.PhongTro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import edu.huflit.appphongtro.HoaDon.ListHoaDon;
import edu.huflit.appphongtro.Khach.Khach;
import edu.huflit.appphongtro.Khach.KhachDB;
import edu.huflit.appphongtro.Khach.ListKhach;
import edu.huflit.appphongtro.R;

public class ThemPhong extends AppCompatActivity {



    PhongTroDB phongTroDB;
    EditText edtDientich, edtGiatien,edtMota, edtLinkanh;
    Button btnThem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_phong);


        phongTroDB = new PhongTroDB(this);
        edtDientich = findViewById(R.id.edtDienTich_AddRoom);
        edtGiatien = findViewById(R.id.edtGiaTien_AddRoom);
        edtMota = findViewById(R.id.edtMota_addRoom);
        edtLinkanh = findViewById(R.id.edtLinkAnh_AddRoom);
        btnThem = findViewById(R.id.btnThemPhong);
        ImageButton btnback = findViewById(R.id.btnBack_addroom);


        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ThemPhong();
            }
        });



        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigationView);
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.dsphong:
                        Intent i = new Intent(ThemPhong.this,MainActivity.class);
                        startActivity(i);
                        return true;
                    case R.id.dskhach:
                        Intent intent = new Intent(ThemPhong.this, ListKhach.class);
                        startActivity(intent);
                        return true;
                    case R.id.dshoadon:
                        Intent intent1 = new Intent(ThemPhong.this, ListHoaDon.class);
                        startActivity(intent1);
                        return true;
                    default:
                        return false;

                }
            }
        });


    }
    private void ThemPhong() {
        String dienTichStr = edtDientich.getText().toString().trim();
        String giaTienStr = edtGiatien.getText().toString().trim();
        String moTa = edtMota.getText().toString().trim();
        String linkAnh = edtLinkanh.getText().toString().trim();

        // Kiểm tra xem các trường có được nhập liệu hay không
        if (dienTichStr.isEmpty() || giaTienStr.isEmpty() || moTa.isEmpty() || linkAnh.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
            return; // Dừng việc thêm phòng nếu có trường trống
        }
        else if (!isNumeric(dienTichStr)) {
            // Hiển thị thông báo nếu Diện tích hoặc Giá tiền không là số
            Toast.makeText(this, "Diện tích phải là số!", Toast.LENGTH_SHORT).show();
            return;
        }
        else if (!isNumeric(giaTienStr)) {
            Toast.makeText(this, "Giá tiền phải là số", Toast.LENGTH_SHORT).show();
            return;
        }
        else if (!isValidUrl(linkAnh)) {
            // Hiển thị thông báo nếu Link ảnh không hợp lệ
            Toast.makeText(this, "Link ảnh không hợp lệ!", Toast.LENGTH_SHORT).show();
            return;
        }

        int dienTich = Integer.parseInt(dienTichStr);
        int giaTien = Integer.parseInt(giaTienStr);
        int flat = 0;
        phongTroDB.insertPhong(dienTich, giaTien, moTa, linkAnh, flat);
        Toast.makeText(this, "Thêm phòng thành công!", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    private boolean isNumeric(String str) {
        return str.matches("\\d+");
    }

    private boolean isValidUrl(String url) {
        // Kiểm tra xem đường dẫn có hợp lệ hay không
        return android.util.Patterns.WEB_URL.matcher(url).matches();
    }

}