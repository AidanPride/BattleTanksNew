import java.awt.*;

public class BT7 extends AbstractTank {
    private Color color;
    private int speed;
    public BT7(ActionField af, BattleField bf) {

        super(af, bf);
        color = new Color(198, 20, 191);
        speed = 5;
    }

    public BT7(ActionField af, BattleField bf, int x, int y, Direction direction) {
        super(af, bf, x, y, direction);
    }

    @Override
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

