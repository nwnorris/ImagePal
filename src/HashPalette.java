import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.paint.Color;

import java.awt.geom.Point2D;
import java.util.*;
import java.util.stream.Stream;

public class HashPalette implements Palette {

    private int dupe;
    private double width;
    private double height;

    private HashMap<Color, PaletteColor> contents;
    private Image image;

    public HashPalette(Image i){
        image = i;
        contents = new HashMap<>();
        width = image.getWidth();
        height = image.getHeight();
        findColors();
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
    }

    public ArrayList<PaletteColor> getColors(){
        Collection<PaletteColor> colors = contents.values();
        return new ArrayList<PaletteColor>(colors);
    }

    public void sort(){

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

    private void addColor(Color c){
        if(!hasColor(c)){
            contents.put(c, new PaletteColor(c));
        } else {
            contents.get(c).count++;
            dupe++;
        }
    }

    public boolean hasColor(Color c){
        if(contents.get(c) == null && contents.get(new Color(c.getRed() + 1/255, c.getGreen() + 1/255, c.getBlue() + 1/255, c.getOpacity())) == null ){
            return false;
        }

        return true;
    }

    public void setImage(Image i){
        image = i;
    }

    public double getWidth(){
        return width;
    }

    public double getHeight(){
        return height;
    }

}
