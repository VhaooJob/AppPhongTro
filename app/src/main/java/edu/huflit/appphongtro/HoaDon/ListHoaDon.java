package edu.huflit.appphongtro.HoaDon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import edu.huflit.appphongtro.Khach.Khach;
import edu.huflit.appphongtro.Khach.KhachAdapter;
import edu.huflit.appphongtro.Khach.KhachDB;
import edu.huflit.appphongtro.Khach.ListKhach;
import edu.huflit.appphongtro.PhongTro.MainActivity;
import edu.huflit.appphongtro.R;

public class ListHoaDon extends AppCompatActivity {


    BottomNavigationView bottomNavigationView;


    RecyclerView rvHoadon;

    HoaDonAdapter hoaDonAdapter;

    ArrayList<HoaDon> lstHoadon;

    HoaDonDB hoaDonDB;

    Context context = this;

    ImageButton btnThemHoaDon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_hoa_don);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.dshoadon);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.dskhach:
                        Intent intent = new Intent(ListHoaDon.this, ListKhach.class);
                        startActivity(intent);
                        return true;
                    case R.id.dsphong:
                        Intent intent1 = new Intent(ListHoaDon.this, MainActivity.class);
                        startActivity(intent1);
                        return true;
                    default:
                        return false;
                }
            }
        });



        btnThemHoaDon = findViewById(R.id.btnAddInvoice);

        hoaDonDB = new HoaDonDB(context);

        hoaDonDB.createTableHoaDon();

        rvHoadon = findViewById(R.id.rvHoaDon);
        lstHoadon = hoaDonDB.getHoaDon();

        hoaDonAdapter = new HoaDonAdapter(context,lstHoadon);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false);

        rvHoadon.setLayoutManager(linearLayoutManager);

        rvHoadon.addItemDecoration(new DividerItemDecoration(context,DividerItemDecoration.VERTICAL));

        rvHoadon.setAdapter(hoaDonAdapter);








    }
}