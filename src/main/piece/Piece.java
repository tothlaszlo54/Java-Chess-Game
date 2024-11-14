import java.awt.image.BufferedImage;

public class Piece {
    public BufferedImage image;
    public int x,y;
    public int col,row,preCol,preRow;
    public int color;

    public Piece(int color, int col, int row){
        this.color=color;
        this.col=col;
        this.row=row;

    }

}
