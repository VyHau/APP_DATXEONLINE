package service;

import java.util.List;

import dao.DatXeDAO;
import dao.KhachHangDAO;
import dao.TaiKhoanDAO;
import dao.TaiXeDAO;
import model.DatXe;
import model.KhachHang;
import model.TaiXe;

public class ChuyenXeService {
    private DatXeDAO datXeDAO = new DatXeDAO();
    private KhachHangDAO khachHangDAO = new KhachHangDAO();
    private TaiXeDAO taiXeDAO=new TaiXeDAO();

    public void bookRide(DatXe datXe) {
        List<TaiXe> drivers = taiXeDAO.findAllDriverActive();
        if (!drivers.isEmpty()) {
//            ride.setDriverId(drivers.get(0).getId());
//            chuyenXe.setStatus("PENDING");
            datXeDAO.insert(datXe);
        } else {
            throw new RuntimeException("No available drivers");
        }
    }
    public int huyChuyenXe(String id) {
    	return datXeDAO.huyChuyenXe(id);
    }

    public List<DatXe> getAllRides() {
        return datXeDAO.selectAll();
    }
    public int tongChuyenXeTrongNgay() {
    	return 0;
    }
}
