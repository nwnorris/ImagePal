import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class Program3 extends Application {

    private Scene rootScene;
    private Group rootGroup;
    private ImageView image;
    private Palette analysis;
    private PaletteGUI pGUI;
    private boolean imageAnalysis;
    private boolean hasImage;
    private static final int MAX_WIDTH = 900;
    private static final int MAX_HEIGHT = 600;

    public void start(Stage s){
        image = new ImageView();
        BorderPane border = new BorderPane();
        imageAnalysis = false;
        hasImage = false;

        border.setPrefSize(MAX_WIDTH, MAX_HEIGHT);
        border.setCenter(image);
        border.setTop(initMenuBar(s));

        rootGroup = new Group(border);
        rootScene = new Scene(rootGroup);

        s.setScene(rootScene);
        s.show();
    }

    private HBox initMenuBar(Stage s){
        Button arrayAnalysisButton = new Button("Analysis [ArrayList]");
        Button hashAnalysisButton = new Button("Analysis [HashMap]");
        Button importButton = new Button("Import Image");
        Button paletteButton = new Button("Show Palette");

        paletteButton.setDisable(!imageAnalysis);
        paletteButton.setOnMouseClicked(event -> {
            pGUI.show();
        });

        hashAnalysisButton.setDisable(!hasImage);
        hashAnalysisButton.setOnMouseClicked(event -> {
            analysis = new HashPalette(image.getImage());
            pGUI = new HashPaletteGUI(analysis);
            imageAnalysis = true;
            paletteButton.setDisable(!imageAnalysis);
        });

        arrayAnalysisButton.setDisable(!imageAnalysis);
        arrayAnalysisButton.setOnMouseClicked(event -> {
            analysis = new ArrayPalette(image.getImage());
            pGUI = new ArrayPaletteGUI(analysis);
            imageAnalysis = true;
            paletteButton.setDisable(!imageAnalysis);
            pGUI.update();
        });


        importButton.setOnMouseClicked(event -> {
            File newImage = new FileChooser().showOpenDialog(s);
            //File newImage = new File("C:\\Users\\nathannorris\\Pictures\\02.PNG");
            //File newImage = new File("/Users/nathannorris/Documents/_code/ImagePal/fruit.jpg");
            System.out.println(newImage.getPath());
            showImage(newImage);
            if(hasImage){
                hashAnalysisButton.setDisable(!hasImage);
                arrayAnalysisButton.setDisable(!hasImage);
            }
        });

        HBox menuBar = new HBox(arrayAnalysisButton, hashAnalysisButton, importButton,paletteButton);
        return menuBar;
    }

    private void showImage(File i){
        Image newImage;
        try {
            newImage = new Image(new FileInputStream(i.getPath()));
            image.setImage(newImage);

            if(newImage.getWidth() <= MAX_WIDTH){
                image.setFitWidth(newImage.getWidth());
            } else {
                image.setFitWidth(MAX_WIDTH);
            }

            image.setPreserveRatio(true);
            hasImage = true;
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }

}
