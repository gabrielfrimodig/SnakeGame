import javax.swing.*;
import java.awt.*;

public class SnakeGame extends JFrame{

    public SnakeGame(){
        initScreen();
    }

    private void initScreen(){
        add(new GamePanel());
        setResizable(false);
        pack();
        setTitle("Snake Game");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args){
        EventQueue.invokeLater(() -> {
            JFrame frame = new SnakeGame();
            frame.setVisible(true);
        });
    }
}
