package duanvdph37524.fpoly.techstorre.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import duanvdph37524.fpoly.techstorre.DAO.NguoiDungDAO;
import duanvdph37524.fpoly.techstorre.LoginActivity;
import duanvdph37524.fpoly.techstorre.R;
import duanvdph37524.fpoly.techstorre.model.NguoiDung;

public class TaiKhoanActivity extends AppCompatActivity {
    private TextView tvHoTen;
    private TextView tvTenDangNhap;
    private ImageView img_change_user;
    private RecyclerView recyclerView;
    private Button btnDangXuat;

    private NguoiDungDAO nguoiDungDAO;
    private NguoiDung nguoiDung;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tai_khoan);
        tvHoTen = findViewById(R.id.tvHoTen);
        tvTenDangNhap = findViewById(R.id.tvTenDangNhap);
        btnDangXuat = findViewById(R.id.btn_log_out);
        img_change_user = findViewById(R.id.img_change_user);
        nguoiDungDAO = new NguoiDungDAO(this);
        getTTKhachHang();
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