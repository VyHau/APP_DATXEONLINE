package me.myproject.BUSINESSLOGIC;

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
    private List<DatXe> chuyenDiList;

    public List<DatXe> getChuyenDi(String maKh) throws Exception {
        Map<String, String> data = new HashMap<>();
        Map<String, Object> response = APIHelper.postForMap("http://localhost:8080/ProjectNHOM/api-account", data);
        String status = (String) response.get("status");
        if (!"success".equals(status))
            throw new Exception((String) response.getOrDefault("message", "Lỗi khi lấy lịch sử chuyến đi!"));

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> chuyenDiData = (List<Map<String, Object>>) response.get("data");

        chuyenDiList = new ArrayList<>();
        for (Map<String, Object> item : chuyenDiData) {
            DatXe chuyenDi = new DatXe();
            chuyenDi.setID_DatXe((String) item.get("maChuyen"));
            chuyenDi.setID_TX((String) item.get("taiXe"));
            chuyenDi.setThoiGianDat(mapper.convertValue(item.get("thoiGianDat"), Date.class));
            chuyenDi.setThoiGianDen(mapper.convertValue(item.get("thoiGianDen"), Date.class));
            chuyenDi.setTrangThai((String) item.get("trangThai"));
            chuyenDi.setDiemDon((String) item.get("diemDon"));
            chuyenDi.setDiemTra((String) item.get("diemDen"));
            chuyenDi.setKhoangCach(((Number) item.get("soKm")).doubleValue());
            chuyenDi.setID_KhuyenMai((String) item.get("khuyenMai"));
            chuyenDi.setDiemSo(((Number) item.get("diemDanhGia")).intValue());
            chuyenDi.setDanhGia((String) item.get("danhGia"));
            chuyenDi.setID_KH((String) item.get("maKh"));
            if (chuyenDi.getID_KH().equals(maKh))
                chuyenDiList.add(chuyenDi);
        }
        return chuyenDiList;
    }

    public List<DatXe> LocChuyenDi(String loaiXe, Date tuNgay, Date denNgay, String maKH) throws Exception {
        if (chuyenDiList == null || chuyenDiList.isEmpty())
            chuyenDiList = getChuyenDi(maKH);
        if (tuNgay != null && denNgay != null && tuNgay.after(denNgay))
            throw new Exception("Ngày bắt đầu phải trước ngày kết thúc!");
        List<DatXe> filteredList = new ArrayList<>();
        for (DatXe chuyenDi : chuyenDiList) {
            boolean matches = true;
            // if (loaiXe != null && !loaiXe.isEmpty() &&
            // !loaiXe.equals(chuyenDi.getLoaiXe())) {
            // matches = false;
            // }
            if (tuNgay != null && chuyenDi.getThoiGianDat().before(tuNgay))
                matches = false;
            if (denNgay != null && chuyenDi.getThoiGianDat().after(denNgay))
                matches = false;
            if (matches)
                filteredList.add(chuyenDi);
        }
        return filteredList;
    }

    public void HuyChuyenDi(String maDX) throws Exception {
        Map<String, String> data = new HashMap<>();
        data.put("maDX", maDX); // Chuyển mã chuyến đi cần hủy
        Map<String, Object> response = APIHelper.postForMap("http://localhost:8080/ProjectNHOM/api-account/huyChuyenDi",
                data);
        String status = (String) response.get("status");
        if (!"success".equals(status))
            throw new Exception((String) response.getOrDefault("message", "Lỗi khi hủy chuyến đi!"));
    }

}