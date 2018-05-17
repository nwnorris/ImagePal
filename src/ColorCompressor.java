import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Takes a HashPalette and reduces the number of colors down to 256.
 * Author: nwnorris
 */
public class ColorCompressor {

    //Instance variables
    private PaletteColor[] colors;
    private HashPalette palette;
    private Image input;
    private WritableImage output;
    private PixelReader inputPixels;
    private PixelWriter outputPixels;
    private double margin = 1.5/255.0;

    /**
     * Constructor, inits the contents array and pixel I/O.
     * @param p
     */
    public ColorCompressor(HashPalette p){
        palette = p;
        colors = new PaletteColor[p.getColors().size()];

        input = palette.getImage();
        inputPixels = input.getPixelReader();

        palette.getColors().toArray(colors);
        output = new WritableImage(input.getPixelReader(), (int) input.getWidth(), (int) input.getHeight());
        outputPixels = output.getPixelWriter();
    }

    /**
     * Updates the output WritableImage with the current state of the HashPalette. Since PaletteColor.newColor == PaletteColor.color at
     * initialization, writing using newColor effectively maintains unchanged PaletteColors but allows changes to PaletteColors.
     */
    public void update(){
        HashMap<Color, PaletteColor> p = palette.getPalette();

        for(int i = 0; i < input.getHeight(); i++){
            for(int j = 0; j < input.getWidth(); j++){
                Color imagePixel = inputPixels.getColor(j, i);
                outputPixels.setColor(j, i, p.get(imagePixel).newColor);
            }
        }

        System.out.println("Updated image.");
    }

    /**
     * Updates all the colors in a HashPalette with new colors, picking the closest color in the color array for each new color.
     * This effectively finalizes any color compression operations.
     * @param updatedColors The array of PaletteColors that will be mapped to the HashPalette.
     */
    public void updatePalette(PaletteColor[] updatedColors){
        System.out.println("Updating palette from " + updatedColors.length + " colors.");
        ArrayList<PaletteColor> paletteColors = palette.getColors();
        for(PaletteColor c: paletteColors){
            Color closest = getClosestColor(c.color, updatedColors);
            palette.getPaletteColor(c.color).newColor = closest;
        }
    }

    /**
     * For any given color, gets the closest color in the current search space.
     * @param root The color that needs a similar color in the compressed palette.
     * @param searchSpace All the potential similar colors.
     * @return The PaletteColor with the smallest euclidean distance to root.
     */
    private Color getClosestColor(Color root, PaletteColor[] searchSpace){
        Color minColor = null;
        double minDist = 0;
        for(PaletteColor c : searchSpace){
            double dist = c.getEuclideanDistance(root);
            if((dist < minDist || minColor == null)){
                minDist = dist;
                minColor = c.color;
            }
        }
        return minColor;
    }

    /**
     * Given a large array of PaletteColors, chops off the first 256. Useful to get the output of a sorted array.
     * @param largeSet The larger set of colors to pull from.
     * @return An array of 256 colors.
     */
    private PaletteColor[] first256(PaletteColor[] largeSet){
        int size = 256;
        if(largeSet.length < 256) size = largeSet.length;
        PaletteColor[] reducedColors = new PaletteColor[size];
        for(int i = 0; i < size; i++){
            reducedColors[i] = largeSet[i];
        }
        return reducedColors;
    }

    /**
     * Tests if two colors are "similar" to each other. Euclidean distance is compared to a hard-coded margin value.
     * @param a The first PaletteColor in the pair to be tested
     * @param b The second PaletteColor in the pair to be tested.
     * @return True if a and b are "similar", false otherwise.
     */
    private boolean similar(PaletteColor a, PaletteColor b){
        double distance = a.getEuclideanDistance(b.color);
        if(distance < margin) return true;
        return false;
    }

    /**
     * Iterates through the current compression palette and removes any colors that are similar and have fewer duplicates than another.
     * Winning colors are added to an ArrayList which is then converted back to an array.
     */
    public void removeSimilar(){
        ArrayList<PaletteColor> updatedColors = new ArrayList<>();
        for(int start = 0; start < colors.length-1; start++){
            if(similar(colors[start], colors[start+1])){
                //Only keep the higher count of two similar colors
                if(colors[start].count > colors[start+1].count){
                    updatedColors.add(colors[start]);
                } else {
                    updatedColors.add(colors[start+1]);
                }
            }
        }
        //Convert back to array and return
        PaletteColor[] out = new PaletteColor[updatedColors.size()];
        updatedColors.toArray(out);
        colors = out;
    }

    /**
     * Compresses the color palette by prioritizing colors that were used more in the original image.
     * Produces ugly, but interesting results.
     */
    public void reduceByCount(){
        Arrays.sort(colors, new PaletteColorCountComparator());
        updatePalette(first256(colors));
    }

    /**
     * Gets the current output of the compression, provided that update() has been called at least once.
     * @return The output of the ColorCompressor.
     */
    public WritableImage getOutput() {
        return output;
    }

    /**
     * Resets the compressor to the original image in the palette. This prevents the compressor from compressing the previous result
     * of its compression.
     */
    public void reset(){
        colors = new PaletteColor[palette.getColors().size()];
        palette.getColors().toArray(colors);
    }
}
