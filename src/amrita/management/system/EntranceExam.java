package amrita.management.system;
import javax.swing.*;
    import java.awt.*;
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;
    import java.util.*;

    public class EntranceExam extends JFrame {
        private JComboBox<String> courseComboBox;
        private JComboBox<String> examComboBox;
        private JTextField marksField;
        private JTextField nameField;
        private JButton submitButton;
        private JLabel resultLabel;

        private static final int MAX_SLAB1_COUNT = 5;
        private static Map<String, Integer> slab1Counts;
        private Set<String> usedNames;
        private java.util.List<Student> students;

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

            usedNames = new HashSet<>();

            students = new ArrayList<>();

            // Main panel with beige background
            JPanel mainPanel = new JPanel(new BorderLayout());
            mainPanel.setBackground(new Color(245, 245, 220));  // Beige color

            // Title panel with a bold font
            JPanel titlePanel = new JPanel();
            titlePanel.setBackground(new Color(222, 169, 119));
            JLabel titleLabel = new JLabel("Entrance Exam Scoring");
            titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
            titleLabel.setForeground(Color.WHITE);
            titlePanel.add(titleLabel);
            mainPanel.add(titlePanel, BorderLayout.NORTH);

            // Form panel with grid layout
            JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));
            formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            formPanel.setBackground(new Color(65, 30, 30));

            JLabel nameLabel = new JLabel("Enter Name:");
            nameLabel.setForeground(Color.WHITE);
            nameField = new JTextField();
            nameField.setBackground(Color.WHITE);
            nameField.setForeground(Color.WHITE);

            JLabel courseLabel = new JLabel("Select Course:");
            courseLabel.setForeground(Color.WHITE);
            courseComboBox = new JComboBox<>(new String[]{"CSE", "MEE", "ECE", "ELC", "Biotechnology", "Chemistry", "DSML"});
            courseComboBox.setBackground(Color.WHITE);
            courseComboBox.setForeground(Color.WHITE);
            courseComboBox.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    examComboBox.setEnabled(true);
                }
            });

            JLabel examLabel = new JLabel("Preferred Exam:");
            examLabel.setForeground(Color.WHITE);
            examComboBox = new JComboBox<>(new String[]{"AEEE"});
            examComboBox.setEnabled(false);
            examComboBox.setBackground(Color.WHITE);
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
            marksField.setBackground(Color.WHITE);
            marksField.setForeground(Color.WHITE);

            submitButton = new JButton("Submit");
            submitButton.setBackground(new Color(213, 203, 203)); // Custom button color
            submitButton.setForeground(Color.WHITE);
            submitButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    evaluateMarks();
                }
            });

            resultLabel = new JLabel("");
            resultLabel.setFont(new Font("Arial", Font.BOLD, 14));
            resultLabel.setForeground(new Color(138, 58, 185)); // Custom label color

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

            // Beige background for the main content area
            JPanel contentPanel = new JPanel(new BorderLayout());
            contentPanel.setBackground(new Color(245, 245, 220));  // Beige color
            contentPanel.add(mainPanel, BorderLayout.CENTER);

            add(contentPanel);

            // Center the window
            setLocationRelativeTo(null);
        }

        private void evaluateMarks() {
            String studentName = nameField.getText();
            String lowercaseName = studentName.toLowerCase();

            if (usedNames.contains(lowercaseName)) {
                resultLabel.setText("Name already used. Please enter a different name.");
                return;
            }

            usedNames.add(lowercaseName);

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

            Student student = new Student(studentName, selectedCourse, selectedExam, marks, "No slab allotted");

            Exam exam;
            if ("AEEE".equals(selectedExam)) {
                exam = new AEEEExam();
            } else {
                exam = new JEEExam();
            }

            exam.evaluate(student);

            if ("No slab allotted".equals(student.getSlab()) && slab1Counts.get(selectedCourse) >= MAX_SLAB1_COUNT) {
                for (String course : slab1Counts.keySet()) {
                    if (!course.equals(selectedCourse) && slab1Counts.get(course) < MAX_SLAB1_COUNT) {
                        resultLabel.setText("Slab 1 for " + selectedCourse + " is full. Consider " + course + " for Slab 1 availability.");
                        return;
                    }
                }
            }

            students.add(student);

            String result = "Course: " + student.getCourse() + "\nExam: " + student.getExam() + "\nMarks: " + student.getMarks() + "\nAllocated: " + student.getSlab();

            // Make the result dialog larger
            JTextArea resultText = new JTextArea(result);
            resultText.setFont(new Font("Arial", Font.PLAIN, 14));
            resultText.setEditable(false);
            resultText.setBackground(new Color(245, 245, 245));
            resultText.setMargin(new Insets(10, 10, 10, 10));

            JScrollPane scrollPane = new JScrollPane(resultText);
            scrollPane.setPreferredSize(new Dimension(400, 200));

            JOptionPane.showMessageDialog(this, scrollPane, "Result", JOptionPane.INFORMATION_MESSAGE);
            resultLabel.setText("Allocated: " + student.getSlab());
        }

        public static void main(String[] args) {
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    new EntranceExam().setVisible(true);
                }
            });
        }

        public interface Exam {
            void evaluate(Student student);
        }
        public static class AEEEExam implements Exam {
            public void evaluate(Student student) {
                int marks = student.getMarks();
                String slab = "No slab allotted";
                int slab1Count = slab1Counts.getOrDefault(student.getCourse(), 0);

                if (student.getCourse().equals("CSE")) {
                    if (slab1Count < MAX_SLAB1_COUNT) {
                        if (marks >= 95 && marks <= 100) {
                            slab = "Slab 1";
                            slab1Counts.put(student.getCourse(), slab1Count + 1);
                        }
                    }
                    if (slab.equals("No slab allotted")) {
                        if (marks >= 90 && marks < 95) {
                            slab = "Slab 2";
                        } else if (marks >= 83 && marks < 90) {
                            slab = "Slab 3";
                        }
                    }
                } else if (student.getCourse().equals("MEE") || student.getCourse().equals("ECE")) {
                    if (slab1Count < MAX_SLAB1_COUNT) {
                        if (marks >= 90) {
                            slab = "Slab 1";
                            slab1Counts.put(student.getCourse(), slab1Count + 1);
                        }
                    }
                    if (slab.equals("No slab allotted")) {
                        if (marks >= 80) {
                            slab = "Slab 2";
                        } else if (marks >= 75) {
                            slab = "Slab 3";
                        }
                    }
                } else if (student.getCourse().equals("ELC")) {
                    if (slab1Count < MAX_SLAB1_COUNT) {
                        if (marks >= 85) {
                            slab = "Slab 1";
                            slab1Counts.put(student.getCourse(), slab1Count + 1);
                        }
                    }
                    if (slab.equals("No slab allotted")) {
                        if (marks >= 77) {
                            slab = "Slab 2";
                        } else if (marks >= 70) {
                            slab = "Slab 3";
                        }
                    }
                } else if (student.getCourse().equals("Biotechnology")) {
                    if (slab1Count < MAX_SLAB1_COUNT) {
                        if (marks >= 88) {
                            slab = "Slab 1";
                            slab1Counts.put(student.getCourse(), slab1Count + 1);
                        }
                    }
                    if (slab.equals("No slab allotted")) {
                        if (marks >= 80) {
                            slab = "Slab 2";
                        } else if (marks >= 73) {
                            slab = "Slab 3";
                        }
                    }
                } else if (student.getCourse().equals("Chemistry")) {
                    if (slab1Count < MAX_SLAB1_COUNT) {
                        if (marks >= 85) {
                            slab = "Slab 1";
                            slab1Counts.put(student.getCourse(), slab1Count + 1);
                        }
                    }
                    else if (slab.equals("No slab allotted")) {
                        if (marks >= 77) {
                            slab = "Slab 2";
                        } else if (marks >= 70) {
                            slab = "Slab 3";
                        }
                    }
                } else if (student.getCourse().equals("DSML")) {
                    if (slab1Count < MAX_SLAB1_COUNT) {
                        if (marks >= 92) {
                            slab = "Slab 1";
                            slab1Counts.put(student.getCourse(), slab1Count + 1);
                        }
                    }
                    if (slab.equals("No slab allotted")) {
                        if (marks >= 85) {
                            slab = "Slab 2";
                        } else if (marks >= 78) {
                            slab = "Slab 3";
                        }
                    }
                }

                student.setSlab(slab);
            }
        }

        public static class JEEExam implements Exam {
            public void evaluate(Student student) {
                int marks = student.getMarks();
                String slab = "No slab allotted";
                int slab1Count = slab1Counts.getOrDefault(student.getCourse(), 0);

                if (student.getCourse().equals("CSE")) {
                    if (slab1Count < MAX_SLAB1_COUNT) {
                        if (marks >= 95 && marks <= 100) {
                            slab = "Slab 1";
                            slab1Counts.put(student.getCourse(), slab1Count + 1);
                        }
                    }
                    if (slab.equals("No slab allotted")) {
                        if (marks >= 90 && marks < 95) {
                            slab = "Slab 2";
                        } else if (marks >= 83 && marks < 90) {
                            slab = "Slab 3";
                        }
                    }
                } else if (student.getCourse().equals("MEE") || student.getCourse().equals("ECE")) {
                    if (slab1Count < MAX_SLAB1_COUNT) {
                        if (marks >= 90) {
                            slab = "Slab 1";
                            slab1Counts.put(student.getCourse(), slab1Count + 1);
                        }
                    }
                    if (slab.equals("No slab allotted")) {
                        if (marks >= 80) {
                            slab = "Slab 2";
                        } else if (marks >= 75) {
                            slab = "Slab 3";
                        }
                    }
                } else if (student.getCourse().equals("ELC")) {
                    if (slab1Count < MAX_SLAB1_COUNT) {
                        if (marks >= 85) {
                            slab = "Slab 1";
                            slab1Counts.put(student.getCourse(), slab1Count + 1);
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

                student.setSlab(slab);
            }
        }

        public static class Student {
            private String name;
            private String course;
            private String exam;
            private int marks;
            private String slab;

            public Student(String name, String course, String exam, int marks, String slab) {
                this.name = name;
                this.course = course;
                this.exam = exam;
                this.marks = marks;
                this.slab = slab;
            }

            public String getName() {
                return name;
            }

            public String getCourse() {
                return course;
            }

            public String getExam() {
                return exam;
            }

            public int getMarks() {
                return marks;
            }

            public String getSlab() {
                return slab;
            }

            public void setSlab(String slab) {
                this.slab = slab;
            }
        }
    }
