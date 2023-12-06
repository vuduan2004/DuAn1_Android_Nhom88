package duanvdph37524.fpoly.techstorre.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import duanvdph37524.fpoly.techstorre.R;

public class ChangeUserActivity extends AppCompatActivity {
    private ImageView img_back_tai_khoan;
    private Button btn_doi_thong_tin, btn_doi_mat_khau;
    private TextView tvHoTen;
    private TextView tvNamSinh;
    private TextView tvSoDienThoai;
    private TextView tvDiaChi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_user);
        img_back_tai_khoan = findViewById(R.id.img_back_tai_khoan);
        btn_doi_thong_tin = findViewById(R.id.btn_doi_thong_tin);
        btn_doi_mat_khau = findViewById(R.id.btn_doi_mat_khau);
        tvHoTen = findViewById(R.id.tvHoTen);
        tvNamSinh = findViewById(R.id.tvNamSinh);
        tvSoDienThoai = findViewById(R.id.tvSoDienThoai);
        tvDiaChi = findViewById(R.id.tvDiaChi);
        getTTKhachHang();
        btn_doi_thong_tin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChangeUserActivity.this, DoiThongTinActivity.class);
                intent.putExtra("hoten", tvHoTen.getText().toString());
                intent.putExtra("namsinh", tvNamSinh.getText().toString());
                intent.putExtra("diachi", tvDiaChi.getText().toString());
                intent.putExtra("sodienthoai", tvSoDienThoai.getText().toString());
                startActivity(intent);
            }
        });
        img_back_tai_khoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ChangeUserActivity.this, TaiKhoanActivity.class));
            }
        });
        btn_doi_mat_khau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ChangeUserActivity.this, DoiMatKhauActivity.class));
            }
        });
    }
    private void getTTKhachHang(){
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("THONGTIN", Context.MODE_PRIVATE);
        String hoten = sharedPreferences.getString("hoten", "");
        String namsinh = sharedPreferences.getString("namsinh", "");
        String diachi = sharedPreferences.getString("diachi", "");
        String dienthoai = sharedPreferences.getString("sodienthoai","");
        tvHoTen.setText(hoten);
        tvNamSinh.setText(namsinh);
        tvDiaChi.setText(diachi);
        tvSoDienThoai.setText(dienthoai);
    }

}