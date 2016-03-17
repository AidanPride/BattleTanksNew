package Game.Field;

import Game.Interfaces.Drawable;

import java.awt.*;
import java.util.Random;

public class BattleField implements Drawable{
    private boolean COLORDED_MODE = false;
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
    public String getQuadrantXY(int x, int y) {
        return (x - 1) * 64 + "_" + (y - 1) * 64;
    }
    @Override
    public void draw(Graphics g) {
        int i = 0;
        Color cc;
        for (int v = 0; v < 9; v++) {
            for (int h = 0; h < 9; h++) {
                if (COLORDED_MODE) {
                    if (i % 2 == 0) {
                        cc = new Color(252, 241, 177);
                    } else {
                        cc = new Color(233, 243, 255);
                    }
                } else {
                    cc = new Color(180, 180, 180);
                }
                i++;
                g.setColor(cc);
                g.fillRect(h * 64, v * 64, 64, 64);
            }
        }
        for (int j = 0; j < 9; j++) {
            for (int k = 0; k <9; k++) {
                String coordinates = getQuadrantXY(j + 1, k + 1);
                int separator = coordinates.indexOf("_");
                int y = Integer.parseInt(coordinates.substring(0, separator));
                int x = Integer.parseInt(coordinates.substring(separator + 1));

                if (scanQuadrant(j,k).equals("B")) {
                    Brick brick =new Brick(this, x, y);
                    brick.draw(g);
                }
                if (scanQuadrant(j, k).equals( "E")) {
                    Eagle eagle = new Eagle(this, x, y);
                    eagle.draw(g);
                }
                if (scanQuadrant(j, k).equals("R")) {
                    Rock rock = new Rock(this, x, y);
                    rock.draw(g);
                }
                if (scanQuadrant(j, k).equals("W")) {
                    Water water = new Water(this, x, y);
                    water.draw(g);
                }
            }
        }
    }
}
