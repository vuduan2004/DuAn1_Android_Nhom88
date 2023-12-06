package duanvdph37524.fpoly.techstorre.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

import duanvdph37524.fpoly.techstorre.LoginActivity;
import duanvdph37524.fpoly.techstorre.R;

public class ThanhToanActivity extends AppCompatActivity {
    Toolbar toolbar;
    ImageView imgAnh;


    private TextView txttttensp, txttgiasp, txttsoluong, txtsoluonghienthi, txtTongtien, txtnguoinhan, txtSdt, txtDiaChi;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanh_toan);


        imgAnh = findViewById(R.id.imgttsp);
        txttttensp = findViewById(R.id.txttttensp);
        txttgiasp = findViewById(R.id.txttgia);
        txttsoluong = findViewById(R.id.txtttsoluong);
        txtnguoinhan = findViewById(R.id.txtnguoinhan);
        txtSdt = findViewById(R.id.txtSdt);
        txtDiaChi = findViewById(R.id.txtDiaChi);

        DecimalFormat decimalFormat = new DecimalFormat("#,###.### đ");
        Intent intent = getIntent();
        String getUrl = intent.getStringExtra("hinhAnh");
        String tenSP = intent.getStringExtra("tenSP");
        double giaTien = intent.getDoubleExtra("giaTien", 0);
        int soLuong = intent.getIntExtra("soLuong", 0);

        txttgiasp.setText(decimalFormat.format(giaTien));
        Picasso.get().load(getUrl).into(imgAnh);
        txttttensp.setText(tenSP);
        txttsoluong.setText(String.valueOf(soLuong));

        toolbar = findViewById(R.id.toolbartt);
        toolbar.setTitle("Thanh Toán");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);




    }

}
