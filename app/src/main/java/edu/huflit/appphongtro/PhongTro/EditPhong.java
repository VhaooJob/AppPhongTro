package edu.huflit.appphongtro.PhongTro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import edu.huflit.appphongtro.R;

public class EditPhong extends AppCompatActivity {


    PhongTro phongTro;

    PhongTroDB phongTroDB;
    EditText edtDientich, edtMota,edtGiatien,edtLinkAnh;
    Button btnXong;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_phong);



        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            phongTro = (PhongTro) bundle.getSerializable("phongtro");
        }

        phongTroDB = new PhongTroDB(this);
        phongTroDB.createTable();

        edtDientich = findViewById(R.id.edtDientich_edit);
        edtMota = findViewById(R.id.edtMota_edit);
        edtGiatien = findViewById(R.id.edtGiatien_edit);
        edtLinkAnh = findViewById(R.id.edtLinkanh_edit);


            edtDientich.setText(String.valueOf(phongTro.getDienTich()));
            edtMota.setText(phongTro.getMota());
            edtGiatien.setText(String.valueOf(phongTro.getGiatien()));
            edtLinkAnh.setText(phongTro.getImage());



        btnXong = findViewById(R.id.btnXong_editPhong);
        btnXong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dientichStr = edtDientich.getText().toString().trim();
                String giatienStr = edtGiatien.getText().toString().trim();
                String mota = edtMota.getText().toString().trim();
                String linkAnh = edtLinkAnh.getText().toString().trim();

                if (TextUtils.isEmpty(dientichStr) || TextUtils.isEmpty(giatienStr) || TextUtils.isEmpty(mota) || TextUtils.isEmpty(linkAnh)) {
                    // Hiển thị thông báo nếu các trường có dữ liệu trống
                    Toast.makeText(context, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                }
             else if (!isNumeric(dientichStr)) {
                // Hiển thị thông báo nếu Diện tích hoặc Giá tiền không là số
                Toast.makeText(context, "Diện tích phải là số!", Toast.LENGTH_SHORT).show();}
             else if(!isNumeric(giatienStr)) {
                    Toast.makeText(context, "Giá tiền phải là số", Toast.LENGTH_SHORT).show();
                }
             else if (!isValidUrl(linkAnh)) {
                    // Hiển thị thông báo nếu Link ảnh không hợp lệ
                    Toast.makeText(context, "Link ảnh không hợp lệ!", Toast.LENGTH_SHORT).show();
                    return;
             }
             else {
                    // Nếu dữ liệu không trống, thực hiện cập nhật dữ liệu vào cơ sở dữ liệu và chuyển về MainActivity
                    int dientich = Integer.parseInt(dientichStr);
                    int giatien = Integer.parseInt(giatienStr);
                    int idPhong = phongTro.getIdPhong();
                    int flat = phongTro.getFlat();
                    phongTroDB.updatePhong(idPhong, dientich, giatien, mota, linkAnh, flat);
                    Toast.makeText(context, "Chỉnh sửa thành công! Dữ liệu đã được thay đổi!", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(context, MainActivity.class);
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

        });










    }
}