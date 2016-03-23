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


    public Tiger(ActionField af, BattleField bf) {
        super(af, bf);
        armor=1;
        tankColor = new Color(134, 65, 71);
        towerColor =new Color(0, 255, 0);
        speed = 14;
        imgName = "tank.png";
        try {
            img = ImageIO.read(new File(imgName));
        } catch (IOException e) {
            System.out.println("There is no file");
        }

    }

    public Tiger(ActionField af, BattleField bf, int x, int y, Direction direction) {
        super(af, bf, x, y, direction);
        armor=1;
        tankColor = new Color(134, 65, 71);
        towerColor =new Color(0, 255, 0);
        speed = 14;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
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
