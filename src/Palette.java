import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.HashMap;

public interface Palette {
    void findColors();
    void setImage(Image i);
    Image getImage();
    boolean hasColor(Color c);
    PaletteColor getPaletteColor(Color c);
    int getColorCount();
    ArrayList<PaletteColor> getColors();
    void setMargin(double n);
}
