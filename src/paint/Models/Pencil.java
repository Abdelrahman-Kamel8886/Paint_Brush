package paint.Models;

import java.awt.Color;
import java.awt.Graphics;

public class Pencil extends Eraser {

    public Pencil() {}

    public Pencil(int startX, int startY, int size, Color color) {
        super(startX, startY, size, color);
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(color);
        graphics.fillOval(startX, startY, size, size);
    }
    
    
    
}
