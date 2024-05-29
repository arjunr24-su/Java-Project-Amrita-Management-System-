package amrita.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HostelManagementSystem extends JFrame {

    public HostelManagementSystem() {
        // Initialize the frame and components
        setTitle("Hostel Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Add a menu bar
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        // Add a menu
        JMenu menu = new JMenu("Hostel");
        menuBar.add(menu);

        // Add menu items
        JMenuItem item1 = new JMenuItem("Item 1");
        JMenuItem item2 = new JMenuItem("Item 2");
        menu.add(item1);
        menu.add(item2);

        // Add a button
        JButton button = new JButton("Hostel Button");
        add(button, BorderLayout.CENTER);

        // Add an action listener to the button
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Hostel button clicked!");
            }
        });

        // Make the frame visible
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new HostelManagementSystem());
    }
}
