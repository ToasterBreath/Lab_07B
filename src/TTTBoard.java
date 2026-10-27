public class TTTBoard {
    private final String[][] grid;
    private static final int ROW = 3;
    private static final int COL = 3;

    public TTTBoard() {
        this.grid = new String[ROW][COL];
        clearBoard();
    }

    public void clearBoard() {
        for (int r = 0; r < ROW; r++) {
            for (int c = 0; c < COL; c++) {
                grid[r][c] = " ";
            }
        }
    }

    public boolean isValidMove(int row, int col) {
        if (row < 0 || row >= ROW || col < 0 || col >= COL) return false;
        return grid[row][col].equals(" ");
    }

    public void setMove(int row, int col, String player) {
        grid[row][col] = player;
    }

    public boolean checkWin(String player) {
        // Rows & Columns
        for (int i = 0; i < 3; i++) {
            if (grid[i][0].equals(player) && grid[i][1].equals(player) && grid[i][2].equals(player)) return true;
            if (grid[0][i].equals(player) && grid[1][i].equals(player) && grid[2][i].equals(player)) return true;
        }
        // Diagonals
        if (grid[0][0].equals(player) && grid[1][1].equals(player) && grid[2][2].equals(player)) return true;
        if (grid[0][2].equals(player) && grid[1][1].equals(player) && grid[2][0].equals(player)) return true;

        return false;
    }
}