package edu.huflit.appphongtro.Khach;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import edu.huflit.appphongtro.HoaDon.ListHoaDon;
import edu.huflit.appphongtro.PhongTro.MainActivity;
import edu.huflit.appphongtro.PhongTro.PhongTroAdapter;
import edu.huflit.appphongtro.R;

public class ListKhach extends AppCompatActivity {


    RecyclerView rvKhach;

    KhachAdapter khachAdapter;

    ArrayList<Khach> lstKhach;

    KhachDB khachDB;

    Context context = this;

    ImageButton btnThemKhach;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_khach);

        btnThemKhach = findViewById(R.id.btnThemKhach);

        btnThemKhach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ListKhach.this, ThemKhach.class);
                startActivity(i);
            }
        });

        khachDB = new KhachDB(context);
        khachDB.createTableKhach();
        rvKhach = findViewById(R.id.rvKhach);
        lstKhach = khachDB.getKhach();

        khachAdapter = new KhachAdapter(context,lstKhach);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false);

        rvKhach.setLayoutManager(linearLayoutManager);

        rvKhach.addItemDecoration(new DividerItemDecoration(context,DividerItemDecoration.VERTICAL));

        rvKhach.setAdapter(khachAdapter);




        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.dskhach);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.dsphong:
                        Intent i = new Intent(ListKhach.this, MainActivity.class);
                        startActivity(i);
                        return true;
                    case R.id.dshoadon:
                        Intent i2 = new Intent(ListKhach.this, ListHoaDon.class);
                        startActivity(i2);
                        return true;
                    default:
                        return false;
                }
            }
        });

    }


}