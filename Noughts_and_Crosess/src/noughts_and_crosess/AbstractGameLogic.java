package noughts_and_crosess;

import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public abstract class AbstractGameLogic {
    protected Draw d;
    protected ScoreManager scoreManager;
    protected JFrame frame;
    protected JButton[] buttons;

    public AbstractGameLogic(JFrame frame, JLabel[] playerLabels, JLabel[] playerWinsLabels, JButton[] buttons, String[] playerSymbols, JLabel jlblDraw) {
        this.frame = frame;
        this.buttons = buttons;
        init(playerLabels, playerWinsLabels, playerSymbols, jlblDraw);
    }

    public AbstractGameLogic(JFrame frame, JLabel jlblPlayerX, JLabel jlblPlayerO, JLabel jlblDraw, JButton[] buttons, String p1Name, String p2Name) {
        this.frame = frame;
        this.buttons = buttons;
        init(jlblPlayerX, jlblPlayerO, jlblDraw, p1Name, p2Name);
    }

    private void init(JLabel[] playerLabels, JLabel[] playerWinsLabels, String[] playerSymbols, JLabel jlblDraw) {
        this.scoreManager = new ScoreManager<>(playerWinsLabels, jlblDraw);
        this.d = new Draw(frame, buttons);
    }

    private void init(JLabel jlblPlayerX, JLabel jlblPlayerO, JLabel jlblDraw, String p1Name, String p2Name) {
        this.scoreManager = new ScoreManager<>(jlblPlayerX, jlblPlayerO, jlblDraw);
        this.d = new Draw(frame, buttons);
    }

    public abstract void WiningGame();

    protected void checkForDraw(String[] buttonTexts) {
        if (Arrays.stream(buttonTexts).allMatch(text -> text != null && !text.isEmpty())) {
            scoreManager.handleDraw();
            d.draw();
        }
        scoreManager.updateScoreLabels();
    }

    protected void disableRemainingButtons() {
        for (JButton button : buttons) {
            if (!("X".equals(button.getText()) || "O".equals(button.getText()))) {
                button.setEnabled(false);
            }
        }
    }

    protected abstract void handleWin(String symbol, int[] combination);

    protected String getPlayerName(String symbol, String[] playerSymbols, String[] playerNames) {
        for (int i = 0; i < playerSymbols.length; i++) {
            if (playerSymbols[i].equals(symbol)) {
                return playerNames[i];
            }
        }
        return "";
    }
}
