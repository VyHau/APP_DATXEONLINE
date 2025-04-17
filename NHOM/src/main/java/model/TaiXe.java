package model;

import java.util.Date;

public class TaiXe {
    private String ID_TX;
    private String ID_LOAIXE;
    private String TENTX;
    private Date NGSINH;
    private String CCCD;
    private String GPLX;
    private String SDT;
    private String EMAIL;
    private String BIENSOXE;
    private String TENXE;
    
    public TaiXe(String ID_TaiXe, String ID_LoaiXe, String tenTaiXe, Date ngaySinh, String CCCD, String GPLX, String SDT, String email, String bienSoXe, String tenXe) {
        this.ID_TX = ID_TaiXe;
        this.ID_LOAIXE = ID_LoaiXe;
        this.TENTX = tenTaiXe;
        this.NGSINH = ngaySinh;
        this.CCCD = CCCD;
        this.GPLX = GPLX;
        this.SDT = SDT;
        this.EMAIL = email;
        this.BIENSOXE = bienSoXe;
        this.TENXE = tenXe;
    }

    public TaiXe() {
		// TODO Auto-generated constructor stub
	}

	public String getID_TaiXe() {
        return ID_TX;
    }

    public void setID_TaiXe(String ID_TaiXe) {
        this.ID_TX = ID_TaiXe;
    }

    public String getID_LoaiXe() {
        return ID_LOAIXE;
    }

    public void setID_LoaiXe(String ID_LoaiXe) {
        this.ID_LOAIXE = ID_LoaiXe;
    }

    public String getTenTaiXe() {
        return TENTX;
    }

    public void setTenTaiXe(String tenTaiXe) {
        this.TENTX = tenTaiXe;
    }

    public Date getNgaySinh() {
        return NGSINH;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.NGSINH = ngaySinh;
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
        return EMAIL;
    }

    public void setEmail(String email) {
        this.EMAIL = email;
    }

    public String getBienSoXe() {
        return BIENSOXE;
    }

    public void setBienSoXe(String bienSoXe) {
        this.BIENSOXE = bienSoXe;
    }

    public String getTenXe() {
        return TENXE;
    }

    public void setTenXe(String tenXe) {
        this.TENXE = tenXe;
    }
    
}
