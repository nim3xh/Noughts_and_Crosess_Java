package noughts_and_crosess;

import javax.swing.JLabel;

public class ScoreManager<T> {
    private final JLabel[] playerWinsLabels;
    private final JLabel jlblDraw;

    // Constructor for Three-Player Tic-Tac-Toe
    public ScoreManager(JLabel[] playerWinsLabels, JLabel jlblDraw) {
        this.playerWinsLabels = playerWinsLabels;
        this.jlblDraw = jlblDraw;
    }

    // Constructor for Naughts and Crosses
    public ScoreManager(JLabel jlblPlayerX, JLabel jlblPlayerO, JLabel jlblDraw) {
        this.playerWinsLabels = new JLabel[]{jlblPlayerX, jlblPlayerO};
        this.jlblDraw = jlblDraw;
    }

    public void updateScoreLabels() {
        for (int i = 0; i < playerWinsLabels.length; i++) {
            playerWinsLabels[i].setText(String.valueOf(CounterManager.getPlayerCounter(i)));
        }
        jlblDraw.setText(String.valueOf(CounterManager.getDrawCounter()));
    }

    public void handleWin(T symbol) {
        int playerIndex = getPlayerIndex(symbol);
        CounterManager.incrementPlayerCounter(playerIndex);
        updateScoreLabels();
    }

    public void handleDraw() {
        CounterManager.incrementDrawCounter();
        updateScoreLabels();
    }

    private static class CounterManager {
        private static final int[] playerCounters = new int[3]; // Update to 3 for three players
        private static int drawCounter = 0;

        public static void setDrawCounter(int drawCounter) {
            CounterManager.drawCounter = drawCounter;
        }

        public static int getPlayerCounter(int playerIndex) {
            return playerCounters[playerIndex];
        }

        public static int getDrawCounter() {
            return drawCounter;
        }

        public static void incrementPlayerCounter(int playerIndex) {
            playerCounters[playerIndex]++;
        }

        public static void incrementDrawCounter() {
            drawCounter++;
        }
    }

    private int getPlayerIndex(T symbol) {
        return switch (symbol.toString()) {
            case "X" -> 0;
            case "O" -> 1;
            case "Z" -> 2;
            default -> -1;
        };
    }
}
