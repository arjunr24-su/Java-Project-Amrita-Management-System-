package amrita.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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

       
        JTextField firstNameField = addFormField(panel, "First Name:");
        JTextField middleNameField = addFormField(panel, "Middle Name:");
        JTextField lastNameField = addFormField(panel, "Last Name:");

       
        panel.add(new JLabel("D.O.B:"));
        JSpinner dobSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dobEditor = new JSpinner.DateEditor(dobSpinner, "yyyy/MM/dd");
        dobSpinner.setEditor(dobEditor);
        dobSpinner.setValue(Calendar.getInstance().getTime());
        panel.add(dobSpinner);

        ButtonGroup sexButtonGroup = addRadioButtonGroup(panel, "Sex:", "Male", "Female", "Other");

        JTextField classXMarksField = addFormField(panel, "Class X Marks:");
        JTextField classXIIMarksField = addFormField(panel, "Class XII Marks:");

        JTextField jeeMainsPercentileField = addFormField(panel, "JEE Mains Percentile:");

        JButton studentPhotoButton = addFileChooser(panel, "Student Photo:", "jpg", "png", "gif", "bmp");
        JButton classXMarklistButton = addFileChooser(panel, "Class X Marklist:", "pdf", "docx");
        JButton classXIIMarklistButton = addFileChooser(panel, "Class XII Marklist:", "pdf", "docx");
        JButton jeeMarklistButton = addFileChooser(panel, "JEE Marklist:", "pdf", "docx");
        JButton aadharCardButton = addFileChooser(panel, "Aadhar Card Copy:", "pdf", "jpg", "png");
        JButton dobCertificateButton = addFileChooser(panel, "DOB Certificate Copy:", "pdf", "jpg", "png");

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
                    handleFormSubmission(firstNameField, middleNameField, lastNameField, dobSpinner, sexButtonGroup, classXMarksField, classXIIMarksField, jeeMainsPercentileField, studentPhotoButton, classXMarklistButton, classXIIMarklistButton, jeeMarklistButton, aadharCardButton, dobCertificateButton);
                }
            }
        });
        panel.add(submitButton);
        new Personal();
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

    private JTextField addFormField(JPanel panel, String labelText) {
        panel.add(new JLabel(labelText));
        JTextField textField = new JTextField();
        panel.add(textField);
        return textField;
    }

    private ButtonGroup addRadioButtonGroup(JPanel panel, String labelText, String... options) {
        panel.add(new JLabel(labelText));
        JPanel radioButtonPanel = new JPanel();
        ButtonGroup buttonGroup = new ButtonGroup();
        for (String option : options) {
            JRadioButton radioButton = new JRadioButton(option);
            buttonGroup.add(radioButton);
            radioButtonPanel.add(radioButton);
        }
        panel.add(radioButtonPanel);
        return buttonGroup;
    }

    private JButton addFileChooser(JPanel panel, String labelText, String... fileExtensions) {
        panel.add(new JLabel(labelText));
        JButton uploadButton = new JButton("Upload");
        uploadButton.addActionListener((ActionEvent e) -> {
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Allowed file types", fileExtensions);
            fileChooser.setFileFilter(filter);
            int returnValue = fileChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                JOptionPane.showMessageDialog(Upload.this, "You have selected the file: " + selectedFile.getName());
            }
        });
        panel.add(uploadButton);
        return uploadButton;
    }

    private void handleFormSubmission(JTextField firstNameField, JTextField middleNameField, JTextField lastNameField, JSpinner dobSpinner, ButtonGroup sexButtonGroup, JTextField classXMarksField, JTextField classXIIMarksField, JTextField jeeMainsPercentileField, JButton studentPhotoButton, JButton classXMarklistButton, JButton classXIIMarklistButton, JButton jeeMarklistButton, JButton aadharCardButton, JButton dobCertificateButton) {
        JOptionPane.showMessageDialog(Upload.this, "Form submission successful. Details have been recorded.");
        String firstName = firstNameField.getText();
        String middleName = middleNameField.getText();
        String lastName = lastNameField.getText();
        String dob = dobSpinner.getValue().toString();
        String sex = ((JRadioButton) sexButtonGroup.getSelection()).getText();
        String classXMarks = classXMarksField.getText();
        String classXIIMarks = classXIIMarksField.getText();
        String jeeMainsPercentile = jeeMainsPercentileField.getText();

        String studentPhoto = studentPhotoButton.getText();
        String classXMarklist = classXMarklistButton.getText();
        String classXIIMarklist = classXIIMarklistButton.getText();
        String jeeMarklist = jeeMarklistButton.getText();
        String aadharCard = aadharCardButton.getText();
        String dobCertificate = dobCertificateButton.getText();

        // Insert data into the database
        Database database = new Database();
        database.insertStudentDetails(firstName, middleName, lastName, dob, sex, classXMarks, classXIIMarks, jeeMainsPercentile, studentPhoto, classXMarklist, classXIIMarklist, jeeMarklist, aadharCard, dobCertificate);
    }

    private void resetFormFields() {
        // Reset form fields
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Upload();
            }
        });
    }
}

class Database {
    private static final String URL = "jdbc:mysql://localhost:3306/amrita_management_system";
    private static final String USER = "ARJUN";
    private static final String PASSWORD = "@Arjunr24";

    public void insertStudentDetails(String firstName, String middleName, String lastName, String dob, String sex, String classXMarks, String classXIIMarks, String jeeMainsPercentile, String studentPhoto, String classXMarklist, String classXIIMarklist, String jeeMarklist, String aadharCard, String dobCertificate) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO students (full_name, dob, sex, class_x_marks, class_xii_marks, jee_mains_percentile, student_photo, class_x_marklist, class_xii_marklist, jee_marklist, aadhar_card, dob_certificate) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)")) {

            preparedStatement.setString(1,firstName + " " + middleName + " " + lastName);
            preparedStatement.setString(2, dob);
            preparedStatement.setString(3, sex);
            preparedStatement.setString(4, classXMarks);
            preparedStatement.setString(5, classXIIMarks);
            preparedStatement.setString(6, jeeMainsPercentile);
            preparedStatement.setString(7, studentPhoto);
            preparedStatement.setString(8, classXMarklist);
            preparedStatement.setString(9, classXIIMarklist);
            preparedStatement.setString(10, jeeMarklist);
            preparedStatement.setString(11, aadharCard);
            preparedStatement.setString(12, dobCertificate);

            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
