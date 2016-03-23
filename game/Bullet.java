package game;

import game.interfaces.Destoyable;
import game.interfaces.Direction;
import game.interfaces.Drawable;
import game.tanks.AbstractTank;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

public class Bullet implements Drawable, Destoyable {

    private int speed = 2;
    private int x;
    private int y;
    private AbstractTank tank;
    private Direction direction;
    private Image img;
    private String imgName = "bullet.png";

    public Bullet(int x , int y , AbstractTank tank, Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.tank=tank;
        try {
            img = ImageIO.read(new File(imgName));
        } catch (IOException e) {
            System.out.println("There is no file");
        }

    }

    public AbstractTank getTank() {
        return tank;
    }

    public int getX() {
        return x;
    }


    public int getY() {
        return y;
    }

    public int getSpeed() {
        return speed;
    }

    public Direction getDirection() {
        return direction;
    }
    public void updateX(int x){
            this.x+=x;
    }
    public void updateY(int y){
            this.y+=y;

    }

    public  void destroy(){
        this.x = -100;
        this.y = -100;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(img, x, y, new ImageObserver() {
            @Override
            public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
                return false;
            }
        });
    }
}
