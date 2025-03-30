
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class HostelManagementSystem extends JFrame {
    private Connection conn;

    public HostelManagementSystem() {
        // Setup database connection
        setupDatabaseConnection();

        // Frame settings
        setTitle("HOSTEL MANAGEMENT SYSTEM");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(0, 150, 136));
        
        // ImageIcon icon = new ImageIcon("C:\\Users\\Madhav\\OneDrive\\Documents\\Attachments\\Desktop\\HostelManagement\\src\\main\\background.png");  // Replace with your image path
        // JLabel imageLabel = new JLabel(icon);
        // headerPanel.add(imageLabel, BorderLayout.WEST);
        
        JLabel titleLabel = new JLabel("HOSTEL MANAGEMENT SYSTEM");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel, BorderLayout.CENTER);
        // Left Panel with buttons
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(new Color(0, 150, 136));
        leftPanel.setLayout(new GridLayout(8, 1, 10, 10));
        leftPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String[] buttonLabels = {
            "Add Student", "Add New Room", "In And Outtime", "Visitor",
            "View Information", "Leave Application", "EXIT"
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.setFont(new Font("Arial", Font.BOLD, 14));
            button.addActionListener(new ButtonClickListener(label));
            leftPanel.add(button);
        }
        JPanel centerPanel = new JPanel(new BorderLayout());
        ImageIcon centerImageIcon = new ImageIcon("C:\\Users\\Madhav\\Downloads\\WELCOME TO OUR HOSTEL MANAGEMENT SYSTEM (1).png"); // Use the correct path for the uploaded image
        JLabel centerImageLabel = new JLabel(centerImageIcon);
        centerImageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        centerPanel.add(centerImageLabel, BorderLayout.CENTER);
        // Adding components to Frame
        add(headerPanel, BorderLayout.NORTH);
        add(leftPanel, BorderLayout.WEST);
        add(centerPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    // Setup JDBC connection
    private void setupDatabaseConnection() {
        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
    
            // Establish connection
            conn = DriverManager.getConnection("jdbc:mysql://localhost:33060/hostel_management", "root", "root");
            System.out.println("Database connected successfully!");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "MySQL JDBC Driver not found!", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database connection failed!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    

    // ActionListener for each button
    private class ButtonClickListener implements ActionListener {
        private String label;

        public ButtonClickListener(String label) {
            this.label = label;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            switch (label) {
                case "Add Student":
                    handleAddStudent();
                    break;

                case "Add New Room":
                    handleAddRoom();
                    break;

                case "In And Outtime":
                    handleInOutTime();
                    break;

                case "Visitor":
                    handleVisitor();
                    break;

                case "View Information":
                    handleViewInformation();
                    break;

                case "Leave Application":
                    handleLeaveApplication();
                    break;

                case "EXIT":
                    System.exit(0);
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "No action defined for this button.");
                    break;
            }
        }
    }

    // Methods for handling button actions and database interactions
    private void handleAddStudent() {
        JTextField[] fields = new JTextField[10];
        String[] labels = {"First Name", "Last Name", "Father Name", "Mother Name", "DOB (Y-M-D)", "Contact", "Email", "Address", "Vehicle", "Work Place/College"};
    
        JPanel panel = new JPanel(new GridLayout(11, 2));
        for (int i = 0; i < labels.length; i++) {
            panel.add(new JLabel(labels[i]));
            fields[i] = new JTextField(15);
            panel.add(fields[i]);
        }
    
        // Gender selection
        JRadioButton male = new JRadioButton("Male");
        JRadioButton female = new JRadioButton("Female");
        JRadioButton other = new JRadioButton("Other");
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(male);
        genderGroup.add(female);
        genderGroup.add(other);
    
        JPanel genderPanel = new JPanel();
        genderPanel.add(new JLabel("Gender:"));
        genderPanel.add(male);
        genderPanel.add(female);
        genderPanel.add(other);
    
        panel.add(genderPanel);
    
        int result = JOptionPane.showConfirmDialog(null, panel, "Add Student", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String gender = male.isSelected() ? "Male" : female.isSelected() ? "Female" : "Other";
            addStudentToDatabase(fields, gender);
        }
    }
    

    private void addStudentToDatabase(JTextField[] fields, String gender) {
        String query = "INSERT INTO students (first_name, last_name, father_name, mother_name, dob, contact, email, address, vehicle, workplace, gender) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            // Validate if all fields are filled out
            for (int i = 0; i < fields.length; i++) {
                if (fields[i].getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please fill out all fields.");
                    return;  // Stop execution if any field is empty
                }
                stmt.setString(i + 1, fields[i].getText());
            }
            stmt.setString(11, gender); // Set gender
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Student added successfully!");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error adding student to database.");
        }
    }

// Method to handle adding a new room
private void handleAddRoom() {
    JTextField roomNumberField = new JTextField(10);
    JTextField bedCountField = new JTextField(10);
    
    JPanel panel = new JPanel(new GridLayout(3, 2));
    panel.add(new JLabel("Room Number:"));
    panel.add(roomNumberField);
    panel.add(new JLabel("Number of Beds:"));
    panel.add(bedCountField);

    int result = JOptionPane.showConfirmDialog(null, panel, "Add New Room", JOptionPane.OK_CANCEL_OPTION);
    if (result == JOptionPane.OK_OPTION) {
        addRoomToDatabase(roomNumberField.getText(), bedCountField.getText());
    }
}

