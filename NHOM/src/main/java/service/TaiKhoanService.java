package service;

import java.util.List;

import dao.TaiKhoanDAO;
import model.TaiKhoan;

public class TaiKhoanService {
	private TaiKhoanDAO taiKhoanDAO=new TaiKhoanDAO();
	
	public int signUp(TaiKhoan taiKhoan) {
		if(taiKhoanDAO.selectById(taiKhoan.getUserName())!=null) {
			return -1;
		}
		return taiKhoanDAO.insert(taiKhoan);
	}
	public int doiMatKhau(TaiKhoan taiKhoan) {
		return taiKhoanDAO.update(taiKhoan);
	}
	public int deleteAccount(String id) {
		return taiKhoanDAO.delete(id);
	}
	public List<TaiKhoan> selectAll(){
		return taiKhoanDAO.selectAll();
	}
	public TaiKhoan signIn(TaiKhoan taiKhoan) 
	{
		return taiKhoanDAO.signIn(taiKhoan.getUserName(), taiKhoan.getPassWord());
	}
}
