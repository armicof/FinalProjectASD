import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * The main Sudoku program
 */
public class Sudoku extends JFrame {
    private static final long serialVersionUID = 1L;  // to prevent serial warning

    // private variables
    GameBoardPanel board;
    private JTextField statusBar; // The status bar
    private JLabel timerLabel;  // JLabel to show the timer
    private int secondsElapsed; // Counter for elapsed seconds
    private Timer timer; // Timer to update every second
    private JLabel scoreLabel;
    private JLabel playerNameLabel; 
    private JTextField playerNameField;

    // Constructor
    public Sudoku() {
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        board = new GameBoardPanel(this);
        cp.add(board, BorderLayout.CENTER);

        // Create and set the menu bar
        SudokuMenuBar menuBar = new SudokuMenuBar(this);
        setJMenuBar(menuBar);

        // Create the status bar
        statusBar = new JTextField();
        statusBar.setEditable(false);
        statusBar.setText("Welcome to Sudoku! Start a new game.");
        add(statusBar, BorderLayout.SOUTH);

        // Inisialisasi JPanel untuk menampung timer dan scoreLabel
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER)); // Menggunakan FlowLayout untuk mengatur tata letak secara horizontal
        // Tambahkan topPanel ke BorderLayout.NORTH
        this.add(topPanel, BorderLayout.NORTH);

        // Label to display player's name
        playerNameLabel = new JLabel("Enter Name");
        playerNameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        playerNameLabel.setHorizontalAlignment(SwingConstants.LEFT);
        playerNameLabel.setPreferredSize(new Dimension(100, 30));
        topPanel.add(playerNameLabel);

        // Player name input field
        playerNameField = new JTextField(18);
        playerNameField.setFont(new Font("Arial", Font.PLAIN, 16));
        playerNameField.setToolTipText("Enter your name and press Enter");
        playerNameField.addActionListener(e -> {
            String playerName = playerNameField.getText().trim();
            if (!playerName.isEmpty()) {
                playerNameLabel.setText("Player: " + playerName);
    
                // Remove the text field
                topPanel.remove(playerNameField);

                playerNameLabel.setPreferredSize(null); 
    
                // Refresh the panel to update the UI
                topPanel.revalidate();
                topPanel.repaint();

                // Initialize the game board to start the game
                board.newGame(Puzzle.Difficulty.EASY);
                updateStatusBar();
            } else {
                playerNameLabel.setText("Enter your name");
                JOptionPane.showMessageDialog(this, "Please enter your name to start the game!", "Name Required", JOptionPane.WARNING_MESSAGE);
            }
        });
        topPanel.add(playerNameField);

        // Initialize the timer label
        timerLabel = new JLabel("Time: 00:00");
        timerLabel.setFont(new Font("Poppins", Font.BOLD, 20));
        timerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        timerLabel.setPreferredSize(new Dimension(375, 30));
        topPanel.add(timerLabel);

        // In your Sudoku frame constructor:
        scoreLabel = new JLabel("Score: 0");
        scoreLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        scoreLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        scoreLabel.setPreferredSize(new Dimension(100, 30));
        topPanel.add(scoreLabel);

        // Timer to update the time every second
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                secondsElapsed++;
                updateTimerLabel();
            }
        });

        pack();     // Pack the UI components, instead of using setSize()
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // to handle window-closing
        setTitle("Sudoku");
        setSize(600, 700);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Methods for menu actions
    public void newGame(Puzzle.Difficulty level) {
        board.newGame(level);
        updateStatusBar();

    }

    public void resetGame() {
        board.newGame(Puzzle.Difficulty.MEDIUM); // Reset logic can be more specific if needed
        updateStatusBar();
    }

    public void updateStatusBar() {
        statusBar.setText("Cells remaining: " + board.getRemainingCells());
    }

    // Update the timer label to show elapsed time
    private void updateTimerLabel() {
        int minutes = secondsElapsed / 60;
        int seconds = secondsElapsed % 60;
        timerLabel.setText(String.format("Time: %02d:%02d", minutes, seconds));
    }

    // Reset the timer to zero
    public void resetTimer() {
        secondsElapsed = 0;
        updateTimerLabel();
        timer.start();  // Start the timer when a new game is started
    }

    // Stop the timer when the game ends (for example)
    public void stopTimer() {
        timer.stop();
    }

    public void updateScore(int score) {
        // Assuming you have a label or status bar where you display the score
        scoreLabel.setText("Score: " + score);
    }

    /** The entry main() entry method */
    public static void play() {
        // [TODO 1] Check "Swing program template" on how to run
        //  the constructor of "SudokuMain"
        SwingUtilities.invokeLater(() -> new Sudoku());
    }
}