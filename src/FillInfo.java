//package ass5;

import java.awt.Color;
import java.io.File;

/**
 * @author Yaron's Laptop
 * this class is used to store information about the.
 * fills of blocks.
 */
public class FillInfo {
    private Color color;
    private int hitPoint;
    private File image;

    /**
     * create a new Fill Information based on an array of strings that contains the
     * color and the information about the hit points of the fill.
     * @param s the array of string we are using for the fill.
     */
    public FillInfo(String[] s) {
        // split the fill-k part
        String[] firstPart = s[0].split("-");
        // read the hit point number corresponding to the color.
        this.hitPoint = Integer.parseInt(firstPart[1]);
        // split the color to the description and the color itself.
        String[] secondPart = s[1].split("\\(");
        switch (secondPart[0]) {
        case "color": // if the fill is a color.
            if (secondPart.length > 2) { // if it is rgb second part is of size 3
                // get the rgb color.
                this.color = BlockFactory.getColorFromString(secondPart[1], secondPart[2]);
            } else { // it is an explicit color name
                // get the color.
                this.color = BlockFactory.getColorFromString(secondPart[1], null);
            }
            break;
        case "image": // if it is an image we save it.
            this.image = new File(secondPart[1]);
            break;
        default:
            break;
        }
    }

    /**
     * return the color of the fill.
     * @return the color of the fill.
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * return the number of hit point that start the fill.
     * @return the number of hit points.
     */
    public int getHitPoint() {
        return hitPoint;
    }

    /**
     * return the path to the image that is the fill.
     * @return the path to the image.
     */
    public File getImage() {
        return image;
    }
}
