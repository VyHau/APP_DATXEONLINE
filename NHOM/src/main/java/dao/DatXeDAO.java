package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import model.DatXe;

public class DatXeDAO extends AbstractDAO<DatXe> implements InterfaceDAO<DatXe> {

    @Override
    public int insert(DatXe datXe) {
        String sql = "INSERT INTO DATXE (ID_DATXE, ID_KH, ID_TX, ID_THANHTOAN, ID_KHUYENMAI, DIEMTRA, DIEMDON, THOIGIANDAT, THOIGIANDON, THOIGIANDEN, TRANGTHAI, KHOANGCACH, DIEMSO, DANHGIA)"
        		+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Object[] params = {
            datXe.getID_DatXe(),
            datXe.getID_KhachHang(),
            datXe.getID_TaiXe(),
            datXe.getID_ThanhToan(),
            datXe.getID_KhuyenMai(),
            datXe.getDiemTra(),
            datXe.getDiemDon(),
            datXe.getThoiGianDat(),
            datXe.getThoiGianDon(),
            datXe.getThoiGianDen(),
            datXe.getTrangThai(),
            datXe.getKhoangCach(),
            datXe.getDiemSo(),
            datXe.getDanhGia()
        };
        return executeUpdate(sql, params);
    }

    @Override
    public int update(DatXe datXe) {
        String sql = "UPDATE DATXE SET ID_KH=?, ID_TX=?, ID_THANHTOAN=?, ID_KHUYENMAI=?, DIEMTRA=?, DIEMDON=?, THOIGIANDAT=?, THOIGIANDON=?, THOIGIANDEN=?, TRANGTHAI=?, KHOANGCACH=?, DIEMSO=?, DANHGIA=? WHERE ID_DATXE=?";
        Object[] params = {
            datXe.getID_KhachHang(),
            datXe.getID_TaiXe(),
            datXe.getID_ThanhToan(),
            datXe.getID_KhuyenMai(),
            datXe.getDiemTra(),
            datXe.getDiemDon(),
            datXe.getThoiGianDat(),
            datXe.getThoiGianDon(),
            datXe.getThoiGianDen(),
            datXe.getTrangThai(),
            datXe.getKhoangCach(),
            datXe.getDiemSo(),
            datXe.getDanhGia(),
            datXe.getID_DatXe()
        };
        return executeUpdate(sql, params);
    }

	@Override
	public int delete(String id) {
	    	 String sql = "DELETE FROM DATXE WHERE ID_DATXE=?";
	         Object[] params = { id };
	         return executeUpdate(sql, params);
	}

    @Override
    public DatXe selectById(String id) {
        String sql = "SELECT * FROM DATXE WHERE ID_DATXE=?";
        Object[] params = { id };
        return executeQuery(sql, params, mapChuyenXe());
    }

    @Override
    public List<DatXe> selectAll() {
        String sql = "SELECT * FROM DATXE";
        return executeQueryList(sql, null, mapChuyenXe());
    }

    @Override
    public List<DatXe> selectByCondition() {
       
        return null;
    }
    public int huyChuyenXe(String id) {
    	 String sql = "UPDATE DATXE SET TRANGTHAI='Đã huỷ' WHERE ID_DATXE=?";
         Object[] params = { id };
         return executeUpdate(sql, params);
    }


    private RowMapper<DatXe> mapChuyenXe() {
        return new RowMapper<DatXe>() {
            @Override
            public DatXe mapRow(ResultSet rs) throws SQLException {
                DatXe datXe = new DatXe();
                datXe.setID_DatXe(rs.getString("ID_DATXE"));
                datXe.setID_KhachHang(rs.getString("ID_KH"));
                datXe.setID_TaiXe(rs.getString("ID_TX"));
                datXe.setID_ThanhToan(rs.getString("ID_THANHTOAN"));
                datXe.setID_KhuyenMai(rs.getString("ID_KHUYENMAI"));
                datXe.setDiemTra(rs.getString("DIEMTRA"));
                datXe.setDiemDon(rs.getString("DIEMDON"));
                datXe.setThoiGianDat(rs.getTimestamp("THOIGIANDAT"));
                datXe.setThoiGianDon(rs.getTimestamp("THOIGIANDON"));
                datXe.setThoiGianDen(rs.getTimestamp("THOIGIANDEN"));
                datXe.setTrangThai(rs.getString("TRANGTHAI"));
                datXe.setKhoangCach(rs.getDouble("KHOANGCACH"));
                datXe.setDiemSo(rs.getInt("DIEMSO"));
                datXe.setDanhGia(rs.getString("DANHGIA"));
                return datXe;
            }
        };
    }


}
