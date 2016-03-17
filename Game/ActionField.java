package Game;

import Game.Field.*;
import Game.Interfaces.Direction;
import Game.Tanks.*;

import javax.swing.*;
import java.awt.*;

public class ActionField extends  JPanel{

    private BattleField battleField;
    private T34 defender;
    private BT7 agressor;
    private Bullet bullet;
    private Direction direction;


   void runTheGame() throws Exception {
       agressor.attack();
        defender.fire();
        defender.fire();
        defender.fire();
        defender.fire();
        defender.fire();
        defender.fire();
        defender.fire();
        defender.fire();
        defender.fire();
        defender.fire();


    }

    private boolean processInterception() throws Exception{
        String coordinates = getQuadrant(bullet.getX() , bullet.getY());
        int y = Integer.parseInt(coordinates.split("_")[0]);
        int x = Integer.parseInt(coordinates.split("_")[1]);
        if ((x >= 0 && x < 9) && (y  >= 0 && y < 9)){
            //check battlefield
            if ((battleField.scanQuadrant(y , x)!= " ")){
               battleField.updateQuadrant(y, x, " ");
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
        repaint();
    }

    public void processMove(AbstractTank tank) throws  Exception{
         Direction direction = tank.getDirection();
        tank.turn(direction);
        int i = 1;
        while (i <= 64) {
            if (direction == Direction.UP && tank.getY() >= 0) {
                tank.updateY(-1);
            } else if (direction == Direction.DOWN && tank.getY() <= 512) {
                tank.updateY(1);
            } else if (direction == Direction.LEFT && tank.getX() >= 0) {
                tank.updateX(-1);
            } else if (direction == Direction.RIGHT && tank.getX() <= 512) {
                tank.updateX(1);
            }
            i++;
            repaint();
            Thread.sleep(tank.getSpeed());
        }
    }

    public void processFire(Bullet bullet) throws Exception {
        this.bullet = bullet;

        while (bullet.getX() > -14 && bullet.getX() < 590 && bullet.getY() > -14 && bullet.getY() < 590) {
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



    // Magic bellow. Do not worry about this now, you will understand everything
    // in this course.
    // Please concentrate on your tasks only.

    public ActionField() throws Exception {
        battleField = new BattleField();
        defender = new T34(this, battleField, 64, 512, Direction.UP);
        String location = battleField.randomTankPosition();
        agressor = new BT7(this, battleField,64,64,Direction.RIGHT);
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

}
