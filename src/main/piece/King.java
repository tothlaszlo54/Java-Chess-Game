public class King extends Piece {
    public King(int color, int col, int row) {
        super(color, col, row);

        if (color == GamePanel.WHITE) {
            image = getImage("/piece/King-piece-white");
        } else {
            image = getImage("/piece/King-piece-black");
        }
    }
}
