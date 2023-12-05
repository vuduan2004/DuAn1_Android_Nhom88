package duanvdph37524.fpoly.techstorre.model;

public class ChiTietSanPham {
    private int maSP;
    private String maLoai, thongTinSP;
    private int soLuong;

    public ChiTietSanPham(int maSP, String maLoai, String thongTinSP, int soLuong) {
        this.maSP = maSP;
        this.maLoai = maLoai;
        this.thongTinSP = thongTinSP;
        this.soLuong = soLuong;
    }

    public ChiTietSanPham() {
    }

    public int getMaSP() {
        return maSP;
    }

    public void setMaSP(int maSP) {
        this.maSP = maSP;
    }

    public String getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(String maLoai) {
        this.maLoai = maLoai;
    }

    public String getThongTinSP() {
        return thongTinSP;
    }

    public void setThongTinSP(String thongTinSP) {
        this.thongTinSP = thongTinSP;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}
