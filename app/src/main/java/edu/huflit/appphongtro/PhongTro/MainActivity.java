package edu.huflit.appphongtro.PhongTro;

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

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import edu.huflit.appphongtro.Data;
import edu.huflit.appphongtro.HoaDon.ListHoaDon;
import edu.huflit.appphongtro.Khach.ListKhach;
import edu.huflit.appphongtro.R;

public class MainActivity extends AppCompatActivity {

    RecyclerView rvPhong;
    PhongTroAdapter phongTroAdapter;
    ArrayList<PhongTro> lstPhong;
    PhongTroDB phongTroDB;
    Context context = this;

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        phongTroDB = new PhongTroDB(context);

        phongTroDB.createTable();

        rvPhong = findViewById(R.id.rvPhong);

        lstPhong = phongTroDB.getPhongTro();

        phongTroAdapter = new PhongTroAdapter(context, lstPhong);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false);

        rvPhong.setLayoutManager(linearLayoutManager);

        rvPhong.addItemDecoration(new DividerItemDecoration(context,DividerItemDecoration.VERTICAL));

        rvPhong.setAdapter(phongTroAdapter);


        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.dsphong);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.dskhach:
                        Intent intent = new Intent(MainActivity.this, ListKhach.class);
                        startActivity(intent);
                        return true;
                    case R.id.dshoadon:
                        Intent intent1 = new Intent(MainActivity.this, ListHoaDon.class);
                        startActivity(intent1);
                        return true;
                    default:
                        return false;
                }
            }
        });



    }

    public void gotoAddRoom_OnClick(View view) {
        Intent intent = new Intent(MainActivity.this,ThemPhong.class);
        startActivity(intent);
    }
}