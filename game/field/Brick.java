package game.field;

import game.interfaces.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Brick extends AbstractField implements Destoyable{

    public Brick(int x, int y) {
        super(x, y);
        imgName = "brick.png";
        try{
            img = ImageIO.read(new File(imgName));
        }catch (IOException e){
            System.out.println("There is no file");
        }
    }

}
