public class TTTGame {
    private final TTTBoard board;
    private String currentPlayer;
    private int moveCount;

    public TTTGame() {
        this.board = new TTTBoard();
        resetGame();
    }

    public void resetGame() {
        board.clearBoard();
        this.currentPlayer = "X";
        this.moveCount = 0;
    }

    public String processTurn(int row, int col) {
        if (!board.isValidMove(row, col)) {
            return "INVALID";
        }

        board.setMove(row, col, currentPlayer);
        moveCount++;

        if (board.checkWin(currentPlayer)) {
            return "WIN_" + currentPlayer;
        } else if (moveCount == 9) {
            return "TIE";
        }

        togglePlayer();
        return "CONTINUE";
    }

    private void togglePlayer() {
        currentPlayer = currentPlayer.equals("X") ? "O" : "X";
    }

    public String getCurrentPlayer() {
        return currentPlayer;
    }
}