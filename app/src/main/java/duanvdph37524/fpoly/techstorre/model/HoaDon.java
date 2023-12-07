package duanvdph37524.fpoly.techstorre.model;

import java.util.Date;

public class HoaDon {
    private int maHoaDon, maSP, soLuongMua;
    private double tongTien;
    private Date ngayTao;

    public HoaDon() {
    }

    public HoaDon(int maHoaDon, int maSP, int soLuongMua, double tongTien, Date ngayTao) {
        this.maHoaDon = maHoaDon;
        this.maSP = maSP;
        this.soLuongMua = soLuongMua;
        this.tongTien = tongTien;
        this.ngayTao = ngayTao;
    }

    public int getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(int maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public int getMaSP() {
        return maSP;
    }

    public void setMaSP(int maSP) {
        this.maSP = maSP;
    }

    public int getSoLuongMua() {
        return soLuongMua;
    }

    public void setSoLuongMua(int soLuongMua) {
        this.soLuongMua = soLuongMua;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }
}
