package duanvdph37524.fpoly.techstorre.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

import duanvdph37524.fpoly.techstorre.Adapter.AdapterHoaDon;
import duanvdph37524.fpoly.techstorre.DAO.HoaDonDAO;
import duanvdph37524.fpoly.techstorre.R;
import duanvdph37524.fpoly.techstorre.model.HoaDon;

public class HoaDonActivity extends AppCompatActivity implements AdapterHoaDon.SoLuongHoaDon{
    Toolbar toolbar;
    RecyclerView rcvHoaDon;
    HoaDonDAO hoaDonDAO;
    AdapterHoaDon adapterHoaDon;
    ArrayList<HoaDon> list = new ArrayList<>();
    private TextView tvSoLuong;

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