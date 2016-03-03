package Game.Field;
import java.awt.*;

/**
 * Created by User on 03.03.2016.
 */
public class Water extends AbstractField {

    public Water(BattleField battleField, int x, int y) {
        super(battleField, x, y);
        fieldColor=new Color(35, 230, 255);
    }
}
