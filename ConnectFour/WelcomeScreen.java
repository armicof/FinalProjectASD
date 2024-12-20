import javax.swing.*;
import java.awt.*;

public class WelcomeScreen extends JFrame {
    public WelcomeScreen() {
        // Set cross-platform look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

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
        startButton.setBackground(new Color(239, 105, 80));  // Red background
        startButton.setForeground(Color.WHITE);  // White text
        startButton.setFocusPainted(false);  // Remove the focus paint
        startButton.setOpaque(true);  // Ensure the background color is visible
        startButton.setBorderPainted(false);  // Remove the border
        startButton.setPreferredSize(new Dimension(150, 50));  // Set a fixed size

        JButton quitButton = new JButton("Quit");
        quitButton.setFont(new Font("Poppins", Font.PLAIN, 18));
        quitButton.setBackground(new Color(255, 221, 51));  // Yellow background
        quitButton.setForeground(Color.WHITE);  // White text
        quitButton.setFocusPainted(false);  // Remove the focus paint
        quitButton.setOpaque(true);  // Ensure the background color is visible
        quitButton.setBorderPainted(false);  // Remove the border
        quitButton.setPreferredSize(new Dimension(150, 50));  // Set a fixed size

        // Layout setup
        setLayout(new BorderLayout());
        add(titleLabel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        buttonPanel.setBackground(new Color(255, 255, 255));  // Set a contrasting background for the panel
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
        validate();  // Force layout and repaint
    }
}
