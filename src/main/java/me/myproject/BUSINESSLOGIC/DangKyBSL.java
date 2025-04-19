package me.myproject.BUSINESSLOGIC;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DangKyBSL {
    public String xuLyDangKy(String fullName, String phone, String email, String password) {
        if (fullName == null || fullName.trim().isEmpty()) return "Vui lòng nhập họ tên.";
        if (!SDTHopLe(phone)) return "Số điện thoại không hợp lệ. Phải bắt đầu bằng 03-09 và có 10 chữ số.";
        if (!matKhauHopLe(password)) return "Mật khẩu phải có ít nhất 5 ký tự, gồm chữ hoa, chữ thường, số và ký tự đặc biệt.";
        return "Đăng ký thành công!";
    }

    public boolean SDTHopLe(String phoneNumber) {
        final String PHONE_REGEX = "^(0[3-9])[0-9]{8}$";
        Pattern pattern = Pattern.compile(PHONE_REGEX);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }
    // public boolean EmailHopLe(String email) {
    //     final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    //     Pattern pattern = Pattern.compile(EMAIL_REGEX);
    //     Matcher matcher = pattern.matcher(email);
    //     return matcher.matches();
    // }

    public boolean matKhauHopLe(String password) {
        return password.length() >= 5 &&
                password.matches(".*[A-Z].*") &&
                password.matches(".*[a-z].*") &&
                password.matches(".*\\d.*") &&
                password.matches(".*[!@#$%^&*(),.?\":{}|<>].*");
    }
}
