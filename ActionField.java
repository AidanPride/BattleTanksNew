import javax.swing.*;
import java.awt.*;

public class ActionField extends  JPanel{

    private boolean COLORDED_MODE = false;
    private BattleField battleField;
    private T34 defender;
    private Tiger agressor;
    private Bullet bullet;
    private Direction direction;


   void runTheGame() throws Exception {
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
            if (battleField.scanQuadrant(y , x)!= " "){
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
                agressor.destroy();
                agressor.respawn();
                return true;
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

    void processTurn(AbstractTank tank) throws  Exception{
        repaint();
    }

    public void processMove(AbstractTank tank) throws  Exception{
//        this.tank = tank;
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

    public String getQuadrantXY(int x, int y) {
        return (x - 1) * 64 + "_" + (y - 1) * 64;
    }




    // Magic bellow. Do not worry about this now, you will understand everything
    // in this course.
    // Please concentrate on your tasks only.

    public ActionField() throws Exception {
        battleField = new BattleField();
        defender = new T34(this, battleField, 64, 512, Direction.UP);
        String location = battleField.randomTankPosition();
        agressor = new Tiger(this, battleField,64,64,Direction.RIGHT);
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

        for (int j = 0; j < battleField.getDimentionY(); j++) {
            for (int k = 0; k < battleField.getDimentionX(); k++) {
                if (battleField.scanQuadrant(j , k).equals("B")) {
                    String coordinates = getQuadrantXY(j + 1, k + 1);
                    int separator = coordinates.indexOf("_");
                    int y = Integer.parseInt(coordinates.substring(0, separator));
                    int x = Integer.parseInt(coordinates.substring(separator + 1));
                    g.setColor(new Color(0, 0, 255));
                    g.fillRect(x, y, 64, 64);
                }
            }
        }
        // defender
//        g.setColor(new Color(255, 0, 0));
//        g.fillRect(tank.getX(), tank.getY(), 64, 64);
//
//        g.setColor(new Color(0, 255, 0));
//        if (tank.getDirection() == Direction.UP) {
//            g.fillRect(tank.getX() + 20, tank.getY(), 24, 34);
//        } else if (tank.getDirection() == Direction.DOWN) {
//            g.fillRect(tank.getX() + 20, tank.getY() + 30, 24, 34);
//        } else if (tank.getDirection() == Direction.LEFT) {
//            g.fillRect(tank.getX(), tank.getY() + 20, 34, 24);
//        } else {
//            g.fillRect(tank.getX() + 30, tank.getY() + 20, 34, 24);
//        }
         defender.draw(g);
         agressor.draw(g);
         bullet.draw(g);
         //agressor
//         g.setColor(new Color(134, 65, 71));
//         g.fillRect(agressor.getX(), agressor.getY(), 64, 64);
//
//         g.setColor(new Color(0, 255, 0));
//         if (agressor.getDirection() == Direction.UP) {
//             g.fillRect(agressor.getX() + 20, agressor.getY(), 24, 34);
//         } else if (agressor.getDirection() == Direction.DOWN) {
//             g.fillRect(agressor.getX() + 20, agressor.getY() + 30, 24, 34);
//         } else if (agressor.getDirection() == Direction.LEFT) {
//             g.fillRect(agressor.getX(), agressor.getY() + 20, 34, 24);
//         } else {
//             g.fillRect(agressor.getX() + 30, agressor.getY() + 20, 34, 24);
//         }

        //bullet
//        g.setColor(new Color(255, 255, 0));
//        g.fillRect(bullet.getX(), bullet.getY(), 14, 14);
    }

}
