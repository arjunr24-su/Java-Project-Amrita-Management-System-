package amrita.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener {
    JButton login, signup, cancel;
    JTextField tfusername;
    JPasswordField tfpassword;

    public Login() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel lblusername = new JLabel("Username");
        lblusername.setBounds(40, 20, 100, 20);
        add(lblusername);

        tfusername = new JTextField();
        tfusername.setBounds(150, 20, 150, 20);
        add(tfusername);

        JLabel lblpassword = new JLabel("Password");
        lblpassword.setBounds(40, 70, 100, 20);
        add(lblpassword);

        tfpassword = new JPasswordField();
        tfpassword.setBounds(150, 70, 150, 20);
        add(tfpassword);

        login = new JButton("Login");
        login.setBounds(40, 140, 120, 30);
        login.addActionListener(this);
        add(login);

        signup = new JButton("Signup");
        signup.setBounds(190, 140, 120, 30);
        signup.addActionListener(this);
        add(signup);

        cancel = new JButton("Cancel");
        cancel.setBounds(40, 180, 120, 30);
        cancel.addActionListener(this);
        add(cancel);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/second.jpg"));
        Image i2 = i1.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(350, 0, 200, 200);
        add(image);

        setLocation(500, 250);
        setSize(600, 300);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == login) {
            // Get the username and password from the text fields
            String username = tfusername.getText();
            String password = new String(tfpassword.getPassword());

            // Initialize database connection variables
            Connection conn = null;
            PreparedStatement statement = null;
            ResultSet result = null;

            try {
                // Load the JDBC driver
                Class.forName("com.mysql.cj.jdbc.Driver");

                // Get a connection to the database
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/amritamanagementsystem", "ARJUN", "@Arjunr24");

                // Create a SQL query
                String sql = "SELECT * FROM student_info WHERE username =? AND password =?";

                // Prepare the statement
                statement = conn.prepareStatement(sql);

                // Bind the username and password to the SQL query
                statement.setString(1, username);
                statement.setString(2, password);

                // Execute the query
                result = statement.executeQuery();

                // If the result set is not empty, the login is successful
                if (result.next()) {
                    System.out.println("Login successful!");
                    new Project();
                    setVisible(false);
                } else {
                    System.out.println("Login failed!");
                    JOptionPane.showMessageDialog(this, "Invalid username or password");
                }
            } catch (ClassNotFoundException | SQLException e) {
                JOptionPane.showMessageDialog(this, "Error connecting to database: " + e.getMessage());
            } finally {
                // Close resources in the finally block to ensure they are always closed
                try {
                    if (result!= null) {
                        result.close();
                    }
                    if (statement!= null) {
                        statement.close();
                    }
                    if (conn!= null) {
                        conn.close();
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } else if (ae.getSource() == cancel) {
            setVisible(false);
        } else if (ae.getSource() == signup) {
            new Upload();
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Login());
    }
}
