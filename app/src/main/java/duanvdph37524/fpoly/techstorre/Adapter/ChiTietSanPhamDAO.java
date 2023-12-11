package duanvdph37524.fpoly.techstorre.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import duanvdph37524.fpoly.techstorre.Database.DbHelper;
import duanvdph37524.fpoly.techstorre.model.ChiTietSanPham;

public class ChiTietSanPhamDAO {
    private Context context;
    private SQLiteDatabase database;

    public ChiTietSanPhamDAO(Context context) {
        this.context = context;
        DbHelper dbHelper = new DbHelper(context);
        database = dbHelper.getReadableDatabase();
    }

    public ChiTietSanPham getSoLuong(int id) {
        String sql = "Select * From ChiTietSanPham Where maSP=?";
        ArrayList<ChiTietSanPham> list = getData(sql,String.valueOf(id));
        return list.get(0);
    }

    @SuppressLint("Range")
    public ArrayList<ChiTietSanPham> getData(String sql, String... selection) {
        ArrayList<ChiTietSanPham> list = new ArrayList<>();
        Cursor cursor = database.rawQuery(sql, selection);
        while (cursor.moveToNext()) {
            ChiTietSanPham chiTietSanPham = new ChiTietSanPham();
            chiTietSanPham.setMaSP(Integer.parseInt(cursor.getString(cursor.getColumnIndex("maSP"))));
            chiTietSanPham.setMaLoai(cursor.getString(cursor.getColumnIndex("maLoai")));
            chiTietSanPham.setSoLuong(Integer.parseInt(cursor.getString(cursor.getColumnIndex("soLuong"))));
            chiTietSanPham.setThongTinSP(cursor.getString(cursor.getColumnIndex("thongTinSP")));
            list.add(chiTietSanPham);
        }
        return list;
    }


}
