package duanvdph37524.fpoly.techstorre.DAO;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import duanvdph37524.fpoly.techstorre.Database.DbHelper;
import duanvdph37524.fpoly.techstorre.model.ChiTietSanPham;

public class ChiTietSanPhamDAO {
    private SQLiteDatabase database;

    public ChiTietSanPhamDAO(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        database = dbHelper.getReadableDatabase();
    }

    public ChiTietSanPham getThongTin(int id){
        String sql = "Select * from ChiTietSanPham where maSP =?";
        ArrayList<ChiTietSanPham> list = getData(sql, String.valueOf(id));
        return list.get(0);
    }

    public ArrayList<ChiTietSanPham> getALL(){
        String sql = "Select * From ChiTietSanPham";
        return getData(sql);
    }
    @SuppressLint("Range")
    public ArrayList<ChiTietSanPham> getData(String sql, String...selection){
        ArrayList<ChiTietSanPham> list = new ArrayList<>();
        Cursor cursor = database.rawQuery(sql,selection);
        while (cursor.moveToNext()){
            ChiTietSanPham chiTietSanPham = new ChiTietSanPham();
            chiTietSanPham.setMaSP(Integer.parseInt(cursor.getString(cursor.getColumnIndex("maSP"))));
            chiTietSanPham.setMaLoai(cursor.getString(cursor.getColumnIndex("maLoai")));
            chiTietSanPham.setThongTinSP(cursor.getString(cursor.getColumnIndex("thongTinSP")));
            chiTietSanPham.setSoLuong(Integer.parseInt(cursor.getString(cursor.getColumnIndex("soLuong"))));
            list.add(chiTietSanPham);
        }
        return list;
    }
}
