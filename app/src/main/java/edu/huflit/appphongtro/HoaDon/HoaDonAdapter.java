package edu.huflit.appphongtro.HoaDon;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import edu.huflit.appphongtro.R;
import edu.huflit.appphongtro.ThuePhong.ThuePhongDB;

public class HoaDonAdapter extends RecyclerView.Adapter<HoaDonAdapter.HoaDonVH> {


    ArrayList<HoaDon> lstHoaDon;

    Context context;

    public HoaDonAdapter(Context context, ArrayList<HoaDon> lstHoaDon) {
        this.context = context;
        this.lstHoaDon = lstHoaDon;
    }

    @NonNull
    @Override
    public HoaDonAdapter.HoaDonVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hoadon_row,parent,false);
        return new HoaDonAdapter.HoaDonVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HoaDonAdapter.HoaDonVH holder, int position) {
        ThuePhongDB thuePhongDB =  new ThuePhongDB(context);
        thuePhongDB.openDB();
        HoaDon hoaDon = lstHoaDon.get(position);

        holder.tvIDPhong.setText(String.valueOf(hoaDon.getIdPhong()));

        holder.tvName.setText(thuePhongDB.getTenKhachThuePhong(hoaDon.getIdPhong()));

        holder.row_hoadon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }
    public void OnClickGoToDetail(){

    }

    @Override
    public int getItemCount() {
        return lstHoaDon.size();
    }


    public class HoaDonVH extends RecyclerView.ViewHolder {

        TextView tvIDPhong, tvName;

        ConstraintLayout row_hoadon;


        public HoaDonVH(@NonNull View itemView) {
            super(itemView);
            tvIDPhong = itemView.findViewById(R.id.tvIDPhong_HoaDon);
            tvName = itemView.findViewById(R.id.tvCusName_hoadon);
            row_hoadon = itemView.findViewById(R.id.invoice_row);
        }
    }
}
