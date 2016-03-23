package game.interfaces;

/**
 * Created by User on 23.03.2016.
 */
public interface Tank extends Drawable, Destoyable {
    int getSpeed();

    Direction getDirection();

    int getX();

    int getY();

    void updateX(int x);

    void updateY(int y);

    void setImgName(String imgName);

    void turn(Direction direction) throws Exception;


}
