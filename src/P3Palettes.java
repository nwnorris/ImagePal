import java.util.ArrayList;
import javafx.scene.paint.Color;


/**
 * These are a few color palettes I use in Program #3.
 * Each method returns an ArrayList of colors that comprise the palette.
 * 
 * @author williamkrieger
 *
 * Slightly modified by nwnorris, removed uneeded methods and modified to work with my system.
 */
public class P3Palettes {

    /**
     * Returns a list of 216 web safe colors.
     * Colors are every combination of RGB = {0, 51, 102, 153, 204, 255}.
     * 
     * Source: https://www.rapidtables.com/web/color/Web_Safe.html
     * 
     * @return Returns a list of web safe colors.
     */
    static public ArrayList<PaletteColor> webSafeColors() {
        ArrayList<PaletteColor> colors = new ArrayList<>();

        // RGB 6x6x6 loop = 216 colors
        for( int red = 0; red < 6; red++) {
            for( int green = 0; green < 6; green++) {
                for( int blue = 0; blue < 6; blue++) {
                    // Use RGB index * 51 because 
                    // colors are combinations of: {0, 51, 102, 153, 204, 255}
                    int redColor = red * 51;
                    int greenColor = green * 51;
                    int blueColor = blue *51;
                    Color c = Color.rgb( redColor, greenColor, blueColor);
                    colors.add(new PaletteColor(c));
                }
            }
        }
        return colors;
    }

    /**
     * Returns a list of the 256 colors in the xterm palette.
     * Xterm is a terminal emulator from way back.
     * 
     * Source: https://jonasjacek.github.io/colors/
     * @return Returns a list of xterm colors.
     */
    static public ArrayList<PaletteColor> xtermColors() {
        String[] values = {

            "#000000","#800000","#008000","#808000",
            "#000080","#800080","#008080","#c0c0c0",
            "#808080","#ff0000","#00ff00","#ffff00",
            "#0000ff","#ff00ff","#00ffff","#ffffff",

            "#000000","#00005f","#000087","#0000af",
            "#0000d7","#0000ff","#005f00","#005f5f",
            "#005f87","#005faf","#005fd7","#005fff",
            "#008700","#00875f","#008787","#0087af",

            "#0087d7","#0087ff","#00af00","#00af5f",
            "#00af87","#00afaf","#00afd7","#00afff",
            "#00d700","#00d75f","#00d787","#00d7af",
            "#00d7d7","#00d7ff","#00ff00","#00ff5f",

            "#00ff87","#00ffaf","#00ffd7","#00ffff",
            "#5f0000","#5f005f","#5f0087","#5f00af",
            "#5f00d7","#5f00ff","#5f5f00","#5f5f5f",
            "#5f5f87","#5f5faf","#5f5fd7","#5f5fff",

            "#5f8700","#5f875f","#5f8787","#5f87af",
            "#5f87d7","#5f87ff","#5faf00","#5faf5f",
            "#5faf87","#5fafaf","#5fafd7","#5fafff",
            "#5fd700","#5fd75f","#5fd787","#5fd7af",

            "#5fd7d7","#5fd7ff","#5fff00","#5fff5f",
            "#5fff87","#5fffaf","#5fffd7","#5fffff",
            "#870000","#87005f","#870087","#8700af",
            "#8700d7","#8700ff","#875f00","#875f5f",

            "#875f87","#875faf","#875fd7","#875fff",
            "#878700","#87875f","#878787","#8787af",
            "#8787d7","#8787ff","#87af00","#87af5f",
            "#87af87","#87afaf","#87afd7","#87afff",

            "#87d700","#87d75f","#87d787","#87d7af",
            "#87d7d7","#87d7ff","#87ff00","#87ff5f",
            "#87ff87","#87ffaf","#87ffd7","#87ffff",
            "#af0000","#af005f","#af0087","#af00af",

            "#af00d7","#af00ff","#af5f00","#af5f5f",
            "#af5f87","#af5faf","#af5fd7","#af5fff",
            "#af8700","#af875f","#af8787","#af87af",
            "#af87d7","#af87ff","#afaf00","#afaf5f",

            "#afaf87","#afafaf","#afafd7","#afafff",
            "#afd700","#afd75f","#afd787","#afd7af",
            "#afd7d7","#afd7ff","#afff00","#afff5f",
            "#afff87","#afffaf","#afffd7","#afffff",

            "#d70000","#d7005f","#d70087","#d700af",
            "#d700d7","#d700ff","#d75f00","#d75f5f",
            "#d75f87","#d75faf","#d75fd7","#d75fff",
            "#d78700","#d7875f","#d78787","#d787af",

            "#d787d7","#d787ff","#d7af00","#d7af5f",
            "#d7af87","#d7afaf","#d7afd7","#d7afff",
            "#d7d700","#d7d75f","#d7d787","#d7d7af",
            "#d7d7d7","#d7d7ff","#d7ff00","#d7ff5f",

            "#d7ff87","#d7ffaf","#d7ffd7","#d7ffff",
            "#ff0000","#ff005f","#ff0087","#ff00af",
            "#ff00d7","#ff00ff","#ff5f00","#ff5f5f",
            "#ff5f87","#ff5faf","#ff5fd7","#ff5fff",

            "#ff8700","#ff875f","#ff8787","#ff87af",
            "#ff87d7","#ff87ff","#ffaf00","#ffaf5f",
            "#ffaf87","#ffafaf","#ffafd7","#ffafff",
            "#ffd700","#ffd75f","#ffd787","#ffd7af",

            "#ffd7d7","#ffd7ff","#ffff00","#ffff5f",
            "#ffff87","#ffffaf","#ffffd7","#ffffff",
            "#080808","#121212","#1c1c1c","#262626",
            "#303030","#3a3a3a","#444444","#4e4e4e",

            "#585858","#626262","#6c6c6c","#767676",
            "#808080","#8a8a8a","#949494","#9e9e9e",
            "#a8a8a8","#b2b2b2","#bcbcbc","#c6c6c6",
            "#d0d0d0","#dadada","#e4e4e4","#eeeeee"
        };

        ArrayList<PaletteColor> xterms = new ArrayList<>();
        
        for( String s: values) {
            Color c = Color.web(s);
            xterms.add(new PaletteColor(c));
        }
        return xterms;
    }

