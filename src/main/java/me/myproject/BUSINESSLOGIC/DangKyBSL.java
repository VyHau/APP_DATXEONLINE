package me.myproject.BUSINESSLOGIC;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import me.myproject.Utilities.APIHelper;

public class DangKyBSL {
    public String xuLyDangKy(String fullName, String phone, String address, String password, String confirmPassword) {
        if (!SDTHopLe(phone)) return "Số điện thoại không hợp lệ. Phải bắt đầu bằng 03-09 và có 10 chữ số.";
        if (!matKhauHopLe(password)) return "Mật khẩu phải có ít nhất 5 ký tự, gồm chữ hoa, chữ thường, số và ký tự đặc biệt.";
        if(!xacNhanMatKhau(password, confirmPassword)) return "Mật khẩu không khớp. Vui lòng xác nhận lại!";
        //Call API
        try {
            Map<String, String> data = new HashMap<>();
            data.put("fullName", fullName);
            data.put("phone", phone);
            data.put("address", address);
            data.put("passWord", password);
            data.put("role", "USER"); 
            Map<String, Object> response = APIHelper.postForMap("http://localhost:8080/ProjectNHOM/api-register", data);
            
            String status = (String) response.get("status");
            if ("Success".equals(status)) 
                return "Đăng ký thành công!";
            else {
                String errorMessage = (String) response.getOrDefault("message", "Đăng ký thất bại!");
                return errorMessage;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Không kết nối được tới server!";
        }
    }

    public boolean SDTHopLe(String phoneNumber) {
        final String PHONE_REGEX = "^(0[3-9])[0-9]{8}$";
        Pattern pattern = Pattern.compile(PHONE_REGEX);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }

    public boolean matKhauHopLe(String password) {
        return password.length() >= 5 && 
               password.matches(".*[A-Z].*") && 
               password.matches(".*[a-z].*") &&
               password.matches(".*\\d.*") &&
               password.matches(".*[!@#$%^&*(),.?\":{}|<>].*");
    }

    public boolean xacNhanMatKhau(String pass, String confirmPass){
        return pass.equals(confirmPass);
    }
}
