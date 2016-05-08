package game.tanks;


import game.ActionField;
import game.Bullet;
import game.field.BattleField;
import game.interfaces.Direction;
import game.interfaces.Tank;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;


public abstract class AbstractTank implements Tank {
    protected boolean isDestroyed = false;
    protected int speed = 5;
    protected Direction direction;
    protected int x;
    protected int y;
    protected ActionField af;
    protected BattleField bf;
    protected Image[] images;
    protected int[] location;
    protected Image img;
    protected String imgName;

    public AbstractTank() {
    }

    public AbstractTank(ActionField af, BattleField bf) {
        this.af = af;
        this.bf = bf;
    }

    public AbstractTank(ActionField af, BattleField bf, int x, int y, Direction direction) {
        this.af = af;
        this.bf = bf;
        this.x = x;
        this.y = y;
        this.direction = direction;
        location = af.getQuadrant(x, y);

    }


    public int getSpeed() {
        return speed;
    }

    public Direction getDirection() {
        return direction;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void updateX(int x) {
        this.x += x;
    }

    public void updateY(int y) {
        this.y += y;

    }

    public boolean isDestroyed() {
        return isDestroyed;
    }

    public void setDestroyed(boolean destroyed) {
        isDestroyed = destroyed;
    }

    public int[] getLocation() {
        return location;
    }

    public void setLocation(int[] location) {
        this.location = location;
    }

    public void turn(Direction direction) throws Exception {
        this.direction = direction;
        // af.processTurn(this);
        af.repaint();
    }

    public void move() throws Exception {
        af.processMove(this);
    }

    public void fire() throws Exception {
        Bullet bullet = new Bullet((x + 25), (y + 25), this, direction);
        af.processFire(bullet);
    }

    public void moveToCoordinates(Tank tank) throws Exception {

        int y = tank.getY();
        int x = tank.getX();

        if (this.x < x) {
            while (this.x != x) {
                turn(Direction.RIGHT);
                fire();
                move();
            }
        } else {
            while (this.x != x) {
                turn(Direction.LEFT);
                fire();
                move();
            }
        }


        if (this.y < y) {
            while (this.y != y) {
                turn(Direction.DOWN);
                fire();
                move();
            }
        } else {
            while (this.y != y) {
                turn(Direction.UP);
                fire();
                move();
            }
        }
    }

    public void destroy() {
        imgName = "exp.png";
        try {
            img = ImageIO.read(new File(imgName));
        } catch (IOException e) {
            System.out.println("There is no file");
        }
        isDestroyed = true;
        this.x = -100;
        this.y = -100;
    }

    public void respawn()throws Exception{
        Thread.sleep(1500);
        isDestroyed = false;
        String loc = bf.randomTankPosition();
        y= Integer.parseInt(loc.split("_")[0]);
        x=Integer.parseInt(loc.split("_")[1]);
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(images[getDirection().getId()], x, y,
                new ImageObserver() {
                    @Override
                    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
                        return false;
                    }
        });
    }

}
