import java.awt.*;

public class Apple implements Commons{
    private int x, y;

    public Apple(){
        initApple();
    }

    private void initApple(){
        this.x = ((int)(Math.random() * 42) * 20);
        this.y = ((int)(Math.random() * 32) * 20);
    }

    public void newLocation(){
        x = ((int)(Math.random() * 42) * 20);
        y = ((int)(Math.random() * 32) * 20);
    }

    public void render(Graphics g){
        g.setColor(Color.red);
        g.fillOval(x, y, cellsize, cellsize);
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }
}
