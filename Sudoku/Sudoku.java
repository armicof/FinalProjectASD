import java.awt.*;
import javax.swing.*;
/**
 * The main Sudoku program
 */
public class Sudoku extends JFrame {
    private static final long serialVersionUID = 1L;  // to prevent serial warning

    // private variables
    GameBoardPanel board;
    private JTextField statusBar; // The status bar

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

        // Initialize the game board to start the game
        board.newGame();
        updateStatusBar();

        pack();     // Pack the UI components, instead of using setSize()
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // to handle window-closing
        setTitle("Sudoku");
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

    /** The entry main() entry method */
    public void play() {
        // [TODO 1] Check "Swing program template" on how to run
        //  the constructor of "SudokuMain"
        SwingUtilities.invokeLater(() -> new Sudoku());



    }
}