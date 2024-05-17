package amrita.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Calendar;
import javax.swing.SpinnerDateModel;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Upload extends JFrame {

    private static final int FRAME_WIDTH = 800;
    private static final int FRAME_HEIGHT = 600;
    private static final int GRID_LAYOUT_COLUMNS = 2;

    private JCheckBox confirmationCheckBox;

    public Upload() {
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, GRID_LAYOUT_COLUMNS));
       
        add(panel);

       
        addFormField(panel, "First Name:");
        addFormField(panel, "Middle Name:");
        addFormField(panel, "Last Name:");

       
        panel.add(new JLabel("D.O.B:"));
        JSpinner dobSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dobEditor = new JSpinner.DateEditor(dobSpinner, "yyyy/MM/dd");
        dobSpinner.setEditor(dobEditor);
        dobSpinner.setValue(Calendar.getInstance().getTime());
        panel.add(dobSpinner);

        addRadioButtonGroup(panel, "Sex:", "Male", "Female", "Other");


        addFormField(panel, "Class X Marks:");
        addFormField(panel, "Class XII Marks:");


        addFormField(panel, "JEE Mains Percentile:");


        addFileChooser(panel, "Student Photo:", "jpg", "png", "gif", "bmp");
        addFileChooser(panel, "Class X Marklist:", "pdf", "docx");
        addFileChooser(panel, "Class XII Marklist:", "pdf", "docx");
        addFileChooser(panel, "JEE Marklist:", "pdf", "docx");
        addFileChooser(panel, "Aadhar Card Copy:", "pdf", "jpg", "png");
        addFileChooser(panel, "DOB Certificate Copy:", "pdf", "jpg", "png");


        confirmationCheckBox = new JCheckBox("I confirm that I have entered the right details and the uploaded documents are original.");
        panel.add(confirmationCheckBox);
        panel.add(new JLabel("")); 


        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!confirmationCheckBox.isSelected()) {
                    JOptionPane.showMessageDialog(Upload.this, "Please confirm that the details are correct and the documents are original.");
                } else {
                    handleFormSubmission();
                }
            }
        });
        panel.add(submitButton);
        panel.setVisible(true);


        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetFormFields();
            }
        });
        panel.add(resetButton);

        setVisible(true);
    }

    private void addFormField(JPanel panel, String labelText) {
        panel.add(new JLabel(labelText));
        panel.add(new JTextField());
    }

    private void addRadioButtonGroup(JPanel panel, String labelText, String... options) {
        panel.add(new JLabel(labelText));
        JPanel radioButtonPanel = new JPanel();
        ButtonGroup buttonGroup = new ButtonGroup();
        for (String option :options) {
            JRadioButton radioButton = new JRadioButton(option);
            buttonGroup.add(radioButton);
            radioButtonPanel.add(radioButton);
            
        }
        panel.add(radioButtonPanel);
      
    }

    private void addFileChooser(JPanel panel, String labelText, String... fileExtensions) {
        panel.add(new JLabel(labelText));
        JButton uploadButton = new JButton("Upload");
        uploadButton.addActionListener((ActionEvent e) -> {
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Allowed file types", fileExtensions);
            fileChooser.setFileFilter(filter);
            int returnValue = fileChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                uploadButton.setIcon(new ImageIcon(new ImageIcon(selectedFile.getAbsolutePath()).getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
            }
        });
        panel.add(uploadButton);
    }

    private void handleFormSubmission() {
     
        new Personal();
         setVisible(false);
    }

    private void resetFormFields() {

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Upload());
    }
}
