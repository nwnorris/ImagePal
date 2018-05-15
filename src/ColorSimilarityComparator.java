import javafx.scene.paint.Color;

import java.util.Comparator;

public class ColorSimilarityComparator implements Comparator<PaletteColor>{

    private Color comparison;

    public ColorSimilarityComparator(Color c){
        comparison = c;
    }

    public int compare(PaletteColor a, PaletteColor b){
        double aDistance = a.getEuclidianDistance(comparison);
        double bDistance = b.getEuclidianDistance(comparison);
        //System.out.println(aDistance + " | " + bDistance);
        if(aDistance > bDistance){
            return 1;
        } else if (aDistance < bDistance){
            return -1;
        } else {
            return 0;
        }
    }


}
