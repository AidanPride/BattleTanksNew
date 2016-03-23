package game.tanks;

import game.ActionField;
import game.field.BattleField;
import game.interfaces.Direction;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class BT7 extends AbstractTank {

    public BT7(ActionField af, BattleField bf) {

        super(af, bf);
        tankColor = new Color(198, 20, 191);
        towerColor =new Color(0, 255, 0);
        speed = 5;
    }

    public BT7(ActionField af, BattleField bf, int x, int y, Direction direction) {

        super(af, bf, x, y, direction);
        tankColor = new Color(198, 20, 191);
        towerColor =new Color(0, 255, 0);
        speed = 5;
        imgName = "tankRight.png";
        try {
            img = ImageIO.read(new File(imgName));
        } catch (IOException e) {
            System.out.println("There is no file");
        }
    }

}

