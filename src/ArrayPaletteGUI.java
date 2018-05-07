import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.ArrayList;


public class ArrayPaletteGUI extends Application implements PaletteGUI {

    private ArrayPalette pal;
    private Scene rootScene;
    private Stage stage;
    private Group rootGroup;
    private ImageView image;
    private BorderPane main;
    private ScrollPane scrollPane;
    private WritableImage drawImage;
    private static int prefWidth = 800;
    private static int prefHeight = 500;
    private static int PIXEL_SIZE_MODIFIER = 5;

    public ArrayPaletteGUI(Palette p){
        pal = (ArrayPalette) p;
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
        drawImage = drawPalette(pal.getColors());
    }

    private WritableImage drawPalette(ArrayList<PaletteColor> colors){
        System.out.println(colors.size() + " colors to render.");

        final int PIXELS_PER_ROW = prefWidth/PIXEL_SIZE_MODIFIER;
        float rows = colors.size()/PIXELS_PER_ROW;
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
                            writer.setColor(j + subPixRow, i + subPixCol, colors.get((int) (i*PIXELS_PER_ROW) + j).color );
                        }

                    }
                }

            }
        }

        return newImage;

    }
}
