package game.tanks;

import game.ActionField;
import game.field.BattleField;
import game.interfaces.Direction;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;


public class BT7 extends AbstractTank {
    private String IMG_UP = "BT7UP.png";
    private String IMG_DOWN = "BT7Down.png";
    private String IMG_LEFT = "BT7Left.png";
    private String IMG_RIGHT = "BT7Right.png";
    public BT7(ActionField af, BattleField bf) {
        super(af, bf);
        speed = 5;
        setImages();
    }

    public BT7(ActionField af, BattleField bf, int x, int y, Direction direction) {
        super(af, bf, x, y, direction);
        speed = 5;
        setImages();
    }

    private void setImages() {
        images = new Image[4];
        try {
            images[0] = ImageIO.read(new File(IMG_UP));
            images[1] = ImageIO.read(new File(IMG_DOWN));
            images[2] = ImageIO.read(new File(IMG_LEFT));
            images[3] = ImageIO.read(new File(IMG_RIGHT));
        } catch (IOException e) {
            System.out.println("There is no file");
        }
    }

}

