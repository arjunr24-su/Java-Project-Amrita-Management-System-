package amrita.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Project extends JFrame {

    public Project() {
        setSize(1800, 850);

        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icons/amritapuri_campus.jpg"));
        Image image = imageIcon.getImage().getScaledInstance(1280, 720, Image.SCALE_DEFAULT);
        ImageIcon scaledImageIcon = new ImageIcon(image);
        JLabel backgroundImage = new JLabel(scaledImageIcon);
        add(backgroundImage, BorderLayout.CENTER); 

        JMenuBar menuBar = new JMenuBar();

        JMenu studentDetailsMenu = new JMenu("Student Details");
        studentDetailsMenu.addActionListener(new StudentDetailsMenuListener());
        menuBar.add(studentDetailsMenu);

        JMenu courseAllotedMenu = new JMenu("Course Alloted");
        courseAllotedMenu.addActionListener(new CourseAllotedMenuListener());
        menuBar.add(courseAllotedMenu);

        JMenu slabAllotedMenu = new JMenu("Slab Alloted");
        slabAllotedMenu.addActionListener(new SlabAllotedMenuListener());
        menuBar.add(slabAllotedMenu);

        JMenu feeManagementMenu = new JMenu("Fee Management");
        feeManagementMenu.addActionListener(new FeeManagementMenuListener());
        menuBar.add(feeManagementMenu);

        JMenu hostelMenu = new JMenu("Hostel");
        hostelMenu.addActionListener(new HostelMenuListener());
        menuBar.add(hostelMenu);

        setJMenuBar(menuBar);

        setVisible(true);
    }

    private class StudentDetailsMenuListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                new StudentInfo();
                JOptionPane.showMessageDialog(Project.this, "Student Details menu option selected");
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
                JOptionPane.showMessageDialog(Project.this, "Course Alloted menu option selected");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(Project.this, "Error: " + ex.getMessage());
            }
        }
    }

    private class SlabAllotedMenuListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try { 
                JOptionPane.showMessageDialog(Project.this, "Slab Alloted menu option selected");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(Project.this, "Error: " + ex.getMessage());
            }
        }
    }

    private class FeeManagementMenuListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                JOptionPane.showMessageDialog(Project.this, "Fee Management menu option selected");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(Project.this, "Error: " + ex.getMessage());
            }
        }
    }

    private class HostelMenuListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                JOptionPane.showMessageDialog(Project.this, "Hostel menu option selected");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(Project.this, "Error: " + ex.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Project());
    }
}
