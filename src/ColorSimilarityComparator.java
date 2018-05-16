import javafx.scene.paint.Color;
import java.util.Comparator;

/**
 * A comparator to sort based on how "similar" two colors are to a third comparison color. Similarity is just euclidean distance.
 * Author: nwnorris
 */
public class ColorSimilarityComparator implements Comparator<PaletteColor>{

    private Color comparison;

    public ColorSimilarityComparator(Color c){
        comparison = c;
    }

    /**
     * Standard compare method, compares distance from one color to the comparison color.
     * @param a The first PaletteColor to compare.
     * @param b The second PaletteColor to compare.
     * @return 1 if a is more dissimilar than b, -1 if a is more similar than b, 0 if they are equal.
     */
    public int compare(PaletteColor a, PaletteColor b){
        double aDistance = a.getEuclideanDistance(comparison);
        double bDistance = b.getEuclideanDistance(comparison);
        return Double.compare(aDistance, bDistance);
    }


}
