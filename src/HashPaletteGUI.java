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
    private WritableImage drawImage;
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
        update();

        ScrollPane scrollPane = new ScrollPane();

        ImageView image = new ImageView(drawImage);
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
        drawImage = drawPalette(palette);
    }

    private WritableImage drawPalette(HashMap<Color, PaletteColor> colors){
        System.out.println(colors.size() + " colors to render.");

        Set<Color> drawColors = colors.keySet();
        ArrayList<Color> real = new ArrayList<>(drawColors);

        final int PIXELS_PER_ROW = prefWidth/PIXEL_SIZE_MODIFIER;
        float rows = real.size()/PIXELS_PER_ROW;
        if(rows < 1) rows = 1;
        System.out.println(rows + " rows, " + PIXELS_PER_ROW + " pixels per row.");

        WritableImage newImage = new WritableImage((int) prefWidth, (int) rows);
        PixelWriter writer = newImage.getPixelWriter();

        //Row iterator
        for(int i = 0; i <= rows-PIXEL_SIZE_MODIFIER; i+=PIXEL_SIZE_MODIFIER){
            //Column iterator
            for(int j  = 0; j <= prefWidth-PIXEL_SIZE_MODIFIER; j+=PIXEL_SIZE_MODIFIER){

                //Subpixel iterators
                for(int subPixCol = 0; subPixCol < PIXEL_SIZE_MODIFIER; subPixCol++){
                    for(int subPixRow = 0; subPixRow < PIXEL_SIZE_MODIFIER; subPixRow++){
                        if((j + subPixRow < prefWidth) && (i + subPixCol < rows)){
                                writer.setColor(j + subPixRow, i + subPixCol, real.get((int) (i*PIXELS_PER_ROW) + j) );
                         }
                    }
                }
            }
        }

        return newImage;

    }


}
