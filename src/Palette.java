import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import java.util.ArrayList;

/**
 * The Palette interface defines how a generic palette should be able to interact with Program3. This is fairly simple because it is not
 * practical to do color crunching with anything other than the HashPalette.
 * Author: nwnorris
 */
public interface Palette {
    void findColors();
    Image getImage();
    boolean hasColor(Color c);
    int getColorCount();
    ArrayList<PaletteColor> getColors();
}
