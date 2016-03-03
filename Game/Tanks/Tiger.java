package Game.Tanks;

import java.awt.*;
import Game.Field.BattleField;
import Game.Interfaces.Direction;
import Game.ActionField;

public class Tiger extends AbstractTank{
    private int armor;


    public Tiger(ActionField af, BattleField bf) {
        super(af, bf);
        armor=1;
        tankColor = new Color(134, 65, 71);
        towerColor =new Color(0, 255, 0);
        speed = 14;

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
