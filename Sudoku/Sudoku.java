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
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT)); // Menggunakan FlowLayout untuk mengatur tata letak secara horizontal
        // Tambahkan topPanel ke BorderLayout.NORTH
        this.add(topPanel, BorderLayout.NORTH);

        // In your Sudoku frame constructor:
        scoreLabel = new JLabel("Score: 0");
        scoreLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        scoreLabel.setPreferredSize(new Dimension(200, 30));
        // statusBar.add(scoreLabel); // Assuming you have a statusBar panel
        // add(scoreLabel, BorderLayout.SOUTH);
        topPanel.add(scoreLabel);

        // Initialize the timer label
        timerLabel = new JLabel("Time: 00:00");
        timerLabel.setFont(new Font("Poppins", Font.BOLD, 20));
        timerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        // add(timerLabel, BorderLayout.NORTH);
        topPanel.add(timerLabel);

        // Timer to update the time every second
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                secondsElapsed++;
                updateTimerLabel();
            }
        });

        // Initialize the game board to start the game
        board.newGame();
        updateStatusBar();

        pack();     // Pack the UI components, instead of using setSize()
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // to handle window-closing
        setTitle("Sudoku");
        setSize(600, 700);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Methods for menu actions
    public void newGame() {
        board.newGame();
        updateStatusBar();

    }

    public void resetGame() {
        board.newGame(); // Reset logic can be more specific if needed
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