package ui;

import dao.KhachHangDAO;
import model.KhachHang;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.regex.Pattern;

public class DangKyForm extends JFrame {

    private JTextField txtMaKH, txtHoTen, txtEmail, txtSDT, txtNgaySinh;
    private JTextArea txtDiaChi;
    private JPasswordField txtMatKhau, txtXacNhanMK;
    private JRadioButton rdoNam, rdoNu, rdoKhac;
    private JCheckBox chkDieuKhoan;
    private JButton btnDangKy, btnNhapLai;

    private final KhachHangDAO dao = new KhachHangDAO();

    public DangKyForm() {
        setTitle("Đăng ký tài khoản khách hàng");
        setSize(520, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        int row = 0;

        addLabel(panel, gbc, "Mã Khách Hàng *", 0, row);
        txtMaKH = new JTextField();
        addField(panel, gbc, txtMaKH, 1, row++);

        addLabel(panel, gbc, "Họ và Tên *", 0, row);
        txtHoTen = new JTextField();
        addField(panel, gbc, txtHoTen, 1, row++);

        addLabel(panel, gbc, "Email *", 0, row);
        txtEmail = new JTextField();
        addField(panel, gbc, txtEmail, 1, row++);

        addLabel(panel, gbc, "Số điện thoại *", 0, row);
        txtSDT = new JTextField();
        addField(panel, gbc, txtSDT, 1, row++);

        addLabel(panel, gbc, "Địa chỉ *", 0, row);
        txtDiaChi = new JTextArea(3, 20);
        addField(panel, gbc, new JScrollPane(txtDiaChi), 1, row++);

        addLabel(panel, gbc, "Mật khẩu *", 0, row);
        txtMatKhau = new JPasswordField();
        addField(panel, gbc, txtMatKhau, 1, row++);

        addLabel(panel, gbc, "Xác nhận mật khẩu *", 0, row);
        txtXacNhanMK = new JPasswordField();
        addField(panel, gbc, txtXacNhanMK, 1, row++);

        addLabel(panel, gbc, "Ngày sinh", 0, row);
        txtNgaySinh = new JTextField("dd/MM/yyyy");
        addField(panel, gbc, txtNgaySinh, 1, row++);

        addLabel(panel, gbc, "Giới tính", 0, row);
        rdoNam = new JRadioButton("Nam");
        rdoNu = new JRadioButton("Nữ");
        rdoKhac = new JRadioButton("Khác");

        ButtonGroup group = new ButtonGroup();
        group.add(rdoNam);
        group.add(rdoNu);
        group.add(rdoKhac);

        JPanel genderPanel = new JPanel();
        genderPanel.add(rdoNam);
        genderPanel.add(rdoNu);
        genderPanel.add(rdoKhac);

        addField(panel, gbc, genderPanel, 1, row++);

        chkDieuKhoan = new JCheckBox("Tôi đồng ý với các điều khoản dịch vụ *");
        gbc.gridx = 1;
        gbc.gridy = row++;
        panel.add(chkDieuKhoan, gbc);

        btnDangKy = new JButton("Đăng ký");
        btnNhapLai = new JButton("Nhập lại");

        JPanel btnPanel = new JPanel();
        btnPanel.add(btnDangKy);
        btnPanel.add(btnNhapLai);

        gbc.gridx = 1;
        gbc.gridy = row;
        panel.add(btnPanel, gbc);

        add(panel);

        btnDangKy.addActionListener(this::xuLyDangKy);
        btnNhapLai.addActionListener(e -> resetForm());
    }

    private void xuLyDangKy(ActionEvent e) {
        try {
            String maKH = txtMaKH.getText().trim();
            if (!maKH.matches("^[a-zA-Z0-9]{6,10}$"))
                throw new Exception("Mã KH phải 6–10 ký tự, chỉ chữ và số.");
            if (dao.isMaKHTonTai(maKH))
                throw new Exception("Mã khách hàng đã tồn tại.");

            String hoTen = txtHoTen.getText().trim();
            if (!hoTen.matches("^[\\p{L} ]{5,50}$"))
                throw new Exception("Họ tên từ 5–50 ký tự, chỉ chữ.");

            String email = txtEmail.getText().trim();
            if (!Pattern.matches("^[\\w.-]+@[\\w.-]+\\.\\w+$", email))
                throw new Exception("Email không hợp lệ.");
            if (dao.isEmailTonTai(email))
                throw new Exception("Email đã tồn tại.");

            String sdt = txtSDT.getText().trim();
            if (!sdt.matches("^0\\d{9,11}$"))
                throw new Exception("SĐT phải bắt đầu bằng 0, 10–12 số.");

            String diaChi = txtDiaChi.getText().trim();
            if (diaChi.isEmpty() || diaChi.length() > 255)
                throw new Exception("Địa chỉ không hợp lệ.");

            String mk = new String(txtMatKhau.getPassword());
            if (mk.length() < 8)
                throw new Exception("Mật khẩu ít nhất 8 ký tự.");

            if (!mk.equals(new String(txtXacNhanMK.getPassword())))
                throw new Exception("Mật khẩu xác nhận không khớp.");

            Date ngaySinh = null;
            if (!txtNgaySinh.getText().trim().isEmpty()
                    && !txtNgaySinh.getText().equals("dd/MM/yyyy")) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                sdf.setLenient(false);
                ngaySinh = sdf.parse(txtNgaySinh.getText());

                LocalDate birth = ngaySinh.toInstant()
                        .atZone(ZoneId.systemDefault()).toLocalDate();
                if (Period.between(birth, LocalDate.now()).getYears() < 18)
                    throw new Exception("Người dùng phải đủ 18 tuổi.");
            }

            if (!chkDieuKhoan.isSelected())
                throw new Exception("Bạn phải đồng ý điều khoản.");

            String gioiTinh = rdoNam.isSelected() ? "Nam"
                    : rdoNu.isSelected() ? "Nữ"
                    : rdoKhac.isSelected() ? "Khác" : null;

            KhachHang kh = new KhachHang(
                    maKH, hoTen, email, sdt, diaChi, mk, ngaySinh, gioiTinh
            );

            dao.insert(kh);
            JOptionPane.showMessageDialog(this,
                    "Đăng ký tài khoản thành công!");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(),
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void resetForm() {
        txtMaKH.setText("");
        txtHoTen.setText("");
        txtEmail.setText("");
        txtSDT.setText("");
        txtDiaChi.setText("");
        txtMatKhau.setText("");
        txtXacNhanMK.setText("");
        txtNgaySinh.setText("dd/MM/yyyy");
        chkDieuKhoan.setSelected(false);
    }

    private void addLabel(JPanel p, GridBagConstraints g, String text, int x, int y) {
        g.gridx = x;
        g.gridy = y;
        p.add(new JLabel(text), g);
    }

    private void addField(JPanel p, GridBagConstraints g, Component c, int x, int y) {
        g.gridx = x;
        g.gridy = y;
        p.add(c, g);
    }

    public static void main(String[] args) {
        new DangKyForm().setVisible(true);
    }
}
