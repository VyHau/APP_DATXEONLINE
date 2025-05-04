package dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import model.DatXe;
import dao.TaiXeDAO;
import dao.KhachHangDAO;
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
    public List<DatXe> selectByCondition(String sql,Object[] params) {
        return executeQueryList(sql, params, mapChuyenXe());
    }
    public int huyChuyenXe(String id) {
    	 String sql = "UPDATE DATXE SET TRANGTHAI='Đã huỷ' WHERE ID_DATXE=?";
         Object[] params = { id };
         return executeUpdate(sql, params);
    }
    public List<DatXe> soChuyenXeTrongNgay(LocalDate time) throws SQLException {
    	java.sql.Date sqlDate = java.sql.Date.valueOf(time);
    	Object[] params= {sqlDate};
    	
    	ResultSet rs= callProcedureResultSet("Pr_ThongKeChuyenNgay", params);
    	List<DatXe>list=new ArrayList<>();
    	 while (rs.next()) {
             list.add(mapChuyenXe().mapRow(rs));
         }
     	return list;
    }
    public List<DatXe> soChuyenXeTrongThang(LocalDate time) throws SQLException {
    	List<DatXe>list=new ArrayList<>();
    	java.sql.Date sqlDate = java.sql.Date.valueOf(time);
    	Object[] params= {sqlDate};
    	ResultSet rs = (ResultSet) callProcedureResultSet("Pr_ThongKeChuyenThang", params);
        while (rs.next()) {
            list.add(mapChuyenXe().mapRow(rs));
        }
    	return list;
    }
    public double soTienChuyenXe(String id) throws SQLException {
    	String sql="Fn_TinhTongTien";
    	Object[] params= {id};
    	
    	return (double) callFunction(sql,params,Types.DOUBLE);
    }
    public ResultSet doiTrangThaiChuyenXe(String id,String trangThai) throws SQLException {
    	String sql="Pr_CapNhatTrangThaiChuyenXe";
    	Object[] params= {id,trangThai};
    	return callProcedureResultSet(sql, params);
    }
    public List<Map<String,Object>> chuyenXeGanDay(int soDong) throws SQLException{
    	List<Map<String,Object>> list=new ArrayList<>();
    	String sql="Pr_ChuyenXeGanNhat";
    	Object[] params= {soDong};
    	ResultSet result=(ResultSet) callProcedureResultSet(sql, params);
    	while(result.next()) {
    		list.add(mapChuyenXeGanDay(result));
    	}
    	return list;
    	
    }
    public int soChuyenXeTheoTrangThai(String trangThai) {
    	Object[] params= {trangThai};
    	return (int) callFunction("Fn_SoChuyenXeTheoTrangThai",params , Types.INTEGER);
    }
    public ResultSet soChuyenXeTheoThang(String year) throws SQLException {
    	String sql="Pr_SoChuyenDiTheoThang";
    	Object[] params= {year};
    	
    	return callProcedureResultSet(sql, params);
    }
    public ResultSet ganTaiXeNhanDon(String idChuyenXe,String idTaiXe) throws SQLException {
    	String sql="Pr_GanChuyenXeChoTaiXePhuHop";
    	Object[] params= {idChuyenXe,idTaiXe};
    	return callProcedureResultSet(sql, params);
    }
    public ResultSet tongChuyenXeTheoKhuVuc() throws SQLException {
    	String sql="Pr_TongChuyenTheoKhuVuc";
    	return callProcedureResultSet(sql, null);
    }
    public ResultSet thongKeTyLeDungKM() throws SQLException{
    	String sql="Pr_ThongKeTyLeKhuyenMai";
    	return callProcedureResultSet(sql, null);
    }
    public ResultSet thongKeChuyenXeTheoTrangThai() throws SQLException {
    	String sql="Pr_ThongKeTrangThaiChuyen";
    	return callProcedureResultSet(sql, null);
    }
    public ResultSet thongKePTTT(int year) throws SQLException {
    	String sql="Pr_ThongKePhuongThucThanhToan";
    	Object[] params= {year};
    	return callProcedureResultSet(sql, params);
    }
    public Double tinhTongDoanhThuTheoNam(int nam) throws SQLException {
    	String sql="Fn_TinhTongDoanhThuTheoNam";
    	Object[] params= {nam};
    	return (Double) callFunction(sql, params, Types.DOUBLE);
    }
   
    private Map<String,Object> mapChuyenXeGanDay(ResultSet rs) throws SQLException{
        Map<String,Object> map=new HashMap<>();
        map.put("id_DatXe", rs.getString("ID_DATXE"));
        map.put("tenTX", rs.getString("TENTX"));
        map.put("tenKH", rs.getString("TENKH"));
        map.put("diemDon", rs.getString("DIEMDON"));
        map.put("diemTra", rs.getString("DIEMTRA"));
        map.put("thoiGianDat", rs.getString("THOIGIANDAT"));
        map.put("thoiGianDen", rs.getString("THOIGIANDEN"));
        map.put("thoiGianDon", rs.getString("THOIGIANDON"));
        map.put("trangThai", rs.getString("TRANGTHAI"));
        map.put("khoangCach", rs.getString("KHOANGCACH"));
        map.put("danhGia", rs.getString("DANHGIA"));
        map.put("giaTien", rs.getString("GIATIEN"));
        return map;
        
    }
