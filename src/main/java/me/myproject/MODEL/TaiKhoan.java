package me.myproject.MODEL;
public class TaiKhoan {
    private String userName;
    private String passWord;
    private String ID_VaiTro;
    private String ID_Ref;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getID_VaiTro() {
        return ID_VaiTro;
    }

    public void setID_VaiTro(String iD_VaiTro) {
        ID_VaiTro = iD_VaiTro;
    }

    public String getID_Ref() {
        return ID_Ref;
    }

    public void setID_Ref(String iD_Ref) {
        ID_Ref = iD_Ref;
    }

    public TaiKhoan(String userName, String passWord, String ID_VaiTro, String ID_Ref) {
        this.userName = userName;
        this.passWord = passWord;
        this.ID_VaiTro = ID_VaiTro;
        this.ID_Ref = ID_Ref;
    }
}
