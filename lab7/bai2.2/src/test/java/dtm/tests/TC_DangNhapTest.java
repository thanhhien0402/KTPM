package dtm.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import dtm.base.BaseTest;
import dtm.data.DangNhapData;
import dtm.pages.LoginPage;

public class TC_DangNhapTest extends BaseTest {

    @Test(
            dataProvider = "du_lieu_dang_nhap",
            dataProviderClass = DangNhapData.class,
            description = "Kiểm thử đăng nhập với nhiều bộ dữ liệu"
    )
    public void kiemThuDangNhap(String username,
                                String password,
                                String ketQuaMongDoi,
                                String moTa) {

        LoginPage loginPage = new LoginPage(driver);

        // Xử lý null theo yêu cầu đề
        String userInput = (username == null) ? "" : username;
        String passInput = (password == null) ? "" : password;

        // Nếu muốn coi chuỗi toàn khoảng trắng là trường trống:
        if (username != null && username.trim().isEmpty()) {
            userInput = "";
        }
        if (password != null && password.trim().isEmpty()) {
            passInput = "";
        }

        loginPage.dangNhap(userInput, passInput);

        switch (ketQuaMongDoi) {
            case "THANH_CONG":
                Assert.assertTrue(
                        loginPage.isDangOTrangSanPham(),
                        "FAIL [" + moTa + "]: đáng lẽ đăng nhập thành công"
                );
                break;

            case "BI_KHOA":
                Assert.assertFalse(
                        loginPage.isDangOTrangSanPham(),
                        "FAIL [" + moTa + "]: tài khoản bị khóa nhưng vẫn vào được hệ thống"
                );
                Assert.assertNotNull(
                        loginPage.layThongBaoLoi(),
                        "FAIL [" + moTa + "]: không hiển thị thông báo lỗi khóa tài khoản"
                );
                break;

            case "SAI_THONG_TIN":
                Assert.assertFalse(
                        loginPage.isDangOTrangSanPham(),
                        "FAIL [" + moTa + "]: thông tin sai nhưng vẫn đăng nhập được"
                );
                Assert.assertNotNull(
                        loginPage.layThongBaoLoi(),
                        "FAIL [" + moTa + "]: không hiển thị thông báo lỗi sai thông tin"
                );
                break;

            case "TRUONG_TRONG":
                Assert.assertFalse(
                        loginPage.isDangOTrangSanPham(),
                        "FAIL [" + moTa + "]: để trống dữ liệu nhưng vẫn đăng nhập được"
                );
                Assert.assertNotNull(
                        loginPage.layThongBaoLoi(),
                        "FAIL [" + moTa + "]: không hiển thị thông báo lỗi trường trống"
                );
                break;

            default:
                Assert.fail("Giá trị ketQuaMongDoi không hợp lệ: " + ketQuaMongDoi);
        }
    }
}