import java.awt.*;

/**
 * Created by User on 22.02.2016.
 */
public class T34 extends AbstractTank{
    private int speed;
    Color color;
    public T34(ActionField af, BattleField bf) {
        super(af, bf);
        speed = 10;
         color =new Color(255, 0, 0);
    }

    public T34(ActionField af, BattleField bf, int x, int y, Direction direction) {
        super(af, bf, x, y, direction);
        speed = 10;
        color =new Color(255, 0, 0);
    }


    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, 64, 64);

        g.setColor(new Color(0, 255, 0));
        if (direction==Direction.UP) {
            g.fillRect(x + 20, y, 24, 34);
        } else if (direction == Direction.DOWN) {
            g.fillRect(x + 20, y + 30, 24, 34);
        } else if (direction == Direction.LEFT) {
            g.fillRect(x, y + 20, 34, 24);
        } else {
            g.fillRect(x + 30, y + 20, 34, 24);
        }
    }
}
