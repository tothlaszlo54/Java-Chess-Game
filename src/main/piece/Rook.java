public class Rook extends Piece {
    public Rook(int color, int col, int row) {
        super(color, col, row);

        if (color == GamePanel.WHITE) {
            image = getImage("/piece/Rook-piece-white");
        } else {
            image = getImage("/piece/Rook-piece-black");
        }
    }
}
