package dao;

import model.KhachHang;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class KhachHangDAO {

    // ===== 1. Kiểm tra mã KH tồn tại =====
    public boolean isMaKHTonTai(String maKH) {
        String sql = "SELECT 1 FROM dbo.KHACH_HANG WHERE MaKH = ?";
        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setString(1, maKH);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // ===== 2. Kiểm tra email tồn tại =====
    public boolean isEmailTonTai(String email) {
        String sql = "SELECT 1 FROM dbo.KHACH_HANG WHERE Email = ?";
        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // ===== 3. Thêm khách hàng =====
    public boolean insert(KhachHang kh) {

        // Chặn trùng mã KH
        if (isMaKHTonTai(kh.getMaKH())) return false;

        // Chặn trùng email
        if (isEmailTonTai(kh.getEmail())) return false;

        String sql = """
            INSERT INTO dbo.KHACH_HANG
            (MaKH, HoTen, Email, SoDienThoai, DiaChi, MatKhau, NgaySinh, GioiTinh)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?)
        """;

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setString(1, kh.getMaKH());
            ps.setString(2, kh.getHoTen());
            ps.setString(3, kh.getEmail());
            ps.setString(4, kh.getSoDienThoai());
            ps.setString(5, kh.getDiaChi());
            ps.setString(6, kh.getMatKhau());
            ps.setDate(7, kh.getNgaySinh() == null ? null :
                    new java.sql.Date(kh.getNgaySinh().getTime()));
            ps.setString(8, kh.getGioiTinh());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}