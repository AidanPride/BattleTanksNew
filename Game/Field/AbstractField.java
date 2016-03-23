package game.field;

import game.interfaces.*;

import java.awt.*;


public abstract class AbstractField implements BfObject {
    protected Color fieldColor;
    protected BattleField battleField;

    protected int x;
    protected int y;

    public AbstractField(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void draw(Graphics g) {
                    g.setColor(fieldColor);
                    g.fillRect(x, y, 64, 64);
    }

    public void destroy(){

    }
}
