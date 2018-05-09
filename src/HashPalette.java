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
    private int average;

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

    private int averageCount(){
        average = 0;
        contents.forEach((c, p) -> {
            addToSum(p.count);
        });
        average = average/contents.size();
        return average;
    }

    private void addToSum(int i){
        average += i;
    }

    public HashMap<Color, PaletteColor> getTop265(){
        HashMap<Color, PaletteColor> colors = contents;
        HashMap<Color, PaletteColor> common = new HashMap<>();
        PaletteColor max = getMax(colors, colors.size());
        for(int i = 0; i < 256; i++){
            if(i >= colors.size()){
                common.put(Color.BLACK, new PaletteColor(Color.BLACK));
            } else {
                common.put(max.color, max);
                colors.remove(max.color);
                max = getMax(colors, max.count);
            }
        }
        System.out.println(common.size() + " commons.");
        return common;
    }

    public PaletteColor getMax(HashMap<Color, PaletteColor> map, int ceil){
        int max = 0;
        for(PaletteColor p : map.values()){
            if(p.count > max && p.count <= ceil){
                return p;
            }
        }
        return new PaletteColor(Color.GREEN);
    }

    public ArrayList<PaletteColor> getColors(){
        Collection<PaletteColor> colors = contents.values();
        return new ArrayList<PaletteColor>(colors);
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
