package me.myproject.MODEL;

import java.util.Date;

public class TaiXe {
    private String ID_TaiXe;
    private String ID_LoaiXe;
    private String tenTaiXe;
    private Date ngaySinh;
    private String CCCD;
    private String GPLX;
    private String SDT;
    private String email;
    private String matKhau;
    private String bienSoXe;
    private String tenXe;
    private Boolean trangThaiTK;
    
    public TaiXe(){};
    public TaiXe(String ID_TaiXe, String ID_LoaiXe, String tenTaiXe, Date ngaySinh, String CCCD, String GPLX, String SDT, String email, String matKhau, String bienSoXe, String tenXe, Boolean trangThaiTK) {
        this.ID_TaiXe = ID_TaiXe;
        this.ID_LoaiXe = ID_LoaiXe;
        this.tenTaiXe = tenTaiXe;
        this.ngaySinh = ngaySinh;
        this.CCCD = CCCD;
        this.GPLX = GPLX;
        this.SDT = SDT;
        this.email = email;
        this.matKhau = matKhau;
        this.bienSoXe = bienSoXe;
        this.tenXe = tenXe;
        this.trangThaiTK = trangThaiTK;
    }

    public String getID_TaiXe() {
        return ID_TaiXe;
    }

    public void setID_TaiXe(String ID_TaiXe) {
        this.ID_TaiXe = ID_TaiXe;
    }

    public String getID_LoaiXe() {
        return ID_LoaiXe;
    }

    public void setID_LoaiXe(String ID_LoaiXe) {
        this.ID_LoaiXe = ID_LoaiXe;
    }

    public String getTenTaiXe() {
        return tenTaiXe;
    }

    public void setTenTaiXe(String tenTaiXe) {
        this.tenTaiXe = tenTaiXe;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getCCCD() {
        return CCCD;
    }

    public void setCCCD(String CCCD) {
        this.CCCD = CCCD;
    }

    public String getGPLX() {
        return GPLX;
    }

    public void setGPLX(String GPLX) {
        this.GPLX = GPLX;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getBienSoXe() {
        return bienSoXe;
    }

    public void setBienSoXe(String bienSoXe) {
        this.bienSoXe = bienSoXe;
    }

    public String getTenXe() {
        return tenXe;
    }

    public void setTenXe(String tenXe) {
        this.tenXe = tenXe;
    }
    public Boolean getTrangThaiTK() {
        return trangThaiTK;
    }
    public void setTrangThaiTK(Boolean trangThaiTK) {
        this.trangThaiTK = trangThaiTK;
    }
    
}
