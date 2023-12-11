package duanvdph37524.fpoly.techstorre.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

import duanvdph37524.fpoly.techstorre.DAO.SanPhamDAO;
import duanvdph37524.fpoly.techstorre.R;
import duanvdph37524.fpoly.techstorre.model.GioHang;
import duanvdph37524.fpoly.techstorre.model.SanPham;

public class AdapterTTGH extends RecyclerView.Adapter<AdapterTTGH.viewHolep>{
    Context context;
    ArrayList<GioHang> list;
    SanPhamDAO sanPhamDAO;

    public AdapterTTGH(Context context, ArrayList<GioHang> list) {
        this.context = context;
        this.list = list;
        sanPhamDAO = new SanPhamDAO(context);
    }

    @NonNull
    @Override
    public viewHolep onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(context).inflate(R.layout.item_tt_gh,null);
       return new viewHolep(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolep holder, int position) {
        SanPham sanPham = sanPhamDAO.getSanPham(String.valueOf(list.get(position).getMaSP()));
        DecimalFormat decimalFormat = new DecimalFormat("#,###.### đ");

        Picasso.get().load(sanPham.getHinhAnh()).into(holder.imgAnh);
        holder.tvTenSP.setText(sanPham.getTenSP());
        holder.tvTien.setText(decimalFormat.format(sanPham.getGiaTien()));
        holder.tvSoLuong.setText(String.valueOf(list.get(position).getSoLuongMua()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class viewHolep extends RecyclerView.ViewHolder{
        ImageView imgAnh;
        TextView tvTenSP, tvTien, tvSoLuong;
        public viewHolep(@NonNull View itemView) {
            super(itemView);
            imgAnh = itemView.findViewById(R.id.img_donhang);
            tvTenSP = itemView.findViewById(R.id.tv_tenspdh);
            tvTien = itemView.findViewById(R.id.tv_giadh);
            tvSoLuong = itemView.findViewById(R.id.tv_soluongmua);
        }
    }
}
