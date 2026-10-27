import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class TTTFrame {
    private final JFrame mainFrame;
    private final TTTTile[][] tiles; // Using custom component from
    private final TTTGame gameController;

    private JTextArea statusDisplay;
    private JButton quitButton;

    public TTTFrame() {
        this.gameController = new TTTGame();
        this.mainFrame = new JFrame("Tic Tac Toe");
        this.tiles = new TTTTile[3][3]; //

        initializeUI();
    }

    private void initializeUI() {
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(new BorderLayout());

        JPanel boardPanel = new JPanel(new GridLayout(3, 3));

        // Single listener instance mapped to cleanly routed coordinates
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                tiles[r][c] = new TTTTile(r, c); //
                tiles[r][c].setFont(new Font("Arial", Font.BOLD, 40));
                tiles[r][c].addActionListener(this::handleTileClick);
                boardPanel.add(tiles[r][c]);
            }
        }

        statusDisplay = new JTextArea(3, 20);
        statusDisplay.setEditable(false);
        statusDisplay.setText("Player X's turn.");

        mainFrame.add(boardPanel, BorderLayout.CENTER);
        mainFrame.add(new JScrollPane(statusDisplay), BorderLayout.SOUTH);

        mainFrame.setSize(400, 450);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }

    private void handleTileClick(ActionEvent e) {
        TTTTile clickedTile = (TTTTile) e.getSource(); //
        int r = clickedTile.getRow(); //
        int c = clickedTile.getCol(); //

        String activePlayer = gameController.getCurrentPlayer();
        String result = gameController.processTurn(r, c);

        if (result.equals("INVALID")) {
            JOptionPane.showMessageDialog(mainFrame, "That square is already taken!");
            return;
        }

        // View updates UI strictly based on data result feedback
        clickedTile.setText(activePlayer);

        if (result.startsWith("WIN_")) {
            statusDisplay.append("\nPlayer " + activePlayer + " Wins!");
            disableBoard();
        } else if (result.equals("TIE")) {
            statusDisplay.append("\nIt's a Tie Game!");
        } else {
            statusDisplay.setText("Player " + gameController.getCurrentPlayer() + "'s turn.");
        }
    }

    private void disableBoard() {
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                tiles[r][c].setEnabled(false);
            }
        }
    }
}

