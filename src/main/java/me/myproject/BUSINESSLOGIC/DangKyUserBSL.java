package me.myproject.BUSINESSLOGIC;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
   
public class DangKyUserBSL{
    
    public String xuLyDangKy(String name, String email, String phone, String password) {
        if (name == null || name.isEmpty()) return "Vui lòng nhập họ tên.";
        if (!EmailHopLe(email)) return "Email không hợp lệ.";
        if (!SDTHopLe(phone)) return "Số điện thoại không hợp lệ.";
        if (!matKhauHopLe(password)) return "Mật khẩu phải có ít nhất 5 ký tự, chữ hoa, chữ thường, số và ký tự đặc biệt.";
        return "Đăng ký thành công!";
    }
    public  int tinhTuoi(String birthDateStr) {
       if("".equals(birthDateStr)){
            return 0;
       }
       else
       {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate birthDate = LocalDate.parse(birthDateStr, formatter);
            LocalDate currentDate = LocalDate.now();
            
            return Period.between(birthDate, currentDate).getYears();
       }
    }
    public  boolean SDTHopLe(String phoneNumber) {
        final String PHONE_REGEX = "^(0[3-9])[0-9]{8}$";
        Pattern pattern = Pattern.compile(PHONE_REGEX);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }
    public  boolean EmailHopLe(String email) {
        final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";    
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    public boolean matKhauHopLe(String password) {
        return password.length() >= 5 &&
                password.matches(".*[A-Z].*") &&
                password.matches(".*[a-z].*") &&
                password.matches(".*\\d.*") &&
                password.matches(".*[!@#$%^&*(),.?\":{}|<>].*");
    }
    

}
