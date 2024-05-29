package amrita.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Courses {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Courses::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Course Selection");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 250);
        frame.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(51, 153, 255));
        topPanel.setPreferredSize(new Dimension(600, 50));

        JLabel titleLabel = new JLabel("Select Your Course");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(Color.WHITE);
        topPanel.add(titleLabel);

        JPanel centerPanel = new JPanel(new FlowLayout());
        centerPanel.setBackground(Color.WHITE);

        String[] courses = {"CSE", "CSE AI", "CSE CYS", "DS and ML", "EEE", "ECE", "ELC", "ME", "Aerospace", "Chemical", "BioTech"};
        JComboBox<String> courseList = new JComboBox<>(courses);
        courseList.setPreferredSize(new Dimension(200, 30));

        JTextArea selectedCourse = new JTextArea(5, 30);
        selectedCourse.setEditable(false);
        selectedCourse.setFont(new Font("Arial", Font.PLAIN, 14));
        selectedCourse.setLineWrap(true);
        selectedCourse.setWrapStyleWord(true);

        courseList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String course = (String) courseList.getSelectedItem();
                selectedCourse.setText("You selected: " + course);
                // Add your eligibility check logic here
            }
        });

        centerPanel.add(courseList);
        centerPanel.add(selectedCourse);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        bottomPanel.setBackground(Color.WHITE);

        JButton confirmButton = new JButton("Confirm");
        confirmButton.setPreferredSize(new Dimension(100, 40));
        confirmButton.setBackground(new Color(51, 153, 255));
        confirmButton.setForeground(Color.WHITE);
        confirmButton.setFont(new Font("Arial", Font.BOLD, 16));
        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Instantiate the Project class
                new Project();
            }
        });

        bottomPanel.add(confirmButton);

        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(centerPanel, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    private static class Project {
        public Project() {
            // Dummy constructor for the Project class
            System.out.println("Project created!");
        }
    }
}


