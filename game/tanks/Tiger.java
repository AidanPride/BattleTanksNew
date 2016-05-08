package game.tanks;

import game.ActionField;
import game.field.BattleField;
import game.interfaces.Direction;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Tiger extends AbstractTank{
    private int armor;
    private String IMG_UP = "TigerUP.png";
    private String IMG_DOWN = "TigerDown.png";
    private String IMG_LEFT = "TigerLeft.png";
    private String IMG_RIGHT = "TigerRight.png";

    public Tiger() {
    }

    public Tiger(ActionField af, BattleField bf) {
        super(af, bf);
        armor=1;
        speed = 14;
        setImages();
    }

    public Tiger(ActionField af, BattleField bf, int x, int y, Direction direction) {
        super(af, bf, x, y, direction);
        armor=1;
        speed = 14;
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

    @Override
    public void destroy() {
        if (armor >0){
            armor--;
        }
        else {
            super.destroy();
        }
    }

}
