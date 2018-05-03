import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class SequentialPalette implements Palette {

    private ArrayList<Color> contents;
    private Image image;

    public SequentialPalette(Image i){
        image = i;
        contents = new ArrayList<Color>();
    }

    public void findColors(){
        PixelReader pixelReader = image.getPixelReader();
        try{
            System.out.println(image.getHeight() + "x" + image.getWidth());
            for(int i = 0; i < image.getHeight();i++){
                for(int j = 0; j < image.getWidth(); j++){
                        contents.add(pixelReader.getColor(i, j));
                }
            }
        } catch (IndexOutOfBoundsException e){

        }
    }

    public int getColorCount(){
        return contents.size();
    }

    public Image getImage(){
        return image;
    }

    private void addColor(Color c){
        if(!hasColor(c)){
            contents.add(c);
        }
    }

    public boolean hasColor(Color c){
        for(Color x : contents){
            if(c.equals(x)) return true;
        }
        return false;
    }

    public void setImage(Image i){
        image = i;
    }

}
