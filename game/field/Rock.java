package game.field;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Rock extends AbstractField {

    public Rock(int x, int y) {
        super(x, y);
        imgName = "rock.png";
        try{
            img = ImageIO.read(new File(imgName));
        }catch (IOException e){
            System.out.println("There is no file");
        }
    }
}
