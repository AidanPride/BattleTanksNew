package gui;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

public class GameOver extends JPanel {

    public GameOver() {
        JFrame frame = new JFrame();
        frame.setLocationRelativeTo(null);
        frame.setMinimumSize(new Dimension(410, 340));
        frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        frame.getContentPane().add(new GameOverImage());
        frame.pack();
        frame.setVisible(true);
    }


    private class GameOverImage extends JPanel {
        private Image image;

        public GameOverImage() {
            try {
                image = ImageIO.read(new File("gameOver.png"));
            } catch (IOException e) {
                System.out.println("There is no file gameOver.png");
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            g.drawImage(image, 0, 0, new ImageObserver() {
                @Override
                public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
                    return false;
                }
            });
        }
    }

}
