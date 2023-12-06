package duanvdph37524.fpoly.techstorre.DAO;

import static android.content.Context.MODE_PRIVATE;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import duanvdph37524.fpoly.techstorre.Database.DbHelper;
import duanvdph37524.fpoly.techstorre.model.NguoiDung;


public class NguoiDungDAO {
    private DbHelper dbHelper;
    SharedPreferences sharedPreferences;
    public NguoiDungDAO(Context context){
        dbHelper = new DbHelper(context);
        sharedPreferences = context.getSharedPreferences("THONGTIN", MODE_PRIVATE);
    }
    public boolean checkLogin(String username, String password){
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM NGUOIDUNG WHERE tendangnhap =? AND matkhau = ?", new String[]{username, password});
        if(cursor.getCount()!=0){
            cursor.moveToFirst();
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("tendangnhap", cursor.getString(0));
            editor.putString("hoten", cursor.getString(1));
            editor.putString("diachi", cursor.getString(4));
            editor.putString("namsinh", cursor.getString(2));
            editor.putString("sodienthoai", cursor.getString(3));
            editor.commit();
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
    public boolean capnhatThongTin(String tendangnhap, String hoten,String namsinh, String sodienthoai, String diachi){
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("hoten", hoten);
        contentValues.put("namsinh", namsinh);
        contentValues.put("sodienthoai", sodienthoai);
        contentValues.put("diachi", diachi);
        long check = sqLiteDatabase.update("NGUOIDUNG", contentValues, "tendangnhap =?", new String[]{String.valueOf(tendangnhap)});
        if (check == -1)
            return false;
        return true;
    }
    public int capNhatMatKhau(String tendangnhap, String olPass, String newPass) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM NGUOIDUNG WHERE tendangnhap=? AND matkhau=?", new String[]{tendangnhap, olPass});
        if (cursor.getCount() > 0) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("matkhau", newPass);
            long check = sqLiteDatabase.update("NGUOIDUNG", contentValues, "tendangnhap=?", new String[]{tendangnhap});
            if (check == -1)
                return -1;
            return 1;



        }
        return 0;
    }

}
