package me.myproject.MODEL;

public class NguoiDung {
    private String SDT;
    private String passWord;
    private String vaiTro;

    public NguoiDung(String SDT, String passWord, String vaiTro) {
        this.SDT = SDT;
        this.passWord = passWord;
        this.vaiTro = vaiTro;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getVaiTro() {
        return vaiTro;
    }

    public void setVaiTro(String vaiTro) {
        this.vaiTro = vaiTro;
    }
    

    

}
