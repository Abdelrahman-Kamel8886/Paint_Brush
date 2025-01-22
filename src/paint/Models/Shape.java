package paint.Models;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Shape {

    protected int startX;
    protected int startY;
    protected int endX;
    protected int endY;
    protected Color color;
    protected boolean dotted;

    public Shape() {
    }

    public Shape(int startX, int startY, int endX, int endY, Color color, boolean dotted) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.color = color;
        this.dotted = dotted;
    }

    abstract public void draw(Graphics graphics);

    abstract public boolean contains(int x, int y);
    
    public void setPosition(int newX, int newY) {
        int width = Math.abs(endX - startX);
        int height = Math.abs(endY - startY);
        
        this.startX = newX;
        this.startY = newY;

        this.endX = startX + width;
        this.endY = startY + height;
    }

    public int getStartX() {
        return startX;
    }

    public void setStartX(int startX) {
        this.startX = startX;
    }

    public int getStartY() {
        return startY;
    }

    public void setStartY(int startY) {
        this.startY = startY;
    }

    public int getEndX() {
        return endX;
    }

    public void setEndX(int endX) {
        this.endX = endX;
    }

    public int getEndY() {
        return endY;
    }

    public void setEndY(int endY) {
        this.endY = endY;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean isDotted() {
        return dotted;
    }

    public void setDotted(boolean dotted) {
        this.dotted = dotted;
    }
    

}
