package service;

import java.sql.SQLException;
import java.util.List;
import dao.TaiXeDAO;
import dao.Exception.TaiXeException;
import model.TaiXe;

public class TaiXeService {
    private TaiXeDAO taiXeDAO = new TaiXeDAO();

    public boolean themTaiXe(TaiXe taiXe) throws SQLException, TaiXeException {
    	
    	// Kiểm tra email đã tồn tại
        if (taiXeDAO.existsByEmail(taiXe.getEmail())) {
            throw new TaiXeException("Email đã được sử dụng.");
        }

        // Kiểm tra SDT đã tồn tại
        if (taiXeDAO.existsBySDT(taiXe.getSDT())) {
            throw new TaiXeException("Số điện thoại đã được sử dụng.");
        }

        // Kiểm tra CCCD đã tồn tại
        if (taiXeDAO.existsByCCCD(taiXe.getCCCD())) {
            throw new TaiXeException("CCCD đã được sử dụng.");
        }
        if (taiXeDAO.existsByBSX(taiXe.getBienSoXe())) {
            throw new TaiXeException("Biển số xe đã được sử dụng.");
        }

        return taiXeDAO.insert(taiXe) > 0;
    }
    public void updateTaiXe(TaiXe taiXeMoi) {
    	TaiXe taiXeCu=taiXeDAO.selectById(taiXeMoi.getID_TaiXe());
    	if(taiXeCu.getTenTaiXe()!=null)
    		taiXeCu.setTenTaiXe(taiXeCu.getTenTaiXe());
    	if(taiXeCu.getID_LoaiXe()!=null)
    		taiXeCu.setID_LoaiXe(taiXeCu.getID_LoaiXe());
    	if(taiXeCu.getNgaySinh()!=null)
    		taiXeCu.setNgaySinh(taiXeCu.getNgaySinh());
    	if(taiXeCu.getCCCD()!=null)
    		taiXeCu.setCCCD(taiXeCu.getCCCD());
    	if(taiXeCu.getGPLX()!=null)
    		taiXeCu.setGPLX(taiXeCu.getGPLX());
    	if(taiXeCu.getEmail()!=null)
    		taiXeCu.setEmail(taiXeCu.getEmail());
    	if(taiXeCu.getBienSoXe()!=null)
    		taiXeCu.setBienSoXe(taiXeCu.getBienSoXe());
    	if(taiXeCu.getTenXe()!=null)
    		taiXeCu.setTenXe(taiXeCu.getTenXe());
    	if(taiXeCu.getSDT()!=null)
    		taiXeCu.setSDT(taiXeCu.getSDT());
    	
    	
    	taiXeDAO.update(taiXeCu);
    	
    }
    public List<TaiXe> selectAllTaiXe() {
    	return taiXeDAO.selectAll();
    }
    public void deleteTaiXe(String id) {
    	taiXeDAO.delete(id);
    }

}
