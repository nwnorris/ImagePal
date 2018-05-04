import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.*;
import java.util.stream.Stream;

public class HashPaletteGUI extends Application {

    private HashPalette pal;
    private Scene rootScene;
    private Group rootGroup;
    private ImageView image;
    private BorderPane main;
    private WritableImage drawImage;
    private static final int MAX_WIDTH = 800;
    private static final int MAX_HEIGHT = 500;

    public HashPaletteGUI(Palette p){
        pal = (HashPalette) p;
        start(new Stage());
    }

    public void start(Stage stage){
        main = new BorderPane();
        main.setPrefSize(MAX_WIDTH, MAX_HEIGHT);
        update();
        image = new ImageView(drawImage);
        main.setCenter(image);
        rootGroup = new Group(main);
        rootScene = new Scene(rootGroup);
        stage.setScene(rootScene);
        stage.show();

    }

    public void update(){
        HashMap<Color, PaletteColor> palette = pal.getContents();
        drawImage = drawPalette(palette);
    }

    private WritableImage drawPalette(HashMap<Color, PaletteColor> colors){
        System.out.println(colors.size() + " colors.");

        System.out.println("Creating new image " + pal.getWidth() + "x" + pal.getHeight());
        WritableImage newImage = new WritableImage((int) MAX_WIDTH, (int) MAX_HEIGHT);
        PixelWriter writer = newImage.getPixelWriter();

        Set<Color> drawColors = colors.keySet();
        ArrayList<Color> real = new ArrayList<>(drawColors);
        double rows = real.size()/MAX_WIDTH;
        System.out.println(rows + " rows.");
        for(int i = 0; i < rows; i++){
            for(int j  = 0; j < MAX_WIDTH; j++){
                writer.setColor(j, i, real.get((int) (i*MAX_WIDTH) + j));
            }
        }

        return newImage;

    }
}
