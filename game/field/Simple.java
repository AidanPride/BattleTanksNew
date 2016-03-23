package game.field;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Simple extends AbstractField {
    public Simple(int x, int y) {
        super(x, y);
        imgName = "grass.png";
        try{
            img = ImageIO.read(new File(imgName));
        }catch (IOException e){
            System.out.println("There is no file");
        }
    }
}
