package duanvdph37524.fpoly.techstorre.DAO;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import java.util.ArrayList;

import duanvdph37524.fpoly.techstorre.Database.DbHelper;
import duanvdph37524.fpoly.techstorre.model.LoaiSanPham;

public class LoaiSanPhamDAO {
    private SQLiteDatabase database;

    public LoaiSanPhamDAO(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        database = dbHelper.getReadableDatabase();
    }

    public ArrayList<LoaiSanPham> getALL(){
        String sql = "SELECT * FROM LoaiSanPham";
        return getData(sql);
    }

    public LoaiSanPham getID(String id){
        String sql = "SELECT * FROM LoaiSanPham WHERE maLoai =?";
        ArrayList<LoaiSanPham> list = getData(sql,id);
        return list.get(0);
    }
    @SuppressLint("Range")
    public ArrayList<LoaiSanPham> getData(String sql, String... selection) {
        ArrayList<LoaiSanPham> list = new ArrayList<>();
        Cursor cursor = database.rawQuery(sql, selection);
        while (cursor.moveToNext()) {
            LoaiSanPham loaiSanPham = new LoaiSanPham();
            loaiSanPham.setMaLoai(cursor.getString(cursor.getColumnIndex("maLoai")));
            loaiSanPham.setTenLoai(cursor.getString(cursor.getColumnIndex("theLoai")));
            loaiSanPham.setHinhAnh(cursor.getString(cursor.getColumnIndex("hinhAnh")));
            list.add(loaiSanPham);
        }
        return list;
    }

}
