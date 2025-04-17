package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import dao.AbstractDAO.RowMapper;
import model.TaiKhoan;
import model.TaiKhoan;

public class TaiKhoanDAO extends AbstractDAO<TaiKhoan> implements InterfaceDAO<TaiKhoan>{

	@Override
	public int insert(TaiKhoan obj) {
		String sql = "INSERT INTO TAIKHOAN (USERNAME, PASS_WORD,ID_VAITRO,REF_ID) VALUES\n"
				+ "(?,?,?,?)";
		Object[] params= {obj.getUserName(),obj.getPassWord(),obj.getID_VaiTro(),obj.getID_Ref()};
		return executeUpdate(sql, params);
	}

	@Override
	public int update(TaiKhoan obj) {
		String sql = "UPDATE TAIKHOAN SET  PASS_WORD=?,REF_ID=? WHERE USERNAME=? AND ID_VAITRO=?"
				+ "(?,?,?,?)";
		Object[] params= {obj.getPassWord(),obj.getID_Ref(),obj.getUserName(),obj.getID_VaiTro()};
		return executeUpdate(sql, params);
	}

	@Override
	public int delete(String id) {
		String sql = "DELETE TAIKHOAN WHERE USERNAME=?";
		Object[] params= {id};
		return executeUpdate(sql, params);
	}

	@Override
	public TaiKhoan selectById(String id) {
		String sql = "SELECT * FROM TAIKHOAN WHERE USERNAME=?";
		Object[] params= {id};
		return executeQuery(sql, params, taiKhoanMapper);
	}

	@Override
	public List<TaiKhoan> selectAll() {
		String sql = "SELECT * FROM TAIKHOAN ";
		return executeQueryList(sql, null, taiKhoanMapper);
	}

	@Override
	public List<TaiKhoan> selectByCondition() {
		// TODO Auto-generated method stub
		return null;
	}
	public TaiKhoan signIn(String id,String pass) {
		String sql = "SELECT * FROM TAIKHOAN WHERE USERNAME=? AND PASS_WORD=?";
		Object[] params= {id,pass};
		return executeQuery(sql, params, taiKhoanMapper);
	}
	 private final RowMapper<TaiKhoan> taiKhoanMapper = new RowMapper<TaiKhoan>() {
	        @Override
	        public TaiKhoan mapRow(ResultSet rs) throws SQLException {
	            TaiKhoan obj = new TaiKhoan();
	            obj.setUserName(rs.getString("USERNAME"));
	            obj.setPassWord(rs.getString("PASS_WORD"));
	            obj.setID_VaiTro(rs.getString("ID_VAITRO"));
	            obj.setID_Ref(rs.getString("REF_ID"));
	            return obj;
	        }
	    };

}
