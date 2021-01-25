import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

public class Snake implements Commons{
    private Point head;
    private final LinkedList<Point> body = new LinkedList<>();
    private boolean right, down, left, up;
    private Color colHead, colBody;

    public Snake(){
        initSnake();
    }

    private void initSnake(){
        down = false;
        left = true;
        up = false;
        right = false;

        colHead = new Color((int)(Math.random() * 0x1000000));
        colBody = new Color((int)(Math.random() * 0x1000000));

        int x = 620;
        int y = 160;

        head = new Point(x,y);
        body.add(new Point((620+cellsize), 160));
        body.add(new Point((620+cellsize*2), 160));
        body.add(new Point((620+cellsize*3), 160));
        body.add(new Point((620+cellsize*4), 160));
    }

    public void render(Graphics g){
        g.setColor(colHead);
        g.fillRect(head.x, head.y, cellsize-1, cellsize-1);

        g.setColor(colBody);
        for(Point p : body){
            g.fillRect(p.x, p.y, cellsize-1, cellsize-1);
        }
    }

    public void move(){
        body.removeLast();
        body.addFirst(new Point(head.x, head.y));

        if(left){
            head.x -= cellsize;
        }else if(up){
            head.y -= cellsize;
        }else if(right){
            head.x += cellsize;
        }else{
            head.y += cellsize;
        }
    }

    public void addPart(){
        Point p = body.getLast();
        body.addLast(p);
    }

    public boolean hit(){
        for(Point p : body){
            if(head.x == p.x && head.y == p.y) return true;
        }
        return false;
    }

    public boolean outsideBoarder() {
        return (head.x > 42 * cellsize - cellsize || head.x < 0 || head.y > 32 * cellsize - cellsize || head.y < 0);
    }

    public boolean eaten(int appleX, int appleY) {
        return (head.x == appleX && head.y == appleY);
    }

    public boolean occupiedLocation(int appleX, int appleY) {
        return body.contains(new Point(appleX, appleY));
    }

    /*
        Cut the snake in half
     */
    public void cutSnake(){
        if(body.size() > 4){
            for(int i = 0; i < body.size() / 2 ; i++){
                body.removeLast();
            }
        }
    }

    public void action(int key){
        if ((key == KeyEvent.VK_LEFT) && (!right)) {
            left = true;
            up = false;
            down = false;
        }

        if ((key == KeyEvent.VK_RIGHT) && (!left)) {
            right = true;
            up = false;
            down = false;
        }

        if ((key == KeyEvent.VK_UP) && (!down)) {
            up = true;
            right = false;
            left = false;
        }

        if ((key == KeyEvent.VK_DOWN) && (!up)) {
            down = true;
            right = false;
            left = false;
        }
    }


}
