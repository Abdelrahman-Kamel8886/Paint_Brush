package paint.Models;

import java.awt.Color;
import java.awt.Graphics;
public class Eraser extends Shape {
    
    protected int size;

    public Eraser() {
    }

    public Eraser(int startX, int startY,int size, Color color) {
        this.startX = startX;
        this.startY = startY;
        this.size = size;
        this.color = color;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(color);
        graphics.fillRect(startX, startY, size, size);
    }

    @Override
    public boolean contains(int x, int y) {
        return false;
    }
    
    
    
}
