import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;


public class Program3 extends Application {

    private Scene rootScene;
    private Group rootGroup;
    private ImageView image;
    private Text status;
    private Slider margin;
    private Palette analysis;
    private boolean imageAnalysis;
    private boolean hasImage;
    private static final int MAX_WIDTH = 900;
    private static final int MAX_HEIGHT = 600;

    public void start(Stage s){
        image = new ImageView();
        BorderPane border = new BorderPane();
        hasImage = imageAnalysis = false;

        border.setPrefSize(MAX_WIDTH, MAX_HEIGHT);
        border.setCenter(image);
        border.setTop(initMenuBar(s));
        border.setBottom(bottomBar(s));

        rootGroup = new Group(border);
        rootScene = new Scene(rootGroup);

        s.setScene(rootScene);
        s.show();
    }

    private VBox bottomBar(Stage s){
        status = new Text("No image loaded.");
        margin = new Slider(1, 254, 10);
        margin.setSnapToTicks(true);
        margin.setBlockIncrement(5);

        margin.setOnDragDone(event -> {
            analysis.setMargin(margin.getValue());
        });
        VBox box = new VBox(status, margin);
        return box;
    }

    private HBox initMenuBar(Stage s){
        Button arrayAnalysisButton = new Button("Analysis [ArrayList]");
        Button hashAnalysisButton = new Button("Analysis [HashMap]");
        Button importButton = new Button("Import Image");
        Button reduceSimButton = new Button("Reduce Colors [similarity]");
        Button reduceCountButton = new Button("Reduce Colors [count]");

        //Reduce button
        reduceSimButton.setOnMouseClicked(event -> {
                performReduction(0);
        });

        reduceCountButton.setOnMouseClicked(event -> {
                performReduction(1);
        });

        //Hash analysis button
        hashAnalysisButton.setDisable(!hasImage);
        hashAnalysisButton.setOnMouseClicked(event -> {
            analysis = new HashPalette(image.getImage());
            imageAnalysis = true;
            updateStatus(getStatus() + " [" + analysis.getColorCount() + " Colors]");
        });

        //Array analysis button
        arrayAnalysisButton.setDisable(!imageAnalysis);
        arrayAnalysisButton.setOnMouseClicked(event -> {
            analysis = new ArrayPalette(image.getImage());
            imageAnalysis = true;
            updateStatus(getStatus() + " [" + analysis.getColorCount() + " Colors]");
        });

        //Import button
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

        HBox menuBar = new HBox(arrayAnalysisButton, hashAnalysisButton, importButton, reduceSimButton, reduceCountButton);
        return menuBar;
    }

    public void updateImageFromPalette(){
        image.setImage(((HashPalette) analysis).getOutput());
    }



    public void updateStatus(String s){
        status.setText(s);
    }

    public String getStatus(){
        return status.getText();
    }

    private void performReduction(int method){

        if(method == 0){
            ((HashPalette) analysis).reduceBySimilarity();
        } else if(method == 1){
            ((HashPalette) analysis).reduceByCount();
        }
        //pGUI.update();
        updateImageFromPalette();
    }

    private void showImage(File i){
        Image newImage;
        try {
            newImage = new Image(new FileInputStream(i.getPath()));
            image.setImage(newImage);
            //analysis.setImage(newImage);

            if(newImage.getWidth() <= MAX_WIDTH){
                image.setFitWidth(newImage.getWidth());
            } else {
                image.setFitWidth(MAX_WIDTH);
            }

            image.setPreserveRatio(true);
            hasImage = true;
            updateStatus(newImage.getWidth() + "x" + newImage.getHeight());
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }

}
