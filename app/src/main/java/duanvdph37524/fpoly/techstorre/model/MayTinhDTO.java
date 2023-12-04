package duanvdph37524.fpoly.techstorre.model;

public class MayTinhDTO {
    private int mamt;
    private String tenmt;
    private String hinhanh;
    private int giatien;

    public MayTinhDTO() {
    }

    public MayTinhDTO(int mamt, String tenmt, String hinhanh, int giatien) {
        this.mamt = mamt;
        this.tenmt = tenmt;
        this.hinhanh = hinhanh;
        this.giatien = giatien;
    }

    public int getMamt() {
        return mamt;
    }

    public void setMamt(int mamt) {
        this.mamt = mamt;
    }

    public String getTenmt() {
        return tenmt;
    }

    public void setTenmt(String tenmt) {
        this.tenmt = tenmt;
    }

    public String getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }

    public int getGiatien() {
        return giatien;
    }

    public void setGiatien(int giatien) {
        this.giatien = giatien;
    }
}
