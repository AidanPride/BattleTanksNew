package game.tanks;

import game.ActionField;
import game.field.BattleField;
import game.interfaces.Direction;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class T34 extends AbstractTank{
    private String IMG_UP = "T34UP.png";
    private String IMG_DOWN = "T34Down.png";
    private String IMG_LEFT = "T34Left.png";
    private String IMG_RIGHT = "T34Right.png";

    public T34() {
    }

    public T34(ActionField af, BattleField bf) {
        super(af, bf);
        speed = 10;
        setImages();
    }

    public T34(ActionField af, BattleField bf, int x, int y, Direction direction) {
        super(af, bf, x, y, direction);
        speed = 10;
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
