package amrita.management.system;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class StudentInfo {
    static final String DB_URL = "jdbc:mysql://localhost/amritamanagementsystem";
    static final String USER = "ARJUN";
    static final String PASS = "@Arjunr24";

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new StudentInfo();
            }
        });
    }

    public StudentInfo() {
        JFrame frame = new JFrame("User Form");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Add labels and text fields
        String[] labels = {"Full Name", "D.O.B.", "Sex", "Father's Name", "Mother's Name", "Occupation", "Address", "ZIP/Postal Code", "Country", "Phone Number", "Landline Number"};
        JTextField[] textFields = new JTextField[labels.length];
        for (int i = 0; i < labels.length; i++) {
            JTextField textField = new JTextField();
            textField.setEditable(false);  // Make text field uneditable
            panel.add(new JLabel(labels[i]));
            panel.add(textField);
            textFields[i] = textField;
        }

        // Add buttons
        JButton homeButton = new JButton("Home");
        homeButton.addActionListener(e -> new Project());
        panel.add(homeButton);

        JButton editButton = new JButton("Edit");
        editButton.addActionListener(e -> new Upload());
        panel.add(editButton);

        frame.add(panel);
        frame.setVisible(true);

        connectToDatabase(textFields);
    }

    public void connectToDatabase(JTextField[] textFields) {
        Connection conn = null;
        Statement stmt = null;
        try {
            // Register JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM student_info";
            ResultSet rs = stmt.executeQuery(sql);

            // Extract data from result set
            if (rs.next()) {
                // Retrieve by column name
                // Assuming you have columns named 'name', 'dob', 'ex', etc. in your table
                textFields[0].setText(rs.getString("name"));
                textFields[1].setText(rs.getString("dob"));
                textFields[2].setText(rs.getString("sex"));
                textFields[3].setText(rs.getString("father_name"));
                textFields[4].setText(rs.getString("mother_name"));
                textFields[5].setText(rs.getString("occupation"));
                textFields[6].setText(rs.getString("address"));
                textFields[7].setText(rs.getString("zip_code"));
                textFields[8].setText(rs.getString("country"));
                textFields[9].setText(rs.getString("phone_number"));
                textFields[10].setText(rs.getString("landline_number"));
            }

            // Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            // Finally block used to close resources
            try {
                if (stmt!= null)
                    stmt.close();
            } catch (SQLException se2) {
            } // Nothing we can do
            try {
                if (conn!= null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            } // End finally try
        } // End try
        System.out.println("Goodbye!");
    } // End connectToDatabase

    class Project {
        public Project() {
            // Your code here
        }
    }

    class Upload {
        public Upload() {
            // Your code here
        }
    }
} // End StudentInfo
