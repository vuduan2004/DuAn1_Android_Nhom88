package duanvdph37524.fpoly.techstorre.Activity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import duanvdph37524.fpoly.techstorre.Adapter.AdapterHoaDon;
import duanvdph37524.fpoly.techstorre.Adapter.AdapterLichSu;
import duanvdph37524.fpoly.techstorre.DAO.HoaDonDAO;
import duanvdph37524.fpoly.techstorre.DAO.LichSuDAO;
import duanvdph37524.fpoly.techstorre.Fragment.FragmentLoaiSP;
import duanvdph37524.fpoly.techstorre.Fragment.Fragment_GioHang;
import duanvdph37524.fpoly.techstorre.MainActivity;
import duanvdph37524.fpoly.techstorre.R;
import duanvdph37524.fpoly.techstorre.model.HoaDon;
import duanvdph37524.fpoly.techstorre.model.LichSuMua;

public class LichSuDonHangActivity extends AppCompatActivity {

    Toolbar toolbar;

    RecyclerView rcvLichSu;
    EditText edTuNgay, edDenNgay;
    TextView tvTongCong;
    Button btnXemLS, btnTuNgay, btnDenNgay;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
    int ngay, thang, nam;
    Context context = this;
    LichSuDAO lichSuDAO;
    AdapterLichSu adapterLichSu;
    ArrayList<LichSuMua> list = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lich_su_don_hang);
        toolbar = findViewById(R.id.toolbarLS);
        rcvLichSu = findViewById(R.id.rcv_LichSu);
        edDenNgay = findViewById(R.id.edDenNgay);
        edTuNgay = findViewById(R.id.edTuNgay);
        tvTongCong = findViewById(R.id.tv_tonglichsu);
//        btnTuNgay = findViewById(R.id.btnTuNgay);
//        btnDenNgay = findViewById(R.id.btnDenNgay);
        btnXemLS = findViewById(R.id.btnXemLS);
        toolbar.setTitle("Lịch Sử Đơn Hàng");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        DatePickerDialog.OnDateSetListener dateTuNgay = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                ngay = dayOfMonth;
                thang = month;
                nam = year;
                GregorianCalendar gregorianCalendar = new GregorianCalendar(nam, thang, ngay);
                edTuNgay.setText(simpleDateFormat.format(gregorianCalendar.getTime()));
            }
        };

        DatePickerDialog.OnDateSetListener dateDenNgay = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                ngay = dayOfMonth;
                thang = month;
                nam = year;
                GregorianCalendar gregorianCalendar = new GregorianCalendar(nam, thang, ngay);
                edDenNgay.setText(simpleDateFormat.format(gregorianCalendar.getTime()));
            }
        };

        edTuNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                ngay = calendar.get(Calendar.DAY_OF_MONTH);
                thang = calendar.get(Calendar.MONTH);
                nam = calendar.get(Calendar.YEAR);
                DatePickerDialog dialog = new DatePickerDialog(context, 0, dateTuNgay, nam, thang, ngay);
                dialog.show();
            }
        });

        edDenNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                ngay = calendar.get(Calendar.DAY_OF_MONTH);
                thang = calendar.get(Calendar.MONTH);
                nam = calendar.get(Calendar.YEAR);
                DatePickerDialog dialog = new DatePickerDialog(context, 0, dateDenNgay, nam, thang, ngay);
                dialog.show();
            }
        });

        btnXemLS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tuNgay = edTuNgay.getText().toString();
                String denNgay = edDenNgay.getText().toString();
                lichSuDAO = new LichSuDAO(context);
                list = lichSuDAO.getLichSu(tuNgay, denNgay);
////                list = lichSuDAO.getLichSu1();
                adapterLichSu = new AdapterLichSu(context, list);
                if (checkTime(tuNgay, denNgay, edTuNgay, edDenNgay)) {
                    rcvLichSu.setAdapter(adapterLichSu);
                    rcvLichSu.setLayoutManager(new GridLayoutManager(context, 1));
                    if(list.isEmpty()){
                        Toast.makeText(context, "Không có đơn hàng trong thời gian này", Toast.LENGTH_SHORT).show();
                    }
                    tvTongCong.setText("Tổng cộng: " + list.size() + " sản phẩm");
                }

            }
        });


    }


    public boolean checkTime(String tNgay, String dNgay, EditText tuNgay, EditText denNgay) {
        if (TextUtils.isEmpty(tNgay) || TextUtils.isEmpty(dNgay))
            if (TextUtils.isEmpty(tNgay)) {
                tuNgay.setError("Vui lòng chọn ngày bắt đầu");
            } else {
                tuNgay.setError(null);
            }
        if (TextUtils.isEmpty(dNgay)) {
            denNgay.setError("Vui lòng chọn ngày kết thúc");
        } else {
            denNgay.setError(null);
        }

        try {
            Date startDate = simpleDateFormat.parse(tNgay);
            Date endDay = simpleDateFormat.parse(dNgay);
            Date cDate = new Date();//ngày hiện tại
            if (startDate.after(cDate) || endDay.after(cDate) || startDate.after(endDay)) {
                if (startDate.after(cDate)) {
                    tuNgay.setError("Không được lớn hơn ngày hiện tại");
                } else {
                    tuNgay.setError(null);
                }

                if (endDay.after(cDate)) {
                    denNgay.setError("Không được lớn hơn ngày hiện tại");
                } else {
                    denNgay.setError(null);
                }

                if (startDate.after(endDay)) {
                    tuNgay.setError("Từ ngày không được muộn hơn đến ngày");
                } else {
                    denNgay.setError(null);
                }

                return false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
            Toast.makeText(context, "Lỗi ngày tháng", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}


