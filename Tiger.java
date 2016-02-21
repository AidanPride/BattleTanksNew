
public class Tiger extends Tank{
    private int armor;
    public Tiger(ActionField af, BattleField bf, int x, int y, Direction direction) {
        super(af, bf, x, y, direction);
        armor =1;
        speed = 15;
    }

    public Tiger(ActionField af, BattleField bf, int armor) {
        super(af, bf);
        this.armor = armor;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }
}
