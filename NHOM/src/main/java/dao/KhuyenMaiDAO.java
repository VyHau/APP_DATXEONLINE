package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import dao.AbstractDAO.RowMapper;
import model.KhuyenMai;
import model.KhuyenMai;

public class KhuyenMaiDAO extends AbstractDAO<KhuyenMai> implements InterfaceDAO<KhuyenMai> {

	@Override
	public int insert(KhuyenMai obj) {
		String sql="INSERT INTO KHUYENMAI (ID_KHUYENMAI, TENKM, HANMUC, TGBATDAU, TGKETTHUC, DIEUKIENAPDUNG, SOLUONG) VALUES\n"
				+ "(?,?,?,?,?,?,?)";
		Object[] params = {obj.getID_KhuyenMai(),obj.getTenKhuyenMai(),obj.getHanMuc(),obj.getThoiGianBatDau(),obj.getThoiGianKetThuc(),obj.getDieuKienApDung(),obj.getSoLuong()};
		return executeUpdate(sql, params);
	}

	@Override
	public int update(KhuyenMai obj) {
		String sql="UPDATE KHUYENMAI SET TENKM=?, HANMUC=?, TGBATDAU=?, TGKETTHUC=?, DIEUKIENAPDUNG=?, SOLUONG=? WHERE ID_KHUYENMAI=?"
				+ "(?,?,?,?,?,?,?)";
		Object[] params = {obj.getTenKhuyenMai(),obj.getHanMuc(),obj.getThoiGianBatDau(),obj.getThoiGianKetThuc(),obj.getDieuKienApDung(),obj.getSoLuong(),obj.getID_KhuyenMai()};
		return executeUpdate(sql, params);
	}

	@Override
	public int delete(String id) {
		String sql="DELETE KHUYENMAI WHERE ID_KHUYENMAI=?"
				+ "(?,?,?,?,?,?,?)";
		Object[] params = {id};
		return executeUpdate(sql, params);
	}

	@Override
	public KhuyenMai selectById(String id) {
		String sql="SELECT * FROM KHUYENMAI WHERE ID_KHUYEMAI=?";
		Object[] params= {id};
		return executeQuery(sql, params, khuyenMaiMapper);
	}

	@Override
	public List<KhuyenMai> selectAll() {
		String sql="SELECT * FROM KHUYENMAI ";
		return executeQueryList(sql, null, khuyenMaiMapper);
	}

	@Override
	public List<KhuyenMai> selectByCondition() {
		// TODO Auto-generated method stub
		return null;
	}
	 private final RowMapper<KhuyenMai> khuyenMaiMapper = new RowMapper<KhuyenMai>() {
	        @Override
	        public KhuyenMai mapRow(ResultSet rs) throws SQLException {
	            KhuyenMai obj = new KhuyenMai();
	            obj.setID_KhuyenMai(rs.getString("ID_KHUYENMAI"));
	            obj.setTenKhuyenMai(rs.getString("TENKM"));
	            obj.setHanMuc(rs.getDouble("HANMUC"));
	            obj.setThoiGianBatDau(rs.getDate("TGBATDAU"));
	            obj.setThoiGianKetThuc(rs.getDate("TGKETTHUC"));
	            obj.setDieuKienApDung(rs.getString("DIEUKIENAPDUNG"));
	            obj.setSoLuong(rs.getInt("SOLUONG"));
	            return obj;
	        }
	    };

}
