package Game.Field;

import java.awt.*;

/**
 * Created by User on 03.03.2016.
 */
public class Rock extends AbstractField {

    public Rock(BattleField battleField, int x, int y) {
        super(battleField, x, y);
        fieldColor=new Color(134, 65, 71);
    }
}
