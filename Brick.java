import java.awt.*;

/**
 * Created by User on 03.03.2016.
 */
public class Brick extends AbstractField{


    public Brick(BattleField battleField, int x, int y) {
        super(battleField, x, y);
        fieldColor=new Color(0, 0, 255);
    }
}
