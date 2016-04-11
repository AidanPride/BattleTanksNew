package game.tanks;


import game.ActionField;
import game.Bullet;
import game.field.BattleField;
import game.interfaces.Direction;
import game.interfaces.Tank;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.Random;


public abstract class AbstractTank implements Tank {
    protected int speed = 5;
    protected Direction direction;
    protected int x;
    protected int y;
    protected ActionField af;
    protected BattleField bf;
    protected Image[] images;

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

    public void moveRandome() throws Exception {
        Random r = new Random();
        int dir = 0;
        while (true) {
            dir = r.nextInt(5);
            if (dir > 0) {
                direction.setId(dir);
                fire();
                af.processMove(this);
                fire();
            }
        }
    }

    public void moveToQuadrant(int v, int h) throws Exception {
        String coordinates = bf.getQuadrantXY(v, h);
        int y = Integer.parseInt(coordinates.split("_")[0]);
        int x = Integer.parseInt(coordinates.split("_")[1]);

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
        this.x = -100;
        this.y = -100;
    }

    public void respawn()throws Exception{
        Thread.sleep(1500);
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
