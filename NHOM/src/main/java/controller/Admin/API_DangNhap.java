package controller.Admin;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.TaiKhoanDAO;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.TaiKhoan;
import service.TaiKhoanService;
import utils.JsonUtil;
import utils.TokenManager;

@WebServlet(urlPatterns = {"/api/admin/login"})
public class API_DangNhap extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	private final TaiKhoanService adminAccountService=new TaiKhoanService();
	private final ObjectMapper mapper=new ObjectMapper();
	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	response.setContentType("application/json");
    	response.setCharacterEncoding("UTF-8");

    	// Đọc dữ liệu gửi từ client
    	String jsonInput = request.getReader().lines().collect(Collectors.joining());
    	TaiKhoan taiKhoan = JsonUtil.fromJson(jsonInput, TaiKhoan.class);
    

    	if (taiKhoan != null) {
    	    TaiKhoan admin = adminAccountService.signIn(taiKhoan);
    	    if (admin != null) {
    	    	
    	        String token = TokenManager.generateToken(admin);
    	        Map<String, Object> responseData = new HashMap<>();
    	        Cookie adminCookie=new Cookie("token", token);
    	        adminCookie.setMaxAge(3600);
    	        adminCookie.setDomain("localhost");
    	        adminCookie.setSecure(false);
    	        adminCookie.setHttpOnly(false);
    	        response.addCookie(adminCookie);
    	        response.setStatus(HttpServletResponse.SC_OK);
    	        
    	        Cookie nameCookie = new Cookie("userName", admin.getUserName()); // Hoặc getUsername()
    	        nameCookie.setMaxAge(3600);
    	        nameCookie.setDomain("localhost");
    	        nameCookie.setSecure(false);
    	        nameCookie.setHttpOnly(false); // Để JS có thể đọc
    	        response.addCookie(nameCookie);
    	        
    	        responseData.put("status", 200);
    	        responseData.put("message","Đăng nhập thành công!");
    	        responseData.put("token", token);
    	        responseData.put("account", admin);
    	        
    	        

    	        mapper.writeValue(response.getWriter(), responseData); 
    	        
    	    } else {
    	        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    	        mapper.writeValue(response.getWriter(), Map.of(
    	        		"status", 401,
    			        "message","Tên tài khoản hoặc mật khẩu không chính xác!")
    	        		); 
    	    }
    	} else {
    	    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    	    mapper.writeValue(response.getWriter(), Map.of(
    	    		"status", 500,
			        "message","Lỗi server.Vui lòng thử lại sau!"
    	    		)); 
    	}


    }
}
