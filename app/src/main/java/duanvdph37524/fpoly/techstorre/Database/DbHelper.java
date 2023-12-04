package duanvdph37524.fpoly.techstorre.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    public DbHelper(Context context){
        super(context, "TechStore", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String qNguoiDung = "CREATE TABLE NGUOIDUNG(tendangnhap text primary key, hoten text, namsinh text, sodienthoai text, diachi text, matkhau text)";
        sqLiteDatabase.execSQL(qNguoiDung);
        String dNguoiDung = "INSERT INTO NGUOIDUNG VALUES('vudinhduan', 'Vũ Đình Duẩn', '2004', '0988539548', 'Vân Canh Hà Nội', 'duan123')";
        sqLiteDatabase.execSQL(dNguoiDung);

        String createTableLoaiSP = "Create table LoaiSanPham" +
                "(maLoai String Primary key," +
                "theLoai String Not null," +
                "hinhAnh String Not null)";
        sqLiteDatabase.execSQL(createTableLoaiSP);
        sqLiteDatabase.execSQL("INSERT INTO LoaiSanPham VALUES ('DT','Điện thoại','https://firebasestorage.googleapis.com/v0/b/teststore-1660c.appspot.com/o/newImagr%2FatvPhone%201.png?alt=media&token=53a02575-da5a-4aaa-999b-41dd0086a96c')" +
                ",('MT','Máy tính','https://firebasestorage.googleapis.com/v0/b/teststore-1660c.appspot.com/o/newImagr%2Flaptop2.png?alt=media&token=e410cb8e-44f1-43db-bdc3-841677358353')" +
                ",('DH','Đồng hồ','https://firebasestorage.googleapis.com/v0/b/teststore-1660c.appspot.com/o/newImagr%2Fdongho1.png?alt=media&token=4800969a-eee9-4ceb-a058-9954dd05f268')");

        String createTableSanPham = "Create table SanPham" +
                "(maSP Integer Primary key Autoincrement," +
                "maLoai String References LoaiSanPham(maLoai)," +
                "tenSP String Not null," +
                "hinhAnh String Not null," +
                "giaTien Real Not null)";
        sqLiteDatabase.execSQL(createTableSanPham);
        sqLiteDatabase.execSQL("INSERT INTO SanPham VALUES(1,'DT','Realme 11 Pro 5G 8GB-256GB','https://firebasestorage.googleapis.com/v0/b/teststore-1660c.appspot.com/o/newImagr%2Fdtrealme2.jpg?alt=media&token=f530c004-41e1-45a8-b09c-895b4cd54697',11990000)" +
                ",(2,'DT','iPhone 15 Pro Max 256GB','https://firebasestorage.googleapis.com/v0/b/teststore-1660c.appspot.com/o/newImagr%2Fdtiphone.png?alt=media&token=6648861a-327b-4a94-a80f-881d8e82985f',34990000)" +
                ",(3,'MT','MacBook Pro 14 inch M1 Pro 2021','https://firebasestorage.googleapis.com/v0/b/teststore-1660c.appspot.com/o/newImagr%2Fmacbook.jpg?alt=media&token=69c709a4-53c1-45e6-93e5-824722063639',47490000)" +
                ",(4,'DH','Apple Watch Series 6 GPS 44mm','https://firebasestorage.googleapis.com/v0/b/teststore-1660c.appspot.com/o/newImagr%2Fsmw.jpg?alt=media&token=3e4f0686-0fb0-45aa-ab9d-5ffa43a7767c',12490000)");

        String createTableChiTietSP = "Create table ChiTietSanPham" +
                "(maSP Integer References SanPham(maSP)," +
                "maLoai String References LoaiSanPham(maLoai)," +
                "thongTinSP String Not null," +
                "soLuong Integer Not null)";
        sqLiteDatabase.execSQL(createTableChiTietSP);
        sqLiteDatabase.execSQL("Insert Into ChiTietSanPham Values" +
                "(1,'DT'," +
                "'Màn hình6.7 inch, OLED, FHD+, 1080 x 2412 Pixels. Camera sau 100.0 MP + 2.0 MP. Camera Selfie 16.0 MP. RAM 8GB. Bộ nhớ trong 256GB. CPUMediaTek Dimensity 7050 5GGPU. Mali-G68. Dung lượng pin 5000mAh. Thẻ sim 2 Nano SIM. Hệ điều hành Android 13.0. Xuất xứ Trung Quốc.'" +
                ",50)," +
                "(3,'MT'," +
                "'Loại card đồ họa 14 nhân GPU. Dung lượng RAM 16GB. Ổ cứng 512GB SSD. Kích thước màn hình 14.2 inches. Công nghệ màn hình 120Hz, Liquid Retina, Mini LED, XDR. Hệ điều hành MacOS. Độ phân giải màn hình 3024 x 1964 pixels. Cổng giao tiếp 1x Headphone jack, 1x MagSafe port, 1x HDMI, 1x Thunderbolt 4, 1x SDXC'" +
                ",50)," +
                "(2,'DT','Màn hình 6.7 inch, OLED, Super Retina XDR, 2796 x 1290 Pixels. Camera sau\t48.0 MP + 12.0 MP + 12.0 MP Camera Selfie\t12.0 MP. RAM 8 GB. Bộ nhớ trong 256 GB. CPU Apple A17 Pro. Dung lượng pin 29 Giờ. Thẻ sim 1 - 1 eSIM, 1 Nano SIM Hệ điều hành iOS 17',50)," +
                "(4,'DH','Chipset Apple S6. Công nghệ màn hình: Retina LTPO OLED, 16 triệu màu,mặt lưng kính Sapphire, 3D Touch, độ sáng 1000 nits. Kích thước màn hình 1.78 inch. Hãng sản xuất Apple Chính hãng. Thời lượng pin 18 giờ sạc đầy trong 1.5 giờ. Cảm biến tiệm cận, cảm biến gia tốc, con quay hồi chuyển, gia tốc kế, la bàn, cảm biến nhịp tim \n" +
                "Tính năng khác\n" +
                "- Đo điện tâm đồ ECG\n" +
                "- Nghe nhạc trên Apple Music\n" +
                "- Các tính năng luyện tập thể dục\n" +
                "- Thay đổi mặt đồng hồ',50)");

        String createTableGioHang = "Create table GioHang" +
                "(maGioHang Integer Primary key Autoincrement," +
                "maSP Integer References SanPham(maSP)," +
                "soLuong Integer Not null)";

        String createTableHoaDon = "Create table HoaDon" +
                "(maHD Integer Primary key Autoincrement," +
                "tongTien Real Not null," +
                "ngayTao Date Not null)";

        String createTableChiTieHD = "Create table ChiTietHoaDon" +
                "(maHD Integer References HoaDon(maHD)," +
                "maSP Integer References SanPham(maSP)," +
                "tenDN String References KhachHang(tenDN)," +
                "tongTien Real Not null," +
                "ngayTao Date Not null," +
                "soLuong Integer Not null)";

        sqLiteDatabase.execSQL(createTableChiTieHD);
        sqLiteDatabase.execSQL(createTableHoaDon);
        sqLiteDatabase.execSQL(createTableGioHang);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        if (i !=i1){
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS NGUOIDUNG");
            sqLiteDatabase.execSQL("Drop table if exists GioHang");
            sqLiteDatabase.execSQL("Drop table if exists SanPham");
            sqLiteDatabase.execSQL("Drop table if exists LoaiSanPham");
            sqLiteDatabase.execSQL("Drop table if exists ChiTietSanPham");
            sqLiteDatabase.execSQL("Drop table if exists HoaDon");
            sqLiteDatabase.execSQL("Drop table if exists ChiTietHoaDon");
            onCreate(sqLiteDatabase);
        }
    }
}
