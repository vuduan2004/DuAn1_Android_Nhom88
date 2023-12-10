package duanvdph37524.fpoly.techstorre.DAO;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import duanvdph37524.fpoly.techstorre.Database.DbHelper;
import duanvdph37524.fpoly.techstorre.model.SanPham;

public class ThongKeDAO {
    private SQLiteDatabase database;
    private Context context;
    SanPhamDAO sanPhamDAO;

    public ThongKeDAO(Context context) {
        this.context = context;
        DbHelper dbHelper = new DbHelper(context);
        database = dbHelper.getReadableDatabase();

    }

    public ArrayList<SanPham> getHot(String maLoai) {
        ArrayList<SanPham> list = new ArrayList<>();
        String sql = "SELECT * FROM HoaDon WHERE maSP IN (SELECT maSP FROM ChiTietSanPham WHERE maLoai =?)";
        Cursor cursor = database.rawQuery(sql, new String[]{maLoai});
        sanPhamDAO = new SanPhamDAO(context);
        while (cursor.moveToNext()) {
            @SuppressLint("Range") SanPham sanPham = sanPhamDAO.getSanPham(cursor.getString(cursor.getColumnIndex("maSP")));
            sanPham.setMaSP(cursor.getColumnIndex("maSP"));
            sanPham.setMaLoai(sanPham.getMaLoai());
            sanPham.setGiaTien(sanPham.getGiaTien());
            sanPham.setHinhAnh(sanPham.getHinhAnh());
            sanPham.setTenSP(sanPham.getTenSP());
            list.add(sanPham);
        }
        return list;
    }

}
