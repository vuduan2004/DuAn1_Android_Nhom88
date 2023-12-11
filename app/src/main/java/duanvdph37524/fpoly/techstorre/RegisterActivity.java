package duanvdph37524.fpoly.techstorre;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;


import duanvdph37524.fpoly.techstorre.DAO.NguoiDungDAO;


public class RegisterActivity extends AppCompatActivity {
    private NguoiDungDAO nguoiDungDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        TextInputEditText edTenDangNhap = findViewById(R.id.edUsername);
        TextInputEditText edHoTen = findViewById(R.id.edHoTen);
        TextInputEditText edNamSinh = findViewById(R.id.edNamSinh);
        TextInputEditText edSDT = findViewById(R.id.edSoDienThoai);
        TextInputEditText edDiaChi = findViewById(R.id.edDiaChi);
        TextInputEditText edMatKhau = findViewById(R.id.edMatKhau);
        TextInputEditText edReMatKhau = findViewById(R.id.edReMatKhau);

        Button btnRegister = findViewById(R.id.btnRegister);
        TextView tvDangNhap = findViewById(R.id.tvDangNhap);
        nguoiDungDAO = new NguoiDungDAO(this);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = edTenDangNhap.getText().toString();
                String hoten = edHoTen.getText().toString();
                String namsinh = edNamSinh.getText().toString();
                String sdt = edSDT.getText().toString();
                String diachi = edDiaChi.getText().toString();
                String matkhau = edMatKhau.getText().toString();
                String reMatKhau = edReMatKhau.getText().toString();
                if(!matkhau.equals(reMatKhau)){
                    Toast.makeText(RegisterActivity.this, "Mật khẩu không khớp", Toast.LENGTH_SHORT).show();
                } else if (Objects.requireNonNull(edTenDangNhap.getText().toString().length()==0)) {
                    Toast.makeText(RegisterActivity.this, "Tên đăng nhập không được để trống", Toast.LENGTH_SHORT).show();
                } else if (Objects.requireNonNull(edTenDangNhap.getText().toString().length() <4)) {
                    Toast.makeText(RegisterActivity.this, "Tên đăng nhập tối thiểu phải 4 kí tự ", Toast.LENGTH_SHORT).show();
                } else if (Objects.requireNonNull(edHoTen.getText().toString().length()==0)) {
                    Toast.makeText(RegisterActivity.this, "Họ tên không được để trống", Toast.LENGTH_SHORT).show();
                } else if (Objects.requireNonNull(edSDT.getText().toString().length()<9)) {
                    Toast.makeText(RegisterActivity.this, "Số điẹn thoại phải 10 số", Toast.LENGTH_SHORT).show();
                } else if (Objects.requireNonNull(edDiaChi.getText().toString().length()==0)) {
                    Toast.makeText(RegisterActivity.this, "Không được để trống địa chỉ", Toast.LENGTH_SHORT).show();
                } else if (Objects.requireNonNull(edMatKhau.getText().toString().length()<5)){
                    Toast.makeText(RegisterActivity.this, "Mật khẩu phải tối thiểu 6 kí tự", Toast.LENGTH_SHORT).show();
                }
                else {
                    boolean check = nguoiDungDAO.Register(user, hoten, namsinh, sdt, diachi, matkhau);
                    if(check){
                        Toast.makeText(RegisterActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                    } else {
                        Toast.makeText(RegisterActivity.this, "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        tvDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });
    }
}