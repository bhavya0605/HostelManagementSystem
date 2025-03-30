
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.*;

public class SignInSignUpPage extends JFrame {
    private CardLayout cardLayout = new CardLayout();
    private JPanel mainPanel = new JPanel(cardLayout);

    // Database connection details
    private static final String DB_URL = "jdbc:mysql://localhost:3306/hostel_management";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "root";

    public SignInSignUpPage() {
        setTitle("Hostel Management System - Sign In / Sign Up");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(550, 450);
        setLocationRelativeTo(null);


        // Background panel setup
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundIcon = new ImageIcon(getClass().getResource("/dark-blue-modern-blurred-background-vector.jpg"));
                Image backgroundImage = backgroundIcon.getImage();
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(new BorderLayout());

        JPanel signInPanel = createSignInPanel();
        JPanel signUpPanel = createSignUpPanel();

        mainPanel.add(signInPanel, "SignIn");
        mainPanel.add(signUpPanel, "SignUp");
        cardLayout.show(mainPanel, "SignIn");

        backgroundPanel.add(mainPanel);
        getContentPane().add(backgroundPanel);
    }

    private JWindow createSplashScreen() {
        JWindow splash = new JWindow();
        splash.setSize(400, 300);
        splash.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(0x004080));
        JLabel splashLabel = new JLabel("Welcome to Hostel Management System");
        splashLabel.setFont(new Font("Roboto", Font.BOLD, 18));
        splashLabel.setForeground(Color.WHITE);
        panel.add(splashLabel);
        splash.getContentPane().add(panel);

