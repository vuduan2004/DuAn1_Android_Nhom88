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

public class LoginActivity extends AppCompatActivity {
    private NguoiDungDAO nguoiDungDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        TextInputEditText edtUsername = findViewById(R.id.edUsername);
        TextInputEditText edtPassword = findViewById(R.id.edPassword);
        TextView tvRigister = findViewById(R.id.tvResigter);
        nguoiDungDAO = new NguoiDungDAO(this);
        Button btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = edtUsername.getText().toString();
                String pass = edtPassword.getText().toString();
                boolean check = nguoiDungDAO.checkLogin(user, pass);
                if (user.isEmpty()){
                    Toast.makeText(LoginActivity.this, "Không được để trống tên đăng nhập", Toast.LENGTH_SHORT).show();
                } else if (pass.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Không được để trống mật khẩu", Toast.LENGTH_SHORT).show();
                } else if (Objects.requireNonNull(user.length()<4)){
                    Toast.makeText(LoginActivity.this, "Tên đăng nhập tối thiểu phải 4 kí tự", Toast.LENGTH_SHORT).show();
                } else if (pass.length()<5) {
                    Toast.makeText(LoginActivity.this, "Mật khẩu tối thiểu phải 6 kí tự", Toast.LENGTH_SHORT).show();
                } else if (check) {
                    Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                } else {
                    Toast.makeText(LoginActivity.this, "Tài khoản hoặc mật khẩu không chính xác", Toast.LENGTH_SHORT).show();
                }
            }
        });
        tvRigister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }
}