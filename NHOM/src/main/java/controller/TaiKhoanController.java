package controller;

import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.KhachHang;
import model.TaiKhoan;
import service.TaiKhoanService;
import utils.JsonUtil;

@WebServlet(urlPatterns = {"/api-account"})
public class TaiKhoanController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	private TaiKhoanService taiKhoanService=new TaiKhoanService();
	private ObjectMapper mapper=new ObjectMapper();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		
		resp.setCharacterEncoding("UTF-8");
		String jsonInput = req.getReader().lines().collect(Collectors.joining());
		System.out.println(jsonInput);
		
		TaiKhoan taiKhoan = JsonUtil.fromJson(jsonInput, TaiKhoan.class);
		System.out.println(taiKhoan);
		
		TaiKhoan taiKhoanNew=taiKhoanService.signIn(taiKhoan);
		if (taiKhoanNew != null) {
		    resp.setStatus(HttpServletResponse.SC_CREATED);
		    mapper.writeValue(resp.getOutputStream(), Map.of(
		        "status", "success",
		        "message", "Đăng nhập thành công",
		        "account", taiKhoanNew 
		    ));
		} else {
		    resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		    
		    mapper.writeValue(resp.getOutputStream(), Map.of(
		        "status", "error",
		        "message", "Số điện thoại hoặc mật khẩu không hợp lệ!"
		    ));
		}
	}
}
