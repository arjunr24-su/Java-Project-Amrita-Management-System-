package amrita.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;

public class Personal extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField retypePasswordField;
    private JTextField fatherNameField;
    private JTextField motherNameField;
    private JTextField fatherOccupationField;
    private JTextField motherOccupationField;
    private JTextField addressField;
    private JTextField zipCodeField;
    private JComboBox<String> stateComboBox;
    private JTextField districtField;
    private JTextField phoneNumberField;
    private JTextField landlineNumberField;

    public Personal() {
        setTitle("Registration Form");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(0, 2));

        add(new JLabel("New Username:"));
        usernameField = new JTextField();
        add(usernameField);

        add(new JLabel("New Password:"));
        passwordField = new JPasswordField();
        add(passwordField);

        add(new JLabel("Retype Password:"));
        retypePasswordField = new JPasswordField();
        add(retypePasswordField);

        add(new JLabel("Father's Name:"));
        fatherNameField = new JTextField();
        add(fatherNameField);

        add(new JLabel("Mother's Name:"));
        motherNameField = new JTextField();
        add(motherNameField);

        add(new JLabel("Father's Occupation:"));
        fatherOccupationField = new JTextField();
        add(fatherOccupationField);

        add(new JLabel("Mother's Occupation:"));
        motherOccupationField = new JTextField();
        add(motherOccupationField);

        add(new JLabel("Address:"));
        addressField = new JTextField();
        add(addressField);

        add(new JLabel("Zip Code:"));
        zipCodeField = new JTextField();
        add(zipCodeField);

        add(new JLabel("State:"));
        String[] states = {"Andhra Pradesh", "Arunachal Pradesh", "Assam", "Bihar", "Chhattisgarh", "Goa", "Gujarat", "Haryana", "Himachal Pradesh", "Jharkhand", "Karnataka", "Kerala", "Madhya Pradesh", "Maharashtra", "Manipur", "Meghalaya", "Mizoram", "Nagaland", "Odisha", "Punjab", "Rajasthan", "Sikkim", "Tamil Nadu", "Telangana", "Tripura", "Uttar Pradesh", "Uttarakhand", "West Bengal"};
        stateComboBox = new JComboBox<>(states);
        add(stateComboBox);

        add(new JLabel("District:"));
        districtField = new JTextField();
        add(districtField);

        add(new JLabel("Phone Number:"));
        phoneNumberField = new JTextField();
        add(phoneNumberField);

        add(new JLabel("Landline Number:"));
        landlineNumberField = new JTextField();
        add(landlineNumberField);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Arrays.equals(passwordField.getPassword(), retypePasswordField.getPassword())) {
                    // Get the details from the form fields
                    String username = usernameField.getText();
                    String password = new String(passwordField.getPassword());
                    String fatherName = fatherNameField.getText();
                    String motherName = motherNameField.getText();
                    String fatherOccupation = fatherOccupationField.getText();
                    String motherOccupation = motherOccupationField.getText();
                    String address = addressField.getText();
                    String zipCode = zipCodeField.getText();
                    String state = (String) stateComboBox.getSelectedItem();
                    String district = districtField.getText();
                    String phoneNumber = phoneNumberField.getText();
                    String landlineNumber = landlineNumberField.getText();

                    try {
                        // Get a connection to the database
                        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/amritamanagementsystem", "root", "root");

                        // Create a SQL query
                        String sql = "INSERT INTO student_info (username, password, father_name, mother_name, father_occupation, mother_occupation, address, zip_code, state, district, phone_number, landline_number) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                        // Prepare the statement
                        PreparedStatement statement = conn.prepareStatement(sql);

                        // Bind the details to the SQL query
                        statement.setString(1, username);
                        statement.setString(2, password);
                        statement.setString(3, fatherName);
                        statement.setString(4, motherName);
                        statement.setString(5, fatherOccupation);
                        statement.setString(6, motherOccupation);
                        statement.setString(7, address);
                        statement.setString(8, zipCode);
                        statement.setString(9, state);
                        statement.setString(10, district);
                        statement.setString(11, phoneNumber);
                        statement.setString(12, landlineNumber);

                        // Execute the query
                        int rowsInserted = statement.executeUpdate();
                        if (rowsInserted > 0) {
                            System.out.println("A new user was inserted successfully!");
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }

                    dispose();
                    new Login();
                } else {
                    JOptionPane.showMessageDialog(null, "Passwords do not match!");
                }
            }
        });
        add(submitButton);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Personal::new);
    }
}

