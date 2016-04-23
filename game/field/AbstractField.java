package game.field;

import game.interfaces.BfObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

public abstract class AbstractField implements BfObject {
    protected int x;
    protected int y;
    protected int[] location;

    protected Image img;
    protected String imgName;

    public AbstractField(int x, int y) {
        this.x = x;
        this.y = y;
        location = new int[]{y / 64, x / 64};

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
        imgName = "exp.png";
        try {
            img = ImageIO.read(new File(imgName));
        } catch (IOException e) {
            System.out.println("There is no file");
        }
    }

    public int[] getLocation() {
        return location;
    }

    public void setLocation(int[] location) {
        this.location = location;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }
}
