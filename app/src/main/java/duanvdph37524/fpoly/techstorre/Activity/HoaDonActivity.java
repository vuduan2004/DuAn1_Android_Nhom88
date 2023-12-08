package duanvdph37524.fpoly.techstorre.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

import duanvdph37524.fpoly.techstorre.Adapter.AdapterHoaDon;
import duanvdph37524.fpoly.techstorre.DAO.HoaDonDAO;
import duanvdph37524.fpoly.techstorre.Fragment.FragmentLoaiSP;
import duanvdph37524.fpoly.techstorre.Fragment.Fragment_GioHang;
import duanvdph37524.fpoly.techstorre.R;
import duanvdph37524.fpoly.techstorre.model.HoaDon;

public class HoaDonActivity extends AppCompatActivity implements AdapterHoaDon.SoLuongHoaDon{
    Toolbar toolbar;
    RecyclerView rcvHoaDon;
    HoaDonDAO hoaDonDAO;
    AdapterHoaDon adapterHoaDon;
    ArrayList<HoaDon> list = new ArrayList<>();
    private TextView tvSoLuong;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoa_don);
        toolbar = findViewById(R.id.toolbarHD);
        rcvHoaDon = findViewById(R.id.rcv_HoaDon);
        tvSoLuong = findViewById(R.id.tv_tongHoaDon);
        hoaDonDAO = new HoaDonDAO(this);
        list = hoaDonDAO.getAll();
        adapterHoaDon = new AdapterHoaDon(this, list, this);

        rcvHoaDon.setLayoutManager(new GridLayoutManager(this, 1));
        rcvHoaDon.setAdapter(adapterHoaDon);

        toolbar.setTitle("Hoá đơn");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        bottomNavigationView = findViewById(R.id.bottom_nav1);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.getItemId() == R.id.itHome1) {
                    startActivity(new Intent(HoaDonActivity.this, ManHinhChinh.class));
                } else if (item.getItemId() == R.id.itLichSu) {
                    startActivity(new Intent(HoaDonActivity.this, LichSuDonHangActivity.class));
                } else if (item.getItemId() == R.id.itUser1) {
                    startActivity(new Intent(HoaDonActivity.this, TaiKhoanActivity.class));
                }

                return false;
            }
        });

    }
    private void replanceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayouthoadon, fragment);
        fragmentTransaction.commit();
    }












    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    public void soLuongHD(int soLuong) {
        tvSoLuong.setText(soLuong + " hoá đơn");
    }

}