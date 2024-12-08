public class Knight extends Piece {
    public Knight(int color, int col, int row) {
        super(color, col, row);

        if (color == GamePanel.WHITE) {
            image = getImage("/piece/Knight-piece-white");
        } else {
            image = getImage("/piece/Knight-piece-black");
        }
    }

    @Override
    public boolean canMove(int targetCol, int targetRow) {

        if(isWithinBoard(targetCol,targetRow)){
            //Knight can move, if its movement ratio of col and row is 1:2 or 2:1
            if(Math.abs(targetCol-preCol) * Math.abs(targetRow-preRow) == 2){
                if(isValidSquare(targetCol,targetRow)){
                    return true;
                }
            }
        }

        return false;
    }

}
