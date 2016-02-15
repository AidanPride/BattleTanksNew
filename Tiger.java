
public class Tiger extends Tank{
    int armor;
    public Tiger(ActionField af, BattleField bf, int x, int y, Direction direction) {
        super(af, bf, x, y, direction);
        armor =1;
        speed = 15;
    }
}
