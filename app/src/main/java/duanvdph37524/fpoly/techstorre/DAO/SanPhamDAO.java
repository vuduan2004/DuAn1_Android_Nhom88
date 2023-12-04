package duanvdph37524.fpoly.techstorre.DAO;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import duanvdph37524.fpoly.techstorre.Database.DbHelper;
import duanvdph37524.fpoly.techstorre.model.SanPham;

public class SanPhamDAO {
    private SQLiteDatabase database;

    public SanPhamDAO(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        database = dbHelper.getReadableDatabase();
    }

    public ArrayList<SanPham> getAll(){
        String sql = "Select * From SanPham";
        return getData(sql);
    }

    public ArrayList<SanPham> getALL1(String id){
        String sql = "Select * From SanPham Where maLoai=?";
        return getData(sql,id);
    }

    public SanPham getSanPham(String id){
        String sql = "Select * From SanPham Where maSP =?";
        ArrayList<SanPham> list = getData(sql,id);
        return list.get(0);
    }

    @SuppressLint("Range")
    public ArrayList<SanPham> getData(String sql, String... selectionArgs) {
        ArrayList<SanPham> list = new ArrayList<>();
        Cursor cursor = database.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            SanPham sanPham = new SanPham();
            sanPham.setMaSP(Integer.parseInt(cursor.getString(cursor.getColumnIndex("maSP"))));
            sanPham.setMaLoai(cursor.getString(cursor.getColumnIndex("maLoai")));
            sanPham.setTenSP(cursor.getString(cursor.getColumnIndex("tenSP")));
            sanPham.setHinhAnh(cursor.getString(cursor.getColumnIndex("hinhAnh")));
            sanPham.setGiaTien(Double.parseDouble(cursor.getString(cursor.getColumnIndex("giaTien"))));
            list.add(sanPham);
        }
        return list;
    }
}
