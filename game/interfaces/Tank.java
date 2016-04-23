package game.interfaces;

public interface Tank extends Drawable, Destoyable {
    int getSpeed();

    Direction getDirection();

    int getX();

    int getY();

    void updateX(int x);

    void updateY(int y);

    void move() throws Exception;
    void turn(Direction direction) throws Exception;

    void fire() throws Exception;

    void moveToCoordinates(Tank tank) throws Exception;

    int[] getLocation();

    void setLocation(int[] location);

}
