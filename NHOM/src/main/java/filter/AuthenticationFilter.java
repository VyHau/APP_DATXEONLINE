package filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Cookie;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import com.fasterxml.jackson.databind.ObjectMapper;
import utils.TokenManager;
import java.util.HashMap;
import java.util.Map;

public class AuthenticationFilter implements Filter {
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String uri = httpRequest.getRequestURI();
        String method = httpRequest.getMethod();

        // Thêm header CORS
        httpResponse.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:3000"); // Thay bằng origin của frontend
        httpResponse.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS, PUT, DELETE");
        httpResponse.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type");
        httpResponse.setHeader("Access-Control-Allow-Credentials", "true"); // Nếu dùng cookies
        httpResponse.setHeader("Access-Control-Max-Age", "3600");

        // Xử lý yêu cầu OPTIONS (preflight)
        if ("OPTIONS".equalsIgnoreCase(method)) {
            httpResponse.setStatus(HttpServletResponse.SC_OK);
            return; // Không tiếp tục chain cho OPTIONS
        }

        // Bỏ qua các endpoint không cần xác thực
        if (uri.endsWith("/api/admin/login") ||
            uri.endsWith("/api/admin/driver/status") ||
            uri.endsWith("/api/admin/quanly/chuyenXe") ||
            uri.endsWith("/api/driver/dsGanKhachHang")||
            uri.endsWith("/api/admin/thongKe")||
            uri.endsWith("/api/admin/datXe/status") ||
            uri.endsWith("/api/admin/tong-quan") ||
            uri.matches(".*\\.(css|js|png|jpg|jpeg|gif|woff|woff2|ttf|svg)")) {
            chain.doFilter(request, response);
            return;
        }

        String token = null;
        Cookie[] cookies = httpRequest.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("token".equals(cookie.getName())) {
                    token = URLDecoder.decode(cookie.getValue(), StandardCharsets.UTF_8.toString());
                    break;
                }
            }
        }

        if (token == null) {
            String authHeader = httpRequest.getHeader("Authorization");
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                token = authHeader.substring(7);
            }
        }

        try {
            if (token != null && TokenManager.isTokenValid(token)) {
                chain.doFilter(request, response);
            } else {
                httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                httpResponse.setContentType("application/json");
                httpResponse.setCharacterEncoding("UTF-8");
                mapper.writeValue(httpResponse.getWriter(), Map.of(
                        "status", 401,
                        "message", "Token không hợp lệ hoặc đã hết hạn!"
                ));
            }
        } catch (Exception e) {
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpResponse.setContentType("application/json");
            httpResponse.setCharacterEncoding("UTF-8");
            mapper.writeValue(httpResponse.getWriter(), Map.of(
                    "status", 402,
                    "message", "Lỗi xác thực token!"
            ));
        }
    }
    

    @Override
    public void destroy() {
    }
}