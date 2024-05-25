import java.awt.*;
import java.awt.event.*;

public class TicTacToe extends Frame implements ActionListener {
    // Tic Tac Toe is fun! gamering I am gamering

    private Button[][] buttons = new Button[3][3];
    private char currentPlayer = 'X';
    private Label statusLabel = new Label("Player X's turn");

    public TicTacToe() {
        setLayout(new BorderLayout());
        Panel board = new Panel();
        board.setLayout(new GridLayout(3, 3));

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new Button("");
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 60));
                buttons[i][j].addActionListener(this);
                board.add(buttons[i][j]);
            }
        }

        Button newGameButton = new Button("New Game");
        newGameButton.addActionListener(e -> resetGame());

        add(statusLabel, BorderLayout.NORTH);
        add(board, BorderLayout.CENTER);
        add(newGameButton, BorderLayout.SOUTH);

        setTitle("Tic Tac Toe");
        setSize(800, 800);
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Button clickedButton = (Button) e.getSource();
        if (!clickedButton.getLabel().equals("")) {
            return;
        }

        clickedButton.setLabel(String.valueOf(currentPlayer));
        if (checkWinner()) {
            statusLabel.setText("Player " + currentPlayer + " wins!");
            disableButtons();
        } else if (isBoardFull()) {
            statusLabel.setText("The game is a draw!");
        } else {
            switchPlayer();
            statusLabel.setText("Player " + currentPlayer + "'s turn");
        }
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    private boolean checkWinner() {
        // Check rows, columns, and diagonals
        for (int i = 0; i < 3; i++) {
            if (buttons[i][0].getLabel().equals(String.valueOf(currentPlayer)) &&
                    buttons[i][1].getLabel().equals(String.valueOf(currentPlayer)) &&
                    buttons[i][2].getLabel().equals(String.valueOf(currentPlayer))) {
                return true;
            }
            if (buttons[0][i].getLabel().equals(String.valueOf(currentPlayer)) &&
                    buttons[1][i].getLabel().equals(String.valueOf(currentPlayer)) &&
                    buttons[2][i].getLabel().equals(String.valueOf(currentPlayer))) {
                return true;
            }
        }
        if (buttons[0][0].getLabel().equals(String.valueOf(currentPlayer)) &&
                buttons[1][1].getLabel().equals(String.valueOf(currentPlayer)) &&
                buttons[2][2].getLabel().equals(String.valueOf(currentPlayer))) {
            return true;
        }
        if (buttons[0][2].getLabel().equals(String.valueOf(currentPlayer)) &&
                buttons[1][1].getLabel().equals(String.valueOf(currentPlayer)) &&
                buttons[2][0].getLabel().equals(String.valueOf(currentPlayer))) {
            return true;
        }
        return false;
    }

    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getLabel().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }

    private void disableButtons() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setEnabled(false);
            }
        }
    }

    private void resetGame() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setLabel("");
                buttons[i][j].setEnabled(true);
            }
        }
        currentPlayer = 'X';
        statusLabel.setText("Player X's turn");
    }

    public static void main(String[] args) {
        new TicTacToe();
    }
}
