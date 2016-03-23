
package game.field;

import game.interfaces.*;

import java.awt.*;
import java.util.Random;

public class BattleField implements Drawable{
    private boolean COLORDED_MODE = false;
    final int BF_WIDTH = 590;
    final int BF_HEIGHT = 590;
    private String[][] battleFieldString = {{" ", "B", "B", "B", "B", "B", "B", "B", " "},
            {"B", " ", "B", "B", " ", " ", " ", " ", "B"},
            {"B", "B", "B", " ", "B", "B", "B", "B", "B"},
            {"B", "B", "B", " ", " ", " ", "B", "B", "B"},
            {"R", "B", "W", "W", "W", "W", "W", "B", "R"},
            {"R", "B", " ", "B", "B", "B", " ", "B", "R"},
            {"R", "B", " ", "B", "R", " ", " ", "B", "R"},
            {"B", " ", " ", "B", "R", "B", "B", " ", "B"},
            {"B", " ", " ", "B", "E", "B", "B", "B", "B"}};
    private BfObject[][] battleField = new BfObject[9][9];

    public BattleField() {
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
        this.battleField[x][y] = new Simple(y*64, x*64);
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
    public String getQuadrantXY(int x, int y) {
        return (x - 1) * 64 + "_" + (y - 1) * 64;
    }

    private void drawBattlefield(){
        for (int j = 0; j < 9; j++) {
            for (int k = 0; k <9; k++) {
                String coordinates = getQuadrantXY(j + 1, k + 1);
                int separator = coordinates.indexOf("_");
                int y = Integer.parseInt(coordinates.substring(0, separator));
                int x = Integer.parseInt(coordinates.substring(separator + 1));
                BfObject bfObject;
                if (scanQuadrant(j,k).equals("B")) {
                    bfObject =new Brick(x, y);
                }
                else if (scanQuadrant(j, k).equals( "E")) {
                    bfObject = new Eagle(x, y);
                }
                else if (scanQuadrant(j, k).equals("R")) {
                    bfObject = new Rock(x, y);
                }
                else if (scanQuadrant(j, k).equals("W")) {
                    bfObject = new Water(x, y);
                }
                else {
                    bfObject = new Simple(x, y);
                }
                battleField[j][k]=bfObject;
            }
        }
    }
    @Override
    public void draw(Graphics g) {
        for (int j = 0; j < 9; j++) {
            for (int k = 0; k <9; k++) {
                battleField[j][k].draw(g);
            }
        }
    }
}
