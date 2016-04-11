package game.field;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

public class Water extends AbstractField {

    public Water(int x, int y) {
        super(x, y);
        imgName = "water.png";
        try{
            img = ImageIO.read(new File(imgName));
        }catch (IOException e){
            System.out.println("There is no file");
        }
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Composite org = g2d.getComposite();
        Composite transluent = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f);
        g2d.setComposite(transluent);
        g.drawImage(img, x, y, new ImageObserver() {
            @Override
            public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
                return false;
            }
        });
        ((Graphics2D) g).setComposite(org);

    }
}
