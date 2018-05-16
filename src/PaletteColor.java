import javafx.scene.paint.Color;

/**
 * A PaletteColor is an entry in a Palette; it contains an original color, a replacement color, a counter for duplicates, and can be flagged
 * for skipping in rendering.
 * Author: nwnorris
 */
public class PaletteColor {

    public Color color;
    public int count;
    public Color newColor;
    public boolean flag; //If flag == true, this PaletteColor will not be applied to the image. Similar colors get flagged.

    /**
     * Calculates euclidean distance between this.color and another color using RGB values as XYZ coordinates in 3D space.
     * Distance = sqrt(x^2 + y^2 + z^2)
     * @param other The other color to compare distance to
     * @return The euclidean distance between this.color and other.
     */
    public double getEuclideanDistance(Color other){
        return Math.sqrt(
                Math.pow((color.getRed() - other.getRed()), 2) +
                Math.pow((color.getGreen() - other.getGreen()), 2) +
                Math.pow(color.getBlue() - other.getBlue(), 2));
    }

    /**
     * Constructor, sets default values. newColor is initialized.
     * @param c The original color of this PaletteColor.
     */
    public PaletteColor(Color c){
        color = c;
        newColor = c;
        count = 1;
        flag = false;
    }
}
