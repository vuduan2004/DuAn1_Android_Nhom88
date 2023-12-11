package duanvdph37524.fpoly.techstorre.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import duanvdph37524.fpoly.techstorre.Adapter.AdapterTTGH;
import duanvdph37524.fpoly.techstorre.R;
import duanvdph37524.fpoly.techstorre.model.GioHang;

public class ThanhToanGioHangActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView rcvTTGH;
    Button btnThanhToan;
    TextView tvTongList, tvTongTien;

    AdapterTTGH adapterTTGH;
    ArrayList<GioHang> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanh_toan_gio_hang);
        toolbar = findViewById(R.id.toolTTGH);
        tvTongList = findViewById(R.id.tv_listTT);
        tvTongTien = findViewById(R.id.tv_TongTienTT);
        btnThanhToan = findViewById(R.id.btnTTGH);
        rcvTTGH = findViewById(R.id.rcv_TTGH);

        Intent intent = getIntent();

        list = intent.getParcelableArrayListExtra("listGioHang");

        adapterTTGH = new AdapterTTGH(this, list);
        rcvTTGH.setAdapter(adapterTTGH);
        rcvTTGH.setLayoutManager(new GridLayoutManager(this, 1));

        tvTongList.setText(String.valueOf(list.size()));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}