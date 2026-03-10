package shopvn.service;

import shopvn.model.User;

import java.util.regex.Pattern;

public class RegisterService {

    public String register(User user) {

        if(user.getFullName() == null || user.getFullName().length() < 2 || user.getFullName().length() > 50){
            return "Họ tên không hợp lệ";
        }

        if(!user.getUsername().matches("^[a-z][a-z0-9_]{4,19}$")){
            return "Username không hợp lệ";
        }

        if(!isValidEmail(user.getEmail())){
            return "Email không hợp lệ";
        }

        if(!user.getPhone().matches("^0\\d{9}$")){
            return "Số điện thoại không hợp lệ";
        }

        if(!isValidPassword(user.getPassword())){
            return "Mật khẩu không hợp lệ";
        }

        if(!user.getPassword().equals(user.getConfirmPassword())){
            return "Mật khẩu xác nhận không khớp";
        }

        if(!user.isAgreeTerms()){
            return "Phải đồng ý điều khoản";
        }

        return "Đăng ký thành công";
    }

    private boolean isValidEmail(String email){
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return Pattern.matches(regex,email);
    }

    private boolean isValidPassword(String password){

        if(password.length() < 8 || password.length() > 32){
            return false;
        }

        boolean hasUpper = password.matches(".*[A-Z].*");
        boolean hasLower = password.matches(".*[a-z].*");
        boolean hasNumber = password.matches(".*[0-9].*");
        boolean hasSpecial = password.matches(".*[!@#$%^&*()].*");

        return hasUpper && hasLower && hasNumber && hasSpecial;
    }
}