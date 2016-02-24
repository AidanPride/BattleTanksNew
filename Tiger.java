import java.awt.*;

public class Tiger extends AbstractTank{
    private int armor;
    private Color color;
    private int speed;

    public Tiger(ActionField af, BattleField bf) {
        super(af, bf);
        armor=1;
        color = new Color(134, 65, 71);
        speed = 14;

    }

    public Tiger(ActionField af, BattleField bf, int x, int y, Direction direction) {
        super(af, bf, x, y, direction);
        armor=1;
        color = new Color(134, 65, 71);
        speed = 14;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    @Override
    public void destroy() {
        if (armor >0){
            armor--;
        }
        else {
            super.destroy();
        }
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
