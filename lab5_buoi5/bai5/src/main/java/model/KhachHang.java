package model;

import java.util.Date;

public class KhachHang {
    private String maKH;
    private String hoTen;
    private String email;
    private String soDienThoai;
    private String diaChi;
    private String matKhau;
    private Date ngaySinh;
    private String gioiTinh;

    // constructor rỗng (bắt buộc cho JDBC + test)
    public KhachHang() {
    }

    // constructor ĐẦY ĐỦ – JUnit sẽ dùng cái này
    public KhachHang(String maKH, String hoTen, String email,
                     String soDienThoai, String diaChi,
                     String matKhau, Date ngaySinh, String gioiTinh) {
        this.maKH = maKH;
        this.hoTen = hoTen;
        this.email = email;
        this.soDienThoai = soDienThoai;
        this.diaChi = diaChi;
        this.matKhau = matKhau;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
    }

    // ===== getter & setter =====
    public String getMaKH() { return maKH; }
    public String getHoTen() { return hoTen; }
    public String getEmail() { return email; }
    public String getSoDienThoai() { return soDienThoai; }
    public String getDiaChi() { return diaChi; }
    public String getMatKhau() { return matKhau; }
    public Date getNgaySinh() { return ngaySinh; }
    public String getGioiTinh() { return gioiTinh; }
}
