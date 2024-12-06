//package sudoku;
/**
 * The Sudoku number puzzle to be solved
 */
public class Puzzle {
    // All variables have package access
    // The numbers on the puzzle
    int[][] numbers = new int[SudokuConstants.GRID_SIZE][SudokuConstants.GRID_SIZE];
    // The clues - isGiven (no need to guess) or need to guess
    boolean[][] isGiven = new boolean[SudokuConstants.GRID_SIZE][SudokuConstants.GRID_SIZE];

    // Constructor
    public Puzzle() {
        super();
    }

    public enum Difficulty {
        EASY, MEDIUM, HARD
    }

    /**
     * Clear cells to create the puzzle by removing certain numbers.
     */
    private void clearCells(int cellsToClear) {
        for (int i = 0; i < SudokuConstants.GRID_SIZE; i++) {
            for (int j = 0; j < SudokuConstants.GRID_SIZE; j++) {
                isGiven[i][j] = true; // Awalnya semua angka diberikan
            }
        }

        // Randomly remove numbers
        for (int i = 0; i < cellsToClear; i++) {
            int row, col;
            do {
                row = (int) (Math.random() * SudokuConstants.GRID_SIZE);
                col = (int) (Math.random() * SudokuConstants.GRID_SIZE);
            } while (!isGiven[row][col]); // Cari cell yang belum kosong
            isGiven[row][col] = false;   // Hapus angka
        }      
    }

    // Generate a new puzzle given the number of cells to be guessed, which can be used
    //  to control the difficulty level.
    // This method shall set (or update) the arrays numbers and isGiven
    public void newPuzzle(Difficulty level) {
        // I hardcode a puzzle here for illustration and testing.
        int[][] hardcodedNumbers =
                {{5, 3, 4, 6, 7, 8, 9, 1, 2},
                        {6, 7, 2, 1, 9, 5, 3, 4, 8},
                        {1, 9, 8, 3, 4, 2, 5, 6, 7},
                        {8, 5, 9, 7, 6, 1, 4, 2, 3},
                        {4, 2, 6, 8, 5, 3, 7, 9, 1},
                        {7, 1, 3, 9, 2, 4, 8, 5, 6},
                        {9, 6, 1, 5, 3, 7, 2, 8, 4},
                        {2, 8, 7, 4, 1, 9, 6, 3, 5},
                        {3, 4, 5, 2, 8, 6, 1, 7, 9}};

        // Copy from hardcodedNumbers into the array "numbers"
        for (int row = 0; row < SudokuConstants.GRID_SIZE; ++row) {
            for (int col = 0; col < SudokuConstants.GRID_SIZE; ++col) {
                numbers[row][col] = hardcodedNumbers[row][col];
            }
        }

        // Clear cells based on difficulty level
        int clues;
        switch (level) {
            case EASY:
                clues = 40; // Banyaknya angka yang diberikan
                break;
            case MEDIUM:
                clues = 30;
                break;
            case HARD:
                clues = 20;
                break;
            default:
                clues = 30;
        }
        clearCells(SudokuConstants.GRID_SIZE * SudokuConstants.GRID_SIZE - clues);
    }
}

    //(For advanced students) use singleton design pattern for this class