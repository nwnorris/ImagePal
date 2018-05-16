import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.paint.Color;
import java.util.ArrayList;

/**
 * An ArrayPalette holds PaletteColors and counts an image's colors using an iterative ArrayList technique. Yikes.
 * Author: nwnorris
 */
public class ArrayPalette implements Palette {

    //Instance variables
    private ArrayList<PaletteColor> contents;
    private Image image;

    /**
     * Constructor, requires an Image input and automatically fires the color count method.
     * @param i The Image to be analyzed.
     */
    public ArrayPalette(Image i){
        image = i;
        contents = new ArrayList<>();
        findColors();
    }

    /**
     * Counts all the unique colors in the Image. A very expensive method, order n^3. Jeepers creepers. Feel the pain!
     */
    public void findColors(){
        double h = image.getHeight();
        double w = image.getWidth();
        System.out.println("Analyzing " + (h * w) + " pixels.");
        PixelReader pixelReader = image.getPixelReader();
        //Try-catch just in case.
        try{
            for(int i = 0; i < h;i++){
                for(int j = 0; j < w; j++){
                        //addColor method handles duplicate checking
                        addColor(pixelReader.getColor(j,i));
                }
            }
        } catch (IndexOutOfBoundsException e){
            e.printStackTrace();
        }
    }

    /**
     * Adds a Color to the Palette if it isn't a duplicate.
     * @param c The Color to be added.
     */
    private void addColor(Color c){
        if(!hasColor(c)){
            contents.add(new PaletteColor(c));
        }
    }

    /**
     * Checks if a color is currently in the palette. Order n. No bueno.
     * @param c The Color to be checked.
     * @return True if the Color is already in the palette, false otherwise.
     */
    public boolean hasColor(Color c){
        for(PaletteColor x : contents){
            if(x.color.equals(c)){
                x.count++;
                return true;
            }
        }
        return false;
    }

    /**
     * Gets an ArrayList containing all the colors in this Palette.
     * @return All the PaletteColors in this Palette.
     */
    public ArrayList<PaletteColor> getColors(){
        return contents;
    }

    /**
     * Gets the number of unique colors in this Palette.
     * @return The size of the contents array.
     */
    public int getColorCount(){
        return contents.size();
    }

    /**
     * Gets the image used by this Palette for analysis.
     * @return The stored Palette image.
     */
    public Image getImage(){
        return image;
    }
}
