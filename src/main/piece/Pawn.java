public class Pawn extends Piece {
    public Pawn(int color, int col, int row) {
        super(color, col, row);

        if (color == GamePanel.WHITE) {
            image = getImage("/piece/Pawn-piece-white");
        } else {
            image = getImage("/piece/Pawn-piece-black");
        }
    }
}