//    private Map<String, Object> mapChuyenXeChiTiet(ResultSet rs) throws SQLException {
//        Map<String, Object> map = new HashMap<>();
//
//        // Thông tin cơ bản của chuyến xe
//        map.put("id_chuyenXe", rs.getString("ID_DATXE"));
//        map.put("id_thanhToan", rs.getString("ID_THANHTOAN"));
//        map.put("id_khuyenMai", rs.getString("ID_KHUYENMAI"));
//        map.put("diemDon", rs.getString("DIEMDON"));
//        map.put("diemDen", rs.getString("DIEMTRA")); // DIEMTRA đổi thành diemDen cho thống nhất
//        map.put("thoiGianDat", rs.getString("THOIGIANDAT"));
//        map.put("thoiGianDon", rs.getString("THOIGIANDON"));
//        map.put("thoiGianDen", rs.getString("THOIGIANDEN"));
//        map.put("trangThai", rs.getString("TRANGTHAI"));
//        map.put("khoangCach", rs.getDouble("KHOANGCACH")); // Sử dụng Double thay vì String
//        map.put("danhGia", rs.getObject("DANHGIA", Integer.class)); // Integer, có thể null
//        map.put("nhanXet", rs.getString("NHANXET"));
//
//       
//        Map<String, Object> khachHang = new HashMap<>();
//        khachHang.put("id_khachHang", rs.getString("ID_KHACHHANG"));
//        khachHang.put("hoTen", rs.getString("TENKH"));
//        khachHang.put("soDienThoai", rs.getString("SODIENTHOAI_KH"));
//        khachHang.put("email", rs.getString("EMAIL"));
//        map.put("khachHang", KhachHangDAO.);
//
//
//        if (rs.getString("ID_TAIXE") != null) {
//            Map<String, Object> taiXe = new HashMap<>();
//            taiXe.put("id_TaiXe", rs.getString("ID_TAIXE"));
//            taiXe.put("tenTaiXe", rs.getString("TENTX"));
//            taiXe.put("sdt", rs.getString("SODIENTHOAI_TX"));
//            taiXe.put("bienSoXe", rs.getString("BIENSOXE"));
//            map.put("taiXe", taiXe);
//        } else {
//            map.put("taiXe", null);
//        }
//
//        return map;
//    }

    private RowMapper<DatXe> mapChuyenXe() {
        return new RowMapper<DatXe>() {
            @Override
            public DatXe mapRow(ResultSet rs) throws SQLException {
                DatXe datXe = new DatXe();
                datXe.setID_DatXe(rs.getString("ID_DATXE"));
                datXe.setID_KhachHang(rs.getString("ID_KHNO"));
                datXe.setID_TaiXe(rs.getString("ID_TXNO"));
                datXe.setID_ThanhToan(rs.getString("ID_THANHTOANNO"));
                datXe.setID_KhuyenMai(rs.getString("ID_KHUYENMAINO"));
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
