package edu.huflit.appphongtro.Khach;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import edu.huflit.appphongtro.PhongTro.PhongTro;
import edu.huflit.appphongtro.R;

public class LVThueThemAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<PhongTro> phongTroList;

    public LVThueThemAdapter(Context context, ArrayList<PhongTro> phongTroList) {
        this.context = context;
        this.phongTroList = phongTroList;
    }

    @Override
    public int getCount() {
        return phongTroList.size();
    }

    @Override
    public Object getItem(int position) {
        return phongTroList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Tạo và inflate một layout cho mỗi dòng trong ListView
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.item_thuephong, parent, false);
        }

        // Lấy thông tin phòng từ danh sách
        PhongTro phongTro = (PhongTro) getItem(position);

        // Hiển thị thông tin phòng trong dòng hiện tại
        TextView tvTenPhong = convertView.findViewById(R.id.tvTenPhong);
        TextView tvDienTich = convertView.findViewById(R.id.tvDienTich);
        TextView tvGiaTien = convertView.findViewById(R.id.tvGiaTien);

        tvTenPhong.setText("Phòng " + phongTro.getIdPhong());
        tvDienTich.setText("Diện tích: " + phongTro.getDienTich() + " m2");
        tvGiaTien.setText("Giá tiền: " + phongTro.getGiatien() + " VND");

        return convertView;
    }
}
