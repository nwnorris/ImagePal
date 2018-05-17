import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Program3: ImagePal
 * Takes an image and reduces the color palette to 256 unique colors. Color compression is done only using HashPalette, counting can be
 * done with both HashPalette and ArrayPalette.
 * Author: nwnorris
 */
public class Program3 extends Application {

    //Instance variables
    private ImageView image;
    private Label status;
    private Palette palette;
    private boolean imageAnalysis;
    private boolean hasImage;
    private Menu countMenu;
    private Menu crunchMenu;
    private static final int MAX_WIDTH = 900;
    private static final int MAX_HEIGHT = 600;

    /**
     * Constructor; creates the root pane and sets up the pane components
     * @param s The Stage that the program will be shown on
     */
    public void start(Stage s){

        image = new ImageView();
        BorderPane border = new BorderPane();
        hasImage = imageAnalysis = false;

        border.setPrefSize(MAX_WIDTH, MAX_HEIGHT);
        border.setCenter(image);
        border.setTop(initMenuBar(s));

        Group rootGroup = new Group(border);
        Scene rootScene = new Scene(rootGroup);

        s.setScene(rootScene);
        s.show();
    }

    /**
     * Sets up the top menu bar of the program; configures menu items and event handlers for them.
     * @param s The Stage the program will be shown on
     * @return An HBox that looks and acts like a menu bar, just with more components.
     */
    private HBox initMenuBar(Stage s){
        //Menu bar configuration, handlers are grouped with their MenuItems.
        MenuBar menuBar = new MenuBar();

        Menu aboutMenu = new Menu("About");
        MenuItem aboutInfo = new MenuItem();
        aboutInfo.setGraphic(new Text("Program3 - ImagePal - by Nate Norris | github.com/nwnorris"));
        aboutInfo.setOnAction(event -> {
            try{
                Desktop.getDesktop().browse(new URI("http://github.com/nwnorris/ImagePal"));
            } catch (Exception e){
                System.out.println("Unable to open URL.");
            }
        });
        aboutMenu.getItems().add(aboutInfo);

        //Image management
        Menu imageMenu = new Menu("Image");
        MenuItem loadMenuItem = new MenuItem("Load");
        loadMenuItem.setOnAction(event -> {
            File newImage = new FileChooser().showOpenDialog(s);
            showImage(newImage);
            if(hasImage){
                updateDisabled();
            }
        });

        MenuItem saveMenuItem = new MenuItem("Save");
        saveMenuItem.setOnAction(event -> saveImage());
        imageMenu.getItems().addAll(loadMenuItem, saveMenuItem);

        //Count functions
        countMenu = new Menu("Count");
        countMenu.setDisable(true);

        MenuItem countArrayMenuItem = new MenuItem("ArrayList");
        countArrayMenuItem.setOnAction(event -> {
            //New palette created when image is analyzed
            BenchmarkTimer timer = new BenchmarkTimer("ArrayPalette");
            palette = new ArrayPalette(image.getImage());
            updateStatus(getImageStats() + "[" + String.format("%,d", (int) palette.getColorCount()) + " colors] (" + timer.stop() + "ms)");
            updateDisabled();
        });

        MenuItem countHashMenuItem = new MenuItem("HashMap");
        countHashMenuItem.setOnAction(event -> {
            //New palette created when image is analyzed
            BenchmarkTimer timer = new BenchmarkTimer("ArrayPalette");
            palette = new HashPalette(image.getImage());
            imageAnalysis = true;
            updateStatus(getImageStats() + "[" + String.format("%,d", (int) palette.getColorCount()) + " colors] (" + timer.stop() + "ms)");
            updateDisabled();
        });

        countMenu.getItems().addAll(countArrayMenuItem, countHashMenuItem);

        //Crunch functions
        crunchMenu = new Menu("Crunch");
        crunchMenu.setDisable(true);

        MenuItem crunchSimilarMenuItem = new MenuItem("Merge Similar Colors");
        crunchSimilarMenuItem.setOnAction(event -> performReduction(0));

        MenuItem crunchCountMenuItem = new MenuItem("Prioritize Frequency");
        crunchCountMenuItem.setOnAction(event -> performReduction(1));

        //Palette presets
        Menu fromPaletteMenuItem = new Menu("From Palette Preset");

        MenuItem webSafe = new MenuItem("Web Safe Colors");
        webSafe.setOnAction(event -> performReduction(2));

        MenuItem xTerm = new MenuItem("XTerm Colors");
        xTerm.setOnAction(event -> performReduction(3));

        MenuItem grayScale = new MenuItem("Grayscale");
        grayScale.setOnAction(event -> performReduction(4));

        MenuItem nintendo = new MenuItem("NES Colors");
        nintendo.setOnAction(event -> performReduction(5));

        MenuItem optimal = new MenuItem("Optimal Colors");
        optimal.setOnAction(event -> performReduction(6));

        fromPaletteMenuItem.getItems().addAll(webSafe, xTerm, grayScale, nintendo, optimal);
        crunchMenu.getItems().addAll(crunchSimilarMenuItem, crunchCountMenuItem, fromPaletteMenuItem);

        //Menus all configured, add them to root MenuBar
        menuBar.getMenus().addAll(aboutMenu, imageMenu, countMenu, crunchMenu);

        //Add the menu bar to a larger container to allow for more elements in it
        //https://stackoverflow.com/questions/35576445/text-on-java-fx-menu-bar
        status = new Label("No image loaded.");
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS); //Empty space in the middle will push the label to the right wall

