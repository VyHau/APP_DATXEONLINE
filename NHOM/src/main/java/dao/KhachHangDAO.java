package dao;

import java.sql.*;
import java.util.List;
import model.KhachHang;
public class KhachHangDAO extends AbstractDAO<KhachHang> implements InterfaceDAO<KhachHang>{

  

    // Ví dụ phương thức chèn mới khách hàng
    @Override
    public int insert(KhachHang khachHang) {
        String sql = "INSERT INTO KHACHHANG (ID_KH, TENKH, SDT, DIACHI) VALUES (?, ?, ?, ?, ?)";
        Object[] params = {khachHang.getID_KhachHang(), khachHang.getTenKhachHang(), khachHang.getSDT(), khachHang.getDiaChi()};
        return executeUpdate(sql, params);
    }

    // Ví dụ phương thức cập nhật khách hàng
    @Override
    public int update(KhachHang khachHang) {
        String sql = "UPDATE KHACHHANG SET TENKH=?, SDT=?, DIACHI=? WHERE ID_KH=?";
        Object[] params = {khachHang.getTenKhachHang(), khachHang.getSDT(), khachHang.getDiaChi(), khachHang.getID_KhachHang()};
        return executeUpdate(sql, params);
    }
	@Override
	public int delete(String id) {
		  String sql = "DELETE FROM KHACHHANG WHERE ID_KH=?";
		    Object[] params = {id};
		    return executeUpdate(sql, params);
	}

    // Ví dụ phương thức chọn một khách hàng theo ID
    @Override
    public KhachHang selectById(String id) {
        String sql = "SELECT * FROM KHACHHANG WHERE ID_KH=?";
        Object[] params = {id};
        return executeQuery(sql, params, khachHangMapper);
    }


	@Override
	public List<KhachHang> selectAll() {
		String sql = "SELECT * FROM KHACHHANG";
	    return executeQueryList(sql, null,khachHangMapper);
	}

	@Override
	public List<KhachHang> selectByCondition() {
		// TODO Auto-generated method stub
		return null;
	}	
	private final RowMapper<KhachHang> khachHangMapper = new RowMapper<KhachHang>() {
	    @Override
	    public KhachHang mapRow(ResultSet rs) throws SQLException {
	    	KhachHang obj = new KhachHang();
            obj.setID_KhachHang(rs.getString("ID_KH"));
            obj.setTenKhachHang(rs.getString("TENKH"));
            obj.setDiaChi(rs.getString("DIACHI"));
            obj.setSDT(rs.getString("SDT"));
            return obj;
	       
	    }
	};


    // Các phương thức khác tiếp tục như vậy...
}
