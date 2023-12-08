package duanvdph37524.fpoly.techstorre.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

import duanvdph37524.fpoly.techstorre.Adapter.AdapterHoaDon;
import duanvdph37524.fpoly.techstorre.DAO.HoaDonDAO;
import duanvdph37524.fpoly.techstorre.Fragment.FragmentLoaiSP;
import duanvdph37524.fpoly.techstorre.Fragment.Fragment_GioHang;
import duanvdph37524.fpoly.techstorre.R;
import duanvdph37524.fpoly.techstorre.model.HoaDon;

public class LichSuDonHangActivity extends AppCompatActivity {

    Toolbar toolbar;

    RecyclerView rcvLichSu;
    HoaDonDAO hoaDonDAO;
    AdapterHoaDon adapterHoaDon;
    ArrayList<HoaDon> list = new ArrayList<>();
    private TextView tvSoLuong;
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lich_su_don_hang);
        toolbar = findViewById(R.id.toolbarLS);
        rcvLichSu = findViewById(R.id.rcv_LichSu);
        bottomNavigationView = findViewById(R.id.bottom_navlichsu);


        toolbar.setTitle("Lịch Sử Đơn Hàng");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.getItemId() == R.id.itHome) {
                    startActivity(new Intent(LichSuDonHangActivity.this, ManHinhChinh.class));
                } else if (item.getItemId() == R.id.itGioHang) {
                    replanceFragmentt(new Fragment_GioHang());
                } else if (item.getItemId() == R.id.itUser) {
                    startActivity(new Intent(LichSuDonHangActivity.this, TaiKhoanActivity.class));
                }

                return false;
            }
        });

    }
    private void replanceFragmentt(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayoutlichsu, fragment);
        fragmentTransaction.commit();
    }

    }


