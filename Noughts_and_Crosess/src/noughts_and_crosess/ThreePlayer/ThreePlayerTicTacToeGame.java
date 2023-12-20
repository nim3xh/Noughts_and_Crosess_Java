package noughts_and_crosess.ThreePlayer;

import noughts_and_crosess.ChosePlayer;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ThreePlayerTicTacToeGame implements ActionListener {
    JButton[] buttons = new JButton[16]; // Update to 16 buttons for a 4x4 grid
    JButton btnExit = new JButton("Exit");
    JButton btnClear = new JButton("New Game");

    JPanel title_panel = new JPanel();
    JLabel textfield = new JLabel();
    JLabel[] playerLabels = new JLabel[3];
    JLabel[] playerWinsLabels = new JLabel[3];

    JFrame frame = new JFrame("Three-Player Tic-Tac-Toe");
    private final ChosePlayer playerChooser;
    private final String[] playerSymbols = {"X", "O", "Z"};
    JLabel Draw = new JLabel("Draw");
    JLabel drawCountLabel=new JLabel("0");

    public ThreePlayerTicTacToeGame(String[] playerNames) {

        for (int i = 0; i < 3; i++) {
            playerLabels[i] = new JLabel(playerNames[i]);
            playerWinsLabels[i] = new JLabel("0");
        }

        this.playerChooser = new ChosePlayer(playerNames);
        initializeUI();
    }

    public void resetButton(JButton btn) {
        playerChooser.reset(btn);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource();

        if (clickedButton == btnExit) {
            System.exit(0);
        } else if (clickedButton == btnClear) {
            startGame(true);
            for (JButton button : buttons) {
                resetButton(button);
            }
        } else {
            String buttonText = clickedButton.getText();
            if (buttonText != null && (buttonText.equals(playerSymbols[0]) || buttonText.equals(playerSymbols[1]) || buttonText.equals(playerSymbols[2]))) {
                return; // Do nothing if the button has already been clicked
            }

            taskPerform(clickedButton);
        }
    }


    private void taskPerform(JButton btn) {
        int currentPlayerIndex = playerChooser.getCurrentPlayerIndex();
        btn.setText(playerSymbols[currentPlayerIndex]);
        btn.setFont(new Font("Arial", Font.PLAIN, 60));

        btn.setForeground(getPlayerColor(currentPlayerIndex));

        playerChooser.choosePlayer(btnClear, textfield);

        new ThreePlayerGameLogic<>(
                frame, playerLabels, playerWinsLabels, buttons, playerSymbols,drawCountLabel
        ).WiningGame();
    }

    public void startGame(boolean start) {
        if (start) {
            btnClear.setText("New Game");
            for (JButton button : buttons) {
                button.setEnabled(true);
            }
        } else {
            btnClear.setText("Start Game");
            for (JButton button : buttons) {
                button.setEnabled(false);
            }
        }
    }

    public void initializeUI() {
        // Setting up frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.getContentPane().setBackground(Color.white);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        // Creating buttons and adding action listener
        for (int i = 0; i < 16; i++) {
            buttons[i] = new JButton();
            buttons[i].setFont(new Font("Arial", Font.PLAIN, 60));
            buttons[i].addActionListener(this);
        }

        // Setting up title
        textfield.setBackground(new Color(25, 25, 25));
        textfield.setForeground(new Color(25, 255, 0));
        textfield.setFont(new Font("Ink Free", Font.BOLD, 40));
        textfield.setHorizontalAlignment(JLabel.CENTER);
        textfield.setText("Three-Player Tic-Tac-Toe");
        textfield.setOpaque(true);

        // Setting title panel to the frame
        title_panel.setLayout(new BorderLayout());
        title_panel.setBounds(0, 0, 1000, 100);
        title_panel.add(textfield);

        // Creating panels
        JPanel main_panel = new JPanel();
        JPanel button_panel = new JPanel();
        JPanel config_Button_panel = new JPanel();

        main_panel.setLayout(null);
        button_panel.setLayout(null);
        config_Button_panel.setLayout(null);

        // Setting bounds for panels
        title_panel.setBounds(0, 0, 800, 80);
        main_panel.setBounds(0, 80, 640, 640);
        button_panel.setBounds(10, 10, 620, 620);

        // Setting bounds for buttons in the button panel
        for (int i = 0; i < 16; i++) {
            int row = i / 4;
            int col = i % 4;
            buttons[i].setBounds(10 + col * 150, 10 + row * 150, 140, 140);
        }

        button_panel.setLayout(new GridLayout(4, 4));

        // Adding buttons to the button panel
        for (int i = 0; i < 16; i++) {
            button_panel.add(buttons[i]);
        }

        // Adding button panel into the main panel
        main_panel.add(button_panel);

        // Setting bounds for config_Button_panel
        config_Button_panel.setBounds(640, 80, 280, 640);

        // Setting bounds for labels and buttons in config_Button_panel
        for (int i = 0; i < 3; i++) {
            playerLabels[i].setBounds(20, 20 + i * 80, 80, 80);
            playerWinsLabels[i].setBounds(120, 40 + i * 80, 40, 40);
            playerWinsLabels[i].setFont(new Font("Arial", Font.BOLD, 15));
            config_Button_panel.add(playerLabels[i]);
            config_Button_panel.add(playerWinsLabels[i]);
        }


        // Setting bounds for labels and buttons in config_Button_panel
        for (int i = 0; i < 3; i++) {
            playerLabels[i].setBounds(20, 20 + i * 80, 80, 80);
            playerWinsLabels[i].setBounds(120, 40 + i * 80, 40, 40);
            playerWinsLabels[i].setFont(new Font("Arial", Font.BOLD, 15));
            config_Button_panel.add(playerLabels[i]);
            config_Button_panel.add(playerWinsLabels[i]);
        }


        Draw.setBounds(20, 20 + 3 * 80, 80, 80);
        Draw.setFont(new Font("Arial", Font.BOLD, 15));

        drawCountLabel.setBounds(120, 20 + 3 * 80, 100, 80);
        drawCountLabel.setFont(new Font("Arial", Font.BOLD, 15));

        config_Button_panel.add(Draw);
        config_Button_panel.add(drawCountLabel);



        btnExit.setBounds(20, 390, 100, 25);
        btnClear.setBounds(20, 360, 100, 25);

        btnExit.setFont(new Font("Arial", Font.BOLD, 15));
        btnClear.setFont(new Font("Arial", Font.BOLD, 15));

        btnExit.addActionListener(this);
        btnClear.addActionListener(this);

        config_Button_panel.add(btnExit);
        config_Button_panel.add(btnClear);

        // Setting up border
        Border br = BorderFactory.createLineBorder(Color.black);
        Container c = frame.getContentPane();
        main_panel.setBorder(br);
        config_Button_panel.setBorder(br);

        // Adding panels to the Container of the JFrame
        c.add(main_panel);
        c.add(config_Button_panel);
        c.add(title_panel, BorderLayout.NORTH);
    }

    private Color getPlayerColor(int playerIndex) {
        return switch (playerIndex) {
            case 0 -> Color.GREEN;
            case 1 -> Color.BLUE;
            case 2 -> Color.RED;
            default -> Color.BLACK;
        };
    }
}
