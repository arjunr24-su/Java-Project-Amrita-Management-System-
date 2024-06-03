package amrita.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class Transaction {
    private final String description;
    private final double amount;
    private final double paidAmount;
    private final LocalDateTime dateTime;

    public Transaction(String description, double amount, double paidAmount, LocalDateTime dateTime) {
        this.description = description;
        this.amount = amount;
        this.paidAmount = paidAmount;
        this.dateTime = dateTime;
    }

    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }

    public double getPaidAmount() {
        return paidAmount;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    @Override
    public String toString() {
        return dateTime.toString() + " - " + description + ": ₹" + paidAmount;
    }
}

class DataModel {
    private final ArrayList<Transaction> transactions;
    private final Map<String, Double> feeAmounts;
    private final Map<String, Double> feePaid;

    public DataModel() {
        transactions = new ArrayList<>();
        feeAmounts = new HashMap<>();
        feePaid = new HashMap<>();

        feeAmounts.put("Hostel Fee", 50000.0);
        feeAmounts.put("Mess Fee", 30000.0);
        feeAmounts.put("Caution Deposit", 5000.0);
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public Map<String, Double> getFeeAmounts() {
        return feeAmounts;
    }

    public Map<String, Double> getFeePaid() {
        return feePaid;
    }
}

public class HostelManagementSystem extends JFrame {
    private final DataModel dataModel;
    private final String feeType;
    private final JButton feeButton;

    public HostelManagementSystem(DataModel dataModel, String feeType) {
        this.dataModel = dataModel;
        this.feeType = feeType;

        setTitle("Hostel Management System");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Set to full-screen mode
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Add a background image
        setContentPane(new JLabel(new ImageIcon("path/to/college/background.jpg")));
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setOpaque(false);

        // Add a heading
        JLabel heading = new JLabel("Fee Structure", JLabel.CENTER);
        heading.setFont(new Font("Arial", Font.BOLD, 24));
        heading.setForeground(Color.black);
        heading.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.add(heading, BorderLayout.NORTH);

        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;

        // Fee structure text on the left
        JPanel feeStructurePanel = new JPanel();
        feeStructurePanel.setOpaque(false);
        feeStructurePanel.setLayout(new BoxLayout(feeStructurePanel, BoxLayout.Y_AXIS));
        JLabel feeStructureLabel = new JLabel("<html><h2>" + feeType + " Fee Structure</h2></html>");
        feeStructureLabel.setFont(new Font("Arial", Font.BOLD, 20));
        feeStructureLabel.setForeground(Color.black);
        feeStructurePanel.add(feeStructureLabel);

        double amountDue = dataModel.getFeeAmounts().get(feeType);
        double amountPaid = dataModel.getFeePaid().getOrDefault(feeType, 0.0);
        double balance = amountDue - amountPaid;

        JLabel amountDueLabel = new JLabel("Amount Due: ₹" + amountDue);
        amountDueLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        amountDueLabel.setForeground(Color.black);
        feeStructurePanel.add(amountDueLabel);

        JLabel amountPaidLabel = new JLabel("Amount Paid: ₹" + amountPaid);
        amountPaidLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        amountPaidLabel.setForeground(Color.black);
        feeStructurePanel.add(amountPaidLabel);

        JLabel balanceLabel = new JLabel("Balance: ₹" + balance);
        balanceLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        balanceLabel.setForeground(Color.black);
        feeStructurePanel.add(balanceLabel);

        contentPanel.add(feeStructurePanel, gbc);

        // Payment option buttons on the right
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        feeButton = new JButton(feeType + (balance > 0 ? " (₹" + balance + ")" : " Completed"));
        feeButton.setFont(new Font("Arial", Font.PLAIN, 18));
        feeButton.setEnabled(balance > 0);
        feeButton.addActionListener(new PaymentButtonListener());
        buttonPanel.add(feeButton);

        JButton viewTransactionsButton = new JButton("View Transactions");
        viewTransactionsButton.setFont(new Font("Arial", Font.PLAIN, 18));
        viewTransactionsButton.addActionListener(new ViewTransactionsListener());
        buttonPanel.add(viewTransactionsButton);

        gbc.gridx = 1;
        contentPanel.add(buttonPanel, gbc);

        mainPanel.add(contentPanel, BorderLayout.CENTER);
        add(mainPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private class PaymentButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFrame paymentFrame = new JFrame("Payment");
            paymentFrame.setSize(400, 200);
            paymentFrame.setLayout(new BorderLayout());
            paymentFrame.setLocationRelativeTo(null);

            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(3, 2, 10, 10));
            panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            JLabel amountLabel = new JLabel("Amount Due: ");
            JTextField amountField = new JTextField();
            panel.add(amountLabel);
            panel.add(amountField);

            JButton payButton = new JButton("Pay");
            payButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    double amountDue = dataModel.getFeeAmounts().get(feeType);
                    double amountPaid = dataModel.getFeePaid().getOrDefault(feeType, 0.0);
                    double amount;

                    try {
                        amount = Double.parseDouble(amountField.getText());
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(paymentFrame, "Please enter a valid amount.");
                        return;
                    }

                    double totalPaid = amountPaid + amount;

                    if (totalPaid > amountDue) {
                        JCheckBox confirmCheckBox = new JCheckBox("I confirm that I want to pay more than the required amount");
                        Object[] message = {
                                "You are about to pay more than the amount required.",
                                confirmCheckBox
                        };
                        int option = JOptionPane.showConfirmDialog(null, message, "Confirm Extra Payment", JOptionPane.YES_NO_OPTION);

                        // If the user doesn't confirm, return
                        if (option != JOptionPane.YES_OPTION || !confirmCheckBox.isSelected()) {
                            JOptionPane.showMessageDialog(null, "Payment not submitted. Please adjust the amount.");
                            return;
                        }
                    }

                    // Proceed with the payment
                    dataModel.getFeePaid().put(feeType, totalPaid);
                    Transaction transaction = new Transaction(feeType, amountDue, amount, LocalDateTime.now());
                    dataModel.getTransactions().add(transaction);

                    if (totalPaid >= amountDue) {
                        JOptionPane.showMessageDialog(null, feeType + " completed!");
                        feeButton.setText(feeType + " Completed");
                        feeButton.setEnabled(false);
                        if (totalPaid > amountDue) {
                            double extraAmount = totalPaid - amountDue;
                            JOptionPane.showMessageDialog(null, "Additional amount of ₹" + extraAmount + " will be deducted from the next year " + feeType);
                        }
                    } else {
                        double balance = amountDue - totalPaid;
                        JOptionPane.showMessageDialog(null, "Balance amount for " + feeType + ": ₹" + balance);
                        feeButton.setText(feeType + " (₹" + balance + ")");
                    }

                    paymentFrame.dispose();
                }
            });

            panel.add(new JLabel()); // Empty label for spacing
            panel.add(payButton);

            paymentFrame.add(panel, BorderLayout.CENTER);
            paymentFrame.setVisible(true);
        }
    }

    private class ViewTransactionsListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JTextArea transactionArea = new JTextArea();
            transactionArea.setEditable(false);
            for (Transaction transaction : dataModel.getTransactions()) {
                transactionArea.append(transaction.toString() + "\n");
            }
            JScrollPane scrollPane = new JScrollPane(transactionArea);
            scrollPane.setPreferredSize(new Dimension(400, 300));
            JOptionPane.showMessageDialog(null, scrollPane, "Transaction History", JOptionPane.PLAIN_MESSAGE);
        }
    }
}
