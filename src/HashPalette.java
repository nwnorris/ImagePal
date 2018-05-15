import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import sun.security.action.PutAllAction;

import java.util.*;

public class HashPalette implements Palette {

    private int dupe;
    private double width;
    private double height;
    private int initialColorCount;
    private ColorCompressor comp;

    private HashMap<Color, PaletteColor> contents;
    private Image image;
    private Image original;
    private Random random;

    public HashPalette(Image i){
        original = image = i;
        random = new Random();
        contents = new HashMap<>();
        width = image.getWidth();
        height = image.getHeight();
        findColors();
        comp = new ColorCompressor(this);
    }

    public void findColors(){
        System.out.println("Analyzing colors.");
        dupe = 0;
        PixelReader pixelReader = image.getPixelReader();
        try{
            System.out.println(image.getHeight() + "x" + image.getWidth());
            double h = image.getHeight();
            double w = image.getWidth();
            for(int i = 0; i < h;i++){
                for(int j = 0; j < w; j++){
                    addColor(pixelReader.getColor(j,i));
                }
            }
            System.out.println(dupe + " duplicates found.");
        } catch (IndexOutOfBoundsException e){
            e.printStackTrace();
        }
        initialColorCount = contents.size();
    }

    public ArrayList<PaletteColor> getColors(){
        return new ArrayList<PaletteColor>(contents.values());
    }

    public PaletteColor getPaletteColor(Color c) {
        return contents.get(c);
    }

    public HashMap<Color, PaletteColor> getPalette(){
        return contents;
    }

    public int getColorCount(){
        return contents.size();
    }

    public Image getImage(){
        return image;
    }

    public HashMap<Color, PaletteColor> getContents() {
        return contents;
    }

    public WritableImage getOutput(){
        return comp.getOutput();
    }
    private void addColor(Color c){
        if(!hasColor(c)){
            contents.put(c, new PaletteColor(c));
        } else {
            contents.get(c).count++;
            dupe++;
        }
    }

    public boolean hasColor(Color c){
        if(contents.get(c) == null){
            return false;
        }

        return true;
    }

    public void setMargin(double n){
        comp.setMargin(n);
        image = original;
    }
    public void reduceByCount(){
        comp.reduceByCount();
        comp.update();
    }

    public void reduceBySimilarity(){
        comp.removeSimilar();
        comp.reduceByCount();
        comp.update();
    }

    public void setImage(Image i){
        image = i;
    }

}
