package amrita.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FeePaymentGUI {

    private final JTextField studentIDField;
    private final JTextField amountField;
    private final JComboBox<String> paymentMethodComboBox;
    private final JButton payButton;
    private JPanel cardDetailsPanel;
    private JTextField cardNumberField;
    private JTextField expiryDateField;
    private JTextField cvvField;

    public FeePaymentGUI() {
        JFrame frame = new JFrame("Fee Payment");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // Set background color and font
        frame.getContentPane().setBackground(new Color(128, 0, 0)); // Maroon color
        Font font = new Font("Segoe UI", Font.BOLD, 14);

        // Create panel with border
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(new Color(128, 0, 0)); // Maroon color

        // Create labels with font and color
        JLabel studentIDLabel = new JLabel("Student ID:");
        studentIDLabel.setFont(font);
        studentIDLabel.setForeground(new Color(255, 255, 255));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(studentIDLabel, gbc);
        studentIDField = new JTextField(20);
        studentIDField.setFont(font);
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(studentIDField, gbc);

        JLabel amountLabel = new JLabel("Amount:");
        amountLabel.setFont(font);
        amountLabel.setForeground(new Color(255, 255, 255));
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(amountLabel, gbc);
        amountField = new JTextField(20);
        amountField.setFont(font);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(amountField, gbc);

        JLabel paymentMethodLabel = new JLabel("Payment Method:");
        paymentMethodLabel.setFont(font);
        paymentMethodLabel.setForeground(new Color(255, 255, 255));
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(paymentMethodLabel, gbc);
        String[] paymentMethods = {"Cash", "Credit Card", "Debit Card"};
        paymentMethodComboBox = new JComboBox<>(paymentMethods);
        paymentMethodComboBox.setFont(font);
        paymentMethodComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handlePaymentMethodSelection();
            }
        });
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(paymentMethodComboBox, gbc);

        // Create pay button with color and font
        payButton = new JButton("Pay");
        payButton.setFont(font);
        payButton.setForeground(new Color(255, 255, 255));
        payButton.setBackground(new Color(0, 123, 255));
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.LINE_END;
        panel.add(payButton, gbc);
        payButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handlePayment();
            }
        });

        // Create card details panel (initially hidden)
        cardDetailsPanel = new JPanel(new GridBagLayout());
        cardDetailsPanel.setBackground(new Color(128, 0, 0)); // Maroon color
        GridBagConstraints cardGbc = new GridBagConstraints();
        cardGbc.insets = new Insets(5, 5, 5, 5);

        JLabel cardNumberLabel = new JLabel("Card Number:");
        cardNumberLabel.setFont(font);
        cardNumberLabel.setForeground(new Color(255, 255, 255));
        cardGbc.gridx = 0;
        cardGbc.gridy = 0;
        cardDetailsPanel.add(cardNumberLabel, cardGbc);
        cardNumberField = new JTextField(20);
        cardNumberField.setFont(font);
        cardGbc.gridx = 1;
        cardGbc.gridy = 0;
        cardDetailsPanel.add(cardNumberField, cardGbc);

        JLabel expiryDateLabel = new JLabel("Expiry Date:");
        expiryDateLabel.setFont(font);
        expiryDateLabel.setForeground(new Color(255, 255, 255));
        cardGbc.gridx = 0;
        cardGbc.gridy = 1;
        cardDetailsPanel.add(expiryDateLabel, cardGbc);
        expiryDateField = new JTextField(20);
        expiryDateField.setFont(font);
        cardGbc.gridx = 1;
        cardGbc.gridy = 1;
        cardDetailsPanel.add(expiryDateField, cardGbc);

        JLabel cvvLabel = new JLabel("CVV:");
        cvvLabel.setFont(font);
        cvvLabel.setForeground(new Color(255, 255, 255));
        cardGbc.gridx = 0;
        cardGbc.gridy = 2;
        cardDetailsPanel.add(cvvLabel, cardGbc);
        cvvField = new JTextField(20);
        cvvField.setFont(font);
        cardGbc.gridx = 1;
        cardGbc.gridy = 2;
        cardDetailsPanel.add(cvvField, cardGbc);

        cardDetailsPanel.setVisible(false); // Initially hidden
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        panel.add(cardDetailsPanel, gbc);

        // Add panel to frame
        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }

    private void handlePaymentMethodSelection() {
        String selectedMethod = (String) paymentMethodComboBox.getSelectedItem();
        cardDetailsPanel.setVisible("Credit Card".equals(selectedMethod) || "Debit Card".equals(selectedMethod));
        SwingUtilities.getWindowAncestor(cardDetailsPanel).pack(); // Adjust the frame size
    }

    private void handlePayment() {
        String studentID = studentIDField.getText();
        String amount = amountField.getText();
        String paymentMethod = (String) paymentMethodComboBox.getSelectedItem();

        // Basic validation
        if (studentID.isEmpty() || amount.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if ("Credit Card".equals(paymentMethod) || "Debit Card".equals(paymentMethod)) {
            String cardNumber = cardNumberField.getText();
            String expiryDate = expiryDateField.getText();
            String cvv = cvvField.getText();

            if (cardNumber.isEmpty() || expiryDate.isEmpty() || cvv.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill in all card details.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        JOptionPane.showMessageDialog(null, "Amount Paid: ₹" + amount, "Payment Successful", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(FeePaymentGUI::new);
    }
}