    /**
     * Returns a list of 256 grayscale colors.
     * The colors have equal RGB values: (0,0,0), (1,1,1), ... (255,255,255).
     * @return Returns a list of 256 grayscale colors.
     */
    public static ArrayList<PaletteColor> grayscaleColors(){
        ArrayList<PaletteColor> grays = new ArrayList<>();
        for( int i = 0; i < 256; i++) {
            Color c = Color.rgb( i, i, i);
            grays.add( new PaletteColor(c));
        }

        return grays;
    }

    /**
     * Returns a list of N grayscale colors.
     * Black (0,0,0) and White (255,255,255) are always included.
     * If numGrays is 2, then black and white the only 2 colors.
     * When numGrays > 2, the gray colors are equally distributed between
     * (0,0,0) and (255,255,255).
     * 
     * @param numGrays The number of colors in the list
     * @return Returns a list of N grayscale colors.
     */
    public static ArrayList<PaletteColor> grayscaleColors( int numGrays){
        if( numGrays >= 256) { return grayscaleColors(); }
        if( numGrays <= 1) { numGrays = 2; }

        ArrayList<PaletteColor> grays = new ArrayList<>();
        grays.add( new PaletteColor(Color.BLACK));

        double multiplier = 256.0 / (numGrays-1);
        for( int i = 1; i < (numGrays-1); i++) {
            int colorValue = (int) (i * multiplier);
            Color c = Color.rgb( colorValue, colorValue, colorValue);
            grays.add( new PaletteColor(c));
        }
        grays.add( new PaletteColor(Color.WHITE));

        return grays;
    }

