package com.oasis.reservation;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Bite Rush login screen — Java Swing, single-file demo.
 * Run: javac FastTrainTikatLogin.java && java FastTrainTikatLogin
 */
public class FastTrainTikatLogin extends JFrame {
    private static final Color LIME = new Color(168, 255, 0);
    private static final Color INK = new Color(23, 25, 24);
    private static final Color FIELD = new Color(48, 51, 48);
    private static final Color MUTED = new Color(190, 195, 187);

    private final JTextField emailField = new JTextField();
    private final JPasswordField passwordField = new JPasswordField();
    private final JLabel message = new JLabel(" ", SwingConstants.CENTER);
    private final JButton loginButton = new JButton("Login");
    private final JButton signupButton = new JButton("Sign Up");
    private final JButton forgotButton = new JButton("Forgot Password?");
    private final JLabel clockLabel = new JLabel();
    private boolean signupMode = false;
    private boolean passwordVisible = false;
    private char passwordEcho;

    public FastTrainTikatLogin() {
        super("Fast Train Tikat — Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(390, 720));
        setSize(430, 820);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(15, 17, 16));
        setLayout(new GridBagLayout());

        JPanel phone = new JPanel(new BorderLayout());
        phone.setPreferredSize(new Dimension(360, 750));
        phone.setBackground(INK);
        phone.setBorder(new CompoundBorder(
                new LineBorder(new Color(65, 69, 64), 1, true),
                new EmptyBorder(0, 0, 0, 0)));
        phone.setOpaque(true);

        JPanel top = new JPanel(new BorderLayout());
        top.setBackground(INK);
        top.setBorder(new EmptyBorder(12, 22, 0, 22));
        clockLabel.setForeground(Color.WHITE);
        clockLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
        JLabel statusIcons = new JLabel("●  ▮▮  ▰");
        statusIcons.setForeground(Color.WHITE);
        statusIcons.setFont(new Font("SansSerif", Font.BOLD, 11));
        top.add(clockLabel, BorderLayout.WEST);
        top.add(statusIcons, BorderLayout.EAST);
        phone.add(top, BorderLayout.NORTH);

        JPanel center = new JPanel();
        center.setBackground(INK);
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));

        HeroPanel hero = new HeroPanel();
        hero.setPreferredSize(new Dimension(360, 275));
        hero.setMaximumSize(new Dimension(Integer.MAX_VALUE, 275));
        center.add(hero);

        JPanel content = new JPanel();
        content.setBackground(INK);
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBorder(new EmptyBorder(24, 26, 14, 26));

        JLabel headline = new JLabel();
        headline.setAlignmentX(Component.CENTER_ALIGNMENT);
        headline.setFont(new Font("SansSerif", Font.BOLD, 22));
        headline.setForeground(Color.WHITE);
        headline.putClientProperty("headline", Boolean.TRUE);
        headline.setText("<html><span style='color:#A8FF00'>Train Tikat?</span> Get It Fast</html>");

        JLabel subtitle = new JLabel("Fresh, fast, and tailored to your taste!");
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        subtitle.setFont(new Font("SansSerif", Font.PLAIN, 12));
        subtitle.setForeground(MUTED);

        content.add(headline);
        content.add(Box.createVerticalStrut(9));
        content.add(subtitle);
        content.add(Box.createVerticalStrut(23));

        emailField.setFont(new Font("SansSerif", Font.PLAIN, 13));
        emailField.setForeground(Color.WHITE);
        emailField.setCaretColor(LIME);
        emailField.setOpaque(false);
        emailField.setBorder(null);
        emailField.setToolTipText("Email address");
        content.add(makeInputRow("✉", emailField, null));
        content.add(Box.createVerticalStrut(12));

        passwordField.setFont(new Font("SansSerif", Font.PLAIN, 13));
        passwordField.setForeground(Color.WHITE);
        passwordField.setCaretColor(LIME);
        passwordField.setOpaque(false);
        passwordField.setBorder(null);
        passwordField.setToolTipText("Password");
        passwordEcho = passwordField.getEchoChar();
        JButton eyeButton = new JButton("◉");
        styleIconButton(eyeButton);
        eyeButton.addActionListener(e -> togglePassword());
        content.add(makeInputRow("♙", passwordField, eyeButton));

        JPanel forgotRow = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        forgotRow.setOpaque(false);
        forgotButton.setFont(new Font("SansSerif", Font.BOLD, 11));
        forgotButton.setForeground(LIME);
        forgotButton.setBorderPainted(false);
        forgotButton.setContentAreaFilled(false);
        forgotButton.setFocusPainted(false);
        forgotButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        forgotButton.addActionListener(e -> showResetDialog());
        forgotRow.add(forgotButton);
        forgotRow.setAlignmentX(Component.CENTER_ALIGNMENT);
        forgotRow.setMaximumSize(new Dimension(Integer.MAX_VALUE, 27));
        content.add(forgotRow);
        content.add(Box.createVerticalStrut(10));

        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginButton.setFont(new Font("SansSerif", Font.BOLD, 13));
        loginButton.setForeground(new Color(35, 52, 12));
        loginButton.setBackground(LIME);
        loginButton.setFocusPainted(false);
        loginButton.setBorderPainted(false);
        loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginButton.setPreferredSize(new Dimension(300, 54));
        loginButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 54));
        loginButton.setBorder(new EmptyBorder(14, 12, 14, 12));
        loginButton.addActionListener(e -> submitLogin());
        loginButton.addMouseListener(new MouseAdapter() {
            @Override public void mouseEntered(MouseEvent e) {
                loginButton.setBackground(new Color(190, 255, 48));
            }
            @Override public void mouseExited(MouseEvent e) {
                loginButton.setBackground(LIME);
            }
        });
        content.add(loginButton);

        message.setAlignmentX(Component.CENTER_ALIGNMENT);
        message.setFont(new Font("SansSerif", Font.PLAIN, 11));
        message.setForeground(LIME);
        content.add(Box.createVerticalStrut(7));
        content.add(message);

        JPanel signupRow = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
        signupRow.setOpaque(false);
        JLabel signupPrompt = new JLabel("Don't have an account?");
        signupPrompt.setName("signupPrompt");
        signupPrompt.setForeground(new Color(215, 217, 212));
        signupPrompt.setFont(new Font("SansSerif", Font.PLAIN, 11));
        signupButton.setFont(new Font("SansSerif", Font.BOLD, 11));
        signupButton.setForeground(LIME);
        signupButton.setBorderPainted(false);
        signupButton.setContentAreaFilled(false);
        signupButton.setFocusPainted(false);
        signupButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        signupButton.addActionListener(e -> toggleSignup(signupPrompt));
        signupRow.add(signupPrompt);
        signupRow.add(signupButton);
        signupRow.setAlignmentX(Component.CENTER_ALIGNMENT);
        content.add(Box.createVerticalStrut(17));
        content.add(signupRow);

        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        bottom.setOpaque(false);
        bottom.setBorder(new EmptyBorder(20, 0, 10, 0));
        JPanel homeIndicator = new JPanel();
        homeIndicator.setBackground(new Color(235, 237, 232));
        homeIndicator.setPreferredSize(new Dimension(104, 4));
        homeIndicator.setMaximumSize(new Dimension(104, 4));
        homeIndicator.setBorder(new LineBorder(new Color(235, 237, 232), 1, true));
        bottom.add(homeIndicator);
        content.add(bottom);

        center.add(content);
        phone.add(center, BorderLayout.CENTER);
        add(phone);

        Timer clockTimer = new Timer(1000, e -> updateClock());
        clockTimer.start();
        updateClock();
        getRootPane().setDefaultButton(loginButton);
    }

    private JPanel makeInputRow(String icon, JComponent input, JButton trailingButton) {
        JPanel row = new JPanel(new BorderLayout(10, 0)) {
            @Override protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(48, 51, 48));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                if (input.hasFocus()) {
                    g2.setColor(new Color(168, 255, 0, 150));
                    g2.setStroke(new BasicStroke(1.2f));
                    g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
                }
                g2.dispose();
                super.paintComponent(g);
            }
        };
        row.setOpaque(false);
        row.setBorder(new EmptyBorder(0, 15, 0, 13));
        row.setPreferredSize(new Dimension(300, 54));
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 54));

        JLabel iconLabel = new JLabel(icon);
        iconLabel.setForeground(new Color(202, 207, 198));
        iconLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        row.add(iconLabel, BorderLayout.WEST);
        row.add(input, BorderLayout.CENTER);
        if (trailingButton != null) row.add(trailingButton, BorderLayout.EAST);
        input.setForeground(Color.WHITE);
        return row;
    }

    private void styleIconButton(JButton button) {
        button.setForeground(MUTED);
        button.setBackground(FIELD);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    private void togglePassword() {
        passwordVisible = !passwordVisible;
        passwordField.setEchoChar(passwordVisible ? (char) 0 : passwordEcho);
    }

    private void toggleSignup(JLabel prompt) {
        signupMode = !signupMode;
        loginButton.setText(signupMode ? "Create Account" : "Login");
        signupPrompt.setText(signupMode ? "Already have an account?" : "Don't have an account?");
        signupButton.setText(signupMode ? "Login" : "Sign Up");
        passwordField.setToolTipText(signupMode ? "Choose a password" : "Password");
        message.setText(" ");
        emailField.requestFocusInWindow();
    }

    private void submitLogin() {
        String email = emailField.getText().trim();
        String password = new String(passwordField.getPassword());
        if (email.isEmpty() || !email.matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")) {
            showMessage("Please enter a valid email address.", true);
            emailField.requestFocusInWindow();
            return;
        }
        if (password.length() < 6) {
            showMessage("Password must be at least 6 characters.", true);
            passwordField.requestFocusInWindow();
            return;
        }
        loginButton.setEnabled(false);
        loginButton.setText(signupMode ? "Creating account…" : "Signing in…");
        Timer timer = new Timer(850, e -> {
            ((Timer) e.getSource()).stop();
            loginButton.setEnabled(true);
            loginButton.setText(signupMode ? "Create Account" : "Login");
            showMessage(signupMode
                    ? "Demo only — connect your sign-up API to create an account."
                    : "Demo only — connect your authentication API to sign in.", false);
        });
        timer.setRepeats(false);
        timer.start();
    }

    private void showResetDialog() {
        JTextField resetEmail = new JTextField(emailField.getText(), 22);
        resetEmail.setFont(new Font("SansSerif", Font.PLAIN, 13));
        JPanel panel = new JPanel(new BorderLayout(0, 10));
        panel.setBackground(INK);
        JLabel text = new JLabel("<html><div style='width:250px;color:#C0C4BC'>Enter your email address to start the password-reset flow.</div></html>");
        text.setForeground(MUTED);
        panel.add(text, BorderLayout.NORTH);
        panel.add(resetEmail, BorderLayout.CENTER);
        int result = JOptionPane.showConfirmDialog(this, panel, "Reset password",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            String value = resetEmail.getText().trim();
            if (!value.matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")) {
                showMessage("Please enter a valid email address.", true);
            } else {
                showMessage("Demo reset request ready for " + value + ".", false);
            }
        }
    }

    private void showMessage(String text, boolean error) {
        message.setForeground(error ? new Color(255, 145, 130) : LIME);
        message.setText(text);
    }

    private void updateClock() {
        clockLabel.setText(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")));
    }

    private static class HeroPanel extends JPanel {
        private float phase = 0f;
        private final Timer animation;
        HeroPanel() {
            setOpaque(false);
            animation = new Timer(45, e -> {
                phase += 0.012f;
                if (phase > Math.PI * 2) phase = 0;
                repaint();
            });
            animation.start();
        }

        @Override protected void paintComponent(Graphics graphics) {
            super.paintComponent(graphics);
            Graphics2D g = (Graphics2D) graphics.create();
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            int w = getWidth(), h = getHeight();

            g.setColor(LIME);
            Path2D shape = new Path2D.Float();
            shape.moveTo(0, 0); shape.lineTo(w, 0); shape.lineTo(w, h - 35);
            shape.quadTo(w / 2.0, h + 10, 0, h - 35); shape.closePath();
            g.fill(shape);

            // Lightly animated food-inspired line doodles.
            g.setColor(new Color(27, 57, 8, 55));
            g.setStroke(new BasicStroke(2.3f));
            for (int i = 0; i < 8; i++) {
                double x = 24 + i * 47;
                double y = 42 + (i % 3) * 65 + Math.sin(phase + i) * 5;
                g.drawOval((int) x, (int) y, 23, 18);
                g.drawLine((int) x + 4, (int) y + 23, (int) x + 25, (int) y + 31);
                g.drawLine((int) x + 7, (int) y + 28, (int) x + 27, (int) y + 36);
            }
            g.setFont(new Font("SansSerif", Font.BOLD, 27));
            g.drawString("♧", 18, 75);
            g.drawString("♨", w - 55, 100);
            g.drawString("☷", 22, h - 70);
            g.drawString("♧", w - 54, h - 67);
            g.drawString("♡", w / 2 - 10, 75);

            String fast = "Fast";
            String train = "Train";
            String tikat = "Tikat";
            Font font = new Font("SansSerif", Font.BOLD, 32);
            g.setFont(font);
            FontMetrics fm = g.getFontMetrics();
            int total = fm.stringWidth(fast) + 12 + fm.stringWidth(train) + 22 + fm.stringWidth(tikat) + 32;
            int startX = (w - total) / 2;
            int baseline = h / 2 + 15;
            g.setColor(new Color(19, 31, 10));
            g.drawString(train, startX, baseline);
            int tagX = startX + fm.stringWidth(train) + 9;
            int tagW = fm.stringWidth(tikat) + 22;
            g.fillRoundRect(tagX, baseline - fm.getAscent() + 1, tagW, fm.getHeight() + 2, 13, 13);
            g.setColor(Color.WHITE);
            g.drawString(tikat, tagX + 11, baseline);
            g.dispose();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try { UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName()); }
            catch (Exception ignored) {}
            new FastTrainTikatLogin().setVisible(true);
        });
    }
}
