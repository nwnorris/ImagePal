import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.stream.Stream;

public class PaletteGUI extends Application {

    private Palette pal;
    private Scene rootScene;
    private Group rootGroup;
    private Canvas drawCanvas;
    private static final int MAX_WIDTH = 500;
    private static final int MAX_HEIGHT = 500;
    private int per_row;

    public PaletteGUI(Palette p){
        pal = p;
        start(new Stage());

    }

    public void start(Stage stage){
        BorderPane main = new BorderPane();
        main.setPrefSize(MAX_WIDTH, MAX_HEIGHT);
        main.setCenter(initCanvas());

        rootGroup = new Group(main);
        rootScene = new Scene(rootGroup);
        stage.setScene(rootScene);
        stage.show();

    }

    public void update(){
        ArrayList<PaletteColor> palette = pal.getColors();
        drawPalette(drawCanvas.getGraphicsContext2D(), palette);
    }

    private Canvas initCanvas(){
        drawCanvas = new Canvas();
        drawCanvas.setHeight(MAX_HEIGHT);
        drawCanvas.setWidth(MAX_WIDTH);
        return drawCanvas;
    }

    private void drawPalette(GraphicsContext g, ArrayList<PaletteColor> colors){
            System.out.println(colors.size() + " colors.");
            per_row = (int) Math.floor(Math.sqrt(colors.size()));
            System.out.println(per_row + " per row.");
            double width = MAX_WIDTH/per_row;

        WritableImage newImage = new WritableImage((int) pal.getWidth(), (int) pal.getHeight());
        PixelWriter writer = newImage.getPixelWriter();

    }
}
