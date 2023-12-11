package duanvdph37524.fpoly.techstorre.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Objects;

import duanvdph37524.fpoly.techstorre.DAO.NguoiDungDAO;
import duanvdph37524.fpoly.techstorre.R;
import duanvdph37524.fpoly.techstorre.RegisterActivity;
import duanvdph37524.fpoly.techstorre.model.NguoiDung;

public class DoiThongTinActivity extends AppCompatActivity {
    private ImageView img_change_info_back_change_user;
    private TextInputEditText edHoTen;
    private TextInputEditText edNamSinh;
    private TextInputEditText edSoDienThoai;
    private TextInputEditText edDiaChi;
    private AppCompatButton btnLogChangePass;
    private NguoiDungDAO nguoiDungDAO;
    private ArrayList<NguoiDung> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doi_thong_tin);
        img_change_info_back_change_user = findViewById(R.id.img_change_info_back_change_user);
        edHoTen = findViewById(R.id.edHoTen);
        edNamSinh = findViewById(R.id.edNamSinh);
        edSoDienThoai = findViewById(R.id.edSoDienThoai);
        edDiaChi = findViewById(R.id.edDiaChi);
        btnLogChangePass = findViewById(R.id.btn_log_change_pass);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String hoten = extras.getString("hoten");
            String namsinh = extras.getString("namsinh");
            String diachi = extras.getString("diachi");
            String sodienthoai = extras.getString("sodienthoai");
            edHoTen.setText(hoten);
            edNamSinh.setText(namsinh);
            edSoDienThoai.setText(sodienthoai);
            edDiaChi.setText(diachi);
        }
        nguoiDungDAO = new NguoiDungDAO(this);
        img_change_info_back_change_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DoiThongTinActivity.this, ChangeUserActivity.class));
            }
        });
        btnLogChangePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UpdateTTKhachHang();
            }
        });
        
    }

    private void UpdateTTKhachHang(){
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("THONGTIN", Context.MODE_PRIVATE);
        String tendangnhap = sharedPreferences.getString("tendangnhap", "");
        String hoten = edHoTen.getText().toString();
        String namsinh = edNamSinh.getText().toString();
        String sodienthoai = edSoDienThoai.getText().toString();
        String diachi = edDiaChi.getText().toString();
        boolean updated = nguoiDungDAO.capnhatThongTin(tendangnhap, hoten, namsinh, sodienthoai, diachi);
        if (updated) {
            Toast.makeText(this, "Cập nhật thông tin thành công", Toast.LENGTH_SHORT).show();
            Intent resultIntent = new Intent();
            resultIntent.putExtra("hoten", hoten);
            resultIntent.putExtra("namsinh", namsinh);
            resultIntent.putExtra("diachi", diachi);
            resultIntent.putExtra("sodienthoai", sodienthoai);
            setResult(RESULT_OK, resultIntent);
            finish();
        } else if (Objects.requireNonNull(edHoTen.getText().toString().length()==0)) {
            Toast.makeText(this, "Họ tên không được để trống", Toast.LENGTH_SHORT).show();
        } else if (Objects.requireNonNull(edSoDienThoai.getText().toString().length()<9)) {
            Toast.makeText(this, "Số điẹn thoại phải 10 số", Toast.LENGTH_SHORT).show();
        } else if (Objects.requireNonNull(edDiaChi.getText().toString().length()==0)) {
            Toast.makeText(this, "Không được để trống địa chỉ", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Cập nhật thông tin thất bại", Toast.LENGTH_SHORT).show();
        }
    }

}