package amrita.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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
        stateComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String selectedState = (String) e.getItem();
                   
                }
            }
        });
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
