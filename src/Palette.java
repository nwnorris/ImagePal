import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public interface Palette {
    void findColors();
    void setImage(Image i);
    Image getImage();
    boolean hasColor(Color c);
    int getColorCount();
}
