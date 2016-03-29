package game.tanks;

import game.ActionField;
import game.field.BattleField;
import game.field.Eagle;
import game.interfaces.Direction;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class BT7 extends AbstractTank {

    public BT7(ActionField af, BattleField bf) {
        super(af, bf);
        speed = 5;
    }

    public BT7(ActionField af, BattleField bf, int x, int y, Direction direction) {
        super(af, bf, x, y, direction);
        speed = 5;
        imgName = "tankRight.png";
        try {
            img = ImageIO.read(new File(imgName));
        } catch (IOException e) {
            System.out.println("There is no file");
        }
    }

    public void attack() throws Exception {
        moveToQuadrant(2, 5);
        turn(Direction.DOWN);
        while (bf.scanObjectQuadrant(8, 4) instanceof Eagle) {
            fire();
        }
    }

}

