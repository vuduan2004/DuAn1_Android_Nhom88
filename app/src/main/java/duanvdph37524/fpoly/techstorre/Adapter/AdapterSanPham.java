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

import java.text.DecimalFormat;
import java.util.ArrayList;

import duanvdph37524.fpoly.techstorre.Activity.ChiTietSanPhamActivity;
import duanvdph37524.fpoly.techstorre.R;
import duanvdph37524.fpoly.techstorre.model.SanPham;

public class AdapterSanPham extends RecyclerView.Adapter<AdapterSanPham.viewHolep>{
Context context;
ArrayList<SanPham> list;

    public AdapterSanPham(Context context, ArrayList<SanPham> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public viewHolep onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list_sanpham,null);
        return new viewHolep(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolep holder, int position) {
        SanPham sanPham = list.get(position);
        String url = sanPham.getHinhAnh();
        DecimalFormat decimalFormat = new DecimalFormat("#,###.### đ");
//        Glide.with(context).load(sanPham.getHinhAnh()).into(holder.imgAnh);
        Picasso.get().load(url).into(holder.imgAnh);

        holder.tvTenSP.setText(sanPham.getTenSP());
        holder.tvGiaTien.setText(decimalFormat.format(sanPham.getGiaTien()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ChiTietSanPhamActivity.class);
                intent.putExtra("maSP",String.valueOf(sanPham.getMaSP()));
                intent.putExtra("hinhAnh",sanPham.getHinhAnh());
                intent.putExtra("tenSP",sanPham.getTenSP());
                intent.putExtra("giaTien",String.valueOf(sanPham.getGiaTien()));
//                Log.e("giá tiền sản phẩm", String.valueOf(sanPham.getGiaTien()));
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class viewHolep extends RecyclerView.ViewHolder{
        ImageView imgAnh;
        TextView tvTenSP, tvGiaTien;
        public viewHolep(@NonNull View itemView) {
            super(itemView);
            imgAnh = itemView.findViewById(R.id.img_sanpham);
            tvTenSP = itemView.findViewById(R.id.tv_tensanpham);
            tvGiaTien = itemView.findViewById(R.id.tv_giaTien);
        }
    }
}
