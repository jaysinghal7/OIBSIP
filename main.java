package com.oasis.reservation;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Main {

    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName()
            );
        } catch (Exception ignored) {
        }

        DatabaseInitializer.initialize();

        SwingUtilities.invokeLater(() ->
                new LoginFrame().setVisible(true)
        );
    }
}