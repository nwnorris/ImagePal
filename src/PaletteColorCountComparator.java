import java.util.Comparator;

public class PaletteColorCountComparator implements Comparator<PaletteColor>{
    public int compare(PaletteColor a, PaletteColor b){
        if(a.count > b.count){
            return 1;
        } else if(a.count == b.count){
            return 0;
        } else {
            return -1;
        }
    }
}
