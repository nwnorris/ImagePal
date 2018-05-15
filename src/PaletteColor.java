import javafx.scene.paint.Color;

public class PaletteColor {

    public Color color;
    public int count;
    public Color newColor;
    public boolean flag;
    double margin = 190/255.0;

    /**
     * Calculates euclidian distance between this.color and another color using RGB values as XYZ coordinates in 3D space.
     * @param other The other color to compare distance to
     * @return The euclidian distance between this.color and other.
     */
    public double getEuclidianDistance(Color other){
        return
                Math.sqrt(
                Math.pow((color.getRed() - other.getRed()), 2) +
                Math.pow((color.getGreen() - other.getGreen()), 2) +
                Math.pow(color.getBlue() - other.getBlue(), 2)
                );
    }

    public PaletteColor(Color c){
        color = c;
        newColor = c;
        count = 1;
        flag = false;
    }

    public int hashCode(){
        return color.hashCode();
    }
}
