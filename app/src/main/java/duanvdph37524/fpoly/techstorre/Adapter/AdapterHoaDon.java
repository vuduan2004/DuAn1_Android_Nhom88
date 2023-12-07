package duanvdph37524.fpoly.techstorre.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import duanvdph37524.fpoly.techstorre.Activity.HoaDonChiTietActivity;
import duanvdph37524.fpoly.techstorre.R;
import duanvdph37524.fpoly.techstorre.model.HoaDon;

public class AdapterHoaDon extends RecyclerView.Adapter<AdapterHoaDon.viewHolep>{
    Context context;
    ArrayList<HoaDon> list;

    public interface SoLuongHoaDon{
        void soLuongHD(int soLuong);
    }

    private SoLuongHoaDon soLuongHoaDon;

    public AdapterHoaDon(Context context, ArrayList<HoaDon> list, SoLuongHoaDon soLuong) {
        this.context = context;
        this.list = list;
        this.soLuongHoaDon = soLuong;
    }

    @NonNull
    @Override
    public viewHolep onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_hoa_don,null);
        return new viewHolep(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolep holder, int position) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        DecimalFormat decimalFormat = new DecimalFormat("#,###.###");
        HoaDon hoaDon = list.get(position);
        holder.tvMaHoaDon.setText("Mã hoá đơn: " + hoaDon.getMaHoaDon());
        holder.tvTongTien.setText(decimalFormat.format(hoaDon.getTongTien()));
        holder.tvNgayTao.setText(dateFormat.format(hoaDon.getNgayTao()));
        Activity activity = (Activity) holder.itemView.getContext();
        Intent intent = activity.getIntent();
        int soLuong = intent.getIntExtra("SoLuongDat",0);

//        SharedPreferences sharedPreferences = context.getSharedPreferences("SoLuongMua", Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putInt("SoLuongMuaHD",soLuong);
//        editor.commit();
        int soLuongHD = list.size();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), HoaDonChiTietActivity.class);
                intent.putExtra("maHoaDon",hoaDon.getMaHoaDon());
//                intent.putExtra("SoLuongMuaHD",soLuong);
                holder.itemView.getContext().startActivity(intent);
            }
        });
        if(soLuongHoaDon != null){
            soLuongHoaDon.soLuongHD(soLuongHD);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class viewHolep extends RecyclerView.ViewHolder{
        TextView tvMaHoaDon, tvTongTien, tvNgayTao;
        public viewHolep(@NonNull View itemView) {
            super(itemView);
            tvMaHoaDon = itemView.findViewById(R.id.tv_maHoaDon);
            tvTongTien = itemView.findViewById(R.id.tv_tienHoaDon);
            tvNgayTao = itemView.findViewById(R.id.tv_ngayTao);

        }
    }
}
