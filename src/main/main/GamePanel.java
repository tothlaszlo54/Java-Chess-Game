import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable {

    public static final int WIDTH = 900;
    public static final int HEIGHT = 640;
    final int FPS = 60;
    Thread gameThread;

    Board board = new Board();
    Mouse mouse = new Mouse();

    //Pieces

    public static ArrayList<Piece> pieces = new ArrayList<>();
    public static ArrayList<Piece> simPieces = new ArrayList<>();
    Piece activeP;

    //Color
    public static final int WHITE = 0;
    public static final int BLACK = 1;
    int currentColor = WHITE;

    //Booleans
    boolean canMove;
    boolean validSquare;

    public GamePanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.black);
        addMouseMotionListener(mouse);
        addMouseListener(mouse);

        setPieces();
        copyPieces(pieces, simPieces);
    }

    public void startGame() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void setPieces() {

        //White team
        pieces.add(new Pawn(WHITE, 0, 6));
        pieces.add(new Pawn(WHITE, 1, 6));
        pieces.add(new Pawn(WHITE, 2, 6));
        pieces.add(new Pawn(WHITE, 3, 6));
        pieces.add(new Pawn(WHITE, 4, 6));
        pieces.add(new Pawn(WHITE, 5, 6));
        pieces.add(new Pawn(WHITE, 6, 6));
        pieces.add(new Pawn(WHITE, 7, 6));
        pieces.add(new Rook(WHITE, 0, 7));
        pieces.add(new Rook(WHITE, 7, 7));
        pieces.add(new Knight(WHITE, 1, 7));
        pieces.add(new Knight(WHITE, 6, 7));
        pieces.add(new Bishop(WHITE, 2, 7));
        pieces.add(new Bishop(WHITE, 5, 7));
        pieces.add(new Queen(WHITE, 3, 7));
        pieces.add(new King(WHITE, 4, 7));

        //Black team
        pieces.add(new Pawn(BLACK, 0, 1));
        pieces.add(new Pawn(BLACK, 1, 1));
        pieces.add(new Pawn(BLACK, 2, 1));
        pieces.add(new Pawn(BLACK, 3, 1));
        pieces.add(new Pawn(BLACK, 4, 1));
        pieces.add(new Pawn(BLACK, 5, 1));
        pieces.add(new Pawn(BLACK, 6, 1));
        pieces.add(new Pawn(BLACK, 7, 1));
        pieces.add(new Rook(BLACK, 0, 0));
        pieces.add(new Rook(BLACK, 7, 0));
        pieces.add(new Knight(BLACK, 1, 0));
        pieces.add(new Knight(BLACK, 6, 0));
        pieces.add(new Bishop(BLACK, 2, 0));
        pieces.add(new Bishop(BLACK, 5, 0));
        pieces.add(new Queen(BLACK, 3, 0));
        pieces.add(new King(BLACK, 4, 0));
    }

    private void copyPieces(ArrayList<Piece> source, ArrayList<Piece> target) {
        target.clear();
        for (int i = 0; i < source.size(); i++) {
            target.add(source.get(i));
        }
    }

    @Override
    public void run() {

        //Game loop
        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }

    }

    private void update() {
        //Mouse button pressed
        if (mouse.pressed) {
            if (activeP == null) {
                //if activeP is null, check if you can pick up a piece
                for (Piece piece : simPieces) {
                    //if the mouse is on an ally piece, pick it up as the activeP
                    if (piece.color == currentColor &&
                            piece.col == mouse.x / Board.SQUARE_SIZE &&
                            piece.row == mouse.y / Board.SQUARE_SIZE) {
                        activeP = piece;
                    }
                }
            } else {
                //If the player is holding a piece, simulate the move
                simulate();
            }
        }

        ///Mouse Button Released///
        if (mouse.pressed == false) {
            if (activeP != null) {
                if (validSquare) {
                    //MOVE CONFIRMED

                    //Update the piece list in case a piece has been captured and removed during the simulation
                    copyPieces(simPieces, pieces);
                    activeP.upDatePosition();

                    changePlayer();

                } else {
                    // The move is not valid, so reset everything
                    copyPieces(pieces, simPieces);
                    activeP.resetPosition();
                    activeP = null;
                }
            }
        }

    }

    private void simulate() {

        canMove = false;
        validSquare = false;

        // Reset the piece list in every loop
        // This is basically for restoring the removed piece during the simulation
        copyPieces(pieces, simPieces);


        //If a piece is bieng held, update its position
        activeP.x = mouse.x - Board.HALF_SQUARE_SIZE;
        activeP.y = mouse.y - Board.HALF_SQUARE_SIZE;
        activeP.col = activeP.getCol(activeP.x);
        activeP.row = activeP.getRow(activeP.y);

        //check if the pieces is hovering over a reachable square
        if (activeP.canMove(activeP.col, activeP.row)) {
            canMove = true;

            // If hitting a Piece, remove it from the list
            if (activeP.hittingP != null) {
                simPieces.remove(activeP.hittingP.getIndex());
            }

            validSquare = true;
        }
    }

    private void changePlayer(){
        if(currentColor == WHITE){
            currentColor = BLACK;
        }else {
            currentColor = WHITE;
        }
        activeP = null;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        //board
        board.draw(g2);

        //pieces
        for (Piece p : simPieces) {
            p.draw(g2);
        }

        if (activeP != null) {
            if (canMove) {
                g2.setColor(Color.white);
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f));
                g2.fillRect(activeP.col * Board.SQUARE_SIZE, activeP.row * Board.SQUARE_SIZE,
                        Board.SQUARE_SIZE, Board.SQUARE_SIZE);
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
            }
            //Draw the active piece in the end, so it won't be hidden by the board or the colored square
            activeP.draw(g2);
        }

        //STATUS MESSAGES

        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setFont(new Font("Book Antiqua",Font.PLAIN,40));
        g2.setColor(Color.white);

        if (currentColor == WHITE){
            g2.drawString("White's turn", 840,550);
        }else {
            g2.drawString("Black's turn", 840,250);
        }
    }


}