    /**
     * Returns a list of Nintendo Entertainment System (NES) colors.
     * There are 64 NES colors.
     * 
     * Source: http://www.thealmightyguru.com/Games/Hacking/Wiki/index.php/NES_Palette
     * @return Returns a list of Nintendo Entertainment System (NES) colors.
     */
    public static ArrayList<PaletteColor> nesColors() {
        String[] values = {
            "#7C7C7C","#0000FC","#0000BC","#4428BC",
            "#940084","#A80020","#A81000","#881400",
            "#503000","#007800","#006800","#005800",
            "#004058","#000000","#000000","#000000",
            "#BCBCBC","#0078F8","#0058F8","#6844FC",
            "#D800CC","#E40058","#F83800","#E45C10",
            "#AC7C00","#00B800","#00A800","#00A844",
            "#008888","#000000","#000000","#000000",
            "#F8F8F8","#3CBCFC","#6888FC","#9878F8",
            "#F878F8","#F85898","#F87858","#FCA044",
            "#F8B800","#B8F818","#58D854","#58F898",
            "#00E8D8","#787878","#000000","#000000",
            "#FCFCFC","#A4E4FC","#B8B8F8","#D8B8F8",
            "#F8B8F8","#F8A4C0","#F0D0B0","#FCE0A8",
            "#F8D878","#D8F878","#B8F8B8","#B8F8D8",
            "#00FCFC","#F8D8F8","#000000","#000000",
        };
        
        ArrayList<PaletteColor> nes = new ArrayList<>();
        
        for( String s: values) {
            Color c = Color.web(s);
            nes.add(new PaletteColor(c));
        }
        return nes;
    }

