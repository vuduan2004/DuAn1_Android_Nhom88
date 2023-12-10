package duanvdph37524.fpoly.techstorre.DAO;

import android.annotation.SuppressLint;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.text.DecimalFormat;
import java.util.ArrayList;

import duanvdph37524.fpoly.techstorre.Database.DbHelper;
import duanvdph37524.fpoly.techstorre.model.HoaDon;
import duanvdph37524.fpoly.techstorre.model.LichSuMua;
import duanvdph37524.fpoly.techstorre.model.SanPham;

public class LichSuDAO {
    private SQLiteDatabase database;
    private Context context;


    public LichSuDAO(Context context) {
        this.context = context;
        DbHelper dbHelper = new DbHelper(context);
        database = dbHelper.getReadableDatabase();
    }

    public ArrayList<LichSuMua> getLichSu(String tuNgay, String denNgay){
        ArrayList<LichSuMua> list = new ArrayList<>();
        String sql = "Select maHD,maSP, soLuongMua From HoaDon Where ngayTao between ? and ? Group By maSP ";
        SanPhamDAO sanPhamDAO = new SanPhamDAO(context);
        HoaDonDAO hoaDonDAO = new HoaDonDAO(context);
        Cursor cursor = database.rawQuery(sql,new String[]{tuNgay, denNgay});
        while(cursor.moveToNext()){
            LichSuMua lichSuMua = new LichSuMua();
            @SuppressLint("Range") SanPham sanPham = sanPhamDAO.getSanPham(cursor.getString(cursor.getColumnIndex("maSP")));
            @SuppressLint("Range") HoaDon hoaDon = hoaDonDAO.getID(cursor.getString(cursor.getColumnIndex("maHD")));
            lichSuMua.setMaSP(Integer.parseInt(String.valueOf(sanPham.getMaSP())));
            lichSuMua.setHinhAnh(sanPham.getHinhAnh());
            lichSuMua.setTenSP(sanPham.getTenSP());
            lichSuMua.setTienSP(Double.parseDouble(String.valueOf(hoaDon.getTongTien())));
            lichSuMua.setSoLuongMua(Integer.parseInt(String.valueOf(hoaDon.getSoLuongMua())));
            list.add(lichSuMua);
        }
        return list;
    }

    public ArrayList<LichSuMua> getLichSu1(){
        ArrayList<LichSuMua> list = new ArrayList<>();
        String sql = "Select maSP, soLuongMua From HoaDon Group By maSP ";
        SanPhamDAO sanPhamDAO = new SanPhamDAO(context);
        Cursor cursor = database.rawQuery(sql,null);
        while(cursor.moveToNext()){
            LichSuMua lichSuMua = new LichSuMua();
            @SuppressLint("Range") SanPham sanPham = sanPhamDAO.getSanPham(cursor.getString(cursor.getColumnIndex("maSP")));
            lichSuMua.setHinhAnh(sanPham.getHinhAnh());
            lichSuMua.setTenSP(sanPham.getTenSP());
            lichSuMua.setTienSP(Double.parseDouble(String.valueOf(sanPham.getGiaTien())));
            list.add(lichSuMua);
        }
        return list;
    }
}
