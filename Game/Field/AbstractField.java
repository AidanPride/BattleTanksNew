package Game.Field;

import Game.Interfaces.Drawable;

import java.awt.*;


public abstract class AbstractField implements Drawable {
    protected Color fieldColor;
    protected BattleField battleField;

    protected int x;
    protected int y;

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
