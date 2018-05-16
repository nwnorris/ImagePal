import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import java.util.*;

/**
 * A HashPalette holds PaletteColors and can search for them in order 1 time. This makes it very effective for color compression and counting.
 * Author: nwnorris
 */
public class HashPalette implements Palette {

    //Instance variables
    private ColorCompressor comp;
    private HashMap<Color, PaletteColor> contents;
    private Image image;
    private Image original;

    /**
     * Constructor, sets the original image and immediately fires the counting method; assumes you will be attempting some
     * sort of color compression (why wouldn't you?)
     * @param i The image to be analyzed.
     */
    public HashPalette(Image i){
        original = image = i;
        contents = new HashMap<>();
        findColors();
        comp = new ColorCompressor(this);
    }

    /**
     * Counts all the unique colors in the image.
     */
    public void findColors(){
        double h = image.getHeight();
        double w = image.getWidth();
        System.out.println("Analyzing " + (h * w) + " pixels.");
        PixelReader pixelReader = image.getPixelReader();

        try{
            for(int i = 0; i < h;i++){
                for(int j = 0; j < w; j++){
                    addColor(pixelReader.getColor(j,i));
                }
            }
        } catch (IndexOutOfBoundsException e){
            e.printStackTrace();
        }
    }

    /**
     * Gets the number of unique colors in the image.
     * @return The size of the contents HashMap.
     */
    public int getColorCount(){
        return contents.size();
    }

    /**
     * Gets the original image in the HashPalette.
     * @return The original, uncompressed image.
     */
    public Image getImage(){
        return original;
    }

    /**
     * Gets the current output of the compressor, provided comp.update() has been called at least once.
     * @return The current compressor output.
     */
    public WritableImage getOutput(){
        return comp.getOutput();
    }

    /**
     * Adds a Color to the HashMap if it's not a duplicate. Does this in order 1 time! Yay hash maps!
     * @param c The Color to be added.
     */
    private void addColor(Color c){
        if(!hasColor(c)){
            contents.put(c, new PaletteColor(c));
        } else {
            contents.get(c).count++;
        }
    }

    /**
     * Checks if a Color is already in the HashMap.
     * @param c The Color to be checked.
     * @return True if c is in the HashMap already, false otherwise.
     */
    public boolean hasColor(Color c){
        if(contents.get(c) == null){
            return false;
        }

        return true;
    }

    /**
     * A compression algorithm that only cares about how many times a color was present in the original image.
     */
    public void reduceByCount(){
        comp.reduceByCount();
        comp.update();
    }

    /**
     * A compression algorithm that compares how similar two colors are, removing the less common of two similar colors.
     */
    public void reduceBySimilarity(){
        comp.removeSimilar();
        comp.reduceByCount();
        comp.update();
    }

    /**
     * Resets the image in the HashMap to the original image rather than the compressed version. Does the same for its compressor.
     */
    public void reset(){
        image = original;
        comp.reset();
    }

    /**
     * Gets all the PaletteColors in the HashPalette.
     * @return The set of values in the HashMap.
     */
    public ArrayList<PaletteColor> getColors(){
        return new ArrayList<PaletteColor>(contents.values());
    }

    /**
     * Gets a PaletteColor value using a Color key.
     * @param c The key to be hashed.
     * @return The corresponding PaletteColor value.
     */
    public PaletteColor getPaletteColor(Color c) {
        return contents.get(c);
    }

    /**
     * Gets the HashMap associated with this Palette.
     * @return The contents of the Palette.
     */
    public HashMap<Color, PaletteColor> getPalette(){
        return contents;
    }

}
