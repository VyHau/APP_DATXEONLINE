package controller;

import java.io.IOException;
import java.io.PrintWriter;
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
import model.DatXe;
import service.ChuyenXeService;
import utils.JsonUtil;

@WebServlet(urlPatterns = {"/api-rides"})
public class ChuyenXeController extends HttpServlet{
	private static final long serialVersionUID = -1137823512321937256L;
	private ChuyenXeService chuyenXeService = new ChuyenXeService();
	private ObjectMapper mapper=new ObjectMapper();

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
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	 resp.setContentType("application/json");
         String jsonInput = req.getReader().lines().collect(Collectors.joining());
         Map<String,String> data = mapper.readValue(jsonInput, new TypeReference<Map<String, String>>() {});
        chuyenXeService.huyChuyenXe(data.get("ID_CHUYENXE"));
        
        mapper.writeValue(resp.getOutputStream(), "{Huỷ chuyến thành công}");
         
    }
}
