public class Knight extends Piece {
    public Knight(int color, int col, int row) {
        super(color, col, row);

        if (color == GamePanel.WHITE) {
            image = getImage("/piece/Knight-piece-white");
        } else {
            image = getImage("/piece/Knight-piece-black");
        }
    }
}
