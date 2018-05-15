import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class ColorCompressor {

    private PaletteColor[] colors;
    private HashPalette palette;
    private Image input;
    private WritableImage output;
    private PixelReader inputPixels;
    private PixelWriter outputPixels;
    private double margin = 1.5/255.0;

    public ColorCompressor(HashPalette p){
        palette = p;
        colors = new PaletteColor[p.getColors().size()];

        input = palette.getImage();
        inputPixels = input.getPixelReader();


        palette.getColors().toArray(colors);
        output = new WritableImage(input.getPixelReader(), (int) input.getWidth(), (int) input.getHeight());
        outputPixels = output.getPixelWriter();
    }

    public void update(){
        HashMap<Color, PaletteColor> p = palette.getPalette();
//        for(PaletteColor c: colors){
//            p.replace(c.color, c);
//        }

        for(int i = 0; i < input.getHeight(); i++){
            for(int j = 0; j < input.getWidth(); j++){
                Color imagePixel = inputPixels.getColor(j, i);
                outputPixels.setColor(j, i, p.get(imagePixel).newColor);
            }
        }

        System.out.println("Updated image.");
    }

    private void updatePalette(PaletteColor[] updatedColors){
        System.out.println("Updating palette from {" + updatedColors.length + "}");
        int count = 0;
        ArrayList<PaletteColor> paletteColors = palette.getColors();
        for(PaletteColor c: paletteColors){
            count++;
            Color closest = getClosestColor(c.color, updatedColors);
            palette.getPaletteColor(c.color).newColor = closest;
            if(count % 1000 == 0){
                //System.out.println(count + "/" + paletteColors.size());
            }
        }
    }

    public void setMargin(double n){
        margin = n/255.0;
    }

    private Color getClosestColor(Color root, PaletteColor[] searchSpace){
        Color minColor = null;
        double minDist = 0;
        for(PaletteColor c : searchSpace){
            double dist = c.getEuclidianDistance(root);
            if((dist < minDist || minColor == null)){
                minDist = dist;
                minColor = c.color;
            }
        }
        return minColor;
    }

    private PaletteColor[] first256(PaletteColor[] largeSet){
        int size = 256;
        if(largeSet.length < 256) size = largeSet.length;
        PaletteColor[] reducedColors = new PaletteColor[size];
        for(int i = 0; i < size; i++){
            reducedColors[i] = largeSet[i];
        }
        return reducedColors;
    }

    public boolean similar(PaletteColor a, PaletteColor other){
        double distance = a.getEuclidianDistance(other.color);
        if(distance < margin) return true;
        return false;
    }


    public void removeSimilar(){
        int count = 0;
        ArrayList<PaletteColor> updatedColors = new ArrayList<>();
        BenchmarkTimer timer = new BenchmarkTimer("removeSimilar()");
        for(int start = 0; start < colors.length-2; start+=1){
            if(similar(colors[start], colors[start+1])){
                //Mark lesser color for removal
                if(colors[start].count > colors[start+1].count){
                    updatedColors.add(colors[start]);
                } else {
                    updatedColors.add(colors[start+1]);
                }
                count++;
            }
        }
        timer.stop();
        System.out.println(count + " colors flagged as similar.");
        PaletteColor[] out = new PaletteColor[updatedColors.size()];
        updatedColors.toArray(out);
        colors = out;
    }

    public void reduceByCount(){
        Arrays.sort(colors, new PaletteColorCountComparator());
        updatePalette(first256(colors));
    }


    public void setAllToRed(){
        for(PaletteColor p : colors){
            p.newColor = Color.MAROON;
        }
        System.out.println("All colors set to maroon.");
    }

    public WritableImage getOutput() {
        return output;
    }
}
