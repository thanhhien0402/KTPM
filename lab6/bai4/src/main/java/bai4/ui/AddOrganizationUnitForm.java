package bai4.ui;

import bai4.dao.SqlOrgUnitDao;
import bai4.db.DbConfig;
import bai4.model.OrganizationUnit;
import bai4.service.OrgUnitService;

import javax.swing.*;
import java.awt.*;

public class AddOrganizationUnitForm extends JFrame {

    private JTextField txtUnitId;
    private JTextField txtName;
    private JTextArea txtDescription;

    private OrgUnitService service;

    public AddOrganizationUnitForm() {

        // Khởi tạo service + DAO
        SqlOrgUnitDao dao = new SqlOrgUnitDao(
                DbConfig.URL,
                DbConfig.USER,
                DbConfig.PASS
        );
        service = new OrgUnitService(dao);

        setTitle("Add Organization Unit");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initUI();
    }

    private void initUI() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(6, 1, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Unit Id
        formPanel.add(new JLabel("Unit Id"));
        txtUnitId = new JTextField();
        formPanel.add(txtUnitId);

        // Name*
        formPanel.add(new JLabel("Name*"));
        txtName = new JTextField();
        formPanel.add(txtName);

        // Description
        formPanel.add(new JLabel("Description"));
        txtDescription = new JTextArea(3, 20);
        formPanel.add(new JScrollPane(txtDescription));

        panel.add(formPanel, BorderLayout.CENTER);

        // Buttons
        JPanel buttonPanel = new JPanel();

        JButton btnSave = new JButton("Save");
        JButton btnCancel = new JButton("Cancel");

        btnSave.addActionListener(e -> handleSave());
        btnCancel.addActionListener(e -> clearForm());

        buttonPanel.add(btnCancel);
        buttonPanel.add(btnSave);

        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel);
    }

    private void handleSave() {
        try {
            OrganizationUnit unit = new OrganizationUnit(
                    txtUnitId.getText(),
                    txtName.getText(),
                    txtDescription.getText(),
                    1   // orgId mặc định = 1
            );

            service.create(unit);

            JOptionPane.showMessageDialog(this,
                    "Saved successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);

            clearForm();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearForm() {
        txtUnitId.setText("");
        txtName.setText("");
        txtDescription.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AddOrganizationUnitForm().setVisible(true);
        });
    }
}