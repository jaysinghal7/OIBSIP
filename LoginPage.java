package com.oasis.reservation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginFrame extends JFrame {

    private final JTextField usernameField =
            new JTextField();

    private final JPasswordField passwordField =
            new JPasswordField();

    private static final Color PRIMARY =
            new Color(75, 0, 130);

    public LoginFrame() {

        setTitle("Online Reservation System - Login");
        setSize(460, 340);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel root =
                new JPanel(new BorderLayout(15, 15));

        root.setBorder(
                new EmptyBorder(25, 30, 25, 30)
        );

        JLabel title =
                new JLabel(
                        "ONLINE RESERVATION SYSTEM",
                        SwingConstants.CENTER
                );

        title.setFont(
                new Font("SansSerif",
                        Font.BOLD, 20)
        );

        title.setForeground(PRIMARY);

        root.add(title, BorderLayout.NORTH);

        JPanel form =
                new JPanel(new GridLayout(2, 2, 10, 15));

        form.add(new JLabel("Username:"));
        form.add(usernameField);

        form.add(new JLabel("Password:"));
        form.add(passwordField);

        JButton loginButton =
                new JButton("Login");

        loginButton.setBackground(PRIMARY);
        loginButton.setForeground(Color.WHITE);

        loginButton.addActionListener(
                e -> login()
        );

        JButton exitButton =
                new JButton("Exit");

        exitButton.addActionListener(
                e -> System.exit(0)
        );

        JPanel center =
                new JPanel(new BorderLayout(15, 15));

        center.add(form, BorderLayout.CENTER);

        JPanel buttons =
                new JPanel();

        buttons.add(loginButton);
        buttons.add(exitButton);

        center.add(buttons, BorderLayout.SOUTH);

        root.add(center, BorderLayout.CENTER);

        JLabel hint =
                new JLabel(
                        "Demo Login: admin / admin123",
                        SwingConstants.CENTER
                );

        root.add(hint, BorderLayout.SOUTH);

        setContentPane(root);

        getRootPane().setDefaultButton(loginButton);
    }

    private void login() {

        String username =
                usernameField.getText().trim();

        String password =
                new String(passwordField.getPassword());

        if (username.isEmpty()
                || password.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter username and password.",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        String sql =
                "SELECT password_hash FROM users " +
                "WHERE username = ?";

        try (Connection connection =
                     DBConnection.getConnection();

             PreparedStatement ps =
                     connection.prepareStatement(sql)) {

            ps.setString(1, username);

            try (ResultSet rs =
                         ps.executeQuery()) {

                if (rs.next()
                        && PasswordHasher
                        .sha256(password)
                        .equals(
                                rs.getString(
                                        "password_hash"
                                )
                        )) {

                    dispose();

                    new MainFrame(username)
                            .setVisible(true);

                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "Invalid username or password.",
                            "Login Failed",
                            JOptionPane.ERROR_MESSAGE
                    );

                    passwordField.setText("");
                }
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Database error: "
                            + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}