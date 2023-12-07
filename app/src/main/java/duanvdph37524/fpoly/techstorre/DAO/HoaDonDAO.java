package duanvdph37524.fpoly.techstorre.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import duanvdph37524.fpoly.techstorre.Database.DbHelper;
import duanvdph37524.fpoly.techstorre.model.HoaDon;

public class HoaDonDAO {
    private SQLiteDatabase database;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public HoaDonDAO(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        database = dbHelper.getReadableDatabase();
    }

    public ArrayList<HoaDon> getAll(){
        String sql = "Select * From HoaDon";
        return getData(sql);
    }

    public HoaDon getID(String id){
        String sql = "Select * From HoaDon Where maHD=?";
        ArrayList<HoaDon> list = getData(sql,id);
        return list.get(0);
    }

    public boolean themHoaDon(HoaDon hoaDon){
        ContentValues values = new ContentValues();
        values.put("maSP",hoaDon.getMaSP());
        values.put("soLuongMua",hoaDon.getSoLuongMua());
        values.put("tongTien",hoaDon.getTongTien());
        values.put("ngayTao",simpleDateFormat.format(hoaDon.getNgayTao()));
        long row = database.insert("HoaDon", null, values);
        return (row > 0);
    }

    @SuppressLint("Range")
    public ArrayList<HoaDon> getData(String sql, String...selection){
        ArrayList<HoaDon> list = new ArrayList<>();

        Cursor cursor = database.rawQuery(sql,selection);
        while(cursor.moveToNext()){
            HoaDon hoaDon = new HoaDon();
            hoaDon.setMaHoaDon(Integer.parseInt(cursor.getString(cursor.getColumnIndex("maHD"))));
            hoaDon.setMaSP(Integer.parseInt(cursor.getString(cursor.getColumnIndex("maSP"))));
            hoaDon.setSoLuongMua(Integer.parseInt(cursor.getString(cursor.getColumnIndex("soLuongMua"))));
            hoaDon.setTongTien(Double.parseDouble(cursor.getString(cursor.getColumnIndex("tongTien"))));
          try{
              hoaDon.setNgayTao(simpleDateFormat.parse(cursor.getString(cursor.getColumnIndex("ngayTao"))));
          }catch (Exception e){
          }
          list.add(hoaDon);
        }
        return list;
    }
}
