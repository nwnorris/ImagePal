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


public class ImageGUI extends Application {

    private Scene rootScene;
    private Group rootGroup;
    private ImageView image;
    private PalAnalysis analysis;
    private PalAnalysis analysisHash;
    private PaletteGUI pGUI;
    private PaletteGUI pGUIHash;
    private static final int MAX_WIDTH = 900;
    private static final int MAX_HEIGHT = 600;

    public void start(Stage s){

        BorderPane border = new BorderPane();
        border.setPrefSize(MAX_WIDTH, MAX_HEIGHT);
        image = new ImageView();
        border.setCenter(image);
        border.setTop(initMenuBar(s));
        rootGroup = new Group(border);
        rootScene = new Scene(rootGroup);
        s.setScene(rootScene);
        s.show();

    }

    private HBox initMenuBar(Stage s){
        Button arrayAnalysisButton = new Button("Analysis [ArrayList]");
        Button hashAnalysisButton = new Button("Analysis [HashSet]");
        Button importButton = new Button("Import Image");
        Button sortButton = new Button("Sort Palette");

        sortButton.setOnMouseClicked(event -> {
            if(analysis != null){
                analysis.sort();
                pGUI.update();
            }
            if(analysisHash != null){
                analysisHash.sort();
                pGUIHash.update();
            }


        });

        hashAnalysisButton.setOnMouseClicked(event -> {
            analysisHash.analyze();
            pGUIHash = new PaletteGUI(analysisHash);
            pGUIHash.update();

        });

        arrayAnalysisButton.setOnMouseClicked(event -> {
            analysis.analyze();
            pGUI = new PaletteGUI(analysis);
            pGUI.update();
        });


        importButton.setOnMouseClicked(event -> {
            File newImage = new FileChooser().showOpenDialog(s);
            //File newImage = new File("C:\\Users\\nathannorris\\Pictures\\02.PNG");
            System.out.println(newImage.getPath());
            showImage(newImage);
        });

        HBox menuBar = new HBox(arrayAnalysisButton, hashAnalysisButton, importButton,sortButton);
        return menuBar;
    }

    private void showImage(File i){
        Image newImage;
        try {
            newImage = new Image(new FileInputStream(i.getPath()));
            image.setImage(newImage);
            analysis = new PalAnalysis(image, PalAnalysis.ANALYSIS_ARRAY_LIST);
            analysisHash = new PalAnalysis(image, PalAnalysis.ANALYSIS_HASH_SET);
            if(newImage.getWidth() <= MAX_WIDTH){
                image.setFitWidth(newImage.getWidth());
            } else {
                image.setFitWidth(MAX_WIDTH);
            }

            image.setPreserveRatio(true);
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }

    }

}
