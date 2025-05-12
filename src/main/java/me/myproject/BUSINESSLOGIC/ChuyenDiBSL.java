package me.myproject.BUSINESSLOGIC;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import me.myproject.MODEL.DatXe;
import me.myproject.Utilities.APIHelper;

public class ChuyenDiBSL {
    private static final ObjectMapper mapper = new ObjectMapper();
    private static final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private static List<DatXe> chuyenDiList = new ArrayList<>(); 

    public ChuyenDiBSL() {
    }

    public static String formatDate(Timestamp timestamp) {
        if (timestamp == null) 
            return "";
        return dateFormatter.format(new Date(timestamp.getTime()));
    }

    public List<DatXe> getChuyenDi() throws Exception {
        Map<String, Object> response;
        try {
            response = APIHelper.getForMap("http://localhost:8080/ProjectNHOM/api/user/dsChuyenXe");
        } catch (Exception e) {
            throw new Exception("Lỗi khi gọi API: " + e.getMessage());
        }
        System.out.println("Phản hồi API: " + response);
        String status = response.get("status") != null ? response.get("status").toString() : null;
        if (!"success".equals(status)) {
            throw new Exception((String) response.getOrDefault("message", "Lỗi khi lấy lịch sử chuyến đi!"));
        }
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> chuyenDiData = (List<Map<String, Object>>) response.get("data");
        List<DatXe> allChuyenDiList  = new ArrayList<>();
        for (Map<String, Object> item : chuyenDiData) {
            DatXe chuyenDi = new DatXe();
            chuyenDi.setID_DatXe((String) item.get("id_DatXe"));
            chuyenDi.setID_TX((String) item.get("id_TaiXe"));
            chuyenDi.setID_KH((String) item.get("id_KhachHang"));
            Object thoiGianDat = item.get("thoiGianDat");
            if (thoiGianDat instanceof Number) 
                chuyenDi.setThoiGianDat(new Timestamp(((Number) thoiGianDat).longValue()));
            Object thoiGianDon = item.get("thoiGianDon");
            if (thoiGianDon instanceof Number) 
                chuyenDi.setThoiGianDon(new Timestamp(((Number) thoiGianDon).longValue()));
            Object thoiGianDen = item.get("thoiGianDen");
            if (thoiGianDen instanceof Number) 
                chuyenDi.setThoiGianDen(new Timestamp(((Number) thoiGianDen).longValue()));
            chuyenDi.setTrangThai((String) item.get("trangThai"));
            chuyenDi.setGiaTien((Double) item.get("giaTien"));
            chuyenDi.setDiemDon((String) item.get("diemDon"));
            chuyenDi.setDiemTra((String) item.get("diemTra"));
            chuyenDi.setKhoangCach(((Number) item.get("khoangCach")).doubleValue());
            chuyenDi.setID_KhuyenMai((String) item.get("id_KhuyenMai"));
            chuyenDi.setID_ThanhToan((String) item.get("id_ThanhToan"));
            chuyenDi.setDiemSo(item.get("diemSo") != null ? ((Number) item.get("diemSo")).intValue() : 0);
            chuyenDi.setDanhGia((String) item.get("danhGia"));
            // Gán giá trị mặc định cho GiaTien nếu API không cung cấp
            chuyenDi.setGiaTien(item.get("giaTien") != null ? ((Number) item.get("giaTien")).doubleValue() : 0.0);
            allChuyenDiList.add(chuyenDi); 
        }
        return allChuyenDiList;
    }
    
    public List<DatXe> getChuyenDiTheoKH(String maKh) throws Exception{
        List<DatXe> allChuyenDiList = getChuyenDi();
        chuyenDiList.clear();
        for (DatXe chuyenDi : allChuyenDiList) 
            if (chuyenDi.getID_KH().equals(maKh)) 
                chuyenDiList.add(chuyenDi);
    	return chuyenDiList;
    }
    
    public void HuyChuyenDi(String maDX, String lyDoHuy) throws Exception {
        Map<String, String> data = new HashMap<>();
        data.put("id_chuyenXe", maDX);
        data.put("ghiChu", lyDoHuy ); // Có thể tùy chỉnh ghiChu
        Map<String, Object> response;
        try {
            response = APIHelper.putForMap("http://localhost:8080/ProjectNHOM/api/user/huyChuyenXe", data);
            if (response == null || response.isEmpty()) {
                throw new Exception("Phản hồi API rỗng hoặc không hợp lệ");
            }
        } catch (Exception e) {
            throw new Exception("Lỗi khi gọi API hủy chuyến: " + e.getMessage());
        }
        System.out.println("Phản hồi API hủy chuyến: " + response);
        String status = response.get("status") != null ? response.get("status").toString() : null;
        if (!"success".equals(status)) 
            throw new Exception((String) response.getOrDefault("message", "Lỗi khi hủy chuyến đi!"));
    }
    
    public List<DatXe> locChuyenDi(String trangThai, Timestamp tuNgay, Timestamp denNgay) throws Exception{
    	List<DatXe> chuyenXeLoc = new ArrayList<>();
        for (DatXe chuyenDi : chuyenDiList) {
            boolean matches = true;
           if (trangThai != null && !trangThai.isEmpty() && !chuyenDi.getTrangThai().equals(trangThai)) 
               matches = false;
            if (matches && tuNgay != null && denNgay != null) {
                Timestamp thoiGianDat = chuyenDi.getThoiGianDat();
                if (thoiGianDat.before(tuNgay) || thoiGianDat.after(denNgay)) 
                    matches = false;
            }
            if (matches) 
                chuyenXeLoc.add(chuyenDi);
        }
    	return chuyenXeLoc;
    }

    
}
