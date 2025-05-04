package controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.KhachHang;
import service.KhachHangService;
import utils.JsonUtil;

@WebServlet("/api-users")
public class KhachHangController extends HttpServlet {
    private static final long serialVersionUID = 1L;
	private final KhachHangService khachHangService = new KhachHangService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	    
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		ObjectMapper mapper=new ObjectMapper();
		List<KhachHang> list=khachHangService.selectAllUser();

		mapper.writeValue(resp.getOutputStream(), list);
	}
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
	    
    	resp.setContentType("application/json");
        String jsonInput = req.getReader().lines().collect(Collectors.joining());
        
        KhachHang khachHang = JsonUtil.fromJson(jsonInput, KhachHang.class);
        khachHangService.registerUser(khachHang);
        resp.setStatus(HttpServletResponse.SC_CREATED);
        resp.getWriter().write("{\"message\": \"Ride booked successfully\", \"ride\": " + JsonUtil.toJson(khachHang) + "}");
    }
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	resp.setContentType("application/json");
    	String jsonInput = req.getReader().lines().collect(Collectors.joining());

        KhachHang khachHang = JsonUtil.fromJson(jsonInput, KhachHang.class);
        System.out.println(khachHang.toString());
        khachHangService.updateUser(khachHang);
        resp.setStatus(HttpServletResponse.SC_CREATED);
//        resp.getWriter().write("{\"message\": \"Ride booked successfully\", \"ride\": " + JsonUtil.toJson(khachHang) + "}");
        ObjectMapper mapper=new ObjectMapper();
        mapper.writeValue(resp.getOutputStream(), "{Đã cập nhật thành công}");
    }
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	resp.setContentType("application/json");
    	ObjectMapper mapper=new ObjectMapper();
    	String jsonInput = req.getReader().lines().collect(Collectors.joining());
    	Map<String, List<String>> data = mapper.readValue(jsonInput, new TypeReference<Map<String, List<String>>>() {});

    	List<String> ids = data.get("ID_KH");
    	for (String key : ids) {
    	    khachHangService.deleteUser(key);
    	}

    	mapper.writeValue(resp.getOutputStream(), "{Đã xoá thành công}");
    	
    }
}