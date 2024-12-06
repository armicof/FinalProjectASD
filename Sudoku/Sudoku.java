import java.awt.*;
import javax.swing.*;
/**
 * The main Sudoku program
 */
public class Sudoku extends JFrame {
    private static final long serialVersionUID = 1L;  // to prevent serial warning

    // private variables
    GameBoardPanel board = new GameBoardPanel();

    // Constructor
    public Sudoku() {
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(board, BorderLayout.CENTER);

        // Create and set the menu bar
        SudokuMenuBar menuBar = new SudokuMenuBar(this);
        setJMenuBar(menuBar);

        // Initialize the game board to start the game
        board.newGame();

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
    }

    public void resetGame() {
        board.newGame(); // Reset logic can be more specific if needed
    }


    /** The entry main() entry method */
    public static void play() {
        // [TODO 1] Check "Swing program template" on how to run
        //  the constructor of "SudokuMain"
        SwingUtilities.invokeLater(() -> new Sudoku());



    }
}