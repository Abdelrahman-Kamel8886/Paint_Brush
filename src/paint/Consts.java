package paint;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;

public class Consts {

    public static final String RECTANGLE = "Rectangle";
    public static final String OVAL = "Oval";
    public static final String LINE = "Line";
    public static final String MOVE = "Move";
    public static final String ERASER = "Eraser";
    public static final String PENCIL = "Pencil";

    public static final String RED_COLOR = "Red";
    public static final String BLUE_COLOR = "Blue";
    public static final String GREEN_COLOR = "Green";
    public static final String BLACK_COLOR = "black";
    public static final String YELLOW_COLOR = "Yellow";
    public static final String CYAN_COLOR = "Cyan";
    public static final String WHITE_COLOR = "White";

    public static final int WIDTH = 1920;
    public static final int HIGHT = 1080;

    public static final int ERASER_SIZE = 30;
    public static final int PENCIL_SIZE = 10;

    public static final Color DARK_COLOR = Color.DARK_GRAY;
    public static final Color LIGHT_COLOR = Color.WHITE;

    private static final float[] DASH_PATERN = {10, 10};
    public static final BasicStroke DOTTED_STROKE = new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10, DASH_PATERN, 0);
    public static final BasicStroke BASIC_STROKE = new BasicStroke(2);

    public static final Font BUTTON_FONT = new Font("Tahoma", Font.BOLD, 14);
    public static final Font LABEL_FONT = new Font("Tahoma", Font.BOLD, 16);

}
