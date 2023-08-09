package edu.huflit.appphongtro.PhongTro;

import android.content.Context;
import android.content.Intent;
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
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import edu.huflit.appphongtro.R;

public class PhongTroAdapter extends RecyclerView.Adapter<PhongTroAdapter.PhongTroVH> {

    ArrayList<PhongTro> lstPhong;
    Context context;

    public PhongTroAdapter(Context context, ArrayList<PhongTro> lstPhong) {
        this.context = context;
        this.lstPhong = lstPhong;
    }

    @NonNull
    @Override
    public PhongTroAdapter.PhongTroVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.room_row,parent,false);
        return new PhongTroVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhongTroAdapter.PhongTroVH holder, int position) {
        PhongTro phongTro = lstPhong.get(position);
        Glide.with(this.context)
                .load(phongTro.getImage())
                .apply(new RequestOptions().override(133,132))
                .into(holder.imgRoom);
        holder.tvidRoom.setText(String.valueOf(phongTro.getIdPhong()));
        holder.tvDientich.setText(String.valueOf(phongTro.getDienTich()));
        holder.tvGiaTien.setText(String.valueOf(phongTro.getGiatien()));
        if(phongTro.getFlat() == 1){
            holder.imgCheck.setImageResource(R.drawable.checked);
        }
        else if(phongTro.getFlat()== 0){
            holder.imgCheck.setImageResource(R.drawable.unchecked);
        }
        holder.room_row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnClickGoToDetailRoom(phongTro);
            }
        });

    }

    private void OnClickGoToDetailRoom(PhongTro phongTro){
        Intent i = new Intent(this.context, DetailPhong.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("phongTro", phongTro);
        i.putExtras(bundle);
        context.startActivity(i);
    }
    @Override
    public int getItemCount() {
        return lstPhong.size();
    }


    public class PhongTroVH extends RecyclerView.ViewHolder {

        ImageView imgRoom;
        TextView tvidRoom;
        TextView tvDientich;
        TextView tvGiaTien;
        ImageView imgCheck;
        ConstraintLayout room_row;



        public PhongTroVH(@NonNull View itemView) {
            super(itemView);
            imgRoom = itemView.findViewById(R.id.imgRoom_row);
            tvidRoom = itemView.findViewById(R.id.tvidRoom_row);
            tvDientich = itemView.findViewById(R.id.tvdtRoom_row);
            tvGiaTien = itemView.findViewById(R.id.tvMoneyRoom_row);
            imgCheck = itemView.findViewById(R.id.imgCheck);
            room_row = itemView.findViewById(R.id.room_row);




        }
    }
}
