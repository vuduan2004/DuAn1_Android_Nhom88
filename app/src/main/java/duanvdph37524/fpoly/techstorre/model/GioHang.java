package duanvdph37524.fpoly.techstorre.model;

public class GioHang {
    private int maGioHang,maSP, soLuong,soLuongMua;

    public GioHang(int maGioHang, int maSP, int soLuong) {
        this.maGioHang = maGioHang;
        this.maSP = maSP;
        this.soLuong = soLuong;
    }

    public GioHang(int maSP, int soLuong) {
        this.maSP = maSP;
        this.soLuong = soLuong;
    }

    public GioHang(int maGioHang, int maSP, int soLuong, int soLuongMua) {
        this.maGioHang = maGioHang;
        this.maSP = maSP;
        this.soLuong = soLuong;
        this.soLuongMua = soLuongMua;
    }

    public GioHang() {
    }

    public int getMaGioHang() {
        return maGioHang;
    }

    public void setMaGioHang(int maGioHang) {
        this.maGioHang = maGioHang;
    }

    public int getMaSP() {
        return maSP;
    }

    public void setMaSP(int maSP) {
        this.maSP = maSP;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getSoLuongMua() {
        return soLuongMua;
    }

    public void setSoLuongMua(int soLuongMua) {
        this.soLuongMua = soLuongMua;
    }
}
