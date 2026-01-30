package bai4;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PatientPaymentForm extends JFrame {

    private JCheckBox cbMale;
    private JCheckBox cbFemale;
    private JCheckBox cbChild;
    private JTextField txtAge;
    private JTextField txtPayment;
    private JButton btnCalculate;

    public PatientPaymentForm() {

        setTitle("Calculate the Payment for the Patient");
        setSize(450, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        // ===== CHECKBOX =====
        cbMale = new JCheckBox("Male");
        cbMale.setBounds(30, 30, 80, 25);
        add(cbMale);

        cbFemale = new JCheckBox("Female");
        cbFemale.setBounds(120, 30, 80, 25);
        add(cbFemale);

        cbChild = new JCheckBox("Child (0 - 17 years)");
        cbChild.setBounds(220, 30, 150, 25);
        add(cbChild);

        // 👉 CHỈ CHỌN 1 TRONG 3
        ButtonGroup group = new ButtonGroup();
        group.add(cbMale);
        group.add(cbFemale);
        group.add(cbChild);

        // ===== AGE =====
        JLabel lblAge = new JLabel("Age (Years)");
        lblAge.setBounds(30, 80, 100, 25);
        add(lblAge);

        txtAge = new JTextField();
        txtAge.setBounds(120, 80, 100, 25);
        add(txtAge);

        // ===== BUTTON =====
        btnCalculate = new JButton("Calculate");
        btnCalculate.setBounds(240, 80, 120, 30);
        add(btnCalculate);

        // ===== PAYMENT =====
        JLabel lblPayment = new JLabel("Payment is");
        lblPayment.setBounds(30, 140, 100, 25);
        add(lblPayment);

        txtPayment = new JTextField();
        txtPayment.setBounds(120, 140, 100, 25);
        txtPayment.setEditable(false);
        add(txtPayment);

        JLabel lblEuro = new JLabel("euro €");
        lblEuro.setBounds(230, 140, 60, 25);
        add(lblEuro);

        // ===== EVENT =====
        btnCalculate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (!cbMale.isSelected() && !cbFemale.isSelected() && !cbChild.isSelected()) {
                        JOptionPane.showMessageDialog(
                                null,
                                "Please select Male, Female or Child",
                                "Error",
                                JOptionPane.ERROR_MESSAGE
                        );
                        return;
                    }

                    int age = Integer.parseInt(txtAge.getText());

                    int payment = PaymentCalculator.calculate(
                            cbMale.isSelected(),
                            cbFemale.isSelected(),
                            cbChild.isSelected(),
                            age
                    );

                    txtPayment.setText(String.valueOf(payment));

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(
                            null,
                            "Age must be a number",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(
                            null,
                            ex.getMessage(),
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });
    }

    public static void main(String[] args) {
        new PatientPaymentForm().setVisible(true);
    }
}
