package service;

import java.util.List;

import dao.KhachHangDAO;
import model.KhachHang;

public class KhachHangService {
    private KhachHangDAO khachHangDAO = new KhachHangDAO();

    public void registerUser(KhachHang khachHang) {
        khachHangDAO.insert(khachHang);
    }
    public void updateUser(KhachHang khachHangMoi) {
    	KhachHang khachHangCu=khachHangDAO.selectById(khachHangMoi.getID_KhachHang());
    	if(khachHangMoi.getTenKhachHang()!=null)
    		khachHangCu.setTenKhachHang(khachHangMoi.getTenKhachHang());
    	if(khachHangMoi.getDiaChi()!=null)
    		khachHangCu.setDiaChi(khachHangMoi.getDiaChi());
    	if(khachHangMoi.getSDT()!=null)
    		khachHangCu.setSDT(khachHangMoi.getSDT());
    	
    	khachHangDAO.update(khachHangCu);
    	
    }
    public List<KhachHang> selectAllUser() {
    	return khachHangDAO.selectAll();
    }
    public void deleteUser(String id) {
    	khachHangDAO.delete(id);
    }


}
