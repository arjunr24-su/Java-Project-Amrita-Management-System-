package amrita.management.system;
import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class StudentInfo {
    static final String DB_URL = "jdbc:mysql://localhost/amritamanagementsystem";
    static final String USER = "root";
    static final String PASS = "root";

    public static void main(String[] args) {
        SwingUtilities.invokeLater(StudentInfo::new);
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
            panel.add(new JLabel(labels[i]));
            panel.add(textField);
            textFields[i] = textField;
        }

        // Add buttons
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> {
            if (saveToDatabase(textFields))
                JOptionPane.showMessageDialog(frame, "Data saved successfully.");
            else
                JOptionPane.showMessageDialog(frame, "Failed to save data.");
        });
        panel.add(saveButton);

        JButton editButton = new JButton("Edit");
        editButton.addActionListener(e -> {
            for (JTextField textField : textFields) {
                textField.setEditable(true);
            }
        });
        panel.add(editButton);

        frame.add(panel);
        frame.setVisible(true);

        fetchDataFromDatabase(textFields);
    }

    public boolean saveToDatabase(JTextField[] textFields) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement preparedStatement = conn.prepareStatement("UPDATE student_info SET name=?, dob=?, sex=?, father_name=?, mother_name=?, occupation=?, address=?, zip_code=?, country=?, phone_number=?, landline_number=?")) {

            for (int i = 0; i < textFields.length; i++) {
                preparedStatement.setString(i + 1, textFields[i].getText());
            }

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public void fetchDataFromDatabase(JTextField[] textFields) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement()) {

            String sql = "SELECT * FROM student_bio";
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
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

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
