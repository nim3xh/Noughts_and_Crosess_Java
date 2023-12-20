package noughts_and_crosess;

import javax.swing.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import noughts_and_crosess.ThreePlayer.ThreePlayerTicTacToeGame;
import noughts_and_crosess.TwoPlayer.TwoPlayerTicTacToeGame;

public class Main {

    public static void main(String[] args) {
        try {
            setLookAndFeel();
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Show a menu to choose the game mode
        String[] options = {"Two Player", "Three Player"};
        int selectedOption = JOptionPane.showOptionDialog(null, "Choose Game Mode", "Game Mode Selection",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        if (selectedOption == 0) {
            startTwoPlayerGame();
        } else if (selectedOption == 1) {
            startThreePlayerGame();
        }
    }

    private static void startTwoPlayerGame() {
        startGame("Enter 1st Player Name", "Enter 2nd Player Name", TwoPlayerTicTacToeGame.class);
    }

    private static void startThreePlayerGame() {
        startGame("Enter 1st Player Name", "Enter 2nd Player Name", "Enter 3rd Player Name", ThreePlayerTicTacToeGame.class);
    }

    private static <T extends TwoPlayerTicTacToeGame> void startGame(String prompt1, String prompt2, Class<T> gameClass) {
        // Getting player names for the game
        Player<String> player1 = createPlayer(prompt1);
        Player<String> player2 = createPlayer(prompt2);

        // Checking if player names are different
        if (!player1.getName().equalsIgnoreCase(player2.getName())) {
            try {
                // Creating a game instance using reflection
                T game = gameClass.getDeclaredConstructor(String.class, String.class).newInstance(player1.getName(), player2.getName());
                // Game starts
                game.startGame(false);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error creating the game.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please choose different names for players.");
        }
    }

    private static <T extends ThreePlayerTicTacToeGame> void startGame(String prompt1, String prompt2, String prompt3, Class<T> gameClass) {
        // Getting player names for the game
        Player<String> player1 = createPlayer(prompt1);
        Player<String> player2 = createPlayer(prompt2);
        Player<String> player3 = createPlayer(prompt3);

        // Checking if player names are different
        if (!player1.getName().equalsIgnoreCase(player2.getName()) &&
                !player1.getName().equalsIgnoreCase(player3.getName()) &&
                !player2.getName().equalsIgnoreCase(player3.getName())) {

            try {
                // Creating a String array for player names
                String[] playerNames = {player1.getName(), player2.getName(), player3.getName()};

                // Creating a game instance using reflection
                T game = gameClass.getDeclaredConstructor(String[].class).newInstance((Object) playerNames);

                // Print debug information
                System.out.println("Game instance created successfully.");

                // Game starts
                game.startGame(false);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error creating the game: " + e.getMessage());
                e.printStackTrace(); // Print the exception details
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please choose different names for players.");
        }

    }

    // Method to set the look and feel for better design
    private static void setLookAndFeel() throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel("com.jtattoo.plaf.texture.TextureLookAndFeel");
    }

    // Method to get the name of a player from the user
    private static Player<String> createPlayer(String prompt) {
        Player<String> player = null;
        boolean validInput = false;
        while (!validInput) {
            try {
                String playerName = JOptionPane.showInputDialog(null, prompt);
                if (playerName == null) {
                    // User clicked Cancel, show an error message and ask again
                    JOptionPane.showMessageDialog(null, "Please enter a valid name.");
                } else if (playerName.isEmpty()) {
                    // User entered an empty name, show an error message and ask again
                    JOptionPane.showMessageDialog(null, "Name cannot be empty. Please enter a valid name.");
                } else {
                    player = new Player<>(playerName);
                    validInput = true;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid name.");
            }
        }
        return player;
    }
}
