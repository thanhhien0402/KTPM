package test;

import dao.KhachHangDAO;
import model.KhachHang;
import org.junit.jupiter.api.*;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class KhachHangDAOTest {

    static KhachHangDAO dao;

    @BeforeAll
    static void setUp() {
        dao = new KhachHangDAO();
    }

    // ===== TC01: Mã KH đã tồn tại =====
    @Test
    @Order(1)
    void TC01_MaKhachHangTonTai() {
        assertTrue(dao.isMaKHTonTai("hien12"));
    }

    // ===== TC02: Email đã tồn tại =====
    @Test
    @Order(2)
    void TC02_EmailTonTai() {
        assertTrue(dao.isEmailTonTai("123@gmail.com"));
    }




    // ===== TC04: Trùng mã KH =====
    @Test
    @Order(4)
    void TC04_TrungMaKhachHang() {
        KhachHang kh = new KhachHang(
                "hien12",
                "Test Trùng",
                "abcxyz@gmail.com",
                "0987654321",
                "Test",
                "12345678",
                null,
                "Nữ"
        );

        assertFalse(dao.insert(kh));
    }

    // ===== TC05: Trùng email =====
    @Test
    @Order(5)
    void TC05_TrungEmail() {
        KhachHang kh = new KhachHang(
                "abc999",
                "Test Email",
                "123@gmail.com",
                "0912345678",
                "Test",
                "12345678",
                null,
                "Khác"
        );

        assertFalse(dao.insert(kh));
    }
}
