import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class PaletteGUI extends Application {

    private PalAnalysis pal;
    private Scene rootScene;
    private Group rootGroup;
    private Canvas drawCanvas;
    private static final int MAX_WIDTH = 500;
    private static final int MAX_HEIGHT = 500;
    private int per_row;

    public PaletteGUI(PalAnalysis p){
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
        ArrayList<Color> palette = pal.getPalette();
        HashSet<Color> hashPal = pal.getHashPalette();
        if(pal.getMethod() == PalAnalysis.ANALYSIS_ARRAY_LIST){
            drawPalette(drawCanvas.getGraphicsContext2D(), palette.toArray(new Color[palette.size()]));
        } else if(pal.getMethod() == PalAnalysis.ANALYSIS_HASH_SET){
            drawHashPalette(drawCanvas.getGraphicsContext2D(), hashPal);
        }
    }

    private Canvas initCanvas(){
        drawCanvas = new Canvas();
        drawCanvas.setHeight(MAX_HEIGHT);
        drawCanvas.setWidth(MAX_WIDTH);
        return drawCanvas;
    }

    private void drawHashPalette(GraphicsContext g, HashSet<Color> p){
        Color[] n = new Color[p.size()];
        n = p.toArray(n);
        drawPalette(g, n);
    }

    private void drawPalette(GraphicsContext g, Color[] p){
        System.out.println(p.length + " colors.");
            per_row = (int) Math.floor(Math.sqrt(p.length));
            System.out.println(per_row + " per row.");
            double width = MAX_WIDTH/per_row;
            for(int i = 0; i < per_row; i++){
                for(int j = 0; j < per_row; j++){
                    g.setFill(p[(i * per_row) + j]);
                    g.fillRect(i*width, j*width, width, width);
                }
        }
    }
}
