package duanvdph37524.fpoly.techstorre.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import duanvdph37524.fpoly.techstorre.DAO.GioHangDAO;
import duanvdph37524.fpoly.techstorre.DAO.HoaDonDAO;
import duanvdph37524.fpoly.techstorre.DAO.NguoiDungDAO;
import duanvdph37524.fpoly.techstorre.Fragment.Fragment_GioHang;
import duanvdph37524.fpoly.techstorre.LoginActivity;
import duanvdph37524.fpoly.techstorre.R;
import duanvdph37524.fpoly.techstorre.model.GioHang;
import duanvdph37524.fpoly.techstorre.model.HoaDon;
import duanvdph37524.fpoly.techstorre.model.NguoiDung;

public class TaiKhoanActivity extends AppCompatActivity {
    private TextView tvHoTen, tvSoHD, tvSoSP;
    private TextView tvTenDangNhap;
    private ImageView imageView2;
    private ImageView img_change_user;
    private RecyclerView recyclerView;
    private Button btnDangXuat;

    private NguoiDungDAO nguoiDungDAO;
    private NguoiDung nguoiDung;
    LinearLayout layout_hoadon, layout_giohang;

    ArrayList<GioHang> list = new ArrayList<>();
    ArrayList<HoaDon> listHD = new ArrayList<>();
    GioHangDAO gioHangDAO;
    HoaDonDAO hoaDonDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tai_khoan);
        tvHoTen = findViewById(R.id.tvHoTen);
        tvTenDangNhap = findViewById(R.id.tvTenDangNhap);
        tvSoHD = findViewById(R.id.tv_soluongDH);
        tvSoSP = findViewById(R.id.tv_soLuongGH);
        layout_hoadon = findViewById(R.id.layout_hoadon);
        layout_giohang = findViewById(R.id.layout_giohang);
        btnDangXuat = findViewById(R.id.btn_log_out);
        imageView2 = findViewById(R.id.imageView2);
        img_change_user = findViewById(R.id.img_change_user);
        nguoiDungDAO = new NguoiDungDAO(this);
        gioHangDAO = new GioHangDAO(this);
        hoaDonDAO = new HoaDonDAO(this);
        list = gioHangDAO.getALL();
        listHD = hoaDonDAO.getAll();
        int soLuongGH = list.size();
        int soLuongHD = listHD.size();

        tvSoHD.setText(String.valueOf(soLuongHD));
        tvSoSP.setText(String.valueOf(soLuongGH));

        getTTKhachHang();
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TaiKhoanActivity.this, ManHinhChinh.class));
            }
        });
        btnDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DiaLogDX();
            }
        });
        img_change_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TaiKhoanActivity.this, ChangeUserActivity.class));
            }
        });

        layout_hoadon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TaiKhoanActivity.this, HoaDonActivity.class));
            }
        });

    }
    private void getTTKhachHang(){
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("THONGTIN", Context.MODE_PRIVATE);
        String hoten = sharedPreferences.getString("hoten", "");
        String tendangnhap = sharedPreferences.getString("tendangnhap", "");
        tvHoTen.setText(hoten);
        tvTenDangNhap.setText("@"+ tendangnhap);

        }
        private void DiaLogDX(){
            AlertDialog.Builder builder = new AlertDialog.Builder(TaiKhoanActivity.this);
            View view = LayoutInflater.from(TaiKhoanActivity.this).inflate(R.layout.dialog_dx, null, false);
            builder.setView(view);
            AlertDialog dialog = builder.create();
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
            Button btnDX = view.findViewById(R.id.btnDangXuat);
            Button btnHuy = view.findViewById(R.id.btnHuy);
            btnDX.setOnClickListener(view1 -> {
                dialog.dismiss();
                startActivity(new Intent(TaiKhoanActivity.this, LoginActivity.class));
                TaiKhoanActivity.this.finish();

            });
            btnHuy.setOnClickListener(view1 -> dialog.dismiss() );
            dialog.show();
        }

}