package edu.huflit.appphongtro.Khach;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.ArrayList;

import edu.huflit.appphongtro.PhongTro.PhongTroAdapter;
import edu.huflit.appphongtro.R;
import edu.huflit.appphongtro.ThuePhong.ThuePhongDB;

public class KhachAdapter extends RecyclerView.Adapter<KhachAdapter.KhachVH> {

    ArrayList<Khach> lstKhach;
    Context context;

    public KhachAdapter(Context context,ArrayList<Khach> lstKhach) {
        this.lstKhach = lstKhach;
        this.context = context;
    }

    @NonNull
    @Override
    public KhachAdapter.KhachVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cus_row,parent,false);
        return new KhachAdapter.KhachVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KhachAdapter.KhachVH holder, int position) {
        Khach khach = lstKhach.get(position);
        holder.tvCusName.setText(khach.getTen());
        Glide.with(this.context)
                .load(khach.getImgKhach())
                .into(holder.imgCus);

        ArrayList<Integer> roomIDs = getRoomIDsOfCustomer(khach.getIdKhach());
        if (roomIDs.isEmpty()) {
            holder.tvIDPhong.setText("Chưa thuê");
        } else {
            // Nếu khách hàng thuê ít nhất một phòng, hiển thị danh sách các ID phòng cách nhau bằng dấu ","
            StringBuilder roomIDsString = new StringBuilder();
            for (int i = 0; i < roomIDs.size(); i++) {
                if (i > 0) {
                    roomIDsString.append(", ");
                }
                roomIDsString.append(roomIDs.get(i));
            }
            holder.tvIDPhong.setText(roomIDsString.toString());
        }

        holder.row_cus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnClickGotoDetailCus(khach);
            }
        });





    }

    private void OnClickGotoDetailCus(Khach khach){
        Intent i = new Intent(this.context, DetailKhach.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("khach",khach);
        i.putExtras(bundle);
        context.startActivity(i);

    }


    @Override
    public int getItemCount() {return lstKhach.size();}

    public class KhachVH extends RecyclerView.ViewHolder {

        TextView tvCusName;
        TextView tvIDPhong;
        ImageView imgCus;

        ConstraintLayout row_cus;
        public KhachVH(@NonNull View itemView) {
            super(itemView);
            tvCusName = itemView.findViewById(R.id.tvName_CusRow);
            tvIDPhong = itemView.findViewById(R.id.tvIDRoom_CusRow);
            imgCus = itemView.findViewById(R.id.img_CusRow);
            row_cus = itemView.findViewById(R.id.row_cus);


        }
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

