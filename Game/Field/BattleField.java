package Game.Field;

import java.util.Random;

public class BattleField {
    final int BF_WIDTH = 590;
    final int BF_HEIGHT = 590;
        private String[][] battleField = {{" ", "B", "B", "B", "B", "B", "B", "B", " "},
            {"B", " ", "B", "B", " ", " ", " ", " ", "B"},
            {"B", "B", "B", " ", "B", "B", "B", "B", "B"},
            {"B", "B", "B", " ", " ", " ", "B", "B", "B"},
            {"R", "B", "W", "W", "W", "W", "W", "B", "R"},
            {"R", "B", " ", "B", "B", "B", " ", "B", "R"},
            {"R", "B", " ", "B", "B", " ", " ", "B", "R"},
            {"B", " ", " ", "B", "B", "B", "B", " ", "B"},
            {"B", " ", " ", "B", "E", "B", "B", "B", "B"}};

    public BattleField() {

    }

    public int getBF_WIDTH() {
        return BF_WIDTH;
    }

    public int getBF_HEIGHT() {
        return BF_HEIGHT;
    }

    public String[][] getBattleField() {
        return battleField;
    }

    public String scanQuadrant(int x, int y) {
        return battleField[x][y];
    }

    public void updateQuadrant(int x, int y, String field) {
        this.battleField[x][y] = field;
    }

    public int getDimentionX() {
        return 9;
    }

    public int getDimentionY() {
        return 9;
    }

    public String randomTankPosition() throws  Exception{
        String loc="";
        Random r = new Random();
        switch (r.nextInt(3)) {
            case 1:
                loc= "0_0";
            break;
            case 2:
                loc= "0_512";
            break;
            default:
                loc= "64_64";
            break;
        }
        return loc;

    }
}
