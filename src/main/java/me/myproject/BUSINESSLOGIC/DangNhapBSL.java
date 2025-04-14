// File: ClientProject/src/me/myproject/BUSINESSLOGIC/DangNhapBSL.java
package me.myproject.BUSINESSLOGIC;

import java.util.HashMap;

public class DangNhapBSL {
    private HashMap<String, String> listAccount;
    private HashMap<String, String> otpMap;

    public DangNhapBSL() {
        listAccount = new HashMap<>();
        otpMap = new HashMap<>();

        // Thêm dữ liệu giả lập: số điện thoại và mật khẩu
        listAccount.put("0355160346", "12345");
        listAccount.put("0328749913", "12345");
    }

    // Kiểm tra sự tồn tại của số điện thoại
    public boolean kiemTraTonTaiSDT(String phone) {
        try {
            return listAccount.containsKey(phone);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Phương thức đăng nhập
    public String xuLyDangNhap(String phone, String password) {
        // Kiểm tra dữ liệu đầu vào
        if (phone == null || phone.trim().isEmpty()) {
            return "Vui lòng nhập số điện thoại!";
        }
        if (password == null || password.trim().isEmpty()) {
            return "Vui lòng nhập mật khẩu!";
        }

        // Kiểm tra số điện thoại có tồn tại không
        if (!kiemTraTonTaiSDT(phone)) {
            return "Số điện thoại không tồn tại!";
        }

        try {
            // Lấy mật khẩu từ "cơ sở dữ liệu" giả lập
            String storedPassword = listAccount.get(phone);
            if (storedPassword == null) {
                return "Lỗi khi truy vấn dữ liệu!";
            }
            // Kiểm tra mật khẩu
            if (storedPassword.equals(password)) {
                return "Đăng nhập thành công!";
            } else {
                return "Mật khẩu không chính xác!";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Lỗi khi xử lý đăng nhập!";
        }
    }

    // Phương thức xử lý quên mật khẩu
    public String xuLyQuenMatKhau(String phone) {
        if (phone == null || phone.trim().isEmpty()) {
            return "Vui lòng nhập số điện thoại!";
        }
        if (!kiemTraTonTaiSDT(phone)) {
            return "Số điện thoại không tồn tại trong hệ thống.";
        }
        sendOTP(phone);
        return "Mã OTP đã được gửi đến số điện thoại của bạn.";
    }

    // Gửi OTP (mã giả lập)
    private void sendOTP(String phone) {
        otpMap.put(phone, "123456"); // Mã OTP giả lập
    }

    // Xác thực OTP
    public String xacThucOTP(String phone, String otpInput) {
        if (otpInput == null || otpInput.trim().isEmpty()) {
            return "Bạn chưa nhập mã OTP!";
        }
        String validOTP = otpMap.get(phone);
        if (validOTP != null && validOTP.equals(otpInput)) {
            return "Xác thực OTP thành công! Vui lòng nhập mật khẩu mới.";
        } else {
            return "Mã OTP không hợp lệ!";
        }
    }

    // Cập nhật mật khẩu
    public String capNhatMatKhau(String phone, String newPass, String confirmPass) {
        // Kiểm tra dữ liệu đầu vào
        if (newPass == null || newPass.trim().isEmpty() || confirmPass == null || confirmPass.trim().isEmpty()) {
            return "Mật khẩu không được để trống!";
        }
        if (!newPass.equals(confirmPass)) {
            return "Mật khẩu không khớp!";
        }

        try {
            if (kiemTraTonTaiSDT(phone)) {
                listAccount.put(phone, newPass); // Cập nhật mật khẩu trong "cơ sở dữ liệu" giả lập
                return "Đổi mật khẩu thành công!";
            }
            return "Số điện thoại không tồn tại!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Lỗi khi cập nhật mật khẩu!";
        }
    }
}