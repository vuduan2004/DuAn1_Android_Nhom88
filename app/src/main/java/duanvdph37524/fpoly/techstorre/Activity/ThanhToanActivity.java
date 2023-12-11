package duanvdph37524.fpoly.techstorre.Activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import duanvdph37524.fpoly.techstorre.DAO.ChiTietSanPhamDAO;
import duanvdph37524.fpoly.techstorre.DAO.HoaDonDAO;
import duanvdph37524.fpoly.techstorre.DAO.NguoiDungDAO;
import duanvdph37524.fpoly.techstorre.DAO.SanPhamDAO;
import duanvdph37524.fpoly.techstorre.R;
import duanvdph37524.fpoly.techstorre.model.ChiTietSanPham;
import duanvdph37524.fpoly.techstorre.model.HoaDon;
import duanvdph37524.fpoly.techstorre.model.NguoiDung;
import duanvdph37524.fpoly.techstorre.model.SanPham;

public class ThanhToanActivity extends AppCompatActivity {
    Toolbar toolbar;
    ImageView imgAnh;
    ImageButton giamsoluong, tangsoluong;
    TextView txttttensp, txttgiasp, txttsoluong, txtnguoinhan, txtSdt, txtDiaChi, txtTongTien, txtnavsoluong;

    Button btnthanhtoan;
    double totalPrice;
    private HoaDonDAO hoaDonDAO;
    private int maSP, currentQuantity;
    private SanPham sanPham;
    SanPhamDAO sanPhamDAO;
    Context context = this;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanh_toan);
        toolbar = findViewById(R.id.toolbartt);
        toolbar.setTitle("Thanh Toán");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imgAnh = findViewById(R.id.imgttsp);
        txttttensp = findViewById(R.id.txttttensp);
        txttgiasp = findViewById(R.id.txttgia);
        txttsoluong = findViewById(R.id.txtttsoluong);
        txtnguoinhan = findViewById(R.id.txtnguoinhan);
        txtSdt = findViewById(R.id.txtSdt);
        txtDiaChi = findViewById(R.id.txtDiaChi);
        giamsoluong = findViewById(R.id.img_giamnav);
        tangsoluong = findViewById(R.id.img_tangnav);
        txtTongTien = findViewById(R.id.txtTongtien);
        txtnavsoluong = findViewById(R.id.txt_navsoluong);
        btnthanhtoan = findViewById(R.id.btnthanhtoan);


        hoaDonDAO = new HoaDonDAO(this);
        sanPhamDAO = new SanPhamDAO(this);
        ChiTietSanPhamDAO chiTietSanPhamDAO = new ChiTietSanPhamDAO(context);

        DecimalFormat decimalFormat = new DecimalFormat("#,###.### đ");
        Intent intent = getIntent();
        String getUrl = intent.getStringExtra("hinhAnh");
        String tenSP = intent.getStringExtra("tenSP");

        Bundle bundle = intent.getExtras();
        maSP = bundle.getInt("maSP", 0);
        sanPham = sanPhamDAO.getSanPham(String.valueOf(maSP));
        ChiTietSanPham chiTietSanPham = chiTietSanPhamDAO.getThongTin(sanPham.getMaSP());
        Log.d("maspls2", String.valueOf(maSP));
        double giaTien = sanPham.getGiaTien();
        int soLuong = chiTietSanPham.getSoLuong();


        txttgiasp.setText(decimalFormat.format(sanPham.getGiaTien()));
        Picasso.get().load(sanPham.getHinhAnh()).into(imgAnh);
        txttttensp.setText(sanPham.getTenSP());
        txttsoluong.setText(String.valueOf(soLuong));
        NguoiDungDAO nguoiDungDAO = new NguoiDungDAO(this);
        NguoiDung nguoiDung = nguoiDungDAO.layThongTinNguoiDung();

        if (nguoiDung != null) {
            txtnguoinhan.setText(nguoiDung.getTenKhachHang());
            txtSdt.setText(nguoiDung.getSoDienThoai());
            txtDiaChi.setText(nguoiDung.getDiaChi());
        }


        btnthanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy tổng tiền từ TextView
                String tongTienString = txtTongTien.getText().toString();
                double tongTien = Double.parseDouble(tongTienString.replaceAll("[^\\d.]+", ""));
                // Kiểm tra nếu tổng tiền là 0
                if (tongTien == 0) {
                    // Hiển thị thông báo không thể thanh toán
                    Toast.makeText(ThanhToanActivity.this, "Giỏ hàng của bạn đang trống. Vui lòng thêm sản phẩm để thanh toán.", Toast.LENGTH_SHORT).show();
                } else {
                    // Intent intent = new Intent(ThanhToanActivity.this, HoaDonActivity.class);
//                    Toast.makeText(ThanhToanActivity.this, "Thanh toán thành công", Toast.LENGTH_SHORT).show();

                    int capNhatSoLuong = chiTietSanPham.getSoLuong() - currentQuantity;
                    chiTietSanPham.setSoLuong(capNhatSoLuong);
                    chiTietSanPhamDAO.capNhat(chiTietSanPham);
                    Log.d("kiemtralai", String.valueOf(capNhatSoLuong));


                    hienThiDialogDatHangThanhCong();
                }

            }
        });

        // Bắt sự kiện click giảm số lượng
        giamsoluong.setOnClickListener(view -> {
            currentQuantity = Integer.parseInt(txtnavsoluong.getText().toString());
            if (currentQuantity > 0) {
                currentQuantity--;
                txtnavsoluong.setText(String.valueOf(currentQuantity));
                updateTotalPriceAndQuantity(currentQuantity, giaTien);
            } else {
                Toast.makeText(ThanhToanActivity.this, "Số lượng không hợp lệ", Toast.LENGTH_SHORT).show();
            }
        });


        // Bắt sự kiện click tăng số lượng
        tangsoluong.setOnClickListener(view -> {
            currentQuantity = Integer.parseInt(txtnavsoluong.getText().toString());
            if (currentQuantity < soLuong) {
                currentQuantity++;
                txtnavsoluong.setText(String.valueOf(currentQuantity));

                updateTotalPriceAndQuantity(currentQuantity, giaTien);
            } else {
                Toast.makeText(ThanhToanActivity.this, "Hết Hàng ", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void updateTotalPriceAndQuantity(int quantity, double giaTien) {

        txtnavsoluong.setText(String.valueOf(quantity));

        if (quantity > 0) {
            totalPrice = quantity * giaTien;
            DecimalFormat decimalFormat = new DecimalFormat("#,###.### đ");
            txtTongTien.setText(decimalFormat.format(totalPrice));
        } else {
            txtTongTien.setText("0 đ");
        }

    }

    private void hienThiDialogDatHangThanhCong() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // Thiết lập thông tin cho Dialog
        builder.setTitle("Đặt hàng thành công")
                .setMessage("Đơn hàng của bạn đã được đặt thành công.")
                .setIcon(R.drawable.tick) // Thay thế bằng icon thông báo của bạn
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        HoaDon hoaDon = new HoaDon();
                        hoaDon.setMaSP(maSP);
                        hoaDon.setNgayTao(new Date());
                        hoaDon.setTongTien(totalPrice);
                        hoaDon.setSoLuongMua(currentQuantity);

                        if (hoaDonDAO.themHoaDon(hoaDon)) {
                            Intent intent = new Intent(ThanhToanActivity.this, HoaDonActivity.class);
//                            intent.putExtra("SoLuongDat", currentQuantity);
//                            Log.d("soluongdat",String.valueOf(currentQuantity))
//                            chiTietSanPhamDAO.capNhat(chiTietSanPham);
                            startActivity(intent);
                            dialog.dismiss();
                        }

                    }
                });

        // Tạo và hiển thị Dialog
        AlertDialog dialog = builder.create();
        dialog.show();

        // Tự động đóng dialog sau 5 giây (5000 milliseconds)
        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
            }
        };

        handler.postDelayed(runnable, 5000); // 5000 milliseconds = 5 giây
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
