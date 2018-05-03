import javafx.scene.paint.Color;

import java.util.Comparator;

public class PaletteComparatorA implements Comparator<Color>{
    public int compare(Color c1, Color c2){
        double c1Sum = c1.getBlue() + c1.getGreen() + c1.getRed();
        double c2Sum = c2.getBlue() + c2.getGreen() + c2.getRed();

        if(c1Sum > c2Sum){
            return 1;
        } else if(c1Sum == c2Sum){
            return 0;
        } else{
            return -1;
        }
    }
}
