import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    public static final int WIDTH = 1100;
    public static final int HEIGHT = 800;

    public GamePanel (){
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        setBackground(Color.black);
    }

}