private void addRoomToDatabase(String roomNumber, String bedCount) {
    String query = "INSERT INTO rooms (room_no, beds) VALUES (?, ?)";
    try (PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setString(1, roomNumber);
        stmt.setInt(2, Integer.parseInt(bedCount));
        stmt.executeUpdate();
        JOptionPane.showMessageDialog(this, "Room added successfully!");
    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error adding room to database.");
    }
}

// Method to handle In and Out time recording
private void handleInOutTime() {
    JTextField studentIdField = new JTextField(10);
    JComboBox<String> timeTypeCombo = new JComboBox<>(new String[]{"In", "Out"});
    JTextField timeField = new JTextField(10);

    JPanel panel = new JPanel(new GridLayout(4, 2));
    panel.add(new JLabel("Student ID:"));
    panel.add(studentIdField);
    panel.add(new JLabel("Time Type:"));
    panel.add(timeTypeCombo);
    panel.add(new JLabel("Time (HH:MM):"));
    panel.add(timeField);

    int result = JOptionPane.showConfirmDialog(null, panel, "Record In/Out Time", JOptionPane.OK_CANCEL_OPTION);
    if (result == JOptionPane.OK_OPTION) {
        recordInOutTime(studentIdField.getText(), timeTypeCombo.getSelectedItem().toString(), timeField.getText());
    }
}

private void recordInOutTime(String studentId, String timeType, String time) {
    String query = "INSERT INTO in_out_time (student_id, time_type, time) VALUES (?, ?, ?)";
    try (PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setString(1, studentId);
        stmt.setString(2, timeType);
        stmt.setString(3, time);
        stmt.executeUpdate();
        JOptionPane.showMessageDialog(this, "In/Out time recorded successfully!");
    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error recording in/out time.");
    }
}

// Method to handle adding a visitor
private void handleVisitor() {
    JTextField visitorNameField = new JTextField(10);
    JTextField purposeField = new JTextField(10);
    JTextField studentIdField = new JTextField(10);

    JPanel panel = new JPanel(new GridLayout(4, 2));
    panel.add(new JLabel("Visitor Name:"));
    panel.add(visitorNameField);
    panel.add(new JLabel("Purpose of Visit:"));
    panel.add(purposeField);
    panel.add(new JLabel("Student ID (if visiting a student):"));
    panel.add(studentIdField);

    int result = JOptionPane.showConfirmDialog(null, panel, "Add Visitor", JOptionPane.OK_CANCEL_OPTION);
    if (result == JOptionPane.OK_OPTION) {
        addVisitorToDatabase(visitorNameField.getText(), purposeField.getText(), studentIdField.getText());
    }
}

private void addVisitorToDatabase(String visitorName, String purpose, String studentId) {
    String query = "INSERT INTO visitors (visitor_name, purpose, student_id) VALUES (?, ?, ?)";
    try (PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setString(1, visitorName);
        stmt.setString(2, purpose);
        stmt.setString(3, studentId.isEmpty() ? null : studentId);
        stmt.executeUpdate();
        JOptionPane.showMessageDialog(this, "Visitor added successfully!");
    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error adding visitor to database.");
    }
}

// Method to view information
private void handleViewInformation() {
    // Implement code here to retrieve and display information from the database
    // For simplicity, let's display a message for now
    JOptionPane.showMessageDialog(this, "View information functionality is currently under construction.");
}

// Method to handle leave application
private void handleLeaveApplication() {
    JTextField studentIdField = new JTextField(10);
    JTextField startDateField = new JTextField(10);
    JTextField endDateField = new JTextField(10);

    JPanel panel = new JPanel(new GridLayout(4, 2));
    panel.add(new JLabel("Student ID:"));
    panel.add(studentIdField);
    panel.add(new JLabel("Leave Start Date (YYYY-MM-DD):"));
    panel.add(startDateField);
    panel.add(new JLabel("Leave End Date (YYYY-MM-DD):"));
    panel.add(endDateField);

    int result = JOptionPane.showConfirmDialog(null, panel, "Leave Application", JOptionPane.OK_CANCEL_OPTION);
    if (result == JOptionPane.OK_OPTION) {
        addLeaveApplicationToDatabase(studentIdField.getText(), startDateField.getText(), endDateField.getText());
    }
}

private void addLeaveApplicationToDatabase(String student_id, String start_date, String end_date) {
    String query = "INSERT INTO leave_applications (student_id, start_date, end_date) VALUES (?, ?, ?)";
    try (PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setString(1, student_id);
        stmt.setString(2, start_date);
        stmt.setString(3, end_date);
        stmt.executeUpdate();
        JOptionPane.showMessageDialog(this, "Leave application submitted successfully!");
    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error submitting leave application.");
    }
}
public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> new HostelManagementSystem());
}
}
