package duanvdph37524.fpoly.techstorre.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import duanvdph37524.fpoly.techstorre.DAO.NguoiDungDAO;
import duanvdph37524.fpoly.techstorre.LoginActivity;
import duanvdph37524.fpoly.techstorre.R;

public class DoiMatKhauActivity extends AppCompatActivity {
    private TextInputEditText edOldPass;
    private TextInputEditText edNewPass;
    private TextInputEditText edReNewPass;
    private ImageView img_change_pass_back_chang_user;
    private AppCompatButton btnLogChangePass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doi_mat_khau);
        img_change_pass_back_chang_user = findViewById(R.id.img_change_pass_back_change_user);
        edOldPass = findViewById(R.id.edOldPass);
        edNewPass = findViewById(R.id.edNewPass);
        edReNewPass = findViewById(R.id.edReNewPass);
        btnLogChangePass = findViewById(R.id.btn_log_change_pass);
        btnLogChangePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CapNhatMK();
            }
        });
        img_change_pass_back_chang_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DoiMatKhauActivity.this, ChangeUserActivity.class));
            }
        });
    }

    private void CapNhatMK() {
        String oldPass = edOldPass.getText().toString();
        String newPass = edNewPass.getText().toString();
        String reNewPass = edReNewPass.getText().toString();
        if (oldPass.equals("") || newPass.equals("") || reNewPass.equals("")) {
            Toast.makeText(DoiMatKhauActivity.this, "Nhap day du thong tin", Toast.LENGTH_SHORT).show();
        } else {
            if (newPass.equals(reNewPass)) {
                SharedPreferences sharedPreferences = getSharedPreferences("THONGTIN", MODE_PRIVATE);
                String tendangnhap = sharedPreferences.getString("tendangnhap", "");
                NguoiDungDAO nguoiDungDAO = new NguoiDungDAO(DoiMatKhauActivity.this);
                int check = nguoiDungDAO.capNhatMatKhau(tendangnhap, oldPass, newPass);

                if (check == 1) {
                    Toast.makeText(DoiMatKhauActivity.this, "cap nhat mat khau thanh cong", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(DoiMatKhauActivity.this, LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                } else if (check == 0) {
                    Toast.makeText(DoiMatKhauActivity.this, "Mật khẩu cũ không chính ", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(DoiMatKhauActivity.this, "Cập nhật mật khẩu thất bại", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(DoiMatKhauActivity.this, "Nhập mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
            }
        }
    }

}