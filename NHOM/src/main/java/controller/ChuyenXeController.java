package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.DatXe;
import service.ChuyenXeService;
import utils.JsonUtil;

@WebServlet(urlPatterns = {"/api-rides"})
public class ChuyenXeController extends HttpServlet{
	private static final long serialVersionUID = -1137823512321937256L;
	private final ChuyenXeService chuyenXeService = new ChuyenXeService();
	private final ObjectMapper mapper=new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        
        List<DatXe> list = chuyenXeService.getAllRides();
        mapper.writeValue(resp.getOutputStream(), list);
      
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        String jsonInput = req.getReader().lines().collect(Collectors.joining());
        DatXe datXe = JsonUtil.fromJson(jsonInput, DatXe.class);
        chuyenXeService.bookRide(datXe);
        
        resp.setStatus(HttpServletResponse.SC_CREATED);
        mapper.writeValue(resp.getOutputStream(), "{Đã đặt xe thành công!}");
    }
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	resp.setContentType("application/json");
    	resp.setCharacterEncoding("UTF-8");
    	
    	StringBuilder bodyBuilder = new StringBuilder();
	    try (BufferedReader reader = req.getReader()) {
	        String line;
	        while ((line = reader.readLine()) != null) {
	            bodyBuilder.append(line);
	        }
	    }
	    String bodyString = bodyBuilder.toString();

	    JsonObject body = JsonUtil.fromJson(bodyString, JsonObject.class);
	    String idChuyenXe = body.get("id_ChuyenXe").getAsString(); 
	    String idTaiXe = body.get("id_TaiXe").getAsString();
	    String ghiChu= body.get("ghiChu").getAsString();
	    String message="Lỗi.Vui lòng thử lại sau!";
	    int status=400;
    	try {
    		message=chuyenXeService.ganTaiXeNhanDon(idChuyenXe,idTaiXe);
			status=200;
		} catch (Exception e) {
			status=400;
			message=e.getMessage();
		}
    	mapper.writeValue(resp.getOutputStream(), Map.of(
				"status",status,
				"message",message));
    	
    }
    
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	 resp.setContentType("application/json");
         String jsonInput = req.getReader().lines().collect(Collectors.joining());
         Map<String,String> data = mapper.readValue(jsonInput, new TypeReference<Map<String, String>>() {});
        chuyenXeService.huyChuyenXe(data.get("ID_DX"));
        
        mapper.writeValue(resp.getOutputStream(), "{Huỷ chuyến thành công}");
         
    }
}
