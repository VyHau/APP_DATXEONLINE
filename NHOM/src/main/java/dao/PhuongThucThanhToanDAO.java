package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import dao.AbstractDAO.RowMapper;
import model.PhuongThucThanhToan;
import model.PhuongThucThanhToan;

public class PhuongThucThanhToanDAO extends AbstractDAO<PhuongThucThanhToan> implements InterfaceDAO<PhuongThucThanhToan>{

	@Override
	public int insert(PhuongThucThanhToan obj) {
		String sql="INSERT INTO PHUONGTHUCTHANHTOAN (ID_THANHTOAN, LOAIHINHTHANHTOAN, TRANGTHAITT) VALUES\n"
				+ "(?,?,?)";
		Object [] params= {obj.getID_ThanhToan(),obj.getLoaiHinhThucThanhToan(),obj.isTrangThaiThanhToan()};
		return executeUpdate(sql, params);
	}

	@Override
	public int update(PhuongThucThanhToan obj) {
		String sql="UPDATE PHUONGTHUCTHANHTOAN SET LOAIHINHTHANHTOAN=?, TRANGTHAITT=? WHERE ID_THANHTOAN=?";
		Object [] params= {obj.getLoaiHinhThucThanhToan(),obj.isTrangThaiThanhToan(),obj.getID_ThanhToan()};
		return executeUpdate(sql, params);
	}

	@Override
	public int delete(String id) {
		String sql="DELETE PHUONGTHUCTHANHTOAN   WHERE ID_THANHTOAN=?";
		Object [] params= {id};
		return executeUpdate(sql, params);
	}

	@Override
	public PhuongThucThanhToan selectById(String id) {
		String sql="SELECT * FROM PHUONGTHUCTHANHTOAN WHERE ID_THANHTOAN=?";
		Object [] params= {id};
		return executeQuery(sql, params, ptttMapper);
	}

	@Override
	public List<PhuongThucThanhToan> selectAll() {
		String sql="SELECT * FROM PHUONGTHUCTHANHTOAN ";
		return executeQueryList(sql, null, ptttMapper);
	}

	@Override
	public List<PhuongThucThanhToan> selectByCondition() {
		// TODO Auto-generated method stub
		return null;
	}
	 private final RowMapper<PhuongThucThanhToan> ptttMapper = new RowMapper<PhuongThucThanhToan>() {
	        @Override
	        public PhuongThucThanhToan mapRow(ResultSet rs) throws SQLException {
	            PhuongThucThanhToan obj = new PhuongThucThanhToan();
	            obj.setID_ThanhToan(rs.getString("ID_THANHTOAN"));
	            obj.setLoaiHinhThucThanhToan(rs.getString("LOAIHINHTHANHTOAN"));
	            obj.setTrangThaiThanhToan(rs.getBoolean("TRANGTHAITT"));
	            return obj;
	        }
	    };
}
