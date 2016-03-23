package game.tanks;

import game.ActionField;
import game.field.BattleField;
import game.interfaces.Direction;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class T34 extends AbstractTank{

    public T34(ActionField af, BattleField bf) {
        super(af, bf);
        speed = 10;
//         tankColor =new Color(255, 0, 0);
//        towerColor =new Color(0, 255, 0);
    }

    public T34(ActionField af, BattleField bf, int x, int y, Direction direction) {
        super(af, bf, x, y, direction);
        speed = 10;
//        tankColor =new Color(255, 0, 0);
//        towerColor =new Color(0, 255, 0);
        imgName = "tankUP.png";
        try {
            img = ImageIO.read(new File(imgName));
        } catch (IOException e) {
            System.out.println("There is no file");
        }
    }

    }
