import javafx.scene.image.*;
import javafx.scene.paint.Color;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class PalAnalysis {

    private ImageView image;
    private PixelReader pixelReader;
    private ArrayList<Color> palette;
    private HashSet<Color> hashPalette;
    WritablePixelFormat<IntBuffer> pixelFormat;
    public static final int ANALYSIS_HASH_SET = 1;
    public static final int ANALYSIS_ARRAY_LIST = 2;
    private int method;

    public PalAnalysis(ImageView i, int method){
        this.method = method;
        palette = new ArrayList<Color>();
        hashPalette = new HashSet<>();
        image = i;
        pixelReader = image.getImage().getPixelReader();
        analyze();
    }

    public ArrayList<Color> getPalette(){
        return palette;
    }

    public HashSet<Color> getHashPalette() {
        return hashPalette;
    }

    public void analyze(){
        try{
            System.out.println(image.getImage().getHeight() + "x" + image.getImage().getWidth());
            for(int i = 0; i < image.getImage().getHeight();i++){
                for(int j = 0; j < image.getImage().getWidth(); j++){
                    if(method == ANALYSIS_ARRAY_LIST){
                        palette.add(pixelReader.getColor(i, j));
                    } else if(method == ANALYSIS_HASH_SET){
                        hashPalette.add(pixelReader.getColor(i,j));
                    }
                }
            }
        } catch (IndexOutOfBoundsException e){

        }

    }

    public int getSize(){
        return palette.size();
    }
    public void sort(){
        if(method == ANALYSIS_ARRAY_LIST){
            palette.sort(new PaletteComparatorA());
        }
    }

    public int getMethod(){
        return method;
    }

}
