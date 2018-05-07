import javafx.scene.paint.Color;

public class PaletteColor {

    public Color color;
    public int count;

    public PaletteColor(Color c){
        color = c;
        count = 1;
    }

    public int hashCode(){
        return color.hashCode();
    }
}
