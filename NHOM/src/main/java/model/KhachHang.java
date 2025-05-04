package model;

public class KhachHang {
    private String ID_KH;
    private String TENKH;
    private String SDT;
    private String DIACHI;
    private String MATKHAU;

    public String getMATKHAU() {
        return MATKHAU;
    }

    public void setMATKHAU(String mATKHAU) {
        MATKHAU = mATKHAU;
    }

    public KhachHang(String ID_KhachHang, String tenKhachHang, String SDT, String diaChi, String matKhau) {
        this.ID_KH = ID_KhachHang;
        this.TENKH = tenKhachHang;
        this.SDT = SDT;
        this.DIACHI = diaChi;
        this.MATKHAU = matKhau;
    }

    public KhachHang() {
		// TODO Auto-generated constructor stub
	}

	public String getID_KhachHang() {
        return ID_KH;
    }

    public void setID_KhachHang(String ID_KhachHang) {
        this.ID_KH = ID_KhachHang;
    }

    public String getTenKhachHang() {
        return TENKH;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.TENKH = tenKhachHang;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getDiaChi() {
        return DIACHI;
    }

    public void setDiaChi(String diaChi) {
        this.DIACHI = diaChi;
    }

    @Override
    public String toString() {
    	return ID_KH +  " " + TENKH + " "+ SDT + " " + DIACHI +"\n";
    }
    
}
