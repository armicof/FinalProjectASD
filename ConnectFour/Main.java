//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import javax.swing.SwingUtilities;

public class Main {
    // public static void main(String[] args) {
    //     // GameMain connectFour = new GameMain();
    //     // connectFour.play();
    //     javax.swing.SwingUtilities.invokeLater(() -> {
    //         new WelcomeScreen(); // Launch the welcome screen
    //     });
    // }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new WelcomeScreen().setVisible(true);
            }
        });
    }
}