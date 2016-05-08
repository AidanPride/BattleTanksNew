package game;

import game.field.BattleField;
import game.field.Rock;
import game.field.Simple;
import game.field.Water;
import game.interfaces.BfObject;
import game.interfaces.Direction;
import game.interfaces.Tank;
import game.tanks.AI;
import game.tanks.BT7;
import game.tanks.T34;
import game.tanks.Tiger;

import javax.swing.*;
import java.awt.*;

public class ActionField extends  JPanel{

    private BattleField battleField;
    private T34 defender;
    private BT7 agressor;
    private Tiger atacker;
    private Tank tank;
    private Bullet bullet;
    private Direction direction;


   void runTheGame() throws Exception {
//       AI AiBT7 = new AI(this, battleField, agressor);
//       AI AiTiger = new AI(this, battleField, atacker);
//       AI AiT34 = new AI(this, battleField , defender);
//       AiTiger.findDefender();
//       AiBT7.attackHeadQuater();
       AI AiTank = new AI(this, battleField, tank);
       AiTank.attackHeadQuater();
    }

    private boolean processInterception() throws Exception{
        int[] coordinates = getQuadrant(bullet.getX(), bullet.getY());
        int y = coordinates[0];
        int x = coordinates[1];
        BfObject bfObject = battleField.scanObjectQuadrant(y , x);
        if ((x >= 0 && x <= 8) && (y >= 0 && y <= 8)) {
            //check battlefield
            if(bfObject instanceof Rock){
                if (bullet.getTank() instanceof Tiger){
                    bfObject.destroy();
                    repaint();
                    Thread.sleep(10);
                    battleField.destroy(y, x);
                }
                return true;
            }
            if (!(bfObject instanceof Simple)&&!(bfObject instanceof Water)){
                bfObject.destroy();
                repaint();
                Thread.sleep(300);
               battleField.destroy(y, x);
                return true;
            }
            //check defender
            if(checkInterception(getQuadrant(defender.getX() , defender.getY()), coordinates)){
                if(bullet.getTank().equals(defender)){
                    return false;
                  }else {
                    defender.destroy();
                    defender.respawn();
                    return true;
                  }
            }
            //check agressor
            if(checkInterception(getQuadrant(agressor.getX() , agressor.getY()), coordinates)){
                if(bullet.getTank().equals(agressor)){
                    return false;
                }else {
                    agressor.destroy();
                    agressor.respawn();
                    return true;
                }
            }
            //check atacker
            if (checkInterception(getQuadrant(atacker.getX(), atacker.getY()), coordinates)) {
                if (bullet.getTank().equals(atacker)) {
                    return false;
                } else {
                    atacker.destroy();
                    atacker.respawn();
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkInterception(int[] object, int[] quadrant) {
        int oy = object[0];
        int ox = object[1];

        int qy = quadrant[0];
        int qx = quadrant[1];
        if ((ox >= 0 && ox < 9) && (oy  >= 0 && oy < 9)){
            if(ox==qx && oy==qy){
                return true;
            }
        }
        return false;
    }

//    public void processTurn(Tank tank) throws Exception {
//        direction = tank.getDirection();
//        String imgName = tank.getImgName();
//        if (direction == Direction.UP) {
//            imgName = "tankUP.png";
//        } else if (direction == Direction.DOWN) {
//            imgName = "tankDown.png";
//        } else if (direction == Direction.LEFT) {
//            imgName = "tankLeft.png";
//        } else if (direction == Direction.RIGHT) {
//            imgName = "tankRight.png";
//        }
//        tank.setImgName(imgName);
//        repaint();
//    }

    public BfObject nextQuadrant(Tank tank) throws Exception {
        Direction direction = tank.getDirection();
        tank.turn(direction);
        int[] quadrant = getQuadrant(tank.getX(), tank.getY());
        int y = quadrant[0];
        int x = quadrant[1];
        BfObject bfObject = battleField.scanObjectQuadrant(y, x);

        if (tank.getY() >= 0 && tank.getY() <= 512 && tank.getX() >= 0 && tank.getX() <= 512
                && (bfObject instanceof Simple)) {

            // check next quadrant
            if (direction == Direction.UP && tank.getY() >= 1) {
                y--;
            } else if (direction == Direction.DOWN && tank.getY() <= 511) {
                y++;
            } else if (direction == Direction.RIGHT && tank.getX() <= 511) {
                x++;
            } else if (direction == Direction.LEFT && tank.getX() >= 1) {
                x--;
            }
            bfObject = battleField.scanObjectQuadrant(y, x);
        }
        return bfObject;
    }

    public boolean nextQuadrantIsFree(Tank tank) throws Exception {
        Direction direction = tank.getDirection();
        tank.turn(direction);
        int[] quadrant = getQuadrant(tank.getX(), tank.getY());
        int y = quadrant[0];
        int x = quadrant[1];
        BfObject bfObject = battleField.scanObjectQuadrant(y, x);

        if (tank.getY() >= 0 && tank.getY() <= 512 && tank.getX() >= 0 && tank.getX() <= 512
                && (bfObject instanceof Simple)) {

            // check next quadrant
            if (direction == Direction.UP && tank.getY() >= 1) {
                y--;
            } else if (direction == Direction.DOWN && tank.getY() <= 511) {
                y++;
            } else if (direction == Direction.RIGHT && tank.getX() <= 511) {
                x++;
            } else if (direction == Direction.LEFT && tank.getX() >= 1) {
                x--;
            }
            BfObject bfobject = battleField.scanObjectQuadrant(y, x);
            if (bfobject instanceof Simple) {
                return true;
            }
        }
        return false;
    }


    public void processMove(Tank tank) throws Exception {
        Direction direction = tank.getDirection();
        tank.turn(direction);
        int[] quadrant = getQuadrant(tank.getX(), tank.getY());
        int y = quadrant[0];
        int x = quadrant[1];
        BfObject bfObject = battleField.scanObjectQuadrant(y, x);

        if (tank.getY() >= 0 && tank.getY() <= 512 && tank.getX() >= 0 && tank.getX() <= 512
                && (bfObject instanceof Simple)) {

            if (!nextQuadrantIsFree(tank)) {
                System.out.println("Illegal move to " + tank.getX() + " " + tank.getY());
                return;
            }

            //move
            for (int i=1; i<= 64; i++) {
                if (direction == Direction.UP && tank.getY() >= 0) {
                    tank.updateY(-1);
                } else if (direction == Direction.DOWN && tank.getY() <= 512) {
                    tank.updateY(1);
                } else if (direction == Direction.LEFT && tank.getX() >= 0) {
                    tank.updateX(-1);
                } else if (direction == Direction.RIGHT && tank.getX() <= 512) {
                    tank.updateX(1);
                }
                repaint();
                Thread.sleep(tank.getSpeed());
            }
            System.out.println("Move to " + tank.getX() + " " + tank.getY());
        } else {
            System.out.println("Illegal move to " + tank.getX() + " " + tank.getY());
        }

    }

    public void processFire(Bullet bullet) throws Exception {
        this.bullet = bullet;

        while (bullet.getX() > 0 && bullet.getX() < 562 && bullet.getY() > 0 && bullet.getY() < 562) {
            if (bullet.getDirection() == Direction.UP) {
                bullet.updateY(-1);
            }
            else if (bullet.getDirection() == Direction.DOWN) {
                bullet.updateY(1);
            }
            else if (bullet.getDirection() == Direction.LEFT) {
                bullet.updateX(-1);
            }
            else {
                bullet.updateX(1);
            }
            if(processInterception()){
                bullet.destroy();
            }
            repaint();
            Thread.sleep(bullet.getSpeed());
        }
    }

    public int[] getQuadrant(int x, int y) {
        x = x / 64;
        y = y / 64;
        return new int[]{y, x};
    }


    public ActionField(Tank tank) throws Exception {
        battleField = new BattleField();
        defender = new T34(this, battleField, 64, 512, Direction.UP);
        agressor = new BT7(this, battleField, 0, 0, Direction.RIGHT);
        atacker = new Tiger(this, battleField, 512, 0, Direction.LEFT);

        if (tank instanceof Tiger) {
            this.tank = atacker;
        }
        if (tank instanceof T34) {
            this.tank = defender;
        }
        if (tank instanceof BT7) {
            this.tank = agressor;
        }
        bullet = new Bullet(-100, -100, defender, Direction.UP);

        JFrame frame = new JFrame("BATTLE FIELD");
        frame.setMinimumSize(new Dimension(battleField.getBF_WIDTH(), battleField.getBF_HEIGHT() + 22));
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().add(this);
        frame.pack();
        frame.setVisible(true);
    }
     @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
         battleField.draw(g);
         defender.draw(g);
         agressor.draw(g);
         atacker.draw(g);
         bullet.draw(g);
    }

    public T34 getDefender() {
        return defender;
    }

    public BT7 getAgressor() {
        return agressor;
    }

    public Tiger getAtacker() {
        return atacker;
    }
}
