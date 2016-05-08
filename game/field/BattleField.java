
package game.field;

import game.interfaces.BfObject;
import game.interfaces.Drawable;

import java.awt.*;
import java.util.Random;

public class BattleField implements Drawable {
    private boolean COLORDED_MODE = false;
    final int BF_WIDTH = 590;
    final int BF_HEIGHT = 590;
    private String[][] battleFieldString =
            {{" ", "B", "B", "B", "B", "B", "B", "B", " "},
                    {"B", " ", "B", "B", " ", " ", " ", " ", "B"},
                    {"B", "B", "B", " ", "B", "B", "B", "B", "B"},
                    {"B", "B", "B", " ", " ", " ", "B", "B", "B"},
                    {"R", "B", "W", "W", "W", "W", "W", "B", "R"},
                    {"R", "B", " ", "B", "B", "B", " ", "B", "R"},
                    {"R", "B", " ", "B", "B", " ", " ", "B", "R"},
                    {"B", " ", " ", "B", "B", "B", "B", " ", "B"},
                    {"B", " ", " ", "B", "E", "B", "B", "B", "B"}};
    private BfObject[][] battleField = new BfObject[9][9];

    public BattleField() {
        Maps m = new Maps();
        battleFieldString = m.map();
        drawBattlefield();
    }

    public int getBF_WIDTH() {
        return BF_WIDTH;
    }

    public int getBF_HEIGHT() {
        return BF_HEIGHT;
    }

    public BfObject[][] getBattleField() {
        return battleField;
    }

    public String scanQuadrant(int x, int y) {
        return battleFieldString[x][y];
    }

    public BfObject scanObjectQuadrant(int x, int y) {
        return battleField[x][y];
    }

    public void destroy(int x, int y) {
        this.battleField[x][y] = new Simple(y * 64, x * 64);
    }

    public int getDimentionX() {
        return 9;
    }

    public int getDimentionY() {
        return 9;
    }

    public String randomTankPosition() throws Exception {
        String loc = "";
        Random r = new Random();
        switch (r.nextInt(3)) {
            case 1:
                loc = "0_0";
                break;
            case 2:
                loc = "0_512";
                break;
            default:
                loc = "64_64";
                break;
        }
        return loc;

    }

    public int[] getQuadrantXY(int x, int y) {
        return new int[]{(x) * 64, (y) * 64};
    }

    private void drawBattlefield() {
        for (int j = 0; j < 9; j++) {
            for (int k = 0; k < 9; k++) {
                int[] coordinates = getQuadrantXY(j, k);
                int y = coordinates[0];
                int x = coordinates[1];
                BfObject bfObject;
                if (scanQuadrant(j, k).equals("B")) {
                    bfObject = new Brick(x, y);
                } else if (scanQuadrant(j, k).equals("E")) {
                    bfObject = new Eagle(x, y);
                } else if (scanQuadrant(j, k).equals("R")) {
                    bfObject = new Rock(x, y);
                } else if (scanQuadrant(j, k).equals("W")) {
                    bfObject = new Water(x, y);
                } else {
                    bfObject = new Simple(x, y);
                }
                battleField[j][k] = bfObject;
            }
        }
    }

    @Override
    public void draw(Graphics g) {
        for (int j = 0; j < 9; j++) {
            for (int k = 0; k < 9; k++) {
                battleField[j][k].draw(g);
            }
        }
    }

    private class Maps {
        String[][] b1 =
                {{" ", "B", "B", "B", "B", "B", "B", "B", " "},
                        {"B", " ", "B", "B", " ", " ", " ", " ", "B"},
                        {"B", "B", "B", " ", "B", "B", "B", "B", "B"},
                        {"B", "B", "B", " ", " ", " ", "B", "B", "B"},
                        {"R", "B", "W", "W", "W", "W", "W", "B", "R"},
                        {"R", "B", " ", "B", "B", "B", " ", "B", "R"},
                        {"R", "B", " ", "B", "B", " ", " ", "B", "R"},
                        {"B", " ", " ", "B", "B", "B", "B", " ", "B"},
                        {"B", " ", " ", "B", "E", "B", "B", "B", "B"}};

        String[][] b2 =
                {{" ", "B", "B", "B", "B", "B", "B", "B", " "},
                        {"B", " ", "B", "B", " ", " ", "B", " ", "B"},
                        {"B", "B", "B", " ", "B", "B", "B", "B", "B"},
                        {"B", "B", "B", " ", " ", " ", "B", "B", "B"},
                        {"B", "B", "B", "B", "B", "B", "B", "B", "B"},
                        {"B", "B", " ", "B", "B", "B", " ", "B", "B"},
                        {"B", "B", " ", "B", "B", "B", "B", "B", "B"},
                        {"B", " ", " ", "B", "B", "B", "B", " ", "B"},
                        {"B", " ", " ", "B", "E", "B", "B", "B", "B"}};

        String[][] b3 =
                {{" ", "B", "B", "B", "B", "B", "B", "B", " "},
                        {"B", " ", "B", "B", " ", " ", " ", " ", "B"},
                        {"B", "B", "B", " ", "B", "B", "B", "B", "B"},
                        {"B", "B", "B", " ", " ", " ", "B", "B", "B"},
                        {"R", "B", "W", "W", "W", "W", "W", "B", "R"},
                        {"R", "B", " ", "B", "B", "B", " ", "B", "R"},
                        {"R", "B", " ", "B", "B", " ", " ", "B", "R"},
                        {"B", " ", " ", "B", "R", "B", "B", " ", "B"},
                        {"B", " ", " ", "B", "E", "B", "B", "B", "B"}};

        String[][] b4 =
                {{" ", "B", "B", "B", "B", "B", "B", "B", " "},
                        {"B", " ", "B", "B", " ", " ", " ", " ", "B"},
                        {"B", "B", "B", " ", "B", "B", "B", "B", "B"},
                        {"B", "B", "B", " ", " ", " ", "B", "B", "B"},
                        {"R", "B", "W", "W", "W", "W", "W", "B", "R"},
                        {"R", "B", " ", "B", "B", "B", " ", "B", "R"},
                        {"R", "B", " ", "B", "B", " ", " ", "B", "R"},
                        {"B", " ", " ", "B", "R", "R", "B", " ", "B"},
                        {"B", " ", " ", "B", "E", "R", "B", "B", "B"}};

        public Maps() {
        }

        private String[][] map() {
            Random r = new Random();
            int map = r.nextInt(4);
            if (map == 1) {
                return b1;
            } else if (map == 2) {
                return b2;
            } else if (map == 3) {
                return b3;
            } else {
                return b4;
            }

        }
    }
}
