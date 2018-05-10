import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.*;

public class HashPaletteGUI extends Application implements PaletteGUI{

    private HashPalette pal;
    private Stage stage;
    private ImageView image;
    private WritableImage im;
    private static int prefWidth = 800;
    private static int prefHeight = 500;
    private static int PIXEL_SIZE_MODIFIER = 5;

    public HashPaletteGUI(Palette p){
        pal = (HashPalette) p;
        start(new Stage());
    }

    public void start(Stage stage){
        BorderPane main = new BorderPane();
        main.setPrefSize(prefWidth, prefHeight);

        ScrollPane scrollPane = new ScrollPane();

        image = new ImageView();
        update();
        scrollPane.setVmax(prefHeight);
        scrollPane.setContent(image);
        main.setCenter(scrollPane);

        Group rootGroup = new Group(main);
        Scene rootScene = new Scene(rootGroup);
        stage.setScene(rootScene);
        this.stage = stage;
    }

    public void show(){
        stage.show();
    }

    public void update(){
        HashMap<Color, PaletteColor> palette = pal.getContents();
        image.setImage(drawPalette2(palette));
    }

    public void drawMostCommon(){
         image.setImage(drawPalette2(pal.getTop265()));
    }

    private ArrayList<Color> removeFlags(ArrayList<Color> toRemove, HashMap<Color, PaletteColor> colors){
        ArrayList<Color> remove = new ArrayList<>();
        for(Color c: toRemove){
            if(colors.get(c).flag) remove.add(c);
        }
        for(Color c: remove){
            colors.remove(c);
        }
        return new ArrayList<Color>(colors.keySet());
    }

    private WritableImage drawPalette2(HashMap<Color, PaletteColor> colors){
        int pixelSize, modPixelsPerRow;
        ArrayList<Color> colorsToRender = new ArrayList<>(colors.keySet());
        colorsToRender = removeFlags(colorsToRender, colors);

        if(colorsToRender.size() < prefWidth){
            pixelSize = prefWidth/((int) Math.ceil(Math.sqrt(colorsToRender.size())));
        } else {
            pixelSize = PIXEL_SIZE_MODIFIER;
        }


        modPixelsPerRow = colorsToRender.size()/(prefWidth/pixelSize);
        int modPixelRows = (int) Math.ceil(colorsToRender.size()/modPixelsPerRow);

        System.out.println("Creating image: " + prefWidth + "x" + modPixelRows*pixelSize);
        WritableImage drawnImage = new WritableImage(prefWidth, modPixelRows*pixelSize);
        PixelWriter writer = drawnImage.getPixelWriter();

        for(int row = 0; row < modPixelRows; row++){
            for(int col = 0; col < modPixelsPerRow; col++){
                int rowIndex = row*pixelSize;
                int colIndex = col*pixelSize;
                Color pixelColor = colorsToRender.get((row * modPixelsPerRow) + col);

                if(rowIndex + pixelSize < drawnImage.getHeight() && colIndex + pixelSize < drawnImage.getWidth() && !colors.get(pixelColor).flag){
                    drawModPixel(writer, colIndex, rowIndex, pixelSize, pixelColor);
                }

            }
        }
        return drawnImage;
    }

    private void drawModPixel(PixelWriter image, int startX, int startY, int pixelSize, Color c){
        //System.out.println("Drawing pixel at " + startX + "," + startY + "[" + pixelSize + "]");
        for(int i = 0; i < pixelSize; i++){
            for(int j = 0; j < pixelSize; j++){
                image.setColor(startX + j, startY + i, c);
            }
        }
    }

}
