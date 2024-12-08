public class Queen extends Piece {
    public Queen(int color, int col, int row) {
        super(color, col, row);

        if (color == GamePanel.WHITE) {
            image = getImage("/piece/Queen-piece-white");
        } else {
            image = getImage("/piece/Queen-piece-black");
        }
    }

    @Override
    public boolean canMove(int targetCol, int targetRow) {

        if (isWithinBoard(targetCol, targetRow) && isSameSquare(targetCol, targetRow) == false) {

            //vertical&horizontal
            if (targetCol == preCol || targetRow == preRow){
                if (isValidSquare(targetCol,targetRow) && pieceIsOnStraightLine(targetCol,targetRow)==false){
                    return true;
                }
            }

            //diagonal
            if (Math.abs(targetCol-preCol) == Math.abs(targetRow-preRow)){
                if (isValidSquare(targetCol,targetRow) && pieceIsOnDiagonalLine(targetCol,targetRow)==false){
                    return true;
                }
            }
        }
        return false;
    }
}
