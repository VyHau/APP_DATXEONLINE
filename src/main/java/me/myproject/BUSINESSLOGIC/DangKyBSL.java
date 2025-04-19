package me.myproject.BUSINESSLOGIC;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DangKyBSL {
    public String xuLyDangKy(String fullName, String phone, String email, String password) {
        if (fullName == null || fullName.trim().isEmpty()) return "Vui lòng nhập họ tên.";
        if (!SDTHopLe(phone)) return "Số điện thoại không hợp lệ. Phải bắt đầu bằng 03-09 và có 10 chữ số.";
        if (!matKhauHopLe(password)) return "Mật khẩu phải có ít nhất 5 ký tự, gồm chữ hoa, chữ thường, số và ký tự đặc biệt.";
        return "Đăng ký thành công!";
    }

    public int tinhTuoi(String birthDateStr) {
        if ("".equals(birthDateStr)) 
            return 0;
        else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate birthDate = LocalDate.parse(birthDateStr, formatter);
            LocalDate currentDate = LocalDate.now();
            return Period.between(birthDate, currentDate).getYears();
        }
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

    public boolean maBLXHopLe(String blxCode) {
        final String BLX_REGEX = "^[A-Za-z0-9]{5,15}$";
        Pattern pattern = Pattern.compile(BLX_REGEX);
        Matcher matcher = pattern.matcher(blxCode);
        return matcher.matches();
    }

    public boolean bienSoHopLe(String licensePlate) {
        // Cars: 29A-12345 (2 số, 1 chữ, gạch nối, 4-5 số)
        // Motorbikes: 29A1-12345 (2 số, 1 chữ, 1 digit, gạch nối, 4-5 số)
        final String LICENSE_PLATE_REGEX = "^[0-9]{2}[A-Z]([0-9])?-?[0-9]{4,5}$";
        Pattern pattern = Pattern.compile(LICENSE_PLATE_REGEX);
        Matcher matcher = pattern.matcher(licensePlate);
        return matcher.matches();
    }
}
