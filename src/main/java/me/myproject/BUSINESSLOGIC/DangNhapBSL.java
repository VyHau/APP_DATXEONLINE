package me.myproject.BUSINESSLOGIC;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import me.myproject.MODEL.TaiKhoan;
import me.myproject.Utilities.APIHelper;

public class DangNhapBSL {
    private HashMap<String, String> listAccount;
    private HashMap<String, String> otpMap;

    public DangNhapBSL() {
        listAccount = new HashMap<>();
        otpMap = new HashMap<>();
    }

    public Map<String, Object> xuLyDangNhap(String phone, String password, String role) {
        Map<String, Object> result = new HashMap<>();
        try {
            Map<String, String> data = Map.of("SDT", phone, "matKhau", password, "ID_VaiTro", role);
            
            // Gọi API và nhận phản hồi
            Map<String, Object> response = APIHelper.postForMap("http://localhost:8080/ProjectNHOM/api-account", data);
            
            // Kiểm tra xem status có phải là "success" không
            String status = (String) response.get("status");
            
            if ("success".equals(status)) {
                // Nếu "status" là success, lấy thông tin "account" từ "data"
                Map<String, Object> accountData = (Map<String, Object>) response.get("account");
                TaiKhoan taiKhoan = new TaiKhoan(
                    (String) accountData.get("userName"),
                    (String) accountData.get("matKhau"),
                    (String) accountData.get("id_VaiTro"),
                    (String) accountData.get("id_NguoiDung")
                );
                result.put("message", "Đăng nhập thành công");
                result.put("taiKhoan", taiKhoan);
            } else {
                String errorMessage = (String) response.getOrDefault("message", "Đăng nhập thất bại!");
                result.put("message", errorMessage);
                result.put("taiKhoan", null);
            }
        } catch (IOException e) {
            e.printStackTrace();
            result.put("message", "Không kết nối được tới server!");
            result.put("taiKhoan", null);
        }
        return result;
    }



    public boolean kiemTraTonTaiSDT(String phone) {
        //Gọi API trả về DS tài khoản rồi truyền vào listAccount
        try {
            return listAccount.containsKey(phone);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public String xuLyQuenMatKhau(String phone) {
        if (phone == null || phone.trim().isEmpty()) 
            return "Vui lòng nhập số điện thoại!";
        if (!kiemTraTonTaiSDT(phone)) 
            return "Số điện thoại không tồn tại trong hệ thống.";
        sendOTP(phone);
        return "Mã OTP đã được gửi đến số điện thoại của bạn.";
    }

    private void sendOTP(String phone) {
        otpMap.put(phone, "123456");
    }

    public String xacThucOTP(String phone, String otpInput) {
        if (otpInput == null || otpInput.trim().isEmpty()) 
            return "Bạn chưa nhập mã OTP!";
        String validOTP = otpMap.get(phone);
        if (validOTP != null && validOTP.equals(otpInput)) 
            return "Xác thực OTP thành công! Vui lòng nhập mật khẩu mới.";
        else 
            return "Mã OTP không hợp lệ!";
    }

    public String capNhatMatKhau(String phone, String newPass, String confirmPass) {
        if (newPass == null || newPass.trim().isEmpty() || confirmPass == null || confirmPass.trim().isEmpty()) 
            return "Mật khẩu không được để trống!";
        if (!newPass.equals(confirmPass)) 
            return "Mật khẩu không khớp!";
        try {
            if (kiemTraTonTaiSDT(phone)) {
                listAccount.put(phone, newPass);
                return "Đổi mật khẩu thành công!";
            }
            return "Số điện thoại không tồn tại!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Lỗi khi cập nhật mật khẩu!";
        }
    }
    // public static void main(String[] args) {
    //     Map<String, Object> result = xuLyDangNhap("0355160346", String password, String role)
    // }
}
