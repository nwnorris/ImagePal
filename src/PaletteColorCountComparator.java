import java.util.Comparator;

/**
 * Compares two PaletteColors based on how many times they were used in an image.
 * Author: nwnorris
 */
public class PaletteColorCountComparator implements Comparator<PaletteColor>{

    /**
     * Compares two PaletteColors based on their count values. This assumes they were utilized by some sort of pixel counting algorithm.
     * @param a The first PaletteColor to analyze.
     * @param b The second PaletteColor to analyze.
     * @return 1 if a is more frequent than b, -1 if a is less frequent than b, 0 if they are equally frequent.
     */
    public int compare(PaletteColor a, PaletteColor b){
       return Integer.compare(a.count, b.count);
    }
}
