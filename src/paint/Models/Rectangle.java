package paint.Models;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import paint.Consts;

public class Rectangle extends Shape {

    protected boolean solid;
    protected boolean stroke;

    public Rectangle() {
    }
    
    

    public Rectangle(int startX, int startY, int endX, int endY, Color color, boolean solid, boolean stroke , boolean dotted) {
        super(startX, startY, endX, endY, color,dotted);
        this.solid = solid;
        this.stroke = stroke;
    }

    @Override
    public void draw(Graphics graphics) {

        int x = Math.min(startX, endX);
        int y = Math.min(startY, endY);
        
        Graphics2D g2d = (Graphics2D) graphics;
        g2d.setColor(color);
        
        if (dotted) {
            g2d.setStroke(Consts.DOTTED_STROKE);
        } else {
            g2d.setStroke(Consts.BASIC_STROKE);
        }     
        if (solid) {
            g2d.fillRect(x, y, getWidth(), getHeigth());
            if (stroke) {
                g2d.setColor(Color.BLACK);
                g2d.drawRect(x, y, getWidth(), getHeigth());
            }
        } else {
            g2d.drawRect(x, y, getWidth(), getHeigth());
        }
    }

    @Override
    public boolean contains(int x, int y) {
        return x >= startX && x <= endX && y >= startY && y <= endY;
    }

    public boolean isSolid() {
        return solid;
    }

    public void setSolid(boolean solid) {
        this.solid = solid;
    }

    public boolean isStroke() {
        return stroke;
    }

    public void setStroke(boolean stroke) {
        this.stroke = stroke;
    }
    
    public int getWidth() {
        return Math.abs(endX - startX);
    }
    public int getHeigth() {
        return Math.abs(endY - startY);
    }

}
