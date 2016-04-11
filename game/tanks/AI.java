package game.tanks;

import game.ActionField;
import game.field.BattleField;
import game.field.Eagle;
import game.field.Rock;
import game.interfaces.BfObject;
import game.interfaces.Direction;
import game.interfaces.Tank;

public class AI {
    private BattleField bf;
    private ActionField af;
    private Tank tank;

    public AI() {
    }

    public AI(ActionField af, BattleField bf, Tank tank) {
        this.af = af;
        this.bf = bf;
        this.tank = tank;

    }

    public void attackHeadQuater() throws Exception {
        BfObject up = bf.scanObjectQuadrant(7, 4);
        BfObject left = bf.scanObjectQuadrant(8, 3);
        BfObject right = bf.scanObjectQuadrant(8, 5);
        if (!(up instanceof Rock)) {
            moveToQuadrant(8, 4);
            tank.turn(Direction.DOWN);
            while (bf.scanObjectQuadrant(8, 4) instanceof Eagle) {
                tank.fire();
            }
        } else if (!(right instanceof Rock)) {
            moveToQuadrant(8, 5);
            tank.turn(Direction.LEFT);
            while (bf.scanObjectQuadrant(8, 4) instanceof Eagle) {
                tank.fire();
            }
        }
    }

    public void findDefender() throws Exception {
        T34 defender = af.getDefender();
        tank.moveToCoordinates(defender);
    }

    private void moveToQuadrant(int v, int h) throws Exception {

        String coordinates = bf.getQuadrantXY(v, h);
        int y = Integer.parseInt(coordinates.split("_")[0]);
        int x = Integer.parseInt(coordinates.split("_")[1]);

        if (tank.getX() < x) {
            while (tank.getX() != x) {
                tank.turn(Direction.RIGHT);
                tank.fire();
                tank.move();
            }
        } else {
            while (tank.getX() != x) {
                tank.turn(Direction.LEFT);
                tank.fire();
                tank.move();
            }
        }


        if (tank.getY() < y) {
            while (tank.getY() != y) {
                tank.turn(Direction.DOWN);
                tank.fire();
                tank.move();
            }
        } else {
            while (tank.getY() != y) {
                tank.turn(Direction.UP);
                tank.fire();
                tank.move();
            }
        }

    }

}
