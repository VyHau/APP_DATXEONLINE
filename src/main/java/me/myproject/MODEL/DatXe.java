package me.myproject.MODEL;

import java.util.Date;
public class DatXe {
    private String ID_DatXe;
    private String ID_KH;
    private String ID_TX;
    private String ID_ThanhToan;
    private String ID_KhuyenMai;
    private String DiemTra;
    private String DiemDon;
    private Date thoiGianDat;
    private Date thoiGianDon;
    private Date thoiGianDen;
    private String trangThai;
    private double khoangCach;
    private int diemSo;
    private String danhGia;

    public DatXe(){};
    public DatXe(String ID_DatXe, String ID_KH, String ID_TX, String ID_ThanhToan, String ID_KhuyenMai,
             String DiemTra, String DiemDon, Date thoiGianDat, Date thoiGianDon, Date thoiGianDen,
            String trangThai, double khoangCach, int diemSo, String danhGia) {
        this.ID_DatXe = ID_DatXe;
        this.ID_KH = ID_KH;
        this.ID_TX = ID_TX;
        this.ID_ThanhToan = ID_ThanhToan;
        this.ID_KhuyenMai = ID_KhuyenMai;
        this.DiemTra = DiemTra;
        this.DiemDon = DiemDon;
        this.thoiGianDat = thoiGianDat;
        this.thoiGianDon = thoiGianDon;
        this.thoiGianDen = thoiGianDen;
        this.trangThai = trangThai;
        this.khoangCach = khoangCach;
        this.diemSo = diemSo;
        this.danhGia = danhGia;

    }

    public String getID_DatXe() {
        return ID_DatXe;
    }

    public void setID_DatXe(String ID_DatXe) {
        this.ID_DatXe = ID_DatXe;
    }

    public String getID_KH() {
        return ID_KH;
    }

    public void setID_KH(String ID_KH) {
        this.ID_KH = ID_KH;
    }

    public String getID_TX() {
        return ID_TX;
    }

    public void setID_TX(String ID_TX) {
        this.ID_TX = ID_TX;
    }

    public String getID_ThanhToan() {
        return ID_ThanhToan;
    }

    public void setID_ThanhToan(String ID_ThanhToan) {
        this.ID_ThanhToan = ID_ThanhToan;
    }

    public String getID_KhuyenMai() {
        return ID_KhuyenMai;
    }

    public void setID_KhuyenMai(String ID_KhuyenMai) {
        this.ID_KhuyenMai = ID_KhuyenMai;
    }


    public String getDiemTra() {
        return DiemTra;
    }

    public void setDiemTra(String DiemTra) {
        this.DiemTra = DiemTra;
    }

    public String getDiemDon() {
        return DiemDon;
    }

    public void setDiemDon(String DiemDon) {
        this.DiemDon = DiemDon;
    }

    public Date getThoiGianDat() {
        return thoiGianDat;
    }

    public void setThoiGianDat(Date thoiGianDat) {
        this.thoiGianDat = thoiGianDat;
    }

    public Date getThoiGianDon() {
        return thoiGianDon;
    }

    public void setThoiGianDon(Date thoiGianDon) {
        this.thoiGianDon = thoiGianDon;
    }

    public Date getThoiGianDen() {
        return thoiGianDen;
    }

    public void setThoiGianDen(Date thoiGianDen) {
        this.thoiGianDen = thoiGianDen;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public double getKhoangCach() {
        return khoangCach;
    }

    public void setKhoangCach(double khoangCach) {
        this.khoangCach = khoangCach;
    }

    public int getDiemSo() {
        return diemSo;
    }

    public void setDiemSo(int diemSo) {
        this.diemSo = diemSo;
    }

    public String getDanhGia() {
        return danhGia;
    }

    public void setDanhGia(String danhGia) {
        this.danhGia = danhGia;
    }
    
}
