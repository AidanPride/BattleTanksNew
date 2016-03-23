package game;

import game.field.BattleField;
import game.field.Rock;
import game.field.Simple;
import game.field.Water;
import game.interfaces.BfObject;
import game.interfaces.Direction;
import game.tanks.AbstractTank;
import game.tanks.BT7;
import game.tanks.T34;
import game.tanks.Tiger;

import javax.swing.*;
import java.awt.*;

public class ActionField extends  JPanel{

    private BattleField battleField;
    private T34 defender;
    private BT7 agressor;
//    private Tiger agressor;
    private Bullet bullet;
    private Direction direction;



   void runTheGame() throws Exception {
       defender.fire();
       agressor.fire();
       defender.fire();
       agressor.fire();
       defender.fire();
       defender.fire();
       agressor.attack();
    }

    private boolean processInterception() throws Exception{
        String coordinates = getQuadrant(bullet.getX() , bullet.getY());
        int y = Integer.parseInt(coordinates.split("_")[0]);
        int x = Integer.parseInt(coordinates.split("_")[1]);
        BfObject bfObject = battleField.scanObjectQuadrant(y , x);
        if ((x >= 0 && x <= 8) && (y >= 0 && y <= 8)) {
            //check battlefield
            if(bfObject instanceof Rock){
                if (bullet.getTank() instanceof Tiger){
                    battleField.destroy(y, x);
                }
                return true;
            }
            if (!(bfObject instanceof Simple)&&!(bfObject instanceof Water)){
               battleField.destroy(y, x);
                return true;
            }
            //check defender
            if(checkInterception(getQuadrant(defender.getX() , defender.getY()), coordinates)){
                if(bullet.getTank().equals(defender)){
                    return false;
                  }else {
                    defender.destroy();
                    return true;
                  }
            }
            //check agressor
            if(checkInterception(getQuadrant(agressor.getX() , agressor.getY()), coordinates)){
                if(bullet.getTank().equals(agressor)){
                    return false;
                }else {
                    agressor.destroy();
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkInterception(String object, String quadrant){
        int oy = Integer.parseInt(object.split("_")[0]);
        int ox = Integer.parseInt(object.split("_")[1]);

        int qy = Integer.parseInt(quadrant.split("_")[0]);
        int qx = Integer.parseInt(quadrant.split("_")[1]);
        if ((ox >= 0 && ox < 9) && (oy  >= 0 && oy < 9)){
            if(ox==qx && oy==qy){
                return true;
            }
        }
        return false;
    }

    public void processTurn(AbstractTank tank) throws  Exception{
        direction = tank.getDirection();
        String imgName = null;
        if (direction == Direction.UP) {
            imgName = "tankUP.png";
        } else if (direction == Direction.DOWN) {
            imgName = "tankDown.png";
        } else if (direction == Direction.LEFT) {
            imgName = "tankLeft.png";
        } else if (direction == Direction.RIGHT) {
            imgName = "tankRight.png";
        }
        tank.setImgName(imgName);
        repaint();
    }

    public void processMove(AbstractTank tank) throws  Exception{
         Direction direction = tank.getDirection();
        tank.turn(direction);
        String quadrant = getQuadrant(tank.getX(), tank.getY());
        int y= Integer.parseInt(quadrant.split("_")[0]);
        int x= Integer.parseInt(quadrant.split("_")[1]);
        BfObject bfObject = battleField.scanObjectQuadrant(y , x);
        if(tank.getY() >= 0&& tank.getY() <= 512&& tank.getX() >= 0&& tank.getX() <= 512
                &&(bfObject instanceof Simple)) {
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
        String coordinates = getQuadrant(bullet.getX() , bullet.getY());
        int y = Integer.parseInt(coordinates.split("_")[0]);
        int x = Integer.parseInt(coordinates.split("_")[1]);
        BfObject bfObject = battleField.scanObjectQuadrant(y , x);
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

   public  String getQuadrant(int x, int y) {
        x = x / 64;
        y = y / 64;
        return y + "_" + x;
    }


    public ActionField() throws Exception {
        battleField = new BattleField();
        defender = new T34(this, battleField, 64, 512, Direction.UP);
        String location = battleField.randomTankPosition();
        int y= Integer.parseInt(location.split("_")[0]);
        int x= Integer.parseInt(location.split("_")[1]);
        agressor = new BT7(this, battleField,x,y,Direction.RIGHT);
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
         bullet.draw(g);

    }

    public void rotateObject(Object object, Direction direction) {

    }

}
