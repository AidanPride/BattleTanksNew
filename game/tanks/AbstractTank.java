package game.tanks;


import game.ActionField;
import game.Bullet;
import game.field.BattleField;
import game.field.Eagle;
import game.interfaces.Destoyable;
import game.interfaces.Direction;
import game.interfaces.Drawable;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.Random;


public abstract class AbstractTank implements Destoyable, Drawable {
    protected int speed = 5;
    protected Direction direction;
    protected int x;
    protected int y;
    protected ActionField af;
    protected BattleField bf;
    protected Color tankColor;
    protected Color towerColor;
    protected Image img;
    protected String imgName;

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

    public Color getTankColor() {
        return tankColor;
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
        af.processTurn(this);
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

    public void clean() throws Exception {
        moveToQuadrant(1, 1);
        for (int i = 2; i <= 9; i++) {
            moveToQuadrant(1, i);
            turn(Direction.DOWN);
            for (int k = 1; k <= 8; k++) {
                if (bf.scanQuadrant(k, i - 1) == "B") {
                    fire();
                }
            }
            turn(Direction.LEFT);

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
    public void attack() throws Exception {
        moveToQuadrant(2 , 5);
        turn(Direction.DOWN);
        while (bf.scanObjectQuadrant(8,4) instanceof Eagle){
            fire();
        }
    }
    @Override
    public void draw(Graphics g) {
//        g.setColor(tankColor);
//
//         g.fillRect(this.getX(), this.getY(), 64, 64);
//
//         g.setColor(towerColor);
//         if (this.getDirection() == Direction.UP) {
//             g.fillRect(this.getX() + 20, this.getY(), 24, 34);
//         } else if (this.getDirection() == Direction.DOWN) {
//             g.fillRect(this.getX() + 20, this.getY() + 30, 24, 34);
//         } else if (this.getDirection() == Direction.LEFT) {
//             g.fillRect(this.getX(), this.getY() + 20, 34, 24);
//         } else {
//             g.fillRect(this.getX() + 30, this.getY() + 20, 34, 24);
//         }
        g.drawImage(img, x, y, new ImageObserver() {
            @Override
            public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
                return false;
            }
        });
    }
}
