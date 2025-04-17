package controller;

import java.io.Console;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import dao.Exception.TaiXeException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.TaiXe;
import service.TaiXeService;
import utils.JsonUtil;

@WebServlet(urlPatterns = {"/api-driver"})
public class TaiXeController extends HttpServlet{
	
	private static final long serialVersionUID = -5468554703941755526L;
	private TaiXeService taiXeService=new TaiXeService();
	private ObjectMapper mappper=new ObjectMapper();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		    
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		
		List<TaiXe> list=taiXeService.selectAllTaiXe();
		mappper.writeValue(resp.getOutputStream(), list);
	}
	@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        try {
            String jsonInput = req.getReader().lines().collect(Collectors.joining());
            TaiXe taiXe = JsonUtil.fromJson(jsonInput, TaiXe.class);

            boolean success = taiXeService.themTaiXe(taiXe);

            if (success) {
                resp.setStatus(HttpServletResponse.SC_CREATED);
                mapper.writeValue(resp.getOutputStream(), Map.of(
                    "status", "success",
                    "message", "Đăng ký thành công"
                ));
            }

        } catch (TaiXeException e) {
            resp.setStatus(HttpServletResponse.SC_CONFLICT);
            mapper.writeValue(resp.getOutputStream(), Map.of(
                "status", "fail",
                "message", e.getMessage()
            ));
        } catch (SQLException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            mapper.writeValue(resp.getOutputStream(), Map.of(
                "status", "error",
                "message", "Lỗi CSDL: " + e.getMessage()
            ));
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            mapper.writeValue(resp.getOutputStream(), Map.of(
                "status", "error",
                "message", "Lỗi không xác định: " + e.getMessage()
            ));
        }
    }
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		
		String jsonInput=req.getReader().lines().collect(Collectors.joining());
		
		TaiXe taiXe=JsonUtil.fromJson(jsonInput, TaiXe.class);
		taiXeService.updateTaiXe(taiXe);
		resp.setStatus(HttpServletResponse.SC_CREATED);
		mappper.writeValue(resp.getOutputStream(), "{Đã cập nhật thành công}");
		
	}
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		
		String jsonInput = req.getReader().lines().collect(Collectors.joining());
    	Map<String, List<String>> data = mappper.readValue(jsonInput, new TypeReference<Map<String, List<String>>>() {});

    	List<String> ids = data.get("ID_TX");
    	for (String key : ids) {
    	    taiXeService.deleteTaiXe(key);
    	}


    	mappper.writeValue(resp.getOutputStream(), "{Đã xoá thành công}");
	}
	
}
