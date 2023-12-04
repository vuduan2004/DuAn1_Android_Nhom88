package duanvdph37524.fpoly.techstorre.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.TextView;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import duanvdph37524.fpoly.techstorre.Adapter.AdapterSanPham;
import duanvdph37524.fpoly.techstorre.DAO.SanPhamDAO;
import duanvdph37524.fpoly.techstorre.R;
import duanvdph37524.fpoly.techstorre.model.SanPham;

public class ListSanPhamActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView rcvListSP;
    TextView tvAll, tvHot, tvSapXep;
    SanPhamDAO sanPhamDAO;
    AdapterSanPham adapterSanPham;
    Context context = this;
    ArrayList<SanPham> list = new ArrayList<>();
    ArrayList<SanPham> newlist = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_san_pham);
        rcvListSP = findViewById(R.id.rcv_listSP);
        tvAll = findViewById(R.id.tv_All);
        tvHot = findViewById(R.id.tv_hot);
        tvSapXep = findViewById(R.id.tv_sapxep);
        toolbar = findViewById(R.id.toolbarA);
        toolbar.setTitle("Danh sách sản phẩm");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        sanPhamDAO = new SanPhamDAO(this);
        Intent intent = getIntent();
        String maLoai = intent.getStringExtra("maLoai");
        list = sanPhamDAO.getALL1(maLoai);
        newlist = sanPhamDAO.getALL1(maLoai);
        adapterSanPham = new AdapterSanPham(this, list);
        rcvListSP.setAdapter(adapterSanPham);
        rcvListSP.setLayoutManager(new GridLayoutManager(this, 2));

        tvSapXep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(context, v);
                MenuInflater inflater = popupMenu.getMenuInflater();
                inflater.inflate(R.menu.popup_menu, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        int id = item.getItemId();
                        if (id == R.id.it_lonNhat) {
                            Collections.sort(list, new Comparator<SanPham>() {
                                @Override
                                public int compare(SanPham o1, SanPham o2) {
                                    return (int) (o2.getGiaTien() - o1.getGiaTien());
                                }
                            });
                        } else if (id == R.id.it_nhoNhat) {
                            Collections.sort(list, new Comparator<SanPham>() {
                                @Override
                                public int compare(SanPham o1, SanPham o2) {
                                    return (int) (o1.getGiaTien() - o2.getGiaTien());
                                }
                            });
                        }
                        adapterSanPham.notifyDataSetChanged();
                        return false;
                    }
                });
                popupMenu.show();
            }
        });
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_item, menu);
        MenuItem item = menu.findItem(R.id.it_Search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                list.clear();
                String timKiem = xoaDau(query).toLowerCase();
                for (SanPham sanPham : newlist) {
                    String ten = xoaDau(sanPham.getTenSP().toLowerCase());
                    if (ten.equals(timKiem)) {
                        list.add(sanPham);
                    }
                    adapterSanPham.notifyDataSetChanged();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                list.clear();
                String timKiem = xoaDau(newText).toLowerCase();
                for (SanPham sanPham : newlist) {
                    String ten = xoaDau(sanPham.getTenSP()).toLowerCase();
                    if (ten.contains(timKiem)) {
                        list.add(sanPham);
                    }

                }
                adapterSanPham.notifyDataSetChanged();
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    private String xoaDau(String text) {
        return Normalizer.normalize(text, Normalizer.Form.NFD).replaceAll("\\p{M}", "");
    }
}