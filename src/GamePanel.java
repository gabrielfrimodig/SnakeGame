import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GamePanel extends JPanel implements ActionListener, Commons{
    private final int DELAY = 150;
    private Timer timer;

    Apple apple = new Apple();
    Snake snake = new Snake();

    public GamePanel(){
        initPanel();
    }

    private void initPanel(){
        addKeyListener(new GameKeyAdapter());
        setBackground(Color.BLACK);
        setFocusable(true);

        setPreferredSize(new Dimension(width, height));

        timer = new Timer(DELAY, this);
        timer.start();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        apple.render(g);
        snake.render(g);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        snake.move();

        if(snake.eaten(apple.getX(), apple.getY())){
            snake.addPart();
            apple.newLocation();
            while(snake.occupiedLocation(apple.getX(), apple.getY())){
                apple.newLocation();
            }
        }

        if(snake.outsideBoarder()){
            timer.stop();
            System.exit(0);
        }

        if(snake.hit()){
            snake.cutSnake();
        }

        repaint();
    }

    private class GameKeyAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent event) {
            int key = event.getKeyCode();

            snake.action(key);

            if (key == KeyEvent.VK_SPACE){
                if(timer.isRunning()) {
                    timer.stop();
                }else {
                    timer.start();
                }
            }
            if (key == KeyEvent.VK_ESCAPE) {
                System.exit(0);
            }
        }
    }
}
