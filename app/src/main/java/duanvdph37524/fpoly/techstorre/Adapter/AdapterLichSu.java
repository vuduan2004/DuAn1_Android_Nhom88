package duanvdph37524.fpoly.techstorre.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

import duanvdph37524.fpoly.techstorre.Activity.ThanhToanActivity;
import duanvdph37524.fpoly.techstorre.DAO.ChiTietSanPhamDAO;
import duanvdph37524.fpoly.techstorre.R;
import duanvdph37524.fpoly.techstorre.model.ChiTietSanPham;
import duanvdph37524.fpoly.techstorre.model.LichSuMua;

public class AdapterLichSu extends RecyclerView.Adapter<AdapterLichSu.viewHolep> {
    Context context;
    ArrayList<LichSuMua> list;
    ChiTietSanPhamDAO chiTietSanPhamDAO;
    public AdapterLichSu(Context context, ArrayList<LichSuMua> list) {
        this.context = context;
        this.list = list;
        chiTietSanPhamDAO = new ChiTietSanPhamDAO(context);
    }

    @NonNull
    @Override
    public viewHolep onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_lichsumua, null);
        return new viewHolep(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolep holder, int position) {
        LichSuMua lichSuMua = list.get(position);
        ChiTietSanPham chiTietSanPham = chiTietSanPhamDAO.getThongTin(lichSuMua.getMaSP());
        DecimalFormat decimalFormat = new DecimalFormat("#,###.###");
        Picasso.get().load(list.get(position).getHinhAnh()).into(holder.imgAnh);
        holder.tvTenSP.setText(list.get(position).getTenSP());
        holder.tvSoLuong.setText(String.valueOf(list.get(position).getSoLuongMua()));
        holder.tvTien.setText(decimalFormat.format(list.get(position).getTienSP()));

        holder.btnMuaLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), ThanhToanActivity.class);
                intent.putExtra("maSP", lichSuMua.getMaSP());
                intent.putExtra("soLuong", chiTietSanPham.getSoLuong());
                Log.d("maspls", String.valueOf(lichSuMua.getMaSP()));
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class viewHolep extends RecyclerView.ViewHolder {
        ImageView imgAnh;
        TextView tvTenSP, tvSoLuong, tvTien;
        Button btnMuaLai;

        public viewHolep(@NonNull View itemView) {
            super(itemView);
            imgAnh = itemView.findViewById(R.id.img_lichsumua);
            tvTenSP = itemView.findViewById(R.id.tv_tenSPLichSuMua);
            tvSoLuong = itemView.findViewById(R.id.tv_soLuongLS);
            tvTien = itemView.findViewById(R.id.tv_thanhTien);
            btnMuaLai = itemView.findViewById(R.id.btnMuaLai);
        }
    }
}
