package duanvdph37524.fpoly.techstorre.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import java.util.ArrayList;

import duanvdph37524.fpoly.techstorre.Database.DbHelper;
import duanvdph37524.fpoly.techstorre.model.GioHang;

public class GioHangDAO {
    private SQLiteDatabase database;

    public GioHangDAO(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        database = dbHelper.getReadableDatabase();
    }

    public ArrayList<GioHang> getALL() {
        String sql = "Select * From GioHang";
        return getData(sql);
    }

    public boolean themGioHang(GioHang gioHang) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("maSP", gioHang.getMaSP());
        contentValues.put("soLuong", gioHang.getSoLuongMua());
        long row = database.insert("GioHang", null, contentValues);
        return (row > 0);
    }

    public boolean themGioHang2(GioHang gioHang) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("maSP", gioHang.getMaSP());
        contentValues.put("soLuong", gioHang.getSoLuongMua());
        long row = database.insert("GioHang", null, contentValues);
        return (row > 0);
    }

    public boolean update(GioHang gioHang) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("soLuong", gioHang.getSoLuongMua());
        long row = database.update("GioHang", contentValues,"maGioHang=?",new String[]{String.valueOf(gioHang.getMaGioHang())});
        return (row > 0);
    }

    public boolean xoa(String id) {
        long row = database.delete("GioHang", "maGioHang =?", new String[]{id});
        return (row > 0);
    }

    @SuppressLint("Range")
    public ArrayList<GioHang> getData(String sql, String... selection) {
        ArrayList<GioHang> list = new ArrayList<>();
        Cursor cursor = database.rawQuery(sql, selection);
        while (cursor.moveToNext()) {
            GioHang gioHang = new GioHang();
            gioHang.setMaGioHang(Integer.parseInt(cursor.getString(cursor.getColumnIndex("maGioHang"))));
            gioHang.setMaSP(Integer.parseInt(cursor.getString(cursor.getColumnIndex("maSP"))));
            gioHang.setSoLuong(Integer.parseInt(cursor.getString(cursor.getColumnIndex("soLuong"))));
            list.add(gioHang);
        }
        return list;
    }
}
