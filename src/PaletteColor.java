import javafx.scene.paint.Color;

public class PaletteColor {

    public Color color;
    public int count;
    public Color newColor;
    public boolean flag;
    double margin = 2/255;

    public boolean similar(PaletteColor other){

        double deltaR = other.color.getRed() - color.getRed();
        double deltaG = other.color.getGreen() - color.getGreen();
        double deltaB = other.color.getBlue() - color.getBlue();

        if(deltaR <= margin && deltaG <= margin && deltaB <= margin){
            return true;
        }

        return false;
    }

    public PaletteColor(Color c){
        color = c;
        count = 1;
        flag = false;
    }

    public int hashCode(){
        return color.hashCode();
    }
}