        HBox menuBarPane = new HBox(menuBar, spacer, status);
        menuBarPane.getStyleClass().add("menu-bar");
        menuBarPane.setAlignment(Pos.CENTER_LEFT);
        return menuBarPane;
    }

    /**
     * Updates whether or not a menu item should be disabled based on the program state.
     */
    private void updateDisabled(){
        if(hasImage){
            countMenu.setDisable(false);
        } else {
            countMenu.setDisable(true);
        }

        if(imageAnalysis){
            crunchMenu.setDisable(false);
        } else {
            crunchMenu.setDisable(true);
        }
    }

    /**
     * Refreshes the GUI image using the output of the color palette compression.
     * This function must be called in order to display changes to the color palette.
     */
    private void updateImageFromPalette(){
        image.setImage(((HashPalette) palette).getOutput());
    }

    /**
     * Util method -- updates status text with the given input.
     * @param s The new text for the status label.
     */
    private void updateStatus(String s){
        status.setText(s);
    }

    /**
     * HashPalette hook to fire color compression algorithms.
     * @param method The compression algorithm to use. 0 == similarity reduction, 1 == count prioritization.
     */
    private void performReduction(int method){
        ((HashPalette) palette).reset();
        if(method == 0){
            ((HashPalette) palette).reduceBySimilarity();
        } else if(method == 1){
            ((HashPalette) palette).reduceByCount();
        } else if(method > 1){
            ((HashPalette) palette).reduceToPalette(method);

        }
        updateImageFromPalette();
        updateStatus(getImageStats());
    }

    /**
     * Creates a formatted string with image information.
     * @return A string containing image size and pixel data.
     */
    private String getImageStats(){
        double w = image.getImage().getWidth();
        double h = image.getImage().getHeight();
        return "[" + (int) w + "x" + (int) h + " | " + String.format("%,d", (int) (w*h)) + " pixels] ";
    }

    /**
     * Displays an image in the GUI's ImageView.
     * @param i The image to be shown.
     */
    private void showImage(File i){
        Image newImage;
        try {
            newImage = new Image(new FileInputStream(i.getPath()));
            image.setImage(newImage);

            //Set resize size
            if(newImage.getWidth() <= MAX_WIDTH){
                image.setFitWidth(newImage.getWidth());
            } else {
                image.setFitWidth(MAX_WIDTH);
            }
            if(newImage.getHeight() <= MAX_HEIGHT){
                image.setFitHeight(newImage.getHeight());
            } else {
                image.setFitHeight(MAX_HEIGHT-MAX_HEIGHT/7);
            }

            image.setPreserveRatio(true); //Maintain aspect ratio
            hasImage = true;
            imageAnalysis = false;
            updateStatus(getImageStats());
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }

    /**
     * Saves the currently displayed image to the file system.
     * http://www.java2s.com/Tutorials/Java/JavaFX_How_to/Image/Save_an_Image_to_a_file.htm was very helpful!
     */
    private void saveImage(){
        if(hasImage){
            try{
                File output = new File(System.currentTimeMillis() + ".png");
                BufferedImage out = SwingFXUtils.fromFXImage(image.getImage(), null);
                ImageIO.write(out, "png", output);
                System.out.println("Image successfully saved.");
            } catch (IOException e){
                System.out.println("Unable to save image.");
            }
        }

    }

}
