import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;

public class WelcomeScreen {
    private JFrame welcomeFrame;

    public WelcomeScreen() {
        // Set the Look and Feel to Nimbus to avoid L&F overrides on components
//        try {
//            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
//        } catch (Exception e) {
//            e.printStackTrace(); // In case Nimbus is not available
//        }

        // Create a new frame for the welcome screen
        welcomeFrame = new JFrame("Welcome to Sudoku");

        // Create a JPanel with custom paint method to add a background image
        JPanel panelWithImage = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Load the background image
                ImageIcon imageIcon = new ImageIcon("bg copy.png");  // Change the path
                Image image = imageIcon.getImage();
                g.drawImage(image, 0, 0, this);  // Draw the image at coordinates (0, 0)
            }
        };

        // Set the layout of the panel
        panelWithImage.setLayout(new BorderLayout());

        // Set up a label with a welcome message
        JLabel welcomeLabel = new JLabel("Welcome to Sudoku!", JLabel.CENTER);
        welcomeLabel.setFont(new Font("Poppins", Font.BOLD, 50));
        welcomeLabel.setForeground(Color.white);  // Set label text color to white

        // Customize the "Start Game" button
        JButton startButton = new JButton("Start Game");
        startButton.setBackground(new Color(69, 123, 157));  // Set a specific background color
        startButton.setForeground(Color.gray);  // Set white text color
        startButton.setFont(new Font("Poppins", Font.BOLD, 20));  // Custom font
        startButton.setFocusPainted(false); // Remove focus border around the button

        // Customize the "Exit" button
        JButton exitButton = new JButton("Exit");
        exitButton.setBackground(new Color(244, 85, 96));  // Set a specific background color
        exitButton.setForeground(Color.gray);  // Set white text color
        exitButton.setFont(new Font("Poppins", Font.BOLD, 20));
        exitButton.setFocusPainted(false); // Remove focus border around the button

        // Add an ActionListener to the "Start Game" button
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Close the welcome screen
                welcomeFrame.dispose();

                // Start the main Sudoku game
                Sudoku.play();  // Call the play method to start the Sudoku game
            }
        });

        // Add an ActionListener to the "Exit" button
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Exit the application
                System.exit(0);
            }
        });

        // Create a JPanel to hold the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));  // Center-align buttons with space between them
        buttonPanel.setOpaque(false);  // Make sure the button panel is transparent so the background shows
        buttonPanel.add(startButton);
        buttonPanel.add(exitButton);

        // Add components to the background panel
        panelWithImage.add(welcomeLabel, BorderLayout.CENTER);  // Add label to the center
        panelWithImage.add(buttonPanel, BorderLayout.SOUTH);  // Add the panel with buttons to the bottom

        // Set the frame properties
        welcomeFrame.setSize(680, 700);
        welcomeFrame.setLocationRelativeTo(null);  // Center the frame on the screen
        welcomeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        welcomeFrame.setContentPane(panelWithImage);  // Set the custom background panel as the content pane
    }

    public void show() {
        // Make the welcome screen visible
        welcomeFrame.setVisible(true);
    }
}
