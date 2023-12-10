package duanvdph37524.fpoly.techstorre.model;

public class LichSuMua {
    private int maSP;
    private String hinhAnh, tenSP;
    private int soLuongMua;
    private double tienSP;

    public LichSuMua() {
    }

    public LichSuMua(int maSP, String hinhAnh, String tenSP, int soLuongMua, double tienSP) {
        this.maSP = maSP;
        this.hinhAnh = hinhAnh;
        this.tenSP = tenSP;
        this.soLuongMua = soLuongMua;
        this.tienSP = tienSP;
    }

    public int getMaSP() {
        return maSP;
    }

    public void setMaSP(int maSP) {
        this.maSP = maSP;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public int getSoLuongMua() {
        return soLuongMua;
    }

    public void setSoLuongMua(int soLuongMua) {
        this.soLuongMua = soLuongMua;
    }

    public double getTienSP() {
        return tienSP;
    }

    public void setTienSP(double tienSP) {
        this.tienSP = tienSP;
    }
}
