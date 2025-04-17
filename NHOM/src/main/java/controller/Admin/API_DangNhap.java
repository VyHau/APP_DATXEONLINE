package controller.Admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import dao.TaiKhoanDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.TaiKhoan;
import utils.JsonUtil;
import utils.TokenManager;

@WebServlet(urlPatterns = {"/api-admin-login"})
public class API_DangNhap extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	private TaiKhoanDAO adminDAO = new TaiKhoanDAO();
	private ObjectMapper mapper=new ObjectMapper();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	response.setContentType("application/json");
    	response.setCharacterEncoding("UTF-8");

    	// Đọc dữ liệu gửi từ client
    	String jsonInput = request.getReader().lines().collect(Collectors.joining());
    	TaiKhoan taiKhoan = JsonUtil.fromJson(jsonInput, TaiKhoan.class);

    	if (taiKhoan != null) {
    	    TaiKhoan admin = adminDAO.signIn(taiKhoan.getUserName(), taiKhoan.getPassWord());

    	    if (admin != null && admin.getID_VaiTro().trim().equalsIgnoreCase("ADMIN")) {
    	        String token = TokenManager.generateToken(admin);
    	        Map<String, Object> responseData = new HashMap<>();
    	        responseData.put("token", token);
    	        responseData.put("account", admin);
    	        
    	        mapper.writeValue(response.getWriter(), responseData); 
    	    } else {
    	        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    	        mapper.writeValue(response.getWriter(), Map.of("error", "Tên tài khoản hoặc mật khẩu không chính xác!")); 
    	    }
    	} else {
    	    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    	    mapper.writeValue(response.getWriter(), Map.of("error", "Lỗi định !")); 
    	}


    }
}
