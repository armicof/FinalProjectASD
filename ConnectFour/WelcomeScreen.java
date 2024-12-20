import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class WelcomeScreen extends JFrame {
    public WelcomeScreen() {
        // Frame settings
        setTitle("Welcome to Connect Four");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window

        // Create components
        JLabel titleLabel = new JLabel("Welcome to Connect Four!", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Poppins", Font.BOLD, 24));
        titleLabel.setForeground(new Color(239, 105, 80));

        JButton startButton = new JButton("Start Game");
        startButton.setFont(new Font("Poppins", Font.PLAIN, 18));
        startButton.setBackground(Color.BLUE);
        startButton.setForeground(Color.WHITE);
        startButton.setFocusPainted(false);

        JButton quitButton = new JButton("Quit");
        quitButton.setFont(new Font("Poppins", Font.PLAIN, 18));
        quitButton.setBackground(Color.DARK_GRAY);
        quitButton.setForeground(Color.WHITE);
        quitButton.setFocusPainted(false);

        // Layout setup
        setLayout(new BorderLayout());
        add(titleLabel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        buttonPanel.add(startButton);
        buttonPanel.add(quitButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Add event listeners
        startButton.addActionListener(e -> {
            // Open the game and close the welcome screen
            GameMain.play();
            dispose();
        });

        quitButton.addActionListener(e -> {
            // Exit the application
            System.exit(0);
        });

        // Make the frame visible
        setVisible(true);
    }

    // public static void main(String[] args) {
    //     SwingUtilities.invokeLater(new Runnable() {
    //         @Override
    //         public void run() {
    //             new WelcomeScreen().setVisible(true);
    //         }
    //     });
    // }
}
