package duanvdph37524.fpoly.techstorre.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import duanvdph37524.fpoly.techstorre.Fragment.FragmentLoaiSP;
import duanvdph37524.fpoly.techstorre.R;

public class ManHinhChinh extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_chinh);
        bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.getItemId() == R.id.itHome) {
                    replanceFragment(new FragmentLoaiSP());
                } else if (item.getItemId() == R.id.itGioHang) {
//                    startActivity(new Intent(ManHinhChinh.this, GioHangActivity.class));
                } else if (item.getItemId() == R.id.itUser) {
                   startActivity(new Intent(ManHinhChinh.this, TaiKhoanActivity.class));
                }

                return false;
            }
        });
        if (savedInstanceState == null) {
            replanceFragment(new FragmentLoaiSP());
        }
    }
    private void replanceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }
}