    /**
     * Returns a list of 256 "optimal" colors.
     * Source: http://kim.oyhus.no/Palette256.html
     * Source of the source: Thanks, Gabe W!
     * 
     * @return Returns a list of 256 optimal colors.
     */
    public static ArrayList<PaletteColor> optimalColors() {
        int [][] rgbValues = {
            {0, 0, 0}, {0, 0, 102}, {0, 0, 204}, {0, 23, 51}, 
            {0, 23, 153}, {0, 23, 255}, {0, 46, 0}, {0, 46, 102}, 
            {0, 46, 204}, {0, 69, 51}, {0, 69, 153}, {0, 69, 255}, 
            {0, 92, 0}, {0, 92, 102}, {0, 92, 204}, {0, 115, 51},
            {0, 115, 153}, {0, 115, 255}, {0, 139, 0}, {0, 139, 102}, 
            {0, 139, 204}, {0, 162, 51}, {0, 162, 153}, {0, 162, 255}, 
            {0, 185, 0}, {0, 185, 102}, {0, 185, 204}, {0, 208, 51}, 
            {0, 208, 153}, {0, 208, 255}, {0, 231, 0}, {0, 231, 102}, 
            {0, 231, 204}, {0, 255, 51}, {0, 255, 153}, {0, 255, 255}, 
            {42, 0, 51}, {42, 0, 153}, {42, 0, 255}, {42, 23, 0}, 
            {42, 23, 102}, {42, 23, 204}, {42, 46, 51}, {42, 46, 153}, 
            {42, 46, 255}, {42, 69, 0}, {42, 69, 102}, {42, 69, 204}, 
            {42, 92, 51}, {42, 92, 153}, {42, 92, 255}, {42, 115, 0}, 
            {42, 115, 102}, {42, 115, 204}, {42, 139, 51}, {42, 139, 153}, 
            {42, 139, 255}, {42, 162, 0}, {42, 162, 102}, {42, 162, 204}, 
            {42, 185, 51}, {42, 185, 153}, {42, 185, 255}, {42, 208, 0}, 
            {42, 208, 102}, {42, 208, 204}, {42, 231, 51}, {42, 231, 153}, 
            {42, 231, 255}, {42, 255, 0}, {42, 255, 102}, {42, 255, 204}, 
            {85, 0, 0}, {85, 0, 102}, {85, 0, 204}, {85, 23, 51}, 
            {85, 23, 153}, {85, 23, 255}, {85, 46, 0}, {85, 46, 102}, 
            {85, 46, 204}, {85, 69, 51}, {85, 69, 153}, {85, 69, 255}, 
            {85, 92, 0}, {85, 92, 102}, {85, 92, 204}, {85, 115, 51}, 
            {85, 115, 153}, {85, 115, 255}, {85, 139, 0}, {85, 139, 102}, 
            {85, 139, 204}, {85, 162, 51}, {85, 162, 153}, {85, 162, 255}, 
            {85, 185, 0}, {85, 185, 102}, {85, 185, 204}, {85, 208, 51}, 
            {85, 208, 153}, {85, 208, 255}, {85, 231, 0}, {85, 231, 102}, 
            {85, 231, 204}, {85, 255, 51}, {85, 255, 153}, {85, 255, 255}, 
            {127, 0, 51}, {127, 0, 153}, {127, 0, 255}, {127, 23, 0}, 
            {127, 23, 102}, {127, 23, 204}, {127, 46, 51}, {127, 46, 153}, 
            {127, 46, 255}, {127, 69, 0}, {127, 69, 102}, {127, 69, 204}, 
            {127, 92, 51}, {127, 92, 153}, {127, 92, 255}, {127, 115, 0}, 
            {127, 115, 102}, {127, 115, 204}, {127, 139, 51}, {127, 139, 153}, 
            {127, 139, 255}, {127, 162, 0}, {127, 162, 102}, {127, 162, 204}, 
            {127, 185, 51}, {127, 185, 153}, {127, 185, 255}, {127, 208, 0}, 
            {127, 208, 102}, {127, 208, 204}, {127, 231, 51}, {127, 231, 153}, 
            {127, 231, 255}, {127, 255, 0}, {127, 255, 102}, {127, 255, 204}, 
            {170, 0, 0}, {170, 0, 102}, {170, 0, 204}, {170, 23, 51}, 
            {170, 23, 153}, {170, 23, 255}, {170, 46, 0}, {170, 46, 102}, 
            {170, 46, 204}, {170, 69, 51}, {170, 69, 153}, {170, 69, 255}, 
            {170, 92, 0}, {170, 92, 102}, {170, 92, 204}, {170, 115, 51}, 
            {170, 115, 153}, {170, 115, 255}, {170, 139, 0}, {170, 139, 102}, 
            {170, 139, 204}, {170, 162, 51}, {170, 162, 153}, {170, 162, 255}, 
            {170, 185, 0}, {170, 185, 102}, {170, 185, 204}, {170, 208, 51}, 
            {170, 208, 153}, {170, 208, 255}, {170, 231, 0}, {170, 231, 102}, 
            {170, 231, 204}, {170, 255, 51}, {170, 255, 153}, {170, 255, 255}, 
            {212, 0, 51}, {212, 0, 153}, {212, 0, 255}, {212, 23, 0}, 
            {212, 23, 102}, {212, 23, 204}, {212, 46, 51}, {212, 46, 153}, 
            {212, 46, 255}, {212, 69, 0}, {212, 69, 102}, {212, 69, 204}, 
            {212, 92, 51}, {212, 92, 153}, {212, 92, 255}, {212, 115, 0}, 
            {212, 115, 102}, {212, 115, 204}, {212, 139, 51}, {212, 139, 153}, 
            {212, 139, 255}, {212, 162, 0}, {212, 162, 102}, {212, 162, 204}, 
            {212, 185, 51}, {212, 185, 153}, {212, 185, 255}, {212, 208, 0}, 
            {212, 208, 102}, {212, 208, 204}, {212, 231, 51}, {212, 231, 153}, 
            {212, 231, 255}, {212, 255, 0}, {212, 255, 102}, {212, 255, 204}, 
            {255, 0, 0}, {255, 0, 102}, {255, 0, 204}, {255, 23, 51}, 
            {255, 23, 153}, {255, 23, 255}, {255, 46, 0}, {255, 46, 102}, 
            {255, 46, 204}, {255, 69, 51}, {255, 69, 153}, {255, 69, 255}, 
            {255, 92, 0}, {255, 92, 102}, {255, 92, 204}, {255, 115, 51}, 
            {255, 115, 153}, {255, 115, 255}, {255, 139, 0}, {255, 139, 102}, 
            {255, 139, 204}, {255, 162, 51}, {255, 162, 153}, {255, 162, 255}, 
            {255, 185, 0}, {255, 185, 102}, {255, 185, 204}, {255, 208, 51}, 
            {255, 208, 153}, {255, 208, 255}, {255, 231, 0}, {255, 231, 102}, 
            {255, 231, 204}, {255, 255, 51}, {255, 255, 153}, {255, 255, 255}, 
            {204, 204, 204}, {153, 153, 153}, {102, 102, 102}, {51, 51, 51}
        };

        ArrayList<PaletteColor> optimals = new ArrayList<>();
        for( int i = 0; i < 256; i++) {
            Color c = Color.rgb(rgbValues[i][0], rgbValues[i][1], rgbValues[i][2]);
            optimals.add( new PaletteColor(c));
        }
        return optimals;
    }

    public static PaletteColor[] toArray(ArrayList<PaletteColor> list){
        PaletteColor[] out = new PaletteColor[list.size()];
        list.toArray(out);
        return out;
    }

}
