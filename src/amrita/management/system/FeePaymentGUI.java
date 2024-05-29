package amrita.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FeePaymentGUI {

    private final JTextField studentIDField;
    private final JTextField amountField;
    private JComboBox<String> paymentMethodComboBox;
    private final JButton payButton;

    public FeePaymentGUI() {
        JFrame frame = new JFrame("Fee Payment");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 250);

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
                if (paymentMethodComboBox.getSelectedItem().equals("Credit Card") ||
                        paymentMethodComboBox.getSelectedItem().equals("Debit Card")) {
                    JPanel cardDetailsPanel = new JPanel();
                    cardDetailsPanel.setLayout(new GridBagLayout());
                    JLabel cardNumberLabel = new JLabel("Card Number:");
                    cardNumberLabel.setFont(font);
                    cardNumberLabel.setForeground(new Color(255, 255, 255));
                    GridBagConstraints cardGbc = new GridBagConstraints();
                    cardGbc.insets = new Insets(5, 5, 5, 5);
                    cardGbc.gridx = 0;
                    cardGbc.gridy = 0;
                    cardDetailsPanel.add(cardNumberLabel, cardGbc);
                    JTextField cardNumberField = new JTextField(20);
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
                    JTextField expiryDateField = new JTextField(20);
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
                    JTextField cvvField = new JTextField(20);
                    cvvField.setFont(font);
                    cardGbc.gridx = 1;
                    cardGbc.gridy = 2;
                    cardDetailsPanel.add(cvvField, cardGbc);

                    JOptionPane.showMessageDialog(frame, cardDetailsPanel, "Card Details", JOptionPane.PLAIN_MESSAGE);
                }
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
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.LINE_END;
        panel.add(payButton, gbc);
        payButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String amount = amountField.getText();
                JOptionPane.showMessageDialog(frame, "Amount Paid: " + amount, "Payment Successful", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Add panel to frame
        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(FeePaymentGUI::new);
    }
}
