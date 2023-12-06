package duanvdph37524.fpoly.techstorre.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

import duanvdph37524.fpoly.techstorre.DAO.GioHangDAO;
import duanvdph37524.fpoly.techstorre.DAO.SanPhamDAO;
import duanvdph37524.fpoly.techstorre.Fragment.Fragment_GioHang;
import duanvdph37524.fpoly.techstorre.R;
import duanvdph37524.fpoly.techstorre.model.GioHang;
import duanvdph37524.fpoly.techstorre.model.SanPham;

public class AdapterGioHang extends RecyclerView.Adapter<AdapterGioHang.viewHolep> {
    Context context;
    ArrayList<GioHang> list;
    SanPhamDAO sanPhamDAO;
    GioHangDAO gioHangDAO;
    double tongTien, tien, giaTien;
    int soLuongMua,soLuong;



    public interface TongTienListener {
        void tongTien(double tongTien);
        void soLuongSanPham(int soLuong);
    }

    private TongTienListener tongTienListener;



    public AdapterGioHang(Context context, ArrayList<GioHang> list, TongTienListener listener) {
        this.context = context;
        this.list = list;
        this.tongTienListener = listener;
        sanPhamDAO = new SanPhamDAO(context);
        gioHangDAO = new GioHangDAO(context);
    }

    @NonNull
    @Override
    public viewHolep onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_giohang, null);
        return new viewHolep(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolep holder, int position) {
        GioHang gioHang = list.get(position);
        SanPham sanPham = sanPhamDAO.getSanPham(String.valueOf(gioHang.getMaSP()));
        Log.d("masanpham", String.valueOf(gioHang.getMaSP()));
        DecimalFormat decimalFormat = new DecimalFormat("#,###.### đ");
        Picasso.get().load(sanPham.getHinhAnh()).into(holder.imgAnh);
        holder.tvTen.setText(sanPham.getTenSP());
        Log.d("Tensanpham", sanPham.getTenSP());
        holder.tvGia.setText(decimalFormat.format(sanPham.getGiaTien()));
        holder.tvSoLuong.setText("Số lượng còn: " + gioHang.getSoLuong());
        gioHang.setSoLuongMua(1);
        holder.tvSL.setText(String.valueOf(gioHang.getSoLuongMua()));



        giaTien = sanPham.getGiaTien();
        soLuongMua = gioHang.getSoLuongMua();
        tien = giaTien * soLuongMua;
        //
        tongTien += tien;
        Log.d("kiemtratien", decimalFormat.format(tongTien));
        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Xoá sản phẩm");
                builder.setMessage("Bạn có muốn xoá không");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (gioHangDAO.xoa(String.valueOf(gioHang.getMaGioHang()))) {
                            list.clear();
                            list.addAll(gioHangDAO.getALL());
                            Toast.makeText(context, "Xoá thành công", Toast.LENGTH_SHORT).show();
                            for (GioHang gioHang1 : list) {
                                tongTien = gioHang1.getSoLuongMua() * sanPham.getGiaTien();
                            }
                            tongTienListener.tongTien(tongTien);
                            notifyDataSetChanged();
                            if (list.size() == 0) {
                                tongTien = 0;
                                tongTienListener.tongTien(tongTien);
                            }
                            Log.d("kiemtratien", decimalFormat.format(tongTien));
                        }
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.create().show();
            }
        });

        holder.tvTang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gioHang.setSoLuongMua(gioHang.getSoLuongMua() + 1);
                holder.tvSL.setText(String.valueOf(gioHang.getSoLuongMua()));
                //cập nhật tiền
//                tien = giaTien * gioHang.getSoLuongMua();
                giaTien = sanPham.getGiaTien();
                tongTien += giaTien;
                tongTienListener.tongTien(tongTien);
                Log.d("kiemtratien", decimalFormat.format(tongTien));
            }
        });
        holder.tvGiam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (gioHang.getSoLuongMua() > 1) {
                    gioHang.setSoLuongMua(gioHang.getSoLuongMua() - 1);
                    holder.tvSL.setText(String.valueOf(gioHang.getSoLuongMua()));
                    giaTien = sanPham.getGiaTien();
                    tongTien -= giaTien;
                    tongTienListener.tongTien(tongTien);
                    Log.d("kiemtratien", decimalFormat.format(tongTien));
                }
            }
        });

        soLuong = list.size();
        if(tongTienListener != null){
            tongTienListener.tongTien(tongTien);
            tongTienListener.soLuongSanPham(soLuong);
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class viewHolep extends RecyclerView.ViewHolder {
        ImageView imgAnh, imgDelete;
        TextView tvTen, tvGia, tvSoLuong, tvTang, tvGiam, tvSL;

        public viewHolep(@NonNull View itemView) {
            super(itemView);
            imgAnh = itemView.findViewById(R.id.img_giohang);
            imgDelete = itemView.findViewById(R.id.img_delete);
            tvTen = itemView.findViewById(R.id.tv_tenspgh);
            tvGia = itemView.findViewById(R.id.tv_giagh);
            tvSoLuong = itemView.findViewById(R.id.tv_soluonggh);
            tvTang = itemView.findViewById(R.id.tv_tangSL);
            tvGiam = itemView.findViewById(R.id.tv_giamSL);
            tvSL = itemView.findViewById(R.id.tv_slGH);
        }
    }

}
