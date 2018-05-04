import javafx.scene.paint.Color;

import java.util.Comparator;

public class PaletteColorComparator implements Comparator<PaletteColor>{
    public int compare(PaletteColor c1, PaletteColor c2){
//        if(c1.color.getBlue() > c2.color.getBlue()){
//            return 1;
//        } else if(c1.color.getBlue() < c2.color.getBlue()){
//            return -1;
//        } else {
//            if(c1.color.getRed() > c2.color.getRed()){
//                return 1;
//            } else if(c1.color.getRed() < c2.color.getRed()){
//                return -1;
//            } else {
//                if(c1.color.getGreen() > c2.color.getGreen()){
//                    return 1;
//                } else if(c1.color.getGreen() < c2.color.getGreen()){
//                    return -1;
//                }
//            }
//        }
//        return 0;

        if(c1.color.getHue() > c2.color.getHue()){
            return 1;
        } else if(c1.color.getHue() < c2.color.getHue()){
            return -1;
        }
        return 0;
    }
}
