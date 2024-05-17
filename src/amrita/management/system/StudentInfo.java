package amrita.management.system;
import javax.swing.*;
import java.awt.*;

public class StudentInfo {
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
        for (String label : labels) {
            JTextField textField = new JTextField();
            textField.setEditable(false);  // Make text field uneditable
            panel.add(new JLabel(label));
            panel.add(textField);
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
    }
}

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
