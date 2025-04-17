package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.AbstractDAO.RowMapper;
import model.KhachHang;
import model.TaiXe;

public class TaiXeDAO extends AbstractDAO<TaiXe> implements InterfaceDAO<TaiXe> {
	
	@Override
	public int insert(TaiXe taiXe) {
		String newID = callFunctionString("Fn_TangTuDongID_TaiXe()");
	        if (newID == null) return 0;

	    taiXe.setID_TaiXe(newID);
		String sql = "INSERT INTO TAIXE (ID_TX, ID_LOAIXE, TENTX, NGSINH, CCCD, GPLX, SDT, EMAIL, BIENSOXE, TENXE) VALUES (?, ?, ?, ?, ?,?,?,?,?,?)";
        Object[] params = {taiXe.getID_TaiXe(),taiXe.getID_LoaiXe(),taiXe.getTenTaiXe(),taiXe.getNgaySinh(),taiXe.getCCCD(),taiXe.getGPLX(),taiXe.getSDT(),taiXe.getEmail(),taiXe.getBienSoXe(),taiXe.getTenXe()};
        return executeUpdate(sql, params);
	}

	@Override
	public int update(TaiXe taiXe) {
		String sql = "UPDATE TAIXE SET ID_LOAIXE=?, TENTX=?, NGSINH=?, CCCD=?, GPLX=?, SDT=?, EMAIL=?, BIENSOXE=?, TENXE=? WHERE ID_TX=?";
        Object[] params = {taiXe.getID_LoaiXe(),taiXe.getTenTaiXe(),taiXe.getNgaySinh(),taiXe.getCCCD(),taiXe.getGPLX(),taiXe.getSDT(),taiXe.getEmail(),taiXe.getBienSoXe(),taiXe.getTenXe(),taiXe.getID_TaiXe()};
        return executeUpdate(sql, params);
	}

	@Override
	public int delete(String id) {
		 String sql = "DELETE FROM TAIXE WHERE ID_TX=?";
		    Object[] params = {id};
		    return executeUpdate(sql, params);
	}

	@Override
	public TaiXe selectById(String id) {
		 String sql = "SELECT * FROM TAIXE WHERE ID_TX=?";
	        Object[] params = {id};
	        return executeQuery(sql, params,taiXeMapper);
	}

	@Override
	public List<TaiXe> selectAll() {
		String sql = "SELECT * FROM TAIXE";
	    return executeQueryList(sql, null, taiXeMapper);
	}
	
	@Override
	public List<TaiXe> selectByCondition() {
		// TODO Auto-generated method stub
		return null;
	}
    public List<TaiXe> findAllDriverActive() {
        String sql = "SELECT * FROM TAIXE WHERE TRANGTHAI = true";
        return executeQueryList(sql, null, taiXeMapper);
    }
    public int huyChuyenXe(String id) {
   	 String sql = "UPDATE DATXE SET TRANGTHAI='Đã huỷ' WHERE ID_DATXE=?";
        Object[] params = { id };
        return executeUpdate(sql, params);
    }
//    public int nhanChuyenXe(String id) {
//   	 String sql = "INSERT INTO DATXE (ID_DATXE, ID_KH, ID_TX, ID_THANHTOAN, ID_KHUYENMAI, DIEMDON, DIEMTRA, THOIGIANDAT, THOIGIANDON, THOIGIANDEN, TRANGTHAI, KHOANGCACH, DIEMSO, DANHGIA) VALUES\n"
//   	 		+ "(?,?, ?, ?, ?,?, ?, ?, ?, ?, ?, ?, ?,?'),";
//        Object[] params = { };
//        return executeUpdate(sql, params);
//   }
    private final RowMapper<TaiXe> taiXeMapper = new RowMapper<TaiXe>() {
        @Override
        public TaiXe mapRow(ResultSet rs) throws SQLException {
            TaiXe obj = new TaiXe();
            obj.setID_TaiXe(rs.getString("ID_TX"));         // sửa lại tên cho rõ nghĩa
            obj.setID_LoaiXe(rs.getString("ID_LOAIXE"));
            obj.setTenTaiXe(rs.getString("TENTX"));
            obj.setNgaySinh(rs.getDate("NGSINH"));
            obj.setCCCD(rs.getString("CCCD"));
            obj.setGPLX(rs.getString("GPLX"));
            obj.setSDT(rs.getString("SDT"));
            obj.setEmail(rs.getString("EMAIL"));
            obj.setBienSoXe(rs.getString("BIENSOXE"));
            obj.setTenXe(rs.getString("TENXE"));
            return obj;
        }
    };
    public boolean existsByEmail(String email) throws SQLException {
        String sql = "SELECT * FROM TAIXE WHERE EMAIL=?";
        Object[] params = {email};
        return executeQuery(sql, params, taiXeMapper) != null;
    }

    public boolean existsBySDT(String sdt) throws SQLException {
        String sql = "SELECT * FROM TAIXE WHERE SDT=?";
        Object[] params = {sdt};
        return executeQuery(sql, params, taiXeMapper) != null;
    }

    public boolean existsByCCCD(String cccd) throws SQLException {
        String sql = "SELECT * FROM TAIXE WHERE CCCD = ?";
        Object[] params = {cccd};
        return executeQuery(sql, params, taiXeMapper) != null;
    }
    public boolean existsByBSX(String bsx) throws SQLException {
        String sql = "SELECT * FROM TAIXE WHERE BIENSOXE = ?";
        Object[] params = {bsx};
        return executeQuery(sql, params, taiXeMapper) != null;
    }
    



}
