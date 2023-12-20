package noughts_and_crosess.TwoPlayer;

import noughts_and_crosess.ChosePlayer;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TwoPlayerTicTacToeGame implements ActionListener {
    //    performing encapsulation here making them secure using private access specifier
    JButton[] buttons = new JButton[9];

    JButton btnExit = new JButton("Exit");
    JButton btnClear = new JButton("New Game");

    JPanel title_panel = new JPanel();
    JLabel textfield = new JLabel();
    JLabel jlblPlayerX = new JLabel();
    JLabel jlblPlayerO = new JLabel();
    JLabel jlblDraw = new JLabel();

    JLabel PlayerX;
    JLabel PlayerO;
    JLabel Draw = new JLabel("Draw");

    JFrame frame = new JFrame("Noughts-And_Crosses");
    private final ChosePlayer playerChooser;
    public String startGame="X";
    private final String player1;
    private final String player2;

    public TwoPlayerTicTacToeGame(String player1Name, String player2Name) {
        this.player1=player1Name;
        this.player2=player2Name;

        PlayerX = new JLabel(player1);
        PlayerO = new JLabel(player2);

        this.playerChooser = new ChosePlayer(player1Name, player2Name);
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
            // Check if the button has already been clicked
            if ("X".equals(clickedButton.getText()) || "O".equals(clickedButton.getText())) {
                return; // Do nothing if the button has already been clicked
            }

            taskPerform(clickedButton);
        }
    }


    // this is main method, when we click any button its changes the text and their colors
    private void taskPerform(JButton btn) {
        btn.setText(startGame);
        btn.setFont(new Font("Arial", Font.PLAIN, 90));
        if (startGame.equalsIgnoreCase("X")) {
            btn.setForeground(Color.GREEN);
            btn.setText("X");
            startGame = "O"; // Toggle to the next player
        } else {
            btn.setForeground(Color.blue);
            btn.setText("O");
            startGame = "X"; // Toggle to the next player
        }
        playerChooser.choosePlayer(btnClear, textfield);

        new TwoPlayerGameLogic<>(
                frame, jlblPlayerX, jlblPlayerO, jlblDraw, buttons, player1, player2
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
        frame.setSize(800, 480);
        frame.getContentPane().setBackground(Color.white);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        // Creating buttons and adding action listener
        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            buttons[i].setFont(new Font("Arial", Font.PLAIN, 90));
            buttons[i].addActionListener(this);
        }

        // Setting up title
        textfield.setBackground(new Color(25, 25, 25));
        textfield.setForeground(new Color(25, 255, 0));
        textfield.setFont(new Font("Ink Free", Font.BOLD, 40));
        textfield.setHorizontalAlignment(JLabel.CENTER);
        textfield.setText("Noughts-and-Crosses");
        textfield.setOpaque(true);

        // Setting title panel to the frame
        title_panel.setLayout(new BorderLayout());
        title_panel.setBounds(0, 0, 1000, 100);
        title_panel.add(textfield);

        // Creating panels
        JPanel main_panel = new JPanel(); // Main panel for buttons
        JPanel button_panel = new JPanel(); // Game button panel
        JPanel config_Button_panel = new JPanel(); // Left panel for output and buttons

        main_panel.setLayout(null);
        button_panel.setLayout(null);
        config_Button_panel.setLayout(null);

        // Setting bounds for panels
        title_panel.setBounds(0, 0, 800, 80);
        main_panel.setBounds(0, 80, 520, 320);
        button_panel.setBounds(10, 10, 500, 300);

        // Setting bounds for buttons in the button panel
        for (int i = 0; i < 9; i++) {
            int row = i / 3;
            int col = i % 3;
            buttons[i].setBounds(10 + col * 160, 10 + row * 100, 140, 90);
        }

        button_panel.setLayout(new GridLayout(3, 3));

        // Adding buttons to the button panel
        for (int i = 0; i < 9; i++) {
            button_panel.add(buttons[i]);
        }

        // Adding button panel into main panel
        main_panel.add(button_panel);

        // Setting bounds for config_Button_panel
        config_Button_panel.setBounds(520, 80, 280, 320);

        // Setting bounds for labels and buttons in config_Button_panel
        PlayerX.setBounds(20, 20, 80, 80);
        jlblPlayerX.setBounds(120, 40, 40, 40);
        PlayerO.setBounds(20, 100, 80, 80);
        jlblPlayerO.setBounds(120, 125, 40, 40);
        Draw.setBounds(20, 180, 80, 80);
        jlblDraw.setBounds(120, 200, 40, 40);
        btnExit.setBounds(20, 290, 100, 25);
        btnClear.setBounds(20, 260, 100, 25);

        // Setting up fonts
        PlayerX.setFont(new Font("Arial", Font.BOLD, 15));
        Draw.setFont(new Font("Arial", Font.BOLD, 15));
        PlayerO.setFont(new Font("Arial", Font.BOLD, 15));
        jlblDraw.setFont(new Font("Arial", Font.BOLD, 15));
        jlblPlayerX.setFont(new Font("Arial", Font.BOLD, 15));
        jlblPlayerO.setFont(new Font("Arial", Font.BOLD, 15));
        btnExit.setFont(new Font("Arial", Font.BOLD, 15));
        btnClear.setFont(new Font("Arial", Font.BOLD, 15));

        // Adding action listeners for buttons
        btnExit.addActionListener(this);
        btnClear.addActionListener(this);

        // Adding buttons and labels to the left panel
        config_Button_panel.add(PlayerX);
        config_Button_panel.add(PlayerO);
        config_Button_panel.add(jlblDraw);
        config_Button_panel.add(jlblPlayerX);
        config_Button_panel.add(jlblPlayerO);
        config_Button_panel.add(Draw);
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

}

