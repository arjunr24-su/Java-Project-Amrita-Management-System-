    package amrita.management.system;

    import javax.swing.*;
    import java.awt.*;
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;

    public class Project extends JFrame {

        private DataModel dataModel;  // Add a DataModel instance

        public Project() {
            // Initialize the data model
            dataModel = new DataModel();  // Initialize the data model here

            setSize(1800, 850);

            ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icons/amritapuri_campus.jpg"));
            Image image = imageIcon.getImage().getScaledInstance(1280, 720, Image.SCALE_DEFAULT);
            ImageIcon scaledImageIcon = new ImageIcon(image);
            JLabel backgroundImage = new JLabel(scaledImageIcon);
            backgroundImage.setLayout(new BorderLayout()); // Add this line
            add(backgroundImage, BorderLayout.CENTER);

            JMenuBar menuBar = new JMenuBar();

            JMenu studentDetailsMenu = new JMenu("Student Details");
            JMenuItem studentDetailsMenuItem = new JMenuItem("Student Details");
            studentDetailsMenuItem.addActionListener(new StudentDetailsMenuListener());
            studentDetailsMenu.add(studentDetailsMenuItem);
            menuBar.add(studentDetailsMenu);

            JMenu courseAllotedMenu = new JMenu("Course Alloted");
            JMenuItem courseAllotedMenuItem = new JMenuItem("Course Alloted");
            courseAllotedMenuItem.addActionListener(new CourseAllotedMenuListener());
            courseAllotedMenu.add(courseAllotedMenuItem);
            menuBar.add(courseAllotedMenu);

            JMenu slabAllotedMenu = new JMenu("Slab Alloted");
            JMenuItem slabAllotedMenuItem = new JMenuItem("Slab Alloted");
            slabAllotedMenuItem.addActionListener(new SlabAllotedMenuListener());
            slabAllotedMenu.add(slabAllotedMenuItem);
            menuBar.add(slabAllotedMenu);

            JMenu feeManagementMenu = new JMenu("Fee Management");
            JMenuItem feeManagementMenuItem = new JMenuItem("Fee Management");
            feeManagementMenuItem.addActionListener(new FeeManagementMenuListener());
            feeManagementMenu.add(feeManagementMenuItem);
            menuBar.add(feeManagementMenu);

            JMenu hostelMenu = new JMenu("Hostel");
            JMenuItem hostelMenuItem = new JMenuItem("Hostel");
            hostelMenuItem.addActionListener(new HostelMenuListener());
            hostelMenu.add(hostelMenuItem);
            menuBar.add(hostelMenu);

            setJMenuBar(menuBar);

            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Add this line
            setVisible(true);
        }

        private class StudentDetailsMenuListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new StudentInfo();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(Project.this, "Error: " + ex.getMessage());
                }
            }
        }

        private class CourseAllotedMenuListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new Courses();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(Project.this, "Error: " + ex.getMessage());
                }
            }
        }

        private class SlabAllotedMenuListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new EntranceExam();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(Project.this, "Error: " + ex.getMessage());
                }
            }
        }

        private class FeeManagementMenuListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new FeePaymentGUI();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(Project.this, "Error: " + ex.getMessage());
                }
            }
        }

        private class HostelMenuListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new HostelManagementSystem(dataModel, "Hostel Fee");  // Pass the DataModel instance
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(Project.this, "Error: " + ex.getMessage());
                }
            }
        }

        public static void main(String[] args) {
            SwingUtilities.invokeLater(() -> new Project());
        }
    }
