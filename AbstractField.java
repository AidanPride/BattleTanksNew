import java.awt.*;

/**
 * Created by User on 03.03.2016.
 */
public abstract class AbstractField implements Drawable{
    protected Color fieldColor;
    private BattleField battleField;

    private int x;
    private int y;

    public AbstractField(BattleField battleField, int x, int y) {
        this.battleField = battleField;
        this.x = x;
        this.y = y;
    }

    @Override
    public void draw(Graphics g) {
                    g.setColor(fieldColor);
                    g.fillRect(x, y, 64, 64);
    }

}
