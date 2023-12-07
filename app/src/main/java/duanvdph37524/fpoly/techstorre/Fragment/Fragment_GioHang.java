package duanvdph37524.fpoly.techstorre.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;

import duanvdph37524.fpoly.techstorre.Activity.ThanhToanActivity;
import duanvdph37524.fpoly.techstorre.Adapter.AdapterGioHang;
import duanvdph37524.fpoly.techstorre.DAO.GioHangDAO;
import duanvdph37524.fpoly.techstorre.R;
import duanvdph37524.fpoly.techstorre.model.GioHang;


public class Fragment_GioHang extends Fragment implements AdapterGioHang.TongTienListener{
    RecyclerView rcvGioHang;
    GioHangDAO gioHangDAO;
    AdapterGioHang adapterGioHang;
    ArrayList<GioHang> list = new ArrayList<>();
    Toolbar toolbar;
    private TextView tvTongTien;
    private TextView tvSoLuong;
    Button btnDatHang;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment__gio_hang, container, false);
        rcvGioHang = view.findViewById(R.id.rcvGioHang);
        gioHangDAO = new GioHangDAO(getActivity());
        list = gioHangDAO.getALL();

        adapterGioHang = new AdapterGioHang(getContext(), list, this);
        rcvGioHang.setAdapter(adapterGioHang);
        rcvGioHang.setLayoutManager(new GridLayoutManager(getActivity(), 1));

        toolbar = view.findViewById(R.id.toolbarGioHang);
        tvTongTien = view.findViewById(R.id.tv_tongTien);
        tvSoLuong = view.findViewById(R.id.tv_SoSanPham);
        btnDatHang = view.findViewById(R.id.btn_ThanhToan);
        btnDatHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        toolbar.setTitle("Giỏ hàng");
    }


    @Override
    public void tongTien(double tongTien) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###.### đ");
        tvTongTien.setText("Tổng tiền: " + decimalFormat.format(tongTien));
    }

    @Override
    public void soLuongSanPham(int soLuong) {
        tvSoLuong.setText("Số sản phẩm: " + soLuong);
    }

}