package duanvdph37524.fpoly.techstorre.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import duanvdph37524.fpoly.techstorre.Database.DbHelper;


public class NguoiDungDAO {
    private DbHelper dbHelper;
    public NguoiDungDAO(Context context){
        dbHelper = new DbHelper(context);
    }
    public boolean checkLogin(String username, String password){
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM NGUOIDUNG WHERE tendangnhap =? AND matkhau = ?", new String[]{username, password});
        if(cursor.getCount()>0){
            return true;
        }
        return false;
    }
    public boolean Register(String user, String hoten, String namsinh, String sdt, String diachi, String matkhau){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tendangnhap", user);
        contentValues.put("hoten", hoten);
        contentValues.put("namsinh", namsinh);
        contentValues.put("sodienthoai", sdt);
        contentValues.put("diachi", diachi);
        contentValues.put("matkhau", matkhau);
        long check = sqLiteDatabase.insert("NGUOIDUNG",null, contentValues);
        if (check!=-1){
            return true;
        }
        return false;
    }
}
