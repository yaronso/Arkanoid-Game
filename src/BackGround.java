//package ass5;

import java.awt.Color;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import biuoop.DrawSurface;

/**
 * @author Yaron's Laptop
 * this is a sprite that is the back ground of the.
 * levels.
 */
public class BackGround implements Sprite {
    private Color fill = null;
    private Image image = null;

    /**
     * create a new background based on the configuration string that is inputed
     * from the file.
     * @param config the configuration string that contains the data about the
     * background.
     */
    public BackGround(String config) {
        // split the configuration and replace bad characters.
        String[] line = config.split("\\(");
        line[1] = line[1].replaceAll("\r", "");
        switch (line[0]) { // check if the configuration is color or image.
        case "color":
            if (line.length > 2) { // if it is of rgb form
                this.fill = BlockFactory.getColorFromString(line[1], line[2]);
            } else { // if the color is explicitly named.
                this.fill = BlockFactory.getColorFromString(line[1], null);
            }
            break;
        case "image":
            // load the image.
            InputStream in = ClassLoader.getSystemClassLoader().getResourceAsStream(line[1]);
            try {
                // read and save the image.
                this.image = ImageIO.read(in);
            } catch (IOException e) {
                System.out.println("problem loading image to background");
            }
            try {
                // close the image as we don't need it anymore.
                in.close();
            } catch (IOException e) {
                System.out.println("problem closing image file in background.");
            }
            break;
        default:
            break;
        }
    }

    @Override
    public void drawOn(DrawSurface d) {
        if (this.fill != null) { // if we have a fill
            // we set the color of the fill as the fill
            d.setColor(fill);
            // set the color of the screen
            d.fillRectangle(0, 0, 800, 600);
        } else if (this.image != null) { // our background is an image.
            d.drawImage(0, 0, this.image);
        }
    }

    @Override
    public void timePassed(double dt) {
    }

}