        return splash;
    }

    private JPanel createSignInPanel() {
        JPanel signInPanel = new JPanel(new BorderLayout());
        signInPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        signInPanel.setOpaque(false);

        JLabel titleLabel = new JLabel("Sign In", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Roboto", Font.BOLD, 24));
        titleLabel.setForeground(new Color(0x004080));

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        JTextField usernameField = createTextFieldWithPlaceholder("Username");
        JPasswordField passwordField = createPasswordFieldWithPlaceholder("Password");

        JButton signInButton = new JButton("Sign In");
        signInButton.setPreferredSize(new Dimension(300, 40));
        signInButton.setBackground(new Color(0x006699));
        signInButton.setForeground(Color.WHITE);
        signInButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (authenticateUser(username, password)) {
                // Dispose of the current SignInSignUpPage (login screen)
                dispose();  // Close the SignIn/SignUp window
        
                // Show the Hostel Management System window
                HostelManagementSystem hostelManagementSystem = new HostelManagementSystem(); // Create the instance
                hostelManagementSystem.setVisible(true);  // Make it visible
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JButton switchToSignUpButton = new JButton("Don't have an account? Sign Up");
        switchToSignUpButton.setForeground(new Color(0x006699));
        switchToSignUpButton.setBorder(BorderFactory.createEmptyBorder());
        switchToSignUpButton.setContentAreaFilled(false);
        switchToSignUpButton.setFont(new Font("Roboto", Font.PLAIN, 12));
        switchToSignUpButton.addActionListener(e -> cardLayout.show(mainPanel, "SignUp"));

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(signInButton, gbc);

        signInPanel.add(titleLabel, BorderLayout.NORTH);
        signInPanel.add(formPanel, BorderLayout.CENTER);
        signInPanel.add(switchToSignUpButton, BorderLayout.SOUTH);

        return signInPanel;
    }

    private JPanel createSignUpPanel() {
        JPanel signUpPanel = new JPanel(new BorderLayout());
        signUpPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        signUpPanel.setOpaque(false);

        JLabel titleLabel = new JLabel("Sign Up", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Roboto", Font.BOLD, 24));
        titleLabel.setForeground(new Color(0x004080));

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        JTextField usernameField = createTextFieldWithPlaceholder("Username(Please enter your good name!)");
        JPasswordField passwordField = createPasswordFieldWithPlaceholder("Password");
        JTextField emailField = createTextFieldWithPlaceholder("Email(abc@srmist.edu.in)");
        JTextField phoneField = createTextFieldWithPlaceholder("Phone Number(10 digits)");
        JComboBox<String> hostelDropdown = new JComboBox<>(new String[]{"Select Hostel", "Mullai", "Manoranjitham", "Nelson Mandela", "Sannasi-A", "Sannasi-B"});

        JButton signUpButton = new JButton("Sign Up");
        signUpButton.setBackground(new Color(0x006699));
        signUpButton.setForeground(Color.WHITE);
        signUpButton.setPreferredSize(new Dimension(300, 40));
        signUpButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            String email = emailField.getText();
            String phone = phoneField.getText();
            String hostel = (String) hostelDropdown.getSelectedItem();

            if (validateEmail(email) && validatePhone(phone) && !username.isEmpty() && !password.isEmpty()) {
                if (registerUser(username, password, email, phone, hostel)) {
                    JOptionPane.showMessageDialog(this, "Sign Up Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Sign Up Failed. Username might already exist.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please fill in all fields with valid information.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JButton switchToSignInButton = new JButton("Already have an account? Sign In");
        switchToSignInButton.setForeground(new Color(0x006699));
        switchToSignInButton.setBorder(BorderFactory.createEmptyBorder());
        switchToSignInButton.setContentAreaFilled(false);
        switchToSignInButton.setFont(new Font("Roboto", Font.PLAIN, 12));
        switchToSignInButton.addActionListener(e -> cardLayout.show(mainPanel, "SignIn"));

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(emailField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(phoneField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        formPanel.add(hostelDropdown, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        formPanel.add(signUpButton, gbc);

        signUpPanel.add(titleLabel, BorderLayout.NORTH);
        signUpPanel.add(formPanel, BorderLayout.CENTER);
        signUpPanel.add(switchToSignInButton, BorderLayout.SOUTH);

        return signUpPanel;
    }

    private boolean authenticateUser(String username, String password) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement stmt = connection.prepareStatement("SELECT * FROM students1 WHERE username = ? AND password = ?")) {

            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean registerUser(String username, String password, String email, String phone, String hostel) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement stmt = connection.prepareStatement("INSERT INTO students1 (username, password, email, phone, hostel) VALUES (?, ?, ?, ?, ?)")) {
    
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, email);
            stmt.setString(4, phone);
            stmt.setString(5, hostel);
    
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                // Fetch the enrollment_date for the newly registered user
                try (PreparedStatement selectStmt = connection.prepareStatement("SELECT enrollment_date FROM students1 WHERE username = ?")) {
                    selectStmt.setString(1, username);
                    ResultSet rs = selectStmt.executeQuery();
                    if (rs.next()) {
                        String enrollmentDate = rs.getString("enrollment_date");
                        JOptionPane.showMessageDialog(this, "Sign Up Successful! Enrollment Date: " + enrollmentDate, "Success", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    

    private JTextField createTextFieldWithPlaceholder(String placeholder) {
        JTextField textField = new JTextField(20);
        textField.setText(placeholder);
        textField.setForeground(Color.GRAY);
        textField.setPreferredSize(new Dimension(300, 40));

        textField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals(placeholder)) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                }
            }

            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setText(placeholder);
                    textField.setForeground(Color.GRAY);
                }
            }
        });
        return textField;
    }

    private JPasswordField createPasswordFieldWithPlaceholder(String placeholder) {
        JPasswordField passwordField = new JPasswordField(20);
        passwordField.setEchoChar((char) 0);
        passwordField.setText(placeholder);
        passwordField.setForeground(Color.GRAY);
        passwordField.setPreferredSize(new Dimension(300, 40));

        passwordField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (new String(passwordField.getPassword()).equals(placeholder)) {
                    passwordField.setText("");
                    passwordField.setEchoChar('•');
                    passwordField.setForeground(Color.BLACK);
                }
            }

            public void focusLost(FocusEvent e) {
                if (passwordField.getPassword().length == 0) {
                    passwordField.setText(placeholder);
                    passwordField.setEchoChar((char) 0);
                    passwordField.setForeground(Color.GRAY);
                }
            }
        });
        return passwordField;
    }

    private boolean validateEmail(String email) {
        return email.matches("^[a-zA-Z0-9_.+-]+@srmist\\.edu\\.in$");
    }

    private boolean validatePhone(String phone) {
        return phone.matches("^[0-9]{10}$");
    }

    public static void main(String[] args) {
         // Create and show the splash screen
         JWindow splashScreen = new JWindow();
         splashScreen.setSize(450, 100);
         splashScreen.setLocationRelativeTo(null);
         JLabel splashLabel = new JLabel("Welcome to Hostel Management System", SwingConstants.CENTER);
         splashLabel.setFont(new Font("Roboto", Font.BOLD, 18));
         splashLabel.setForeground(new Color(0x004080));  // Navy blue color
         splashScreen.getContentPane().add(splashLabel, BorderLayout.CENTER);
         splashScreen.setVisible(true);
 
         // Timer to show splash screen for 3 seconds
         Timer timer = new Timer(6000, new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 splashScreen.setVisible(false); // Hide splash screen
                 new SignInSignUpPage().setVisible(true); // Show main window
             }
         });
         timer.setRepeats(false);
         timer.start();
 
        // SwingUtilities.invokeLater(() -> {
        //     SignInSignUpPage app = new SignInSignUpPage();
        //     app.setVisible(true);
        //});
    }
}
