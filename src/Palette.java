import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public interface Palette {
    void findColors();
    void setImage(Image i);
    Image getImage();
    boolean hasColor(Color c);
    int getColorCount();
    ArrayList<PaletteColor> getColors();
    double getWidth();
    double getHeight();
}
