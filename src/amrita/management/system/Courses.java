package amrita.management.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Courses {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Course Selection");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,200);
        frame.setLayout(new FlowLayout());

        String[] courses = {"CSE", "CSE AI", "CSE CYS", "DS and ML", "EEE", "ECE", "ELC", "ME", "Aerospace", "Chemical", "BioTech"};
        JComboBox<String> courseList = new JComboBox<>(courses);

        JTextArea selectedCourse = new JTextArea(10, 30);
        selectedCourse.setEditable(false);

        courseList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String course = (String) courseList.getSelectedItem();
                selectedCourse.setText("You selected: " + course);
                // Add your eligibility check logic here
            }
        });

        JButton confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Project();
            }
        });

        frame.getContentPane().setBackground(Color.LIGHT_GRAY);
        frame.add(courseList);
        frame.add(selectedCourse);
        frame.add(confirmButton);
        frame.setVisible(true);
    }
}
