package duanvdph37524.fpoly.techstorre.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class GioHang implements Parcelable {
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

    protected GioHang(Parcel in) {
        maGioHang = in.readInt();
        maSP = in.readInt();
        soLuong = in.readInt();
        soLuongMua = in.readInt();
    }

    public static final Creator<GioHang> CREATOR = new Creator<GioHang>() {
        @Override
        public GioHang createFromParcel(Parcel in) {
            return new GioHang(in);
        }

        @Override
        public GioHang[] newArray(int size) {
            return new GioHang[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(maGioHang);
        dest.writeInt(maSP);
        dest.writeInt(soLuong);
        dest.writeInt(soLuongMua);
    }
}
