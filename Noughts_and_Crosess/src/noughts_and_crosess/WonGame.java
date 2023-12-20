package noughts_and_crosess;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.Color;

public class WonGame<T> {
    public void winGame(String winner, JButton button1, JButton button2, JButton button3, T color, JFrame frame) {
        JOptionPane.showMessageDialog(frame, "Player " + winner + " Won", "Three-Player Tic-Tac-Toe", JOptionPane.INFORMATION_MESSAGE);
        // Change the background color of the buttons that resulted in winning the game
        button1.setBackground((Color) color);
        button2.setBackground((Color) color);
        button3.setBackground((Color) color);
    }
}
