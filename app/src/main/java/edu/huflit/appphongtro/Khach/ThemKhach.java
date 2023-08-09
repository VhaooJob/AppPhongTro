package edu.huflit.appphongtro.Khach;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import edu.huflit.appphongtro.PhongTro.EditPhong;
import edu.huflit.appphongtro.R;

public class ThemKhach extends AppCompatActivity {

    EditText edtTen;
    EditText edtTuoi;
    EditText edtQueQuan;
    EditText edtPhone;
    EditText edtAnh;

    Button btnXong;

    KhachDB khachDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_khach);

        edtTen = findViewById(R.id.edtTenKhach_addCus);
        edtTuoi = findViewById(R.id.edtTuoiKhach_addCus);
        edtQueQuan = findViewById(R.id.edtQueQuan_addCus);
        edtPhone = findViewById(R.id.edtDienThoai_addCus);
        edtAnh = findViewById(R.id.edtImage_addCus);

        khachDB = new KhachDB(this);

        btnXong = findViewById(R.id.btnXong_addCus);

        btnXong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ThemKhach();
            }
        });

    }

    public void ThemKhach(){
        boolean isValid = true; // Biến cờ để kiểm tra hợp lệ

        String Ten = edtTen.getText().toString();
        String Tuoi = edtTuoi.getText().toString();
        String QueQuan = edtQueQuan.getText().toString();
        String Phone = edtPhone.getText().toString();
        String Anh = edtAnh.getText().toString();

        if (Ten.isEmpty() || Tuoi.isEmpty() || QueQuan.isEmpty() || Phone.isEmpty() || Anh.isEmpty()) {
            isValid = false;
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
        }

        if (!Ten.matches("[a-zA-Z]+")) {
            isValid = false;
            Toast.makeText(this, "Tên phải là chữ, không chứa số và ký tự đặc biệt!", Toast.LENGTH_SHORT).show();
        }

        if (!Tuoi.matches("\\d+")) {
            isValid = false;
            Toast.makeText(this, "Tuổi phải là số!", Toast.LENGTH_SHORT).show();
        }

        if (!QueQuan.matches("[a-zA-Z]+")) {
            isValid = false;
            Toast.makeText(this, "Quê quán không chứa số!", Toast.LENGTH_SHORT).show();
        }

        if (!Phone.matches("\\d{10}")) {
            isValid = false;
            Toast.makeText(this, "Số điện thoại phải là 10 chữ số!", Toast.LENGTH_SHORT).show();
        }
        else if (!isValidUrl(Anh)) {
            isValid = false;
            // Hiển thị thông báo nếu Link ảnh không hợp lệ
            Toast.makeText(this, "Link ảnh không hợp lệ!", Toast.LENGTH_SHORT).show();
        }
        else if (isValid) {
            // Nếu không có lỗi, thêm khách và hiển thị thông báo
            Toast.makeText(this, "Thêm khách thành công!", Toast.LENGTH_SHORT).show();
            int tuoi = Integer.parseInt(Tuoi);
            khachDB.insertKhach(Ten, tuoi, QueQuan, Phone, Anh);
            Intent i = new Intent(ThemKhach.this, ListKhach.class);
            startActivity(i);
        }
    }

    private boolean isNumeric(String str) {
        return str.matches("\\d+");
    }

    private boolean isValidUrl(String url) {
        // Kiểm tra xem đường dẫn có hợp lệ hay không
        return android.util.Patterns.WEB_URL.matcher(url).matches();
    }

}






