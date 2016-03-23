package game.field;

import game.interfaces.BfObject;

import java.awt.*;
import java.awt.image.ImageObserver;

public abstract class AbstractField implements BfObject {
    protected int x;
    protected int y;

    protected Image img;
    protected String imgName;

    public AbstractField(int x, int y) {
        this.x = x;
        this.y = y;

    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(img, x, y, new ImageObserver() {
            @Override
            public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
                return false;
            }
        });
    }

    @Override
    public void destroy() {

    }

}
