package noughts_and_crosess.TwoPlayer;

import noughts_and_crosess.AbstractGameLogic;
import noughts_and_crosess.WonGame;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class TwoPlayerGameLogic<T> extends AbstractGameLogic {
    private final String p1Name;
    private final String p2Name;

    public TwoPlayerGameLogic(JFrame frame, JLabel jlblPlayerX, JLabel jlblPlayerO, JLabel jlblDraw, JButton[] buttons, String p1Name, String p2Name) {
        super(frame, jlblPlayerX, jlblPlayerO, jlblDraw, buttons, p1Name, p2Name);
        this.p1Name = p1Name;
        this.p2Name = p2Name;
    }

    @Override
    public void WiningGame() {
        String[] buttonTexts = new String[buttons.length];
        for (int i = 0; i < buttons.length; i++) {
            buttonTexts[i] = buttons[i].getText();
        }

        int[][] winCombinations = {
                {0, 1, 2}, {3, 4, 5}, {6, 7, 8},  // Rows
                {0, 3, 6}, {1, 4, 7}, {2, 5, 8},  // Columns
                {0, 4, 8}, {2, 4, 6}               // Diagonals
        };

        for (int[] combination : winCombinations) {
            String symbol = buttonTexts[combination[0]];

            if (symbol != null && !symbol.isEmpty() &&
                    buttonTexts[combination[1]] != null && buttonTexts[combination[2]] != null &&
                    buttonTexts[combination[1]].equals(symbol) &&
                    buttonTexts[combination[2]].equals(symbol)) {
                handleWin(symbol, combination);
                disableRemainingButtons();
                return;
            }
        }

        checkForDraw(buttonTexts);
    }

    @Override
    protected void handleWin(String symbol, int[] combination) {
        scoreManager.handleWin(symbol);
        WonGame<T> wonGame = new WonGame<>();
        wonGame.winGame(symbol.equals("X") ? p1Name : p2Name, buttons[combination[0]], buttons[combination[1]], buttons[combination[2]], (T) Color.YELLOW, frame);
    }
}
