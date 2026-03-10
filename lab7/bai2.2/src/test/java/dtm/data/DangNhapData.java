package dtm.data;

import org.testng.annotations.DataProvider;

public class DangNhapData {

    @DataProvider(name = "du_lieu_dang_nhap")
    public Object[][] getData() {
        return new Object[][]{
                {"standard_user", "secret_sauce", "THANH_CONG", "Đăng nhập hợp lệ"},
                {"locked_out_user", "secret_sauce", "BI_KHOA", "Tài khoản bị khóa"},
                {"standard_user", "sai_password", "SAI_THONG_TIN", "Username đúng, password sai"},
                {"sai_user", "secret_sauce", "SAI_THONG_TIN", "Username sai, password đúng"},
                {"", "secret_sauce", "TRUONG_TRONG", "Bỏ trống username"},
                {"standard_user", "", "TRUONG_TRONG", "Bỏ trống password"},
                {" standard_user ", "secret_sauce", "SAI_THONG_TIN", "Username có khoảng trắng đầu/cuối"},
                {null, "secret_sauce", "TRUONG_TRONG", "Username = null"}
        };
    }
}