package noughts_and_crosess;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

// This class represents a Draw object for different games.
// It keeps track of the game scores and updates the UI accordingly.
public class Draw {
    private final JFrame frame;
    private final JButton[] buttons;

    // Constructor for Three-Player Tic-Tac-Toe
    public Draw(JFrame frame, JButton[] buttons) {
        this.frame = frame;
        this.buttons = buttons;
    }

    // Constructor for Naughts and Crosses
    public Draw(JFrame frame, JButton[] buttons, boolean isTwoPlayer) {
        this.frame = frame;
        if (isTwoPlayer) {
            this.buttons = Arrays.copyOf(buttons, 9);
        } else {
            this.buttons = Arrays.copyOf(buttons, 16); // Update to 16 buttons for a 4x4 grid
        }
    }

    public void draw(String gameType) {
        JOptionPane.showMessageDialog(frame, "Draw", gameType, JOptionPane.INFORMATION_MESSAGE);
        for (JButton button : buttons) {
            button.setBackground(Color.red);
        }
    }

    // Overloaded draw method without parameters
    public void draw() {
        JOptionPane.showMessageDialog(frame, "Draw", "Game", JOptionPane.INFORMATION_MESSAGE);
        for (JButton button : buttons) {
            button.setBackground(Color.red);
        }
    }
}
