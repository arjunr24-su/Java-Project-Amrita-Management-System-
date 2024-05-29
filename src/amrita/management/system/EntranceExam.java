package amrita.management.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EntranceExam extends JFrame {
    private JComboBox<String> courseComboBox;
    private JComboBox<String> examComboBox;
    private JTextField marksField;
    private JTextField nameField;
    private JButton submitButton;
    private JLabel resultLabel;

    private static final int MAX_SLAB1_COUNT = 5;
    private Map<String, Integer> slab1Counts;
    private List<Student> students;

    public EntranceExam() {
        setTitle("Entrance Exam Scoring");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        slab1Counts = new HashMap<>();
        slab1Counts.put("CSE", 0);
        slab1Counts.put("MEE", 0);
        slab1Counts.put("ECE", 0);
        slab1Counts.put("ELC", 0);

        students = new ArrayList<>();

        // Main panel with YouTube, Spotify, and Instagram colors
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.BLACK);

        // YouTube-themed title panel
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(179, 81, 245));
        JLabel titleLabel = new JLabel("Entrance Exam Scoring");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(Color.WHITE);
        titlePanel.add(titleLabel);
        mainPanel.add(titlePanel, BorderLayout.NORTH);

        // Form panel
        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        formPanel.setBackground(new Color(30, 30, 30));

        JLabel nameLabel = new JLabel("Enter Name:");
        nameLabel.setForeground(Color.WHITE);
        nameField = new JTextField();
        nameField.setBackground(Color.BLACK);
        nameField.setForeground(Color.WHITE);

        JLabel courseLabel = new JLabel("Select Course:");
        courseLabel.setForeground(Color.WHITE);
        courseComboBox = new JComboBox<>(new String[]{"CSE", "MEE", "ECE", "ELC"});
        courseComboBox.setBackground(Color.BLACK);
        courseComboBox.setForeground(Color.WHITE);
        courseComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                examComboBox.setEnabled(true);
            }
        });

        JLabel examLabel = new JLabel("Preferred Exam:");
        examLabel.setForeground(Color.WHITE);
        examComboBox = new JComboBox<>(new String[]{"AEEE", "JEE"});
        examComboBox.setEnabled(false);
        examComboBox.setBackground(Color.BLACK);
        examComboBox.setForeground(Color.WHITE);
        examComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                marksField.setEnabled(true);
            }
        });

        JLabel marksLabel = new JLabel("Enter Marks:");
        marksLabel.setForeground(Color.WHITE);
        marksField = new JTextField();
        marksField.setEnabled(false);
        marksField.setBackground(Color.BLACK);
        marksField.setForeground(Color.WHITE);

        submitButton = new JButton("Submit");
        submitButton.setBackground(new Color(129, 165, 144)); // Spotify green
        submitButton.setForeground(Color.WHITE);
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                evaluateMarks();
            }
        });

        resultLabel = new JLabel("");
        resultLabel.setFont(new Font("Arial", Font.BOLD, 14));
        resultLabel.setForeground(new Color(138, 58, 185)); // Instagram purple

        formPanel.add(nameLabel);
        formPanel.add(nameField);
        formPanel.add(courseLabel);
        formPanel.add(courseComboBox);
        formPanel.add(examLabel);
        formPanel.add(examComboBox);
        formPanel.add(marksLabel);
        formPanel.add(marksField);
        formPanel.add(new JLabel(""));
        formPanel.add(submitButton);

        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(resultLabel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private void evaluateMarks() {
        String studentName = nameField.getText();
        String selectedCourse = (String) courseComboBox.getSelectedItem();
        String selectedExam = (String) examComboBox.getSelectedItem();
        int marks;

        try {
            marks = Integer.parseInt(marksField.getText());
        } catch (NumberFormatException ex) {
            resultLabel.setText("Please enter valid marks.");
            return;
        }

        if (studentName.isEmpty()) {
            resultLabel.setText("Please enter the student's name.");
            return;
        }

        String slab = "No slab allotted";
        int slab1Count = slab1Counts.getOrDefault(selectedCourse, 0);

        if (selectedCourse.equals("CSE")) {
            if (slab1Count < MAX_SLAB1_COUNT) {
                if (marks >= 95 && marks <= 100) {
                    slab = "Slab 1";
                    slab1Counts.put(selectedCourse, slab1Count + 1);
                }
            }
            if (slab.equals("No slab allotted")) {
                if (marks >= 90 && marks < 95) {
                    slab = "Slab 2";
                } else if (marks >= 83 && marks < 90) {
                    slab = "Slab 3";
                }
            }
        } else if (selectedCourse.equals("MEE")) {
            if (slab1Count < MAX_SLAB1_COUNT) {
                if (marks >= 90) {
                    slab = "Slab 1";
                    slab1Counts.put(selectedCourse, slab1Count + 1);
                }
            }
            if (slab.equals("No slab allotted")) {
                if (marks >= 80) {
                    slab = "Slab 2";
                } else if (marks >= 75) {
                    slab = "Slab 3";
                }
            }
        } else if (selectedCourse.equals("ECE")) {
            if (slab1Count < MAX_SLAB1_COUNT) {
                if (marks >= 90) {
                    slab = "Slab 1";
                    slab1Counts.put(selectedCourse, slab1Count + 1);
                }
            }
            if (slab.equals("SSNo slab allotted")) {
                if (marks >= 80) {
                    slab = "Slab 2";
                } else if (marks >= 75) {
                    slab = "Slab 3";
                }
            }
        } else if (selectedCourse.equals("ELC")) {
            if (slab1Count < MAX_SLAB1_COUNT) {
                if (marks >= 85) {
                    slab = "Slab 1";
                    slab1Counts.put(selectedCourse, slab1Count + 1);
                }
            }
            if (slab.equals("No slab allotted")) {
                if (marks >= 77) {
                    slab = "Slab 2";
                } else if (marks >= 70) {
                    slab = "Slab 3";
                }
            }
        }

        if (slab.equals("No slab allotted") && slab1Count >= MAX_SLAB1_COUNT) {
            for (String course : slab1Counts.keySet()) {
                if (!course.equals(selectedCourse) && slab1Counts.get(course) < MAX_SLAB1_COUNT) {
                    resultLabel.setText("Slab 1 for " + selectedCourse + " is full. Consider " + course + " for Slab 1 availability.");
                    return;
                }
            }
        }

        students.add(new Student(studentName, selectedCourse, selectedExam, marks, slab));

        String result = "Course: " + selectedCourse + "\nExam: " + selectedExam + "\nMarks: " + marks + "\nAllocated: " + slab;

        // Make the result dialog larger
        JTextArea resultText = new JTextArea(result);
        resultText.setFont(new Font("Arial", Font.PLAIN, 14));
        resultText.setEditable(false);
        resultText.setBackground(new Color(245, 245, 245));
        resultText.setMargin(new Insets(10, 10, 10, 10));

        JScrollPane scrollPane = new JScrollPane(resultText);
        scrollPane.setPreferredSize(new Dimension(400, 200));

        JOptionPane.showMessageDialog(this, scrollPane, "Result", JOptionPane.INFORMATION_MESSAGE);
        resultLabel.setText("Allocated: " + slab);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new EntranceExam().setVisible(true);
            }
        });
    }

    private static class Student {
        String name;
        String course;
        String exam;
        int marks;
        String slab;

        Student(String name, String course, String exam, int marks, String slab) {
            this.name = name;
            this.course = course;
            this.exam = exam;
            this.marks = marks;
            this.slab = slab;
        }
    }
}
