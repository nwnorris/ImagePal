import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.stream.Stream;

public class ArrayPalette implements Palette {

    private int dupe;
    private double width;
    private double height;

    private ArrayList<PaletteColor> contents;
    private Image image;

    public ArrayPalette(Image i){
        image = i;
        width = i.getWidth();
        height = i.getHeight();
        contents = new ArrayList<>();
        findColors();
    }

    public void setMargin(double n){

    }


    public int countReductions(){
        return 0;
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
        return contents;
    }

    public int getColorCount(){
        return contents.size();
    }

    public Image getImage(){
        return image;
    }

    private void addColor(Color c){
        if(!hasColor(c)){
            contents.add(new PaletteColor(c));
        } else {
            dupe++;
        }
    }

    public boolean hasColor(Color c){
        for(PaletteColor x : contents){
            if(x.color.equals(c)){
                x.count++;
                return true;
            }
        }
        return false;
    }

    public PaletteColor getPaletteColor(Color c) {
        return null;
    }

    public PaletteColor getClosestColor(Color c) {
        return null;
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
