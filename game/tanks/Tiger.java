package game.tanks;

import game.ActionField;
import game.field.BattleField;
import game.interfaces.Direction;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Tiger extends AbstractTank{
    private int armor;


    public Tiger(ActionField af, BattleField bf) {
        super(af, bf);
        armor=1;
        speed = 14;
    }

    public Tiger(ActionField af, BattleField bf, int x, int y, Direction direction) {
        super(af, bf, x, y, direction);
        armor=1;
        speed = 14;
        imgName = "tankLeft.png";
        try {
            img = ImageIO.read(new File(imgName));
        } catch (IOException e) {
            System.out.println("There is no file");
        }
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

    public void findDefender() throws Exception {
        T34 defender = af.getDefender();
        moveToCoordinates(defender);
    }


}
