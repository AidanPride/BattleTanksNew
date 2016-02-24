/**
 * Created by User on 22.02.2016.
 */
public class T34 extends AbstractTank{
    private int speed = 10;
    public T34(ActionField af, BattleField bf) {
        super(af, bf);
    }

    public T34(ActionField af, BattleField bf, int x, int y, Direction direction) {
        super(af, bf, x, y, direction);
    }
}
