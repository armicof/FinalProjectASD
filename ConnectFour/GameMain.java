import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 * Tic-Tac-Toe: Two-player Graphic version with better OO design.
 * The Board and Cell classes are separated in their own classes.
 */
public class GameMain extends JPanel {
    private static final long serialVersionUID = 1L; // to prevent serializable warning

    // Define named constants for the drawing graphics
    public static final String TITLE = "Connect Four";
    public static final Color COLOR_BG = Color.WHITE;
    public static final Color COLOR_BG_STATUS = new Color(216, 216, 216);
    public static final Color COLOR_CROSS = new Color(239, 105, 80);  // Red #EF6950
    public static final Color COLOR_NOUGHT = new Color(64, 154, 225); // Blue #409AE1
    public static final Font FONT_STATUS = new Font("OCR A Extended", Font.PLAIN, 14);

    // Timer constants
    private static final int TIMER_DELAY = 1000; // 1 second
    private static final int INITIAL_TIME = 60; // 1 minutes (60 seconds) per player

    // Define game objects
    private Board board;         // the game board
    private State currentState;  // the current state of the game
    private Seed currentPlayer;  // the current player
    private JLabel statusBar;    // for displaying status message

    // Timer components
    private Timer gameTimer;
    private int playerXTime;
    private int playerOTime;
    private JLabel timerLabel;

    /** Constructor to setup the UI and game components */
    public GameMain() {
        SoundEffect.initGame();
        
        // Initialize timer
        playerXTime = INITIAL_TIME;
        playerOTime = INITIAL_TIME;
        
        // Create timer label
        timerLabel = new JLabel();
        timerLabel.setFont(FONT_STATUS);
        timerLabel.setBackground(COLOR_BG_STATUS);
        timerLabel.setOpaque(true);
        timerLabel.setPreferredSize(new Dimension(300, 20));
        timerLabel.setHorizontalAlignment(JLabel.CENTER);
        timerLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 12));

        // Setup the status bar (JLabel) to display status message
        statusBar = new JLabel();
        statusBar.setFont(FONT_STATUS);
        statusBar.setBackground(COLOR_BG_STATUS);
        statusBar.setOpaque(true);
        statusBar.setPreferredSize(new Dimension(300, 20));
        statusBar.setHorizontalAlignment(JLabel.LEFT);
        statusBar.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 12));

        super.setLayout(new BorderLayout());
        super.setPreferredSize(new Dimension(Board.CANVAS_WIDTH, Board.CANVAS_HEIGHT));
        super.add(statusBar, BorderLayout.PAGE_END); // same as SOUTH
        super.add(timerLabel, BorderLayout.PAGE_START);
        // account for statusBar in height
        super.setBorder(BorderFactory.createLineBorder(COLOR_BG_STATUS, 2, false));
        
        // Create game timer
        gameTimer = new Timer(TIMER_DELAY, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentState == State.PLAYING) {
                    if (currentPlayer == Seed.CROSS) {
                        playerXTime--;
                        if (playerXTime <= 0) {
                            playerXTime = 0; // Ensure we don't go negative
                            currentState = State.NOUGHT_WON;
                            gameTimer.stop();
                        }
                    } else {
                        playerOTime--;
                        if (playerOTime <= 0) {
                            playerOTime = 0; // Ensure we don't go negative
                            currentState = State.CROSS_WON;
                            gameTimer.stop();
                        }
                    }
                    updateTimerLabel();
                    repaint();
                }
            }
        });

        // This JPanel fires MouseEvent
        super.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int mouseX = e.getX();
                int mouseY = e.getY();
                // Get the column clicked
                int colSelected = mouseX / Cell.SIZE;

                if (currentState == State.PLAYING) {
                    if (colSelected >= 0 && colSelected < Board.COLS) {
                        // Look for an empty cell starting from the bottom row
                        for (int row = Board.ROWS - 1; row >= 0; row--) {
                            if (board.cells[row][colSelected].content == Seed.NO_SEED) {
                                // Make a move
                                currentState = board.stepGame(currentPlayer, row, colSelected);
                                if (currentState == State.PLAYING) {
                                    if (currentPlayer == Seed.CROSS){
                                        SoundEffect.EAT_FOOD.play();
                                    } else if (currentPlayer == Seed.NOUGHT) {
                                        SoundEffect.EXPLODE.play();
                                    } else {
                                        SoundEffect.DIE.play();
                                    }
                                } 
                                else {
                                    SoundEffect.DIE.play();
                                }
                                // Switch player
                                currentPlayer = (currentPlayer == Seed.CROSS) ? Seed.NOUGHT : Seed.CROSS;
                                break;
                            }
                        }
                    }
                } else { // game over
                    newGame(); // restart the game
                }

                // Refresh the drawing canvas
                repaint(); // Callback paintComponent().
            }
        });

        // Set up Game
        initGame();
        newGame();
    }

    private void updateTimerLabel() {
        // Convert seconds to minutes:seconds format
        String xTime = String.format("%d:%02d", playerXTime / 60, playerXTime % 60);
        String oTime = String.format("%d:%02d", playerOTime / 60, playerOTime % 60);
        timerLabel.setText(String.format("X: %s | O: %s", xTime, oTime));
    }

    /** Initialize the game (run once) */
    public void initGame() {
        board = new Board();  // allocate the game-board
    }

    /** Reset the game-board contents and the current-state, ready for new game */
    public void newGame() {
        for (int row = 0; row < Board.ROWS; ++row) {
            for (int col = 0; col < Board.COLS; ++col) {
                board.cells[row][col].content = Seed.NO_SEED; // all cells empty
            }
        }
        currentPlayer = Seed.CROSS;    // cross plays first
        currentState = State.PLAYING;  // ready to play

        // Reset timers to initial time
        playerXTime = INITIAL_TIME;
        playerOTime = INITIAL_TIME;
        updateTimerLabel();
        
        // Start timer
        gameTimer.start();
    }

    /** Custom painting codes on this JPanel */
    @Override
    public void paintComponent(Graphics g) {  // Callback via repaint()
        super.paintComponent(g);
        setBackground(COLOR_BG); // set its background color

        board.paint(g);  // ask the game board to paint itself

        // Print status-bar message
        if (currentState == State.PLAYING) {
            statusBar.setForeground(Color.BLACK);
            statusBar.setText((currentPlayer == Seed.CROSS) ? "X's Turn" : "O's Turn");
        } else if (currentState == State.DRAW) {
            statusBar.setForeground(Color.RED);
            statusBar.setText("It's a Draw! Click to play again.");
        } else if (currentState == State.CROSS_WON) {
            statusBar.setForeground(Color.RED);
            statusBar.setText("'X' Won! Click to play again.");
        } else if (currentState == State.NOUGHT_WON) {
            statusBar.setForeground(Color.RED);
            statusBar.setText("'O' Won! Click to play again.");
        }
    }



    /** The entry "main" method */
    public static void play() {
        // Run GUI construction codes in Event-Dispatching thread for thread safety
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame(TITLE);
                // Set the content-pane of the JFrame to an instance of main JPanel
                frame.setContentPane(new GameMain());
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setLocationRelativeTo(null); // center the application window
                frame.setVisible(true);            // show it
            }
        });
    }
}