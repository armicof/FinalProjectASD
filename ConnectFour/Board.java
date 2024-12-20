import java.awt.*;

public class Board {
    // Keep existing constants
    public static final int ROWS = 6;
    public static final int COLS = 7;
    public static final int CANVAS_WIDTH = Cell.SIZE * COLS;
    public static final int CANVAS_HEIGHT = Cell.SIZE * ROWS;
    public static final int GRID_WIDTH = 8;
    public static final int GRID_WIDTH_HALF = GRID_WIDTH / 2;
    public static final Color COLOR_GRID = Color.LIGHT_GRAY;
    public static final int Y_OFFSET = 1;

    Cell[][] cells;

    public Board() {
        initGame();
    }

    public void initGame() {
        cells = new Cell[ROWS][COLS];
        for (int row = 0; row < ROWS; ++row) {
            for (int col = 0; col < COLS; ++col) {
                cells[row][col] = new Cell(row, col);
            }
        }
    }

    public void newGame() {
        for (int row = 0; row < ROWS; ++row) {
            for (int col = 0; col < COLS; ++col) {
                cells[row][col].newGame();
            }
        }
    }

    public State stepGame(Seed player, int selectedRow, int selectedCol) {
        // Update game board
        cells[selectedRow][selectedCol].content = player;

        // Check for win conditions
        if (hasWon(player, selectedRow, selectedCol)) {
            return (player == Seed.CROSS) ? State.CROSS_WON : State.NOUGHT_WON;
        }

        // Check for draw
        for (int row = 0; row < ROWS; ++row) {
            for (int col = 0; col < COLS; ++col) {
                if (cells[row][col].content == Seed.NO_SEED) {
                    return State.PLAYING; // still have empty cells
                }
            }
        }
        return State.DRAW;
    }

    private boolean hasWon( Seed player, int row, int col) {
        // Check horizontal
        int count = 0;
        for (int c = 0; c < COLS; c++) {
            if (cells[row][c].content == player) {
                count++;
                if (count >= 4) return true;
            } else {
                count = 0;
            }
        }

        // Check vertical
        count = 0;
        for (int r = 0; r < ROWS; r++) {
            if (cells[r][col].content == player) {
                count++;
                if (count >= 4) return true;
            } else {
                count = 0;
            }
        }

        // Check diagonal (top-left to bottom-right)
        for (int r = 0; r <= ROWS - 4; r++) {
            for (int c = 0; c <= COLS - 4; c++) {
                if (cells[r][c].content == player &&
                    cells[r+1][c+1].content == player &&
                    cells[r+2][c+2].content == player &&
                    cells[r+3][c+3].content == player) {
                    return true;
                }
            }
        }

        // Check diagonal (top-right to bottom-left)
        for (int r = 0; r <= ROWS - 4; r++) {
            for (int c = COLS - 1; c >= 3; c--) {
                if (cells[r][c].content == player &&
                    cells[r+1][c-1].content == player &&
                    cells[r+2][c-2].content == player &&
                    cells[r+3][c-3].content == player) {
                    return true;
                }
            }
        }

        return false;
    }

    public void paint(Graphics g) {
        // Draw the grid-lines
        g.setColor(COLOR_GRID);
        for (int row = 1; row < ROWS; ++row) {
            g.fillRoundRect(0, Cell.SIZE * row - GRID_WIDTH_HALF,
                    CANVAS_WIDTH - 1, GRID_WIDTH,
                    GRID_WIDTH, GRID_WIDTH);
        }
        for (int col = 1; col < COLS; ++col) {
            g.fillRoundRect(Cell.SIZE * col - GRID_WIDTH_HALF, 0 + Y_OFFSET,
                    GRID_WIDTH, CANVAS_HEIGHT - 1,
                    GRID_WIDTH, GRID_WIDTH);
        }

        // Draw all the cells
        for (int row = 0; row < ROWS; ++row) {
            for (int col = 0; col < COLS; ++col) {
                cells[row][col].paint(g);
            }
        }
    }
}