package duanvdph37524.fpoly.techstorre.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    public DbHelper(Context context){
        super(context, "TechStore", null, 8);
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
        sqLiteDatabase.execSQL("Insert Into SanPham Values(5,'DT','Samsung Galaxy A05 128GB','https://firebasestorage.googleapis.com/v0/b/teststore-1660c.appspot.com/o/newImagr%2Fph-galaxy-a05-sm-a055-sm-a055fzkgphl-thumb-538437008.webp?alt=media&token=c4006371-6300-48de-979f-58edb33fed69', 3090000)" +
                ",(6,'DT','OPPO A58 6GB-128GB ','https://firebasestorage.googleapis.com/v0/b/teststore-1660c.appspot.com/o/newImagr%2F10055983-dien-thoai-oppo-a58-6gb-128gb-xanh-1.jpg?alt=media&token=2ce933e3-f94b-4bca-a600-6d8922ee98ed'," +
                "4990000)," +
                "(7,'DT','Samsung Galaxy Z Fold5 5G 256GB','https://firebasestorage.googleapis.com/v0/b/teststore-1660c.appspot.com/o/newImagr%2F10055880-dien-thoai-samsung-galaxy-z-fold-5-5g-12gb-256gb-xanh_d%C6%B0%C6%A1ng-1.jpg?alt=media&token=05e4c5f0-2da0-41ae-90a8-46fe2d4f91a1',31990000)" +
                ",(8,'DT','OPPO A78 8GB-256GB','https://firebasestorage.googleapis.com/v0/b/teststore-1660c.appspot.com/o/newImagr%2Foppoa78.png?alt=media&token=2f63d8f6-6a4b-4193-a06e-b77b2945cae8',6990000)");

        sqLiteDatabase.execSQL("Insert Into SanPham Values" +
                "(9,'MT','Laptop Asus TUF Gaming FX506HF','https://firebasestorage.googleapis.com/v0/b/teststore-1660c.appspot.com/o/imageMayTinh%2Ftuftgaming.png?alt=media&token=458c3ca2-528f-4efc-898e-5c5240b57be4',17490000)" +
                ",(10,'MT','Laptop MSI Gaming Thin GF63','https://firebasestorage.googleapis.com/v0/b/teststore-1660c.appspot.com/o/imageMayTinh%2Fmsigaming.jfif?alt=media&token=26602df6-78e2-4c6e-bd81-20c0b6cf4d84',19990000)" +
                ",(11,'MT','Laptop Lenovo IdeaPad 1 ','https://firebasestorage.googleapis.com/v0/b/teststore-1660c.appspot.com/o/imageMayTinh%2Flenovo.jfif?alt=media&token=a0b5500c-4285-4ba5-b471-afd64eab6d29',10790000)" +
                ",(12,'MT','Laptop Dell Inspiron 15','https://firebasestorage.googleapis.com/v0/b/teststore-1660c.appspot.com/o/imageMayTinh%2Fdell.jfif?alt=media&token=24d618c3-6bd8-4765-97c8-7aa6ac2b25b9',20990000)");

        sqLiteDatabase.execSQL("Insert Into SanPham Values" +
                "(13,'DH','Đồng hồ thông minh Garmin Forerunner 55','https://firebasestorage.googleapis.com/v0/b/teststore-1660c.appspot.com/o/imageDongHo%2Fdonghogram.jfif?alt=media&token=b99e6b15-b939-4d76-a863-1b7ee07400a5',4990000)" +
                ",(14,'DH','Đồng hồ định vị trẻ em Masstel Smart Hero 20 4G','https://firebasestorage.googleapis.com/v0/b/teststore-1660c.appspot.com/o/imageDongHo%2Fdonghotreem.jfif?alt=media&token=8ada4da6-8455-4df0-9b9b-75da10be434c',1790000)" +
                ",(15,'DH','Đồng hồ thông minh Garmin Epix Gen 2 Sapphire Titanium','https://firebasestorage.googleapis.com/v0/b/teststore-1660c.appspot.com/o/imageDongHo%2Fdonghoxin.jfif?alt=media&token=21a66b2f-e599-4512-83f7-9e328a12daee',19990000)" +
                ",(16,'DH','Đồng hồ thông minh Garmin Instinct 2X Solar','https://firebasestorage.googleapis.com/v0/b/teststore-1660c.appspot.com/o/imageDongHo%2Fdonghotrang.jfif?alt=media&token=ea30fd0b-9e82-4c4d-bc93-af57b5f291d8',11690000)");

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
        sqLiteDatabase.execSQL("Insert Into ChiTietSanPham Values" +
                "(5,'DT','Màn hình 6.7 inch, PLS LCD, HD+, 720 x 1600 Pixels. Camera sau\t50.0 MP + 2.0 MP. Camera Selfie 8.0 MP RAM 4 GB Bộ nhớ trong\t128GB. CPU Helio G85. Dung lượng pin\t5000 mAh. Thẻ sim 2 - 2 Nano SIM. Hệ điều hành Android 13.0\n" +
                "Xuất xứ Việt Nam / Trung Quốc',20)");
        sqLiteDatabase.execSQL("Insert Into ChiTietSanPham Values" +
                "(6,'DT','Màn hình 6.7 inch, IPS LCD, FHD+, 1080 x 2400 Pixels, Màn hình chính: 1080 x 2400 Pixels. Camera sau 50.0 MP + 2.0 MP. Camera Selfie 8.0 MP. RAM 6 GB. Bộ nhớ trong 128 GB. CPU Helio G85, GPU Mali-G52 MP2. Dung lượng pin 5000 mAh. Thẻ sim 2 - 2 Nano SIM. Hệ điều hành Android 13.0 (ColorOS 13.1). Xuất xứ Trung Quốc',60)");
        sqLiteDatabase.execSQL("Insert Into ChiTietSanPham Values" +
                "(7,'DT','Màn hình 7.6 inch, 6.2 inch, Chính: Dynamic AMOLED 2X, QXGA, 2176 x 1812 pixels. Camera sau 50 MP + 12.0 MP + 10.0 MP. Camera Selfie 10.0 MP + 4.0 MP. RAM 12 GB. Bộ nhớ trong\t256 GB. CPU Snapdragon 8 Gen 2, GPU Adreno 740. Dung lượng pin 4400 mAh" +
                "Thẻ sim 2 - 2 Nano SIM hoặc 1 eSIM, 1 Nano SIM. Hệ điều hành. Android 13.0. Xuất xứ Việt Nam / Trung Quốc',150)");
        sqLiteDatabase.execSQL("Insert Into ChiTietSanPham Values" +
                "(8,'DT','Màn hình Chính: 6.4 inch, Màn hình chính: AMOLED, Màn hình chính: FHD+, Màn hình chính: 1080 x 2400 Pixels. Camera sau 50.0 MP + 2.0 MP. Camera Selfie 8.0 MP. RAM 8 GB. Bộ nhớ trong 256 GB. CPU Snapdragon 680, GPU Adreno 610. Dung lượng pin 5000 mAh. Thẻ sim 2 - 2 Nano SIM. Hệ điều hành Android 13.0 (ColorOS 13.1). Xuất xứ Trung Quốc',100)");

        sqLiteDatabase.execSQL("Insert Into ChiTietSanPham Values" +
                "(9,'MT','Màn hình\t15.6 inch, 1920 x 1080 Pixels, IPS, 144 Hz, 250 nits, IPS LCD LED Backlit, True Tone\n" +
                "CPU\tIntel, Core i5, 10300H\n" +
                "RAM\t8 GB, DDR4, 2933 MHz\n" +
                "Ổ cứng\tSSD 512 GB\n" +
                "Đồ họa\tNVIDIA GeForce GTX 1650 4GB; Intel UHD Graphics\n" +
                "Hệ điều hành\tWindows 10 Home\n" +
                "Trọng lượng\t2.3 kg\n" +
                "Kích thước\t359 x 256 x 24.7 mm\n" +
                "Xuất xứ\tTrung Quốc',200)");
        sqLiteDatabase.execSQL("Insert Into ChiTietSanPham Values" +
                "(10,'MT','Màn hình\t15.6 inch, 1920 x 1080 Pixels, IPS, 144 Hz, IPS FHD\n" +
                "CPU\tIntel, Core i5, 12450H\n" +
                "RAM\t16 GB, DDR4, 3200 MHz\n" +
                "Ổ cứng\tSSD 512 GB\n" +
                "Đồ họa\tNVIDIA GeForce RTX 4050 6GB GDDR6; Intel Iris Xe Graphics\n" +
                "Hệ điều hành\tWindows 11 Home\n" +
                "Trọng lượng\t1.86 kg\n" +
                "Kích thước\t359 x 254 x 21.7 mm\n" +
                "Xuất xứ\tTrung Quốc',90)");
        sqLiteDatabase.execSQL("Insert Into ChiTietSanPham Values" +
                "(11,'MT','Màn hình\t15.6 inch, 1920 x 1080 Pixels, TN, 60 Hz, 220 nits, FHD\n" +
                "CPU\tAMD, Ryzen 5, 5500U\n" +
                "RAM\t16 GB (2 thanh 8 GB), DDR4, 3200 MHz\n" +
                "Ổ cứng\tSSD 512 GB\n" +
                "Đồ họa\tAMD Radeon 610 2GB; AMD Radeon Graphics\n" +
                "Hệ điều hành\tWindows 11 Home Single Language\n" +
                "Trọng lượng\t1.58 kg\n" +
                "Kích thước\t360.2 x 236 x 17.9 mm\n" +
                "Xuất xứ\tTrung Quốc',120)");
        sqLiteDatabase.execSQL("Insert Into ChiTietSanPham Values" +
                "(12,'MT','Màn hình\t15.6 inch, FHD (1920 x 1080), WVA, 120 Hz, 250 nits, Anti-Glare LED-Backlit Display\n" +
                "CPU\tIntel, Core i5, 1335U\n" +
                "RAM\t16 GB (2 thanh 8 GB), DDR4, 2666 MHz\n" +
                "Ổ cứng\tSSD 512 GB\n" +
                "Đồ họa\tNVIDIA GeForce MX550 2GB\n" +
                "Hệ điều hành\tWindows 11 Home\n" +
                "Trọng lượng\t1.66 kg\n" +
                "Kích thước\t358 x 236 x 18 mm\n" +
                "Xuất xứ\tTrung Quốc',40)");
        sqLiteDatabase.execSQL("Insert Into ChiTietSanPham Values" +
                "(13,'DH','Màn hình\t1.04 inch, MIP Chống chói, 208 x 208 Pixels\n" +
                "Chất liệu\tMặt kính: Kính cường lực - Dây: Silicone\n" +
                "Hệ điều hành tương thích\tiOS, Android\n" +
                "Thời gian sử dụng pin\t14 ngày\n" +
                "Thương hiệu\tGarmin',100)");
        sqLiteDatabase.execSQL("Insert Into ChiTietSanPham Values" +
                "(14,'DH','Màn hình\t1.77 inch, IPS TFT, 240 x 240 Pixels\n" +
                "Chất liệu\tDây: Cao su\n" +
                "Hệ điều hành tương thích\tRTOS\n" +
                "Thời gian sử dụng pin\t40 giờ Chế độ bình thường\n" +
                "Thương hiệu\tMasstel',65)");
        sqLiteDatabase.execSQL("Insert Into ChiTietSanPham Values" +
                "(15,'DH','Màn hình\t1.3 inch, AMOLED, 416 x 416 Pixels\n" +
                "Bộ nhớ trong\t32 GB\n" +
                "Chất liệu\tDây: Silicone - Mặt kính: Kính Sapphire\n" +
                "Hệ điều hành tương thích\tiOS, Android\n" +
                "Thời gian sử dụng pin\t16 ngày\n" +
                "Thương hiệu\tGarmin',130)");
        sqLiteDatabase.execSQL("Insert Into ChiTietSanPham Values" +
                "(16,'DH','Màn hình\t1.51 inch, MIP Chống chói, 176 x 176 Pixels\n" +
                "Chất liệu\tMặt kính: Power Glass - Khung máy: Polymer gia cố bằng sợi\n" +
                "Hệ điều hành tương thích\tiOS, Android\n" +
                "Thời gian sử dụng pin\tTối đa 40 ngày Chế độ bình thường, 60 giờ Chế độ GPS, Không giới hạn Chế độ bình thường - Có dùng Solar\n" +
                "Thương hiệu\tGarmin', 65)");


        String createTableGioHang = "Create table GioHang" +
                "(maGioHang Integer Primary key Autoincrement," +
                "maSP Integer References SanPham(maSP)," +
                "soLuong Integer Not null)";

        String createTableHoaDon = "Create table HoaDon" +
                "(maHD Integer Primary key Autoincrement," +
                "maSP Integer References SanPham(maSP)," +
                "soLuongMua Integer Not null," +
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
