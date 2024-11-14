public class Bishop extends Piece {
    public Bishop(int color, int col, int row) {
        super(color, col, row);

        if (color == GamePanel.WHITE) {
            image = getImage("/piece/Bishop-piece-white");
        } else {
            image = getImage("/piece/Bishop-piece-black");
        }
    }
}
