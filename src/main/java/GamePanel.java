import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

    public static final int WIDTH = 1100;
    public static final int HEIGHT = 800;
    final int FPS = 60;
    Thread gameThread;

    public GamePanel (){
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        setBackground(Color.black);
    }

    private void update(){

    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

    }

    @Override
    public void run() {

    }
}
