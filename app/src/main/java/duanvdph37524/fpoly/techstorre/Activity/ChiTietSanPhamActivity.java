package duanvdph37524.fpoly.techstorre.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

import duanvdph37524.fpoly.techstorre.DAO.ChiTietSanPhamDAO;
import duanvdph37524.fpoly.techstorre.DAO.GioHangDAO;
import duanvdph37524.fpoly.techstorre.DAO.SanPhamDAO;
import duanvdph37524.fpoly.techstorre.R;
import duanvdph37524.fpoly.techstorre.model.ChiTietSanPham;
import duanvdph37524.fpoly.techstorre.model.GioHang;

public class ChiTietSanPhamActivity extends AppCompatActivity {
    Toolbar toolbar;
    ImageView imgAnh;
    TextView tvTen, tvGia;
    EditText edtThongTin;
    Button btnThemGioHang, btnMuaHang;
    ChiTietSanPhamDAO chiTietSanPhamDAO;
    SanPhamDAO sanPhamDAO;
    int soLuong, maSP;
    GioHangDAO gioHangDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_san_pham);
        imgAnh = findViewById(R.id.img_chitietSP);
        tvTen = findViewById(R.id.tv_ten_chitietSP);
        tvGia = findViewById(R.id.tv_giaTienChiTiet);
        edtThongTin = findViewById(R.id.edt_thongTinSP);

        //
        DecimalFormat decimalFormat = new DecimalFormat("#,###.### đ");
        Intent intent = getIntent();
        String getUrl = intent.getStringExtra("hinhAnh");
        String tenSP = intent.getStringExtra("tenSP");
        maSP = Integer.parseInt(intent.getStringExtra("maSP"));
        Log.e("Kiểm tra mã loại", String.valueOf(maSP));
        double giaTien = Double.parseDouble(intent.getStringExtra("giaTien"));
        Log.e("Kiểm tra", "giaTien" + giaTien);
        Log.e("Kiểm tra", "getUrl" + getUrl);
        //
        chiTietSanPhamDAO = new ChiTietSanPhamDAO(this);
        gioHangDAO = new GioHangDAO(this);
        ArrayList<ChiTietSanPham> list = new ArrayList<>();
        list = chiTietSanPhamDAO.getALL();
        for (ChiTietSanPham chiTietSanPham : list) {
            if (chiTietSanPham.getMaSP() == maSP) {
                edtThongTin.setText(chiTietSanPham.getThongTinSP());
                soLuong = chiTietSanPham.getSoLuong();
            }
        }

        Picasso.get().load(getUrl).into(imgAnh);
        tvGia.setText(decimalFormat.format(giaTien));
        tvTen.setText(tenSP);
        toolbar = findViewById(R.id.toolbarCTSP);
        toolbar.setTitle("Chi tiết sản phẩm");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnThemGioHang = findViewById(R.id.btn_themGioHang);
        btnThemGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GioHang gioHang = new GioHang();
                gioHang.setMaSP(maSP);
                gioHang.setSoLuongMua(1);
                Log.d("Kiểm tra", String.valueOf(maSP));
                Log.d("Kiểm tra", String.valueOf(soLuong));
                if (gioHangDAO.themGioHang(gioHang)) {
                    Toast.makeText(ChiTietSanPhamActivity.this, "Đã thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnMuaHang = findViewById(R.id.btn_muaNgay);
        btnMuaHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChiTietSanPhamActivity.this, ThanhToanActivity.class);
                intent.putExtra("hinhAnh", getUrl); // Truyền đường dẫn hình ảnh
                intent.putExtra("tenSP", tenSP); // Bạn có thể truyền thêm thông tin khác nếu cần
                intent.putExtra("maSP", maSP); // Bạn có thể truyền thêm thông tin khác nếu cần
                Log.d("masp",String.valueOf(maSP));
                intent.putExtra("giaTien", giaTien); // Bạn có thể truyền thêm thông tin khác nếu cần
                intent.putExtra("soLuong", soLuong);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}