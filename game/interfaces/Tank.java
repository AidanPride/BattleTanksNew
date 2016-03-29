package game.interfaces;

public interface Tank extends Drawable, Destoyable {
    int getSpeed();

    Direction getDirection();

    int getX();

    int getY();

    String getImgName();

    void updateX(int x);

    void updateY(int y);

    void setImgName(String imgName);

    void turn(Direction direction) throws Exception;


}
