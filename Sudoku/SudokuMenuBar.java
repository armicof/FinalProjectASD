import javax.swing.*;

public class SudokuMenuBar extends JMenuBar {
    private static final long serialVersionUID = 1L;

    private Sudoku sudokuApp; // Reference to the main Sudoku frame

    // Constructor
    public SudokuMenuBar(Sudoku sudokuApp) {
        this.sudokuApp = sudokuApp;

        // Create the "File" menu
        JMenu fileMenu = new JMenu("File");
        JMenuItem newGameItem = new JMenuItem("New Game");
        JMenuItem resetGameItem = new JMenuItem("Reset Game");
        JMenuItem exitItem = new JMenuItem("Exit");

        // Add action listeners to the menu items
        newGameItem.addActionListener(e -> sudokuApp.newGame());
        resetGameItem.addActionListener(e -> sudokuApp.resetGame());
        exitItem.addActionListener(e -> System.exit(0));

        // Add items to the "File" menu
        fileMenu.add(newGameItem);
        fileMenu.add(resetGameItem);
        fileMenu.addSeparator(); // Adds a separator line
        fileMenu.add(exitItem);

        // Create the "Options" menu
        JMenu optionsMenu = new JMenu("Options");
        JMenuItem settingsItem = new JMenuItem("Settings");
        settingsItem.addActionListener(e -> showSettingsDialog());
        optionsMenu.add(settingsItem);

        // Create the "Help" menu
        JMenu helpMenu = new JMenu("Help");
        JMenuItem aboutItem = new JMenuItem("About");
        aboutItem.addActionListener(e -> showAboutDialog());
        helpMenu.add(aboutItem);

        // Add menus to the menu bar
        add(fileMenu);
        add(optionsMenu);
        add(helpMenu);
    }

    // Show settings dialog (example implementation)
    private void showSettingsDialog() {
        JOptionPane.showMessageDialog(sudokuApp, "Settings dialog not implemented yet.", "Settings", JOptionPane.INFORMATION_MESSAGE);
    }

    // Show about dialog
    private void showAboutDialog() {
        JOptionPane.showMessageDialog(sudokuApp, "Sudoku Game\nVersion 1.0\nDeveloped by\nYudhistira Armico Fidly\nVanya Patia Vinauli Gultom\nRaffi Deva Anargya", "About", JOptionPane.INFORMATION_MESSAGE);
    }
}