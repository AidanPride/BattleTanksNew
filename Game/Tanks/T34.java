package Game.Tanks;

import java.awt.*;
import Game.Field.BattleField;
import Game.Interfaces.Direction;
import Game.ActionField;
public class T34 extends AbstractTank{

    public T34(ActionField af, BattleField bf) {
        super(af, bf);
        speed = 10;
         tankColor =new Color(255, 0, 0);
        towerColor =new Color(0, 255, 0);
    }

    public T34(ActionField af, BattleField bf, int x, int y, Direction direction) {
        super(af, bf, x, y, direction);
        speed = 10;
        tankColor =new Color(255, 0, 0);
        towerColor =new Color(0, 255, 0);
    }

    }
