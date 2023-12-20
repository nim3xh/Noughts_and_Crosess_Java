package noughts_and_crosess.ThreePlayer;

import noughts_and_crosess.AbstractGameLogic;
import noughts_and_crosess.WonGame;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ThreePlayerGameLogic<T> extends AbstractGameLogic {
    private final String[] playerSymbols;
    private final String[] playerNames;

    public ThreePlayerGameLogic(JFrame frame, JLabel[] playerLabels, JLabel[] playerWinsLabels, JButton[] buttons, String[] playerSymbols, JLabel jlblDraw) {
        super(frame, playerLabels, playerWinsLabels, buttons, playerSymbols, jlblDraw);
        this.playerNames = new String[]{playerLabels[0].getText(), playerLabels[1].getText(), playerLabels[2].getText()};
        this.playerSymbols = playerSymbols;
    }

    @Override
    public void WiningGame() {
        String[] buttonTexts = new String[buttons.length];
        for (int i = 0; i < buttons.length; i++) {
            buttonTexts[i] = buttons[i].getText();
        }

        int[][] winCombinations = {
                {0, 1, 2}, {1, 2, 3}, // Row 1
                {4, 5, 6}, {5, 6, 7}, // Row 2
                {8, 9, 10}, {9, 10, 11}, // Row 3
                {12, 13, 14}, {13, 14, 15}, // Row 4
                {0, 4, 8}, {4, 8, 12}, // Column 1
                {1, 5, 9}, {5, 9, 13}, // Column 2
                {2, 6, 10}, {6, 10, 14}, // Column 3
                {3, 7, 11}, {7, 11, 15}, // Column 4
                {0, 5, 10}, {5, 10, 15}, // Diagonal from top-left to bottom-right
                {3, 6, 9}, {6, 9, 12},{7, 10, 13} // Diagonal from top-right to bottom-left
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
        wonGame.winGame(getPlayerName(symbol, playerSymbols, playerNames), buttons[combination[0]], buttons[combination[1]], buttons[combination[2]], (T) Color.YELLOW, frame);
    }
}
