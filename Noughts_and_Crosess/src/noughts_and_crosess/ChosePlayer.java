package noughts_and_crosess;

import javax.swing.JButton;
import javax.swing.JLabel;

public class ChosePlayer<T> {
    private T currentPlayer;
    private final T[] playerNames;
    private int currentPlayerIndex;

    public ChosePlayer(T[] playerNames) {
        this.currentPlayer = playerNames[0];
        this.playerNames = playerNames;
        this.currentPlayerIndex = 0;
    }

    public ChosePlayer(T player1, T player2) {
        this.currentPlayer = player1;
        this.playerNames = (T[]) new Object[]{player1, player2};
        this.currentPlayerIndex = 0;
    }

    public void choosePlayer(JButton btnClear, JLabel label) {
        if (btnClear.getText().equals("Start Game")) {
            String gameType = (playerNames.length == 2) ? "Naughts and Crosses" : "Three-Player Tic-Tac-Toe";
            label.setText(gameType);
        } else {
            label.setText(currentPlayer + "'s turn");
        }

        currentPlayer = switchPlayer();
    }

    public void reset(JButton btn) {
        btn.setText(null);
        btn.setBackground(null);
    }

    public T switchPlayer() {
        currentPlayerIndex = (currentPlayerIndex + 1) % playerNames.length;
        currentPlayer = playerNames[currentPlayerIndex];
        return currentPlayer;
    }

    public T getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(T currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public T[] getPlayerNames() {
        return playerNames;
    }

    public void setCurrentPlayerIndex(int currentPlayerIndex) {
        this.currentPlayerIndex = currentPlayerIndex;
    }

    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }
}

