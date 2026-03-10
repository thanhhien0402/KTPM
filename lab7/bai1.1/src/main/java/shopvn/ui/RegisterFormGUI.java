package shopvn.ui;

import shopvn.model.User;
import shopvn.service.RegisterService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterFormGUI extends JFrame {

    JTextField txtFullName = new JTextField();
    JTextField txtUsername = new JTextField();
    JTextField txtEmail = new JTextField();
    JTextField txtPhone = new JTextField();
    JPasswordField txtPassword = new JPasswordField();
    JPasswordField txtConfirm = new JPasswordField();
    JTextField txtBirth = new JTextField();
    JTextField txtGender = new JTextField();
    JTextField txtReferral = new JTextField();
    JCheckBox chkAgree = new JCheckBox("Đồng ý điều khoản");

    JButton btnRegister = new JButton("Đăng ký");

    public RegisterFormGUI(){

        setTitle("Form Đăng Ký ShopVN");

        setSize(400,500);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new GridLayout(11,2,5,5));

        add(new JLabel("Họ tên"));
        add(txtFullName);

        add(new JLabel("Username"));
        add(txtUsername);

        add(new JLabel("Email"));
        add(txtEmail);

        add(new JLabel("Phone"));
        add(txtPhone);

        add(new JLabel("Password"));
        add(txtPassword);

        add(new JLabel("Confirm Password"));
        add(txtConfirm);

        add(new JLabel("Birthday"));
        add(txtBirth);

        add(new JLabel("Gender"));
        add(txtGender);

        add(new JLabel("Referral Code"));
        add(txtReferral);

        add(new JLabel(""));
        add(chkAgree);

        add(new JLabel(""));
        add(btnRegister);

        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                registerUser();
            }
        });
    }

    private void registerUser(){

        String fullName = txtFullName.getText();
        String username = txtUsername.getText();
        String email = txtEmail.getText();
        String phone = txtPhone.getText();
        String password = new String(txtPassword.getPassword());
        String confirm = new String(txtConfirm.getPassword());
        String birth = txtBirth.getText();
        String gender = txtGender.getText();
        String referral = txtReferral.getText();
        boolean agree = chkAgree.isSelected();

        User user = new User(
                fullName,
                username,
                email,
                phone,
                password,
                confirm,
                birth,
                gender,
                referral,
                agree
        );

        RegisterService service = new RegisterService();

        String result = service.register(user);

        JOptionPane.showMessageDialog(this,result);
    }

    public static void main(String[] args) {

        new RegisterFormGUI().setVisible(true);

    }
}