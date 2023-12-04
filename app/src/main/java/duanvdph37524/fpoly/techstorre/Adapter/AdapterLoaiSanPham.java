package duanvdph37524.fpoly.techstorre.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import duanvdph37524.fpoly.techstorre.Activity.ListSanPhamActivity;
import duanvdph37524.fpoly.techstorre.DAO.LoaiSanPhamDAO;
import duanvdph37524.fpoly.techstorre.R;
import duanvdph37524.fpoly.techstorre.model.LoaiSanPham;

public class AdapterLoaiSanPham extends RecyclerView.Adapter<AdapterLoaiSanPham.viewHolep>{
    Context context;
    ArrayList<LoaiSanPham> list;
    LoaiSanPhamDAO loaiSanPhamDAO;

    public AdapterLoaiSanPham(Context context, ArrayList<LoaiSanPham> list) {
        this.context = context;
        this.list = list;
        loaiSanPhamDAO = new LoaiSanPhamDAO(context);
    }

    @NonNull
    @Override
    public viewHolep onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_loai_sanpham,null);
        return new viewHolep(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolep holder, int position) {
        LoaiSanPham loaiSanPham = list.get(position);
//        Glide.with(context).load(loaiSanPham.getHinhAnh()).placeholder(R.drawable.loading2).into(holder.imgAnh);
        Picasso.get().load(loaiSanPham.getHinhAnh()).into(holder.imgAnh);
        holder.tvTenLoai.setText(loaiSanPham.getTenLoai());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ListSanPhamActivity.class);
                intent.putExtra("maLoai",loaiSanPham.getMaLoai());
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
        TextView tvTenLoai;

        public viewHolep(@NonNull View itemView) {
            super(itemView);
            imgAnh = itemView.findViewById(R.id.img_loaisp);
            tvTenLoai = itemView.findViewById(R.id.tv_tenLoai);
        }
    }
}
