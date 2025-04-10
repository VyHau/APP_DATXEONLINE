package me.myproject.MODEL;


public class KhachHang {
    private String ID_KH;
    private String tenKhachHang;
    private String SDT;
    private String diaChi;

    public KhachHang(String ID_KH, String tenKhachHang, String SDT, String diaChi) {
        this.ID_KH = ID_KH;
        this.tenKhachHang = tenKhachHang;
        this.SDT = SDT;
        this.diaChi = diaChi;
    }

    public String getID_KH() {
        return ID_KH;
    }

    public void setID_KH(String ID_KH) {
        this.ID_KH = ID_KH;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

}
