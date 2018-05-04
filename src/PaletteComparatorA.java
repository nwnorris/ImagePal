import javafx.scene.paint.Color;

import java.util.Comparator;

public class PaletteComparatorA implements Comparator<Color>{
    public int compare(Color c1, Color c2){
        if(c1.getBlue() > c2.getBlue()){
            return 1;
        } else if(c1.getBlue() < c2.getBlue()){
            return -1;
        } else {
            if(c1.getRed() > c2.getRed()){
                return 1;
            } else if(c1.getRed() < c2.getRed()){
                return -1;
            } else {
                if(c1.getGreen() > c2.getGreen()){
                    return 1;
                } else if(c1.getGreen() < c2.getGreen()){
                    return -1;
                }
            }
        }
        return 0;

    }
}
