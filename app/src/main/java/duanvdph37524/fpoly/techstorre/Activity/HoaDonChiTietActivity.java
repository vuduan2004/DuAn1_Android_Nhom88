package duanvdph37524.fpoly.techstorre.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import duanvdph37524.fpoly.techstorre.DAO.HoaDonDAO;
import duanvdph37524.fpoly.techstorre.DAO.SanPhamDAO;
import duanvdph37524.fpoly.techstorre.R;
import duanvdph37524.fpoly.techstorre.model.HoaDon;
import duanvdph37524.fpoly.techstorre.model.SanPham;

public class HoaDonChiTietActivity extends AppCompatActivity {
    TextView tvMaHoaDon, tvTongTien, tvNgayTao, tvTenSP, tvSoLuong, tvTienSP;
    ImageView imgAnh;
    Toolbar toolbar;
    HoaDonDAO hoaDonDAO;
    SanPhamDAO sanPhamDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoa_don_chi_tiet);
        tvMaHoaDon = findViewById(R.id.tv_mahoadon);
        tvTongTien = findViewById(R.id.tv_tongTienCT);
        tvNgayTao = findViewById(R.id.tv_ngayCT);
        tvTenSP = findViewById(R.id.tv_tenHoaDonCT);
        tvSoLuong = findViewById(R.id.tv_soLuongCT);
        tvTienSP = findViewById(R.id.tv_tienHoaDonCT);
        imgAnh = findViewById(R.id.img_hoadonCT);
        toolbar = findViewById(R.id.toolbarCTHD);
        toolbar.setTitle("Chi tiết hoá đơn");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        hoaDonDAO = new HoaDonDAO(this);
        sanPhamDAO = new SanPhamDAO(this);


        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        DecimalFormat decimalFormat = new DecimalFormat("#,###.###");
        Intent intent = getIntent();
        int maHD = intent.getIntExtra("maHoaDon",0);
//        int soLuong = intent.getIntExtra("SoLuongMuaHD",0);
//        SharedPreferences sharedPreferences = getSharedPreferences("SoLuongMua",MODE_PRIVATE);
//        int soLuong = sharedPreferences.getInt("SoLuongMuaHD",0);
        HoaDon hoaDon = hoaDonDAO.getID(String.valueOf(maHD));
        SanPham sanPham = sanPhamDAO.getSanPham(String.valueOf(hoaDon.getMaSP()));

        tvMaHoaDon.setText("Mã hoá đơn: " + hoaDon.getMaHoaDon() );
        tvTongTien.setText("Tổng tiền: " + decimalFormat.format(hoaDon.getTongTien()));
        tvNgayTao.setText(dateFormat.format(hoaDon.getNgayTao()));
        tvTenSP.setText(sanPham.getTenSP());
        tvSoLuong.setText("Số lượng mua: " + hoaDon.getSoLuongMua());
        tvTienSP.setText(decimalFormat.format(sanPham.getGiaTien()));
        Picasso.get().load(sanPham.getHinhAnh()).into(imgAnh);


    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}