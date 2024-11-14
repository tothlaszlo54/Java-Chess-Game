public class Queen extends Piece {
    public Queen(int color, int col, int row) {
        super(color, col, row);

        if (color == GamePanel.WHITE) {
            image = getImage("/piece/Queen-piece-white");
        } else {
            image = getImage("/piece/Queen-piece-black");
        }
    }
}
