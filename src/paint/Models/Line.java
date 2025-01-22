package paint.Models;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import paint.Consts;

public class Line extends Shape {

    public Line() {
    }

    public Line(int startX, int startY, int endX, int endY, Color color, boolean dotted) {
        super(startX, startY, endX, endY, color, dotted);
    }

    @Override
    public void draw(Graphics graphics) {
        Graphics2D g2d = (Graphics2D) graphics;
        if (dotted) {
            g2d.setStroke(Consts.DOTTED_STROKE);
        } else {
            g2d.setStroke(Consts.BASIC_STROKE);
        }
        g2d.setColor(color);
        g2d.drawLine(startX, startY, endX, endY);
    }

    @Override
    public boolean contains(int x, int y) {
        double m = (double) (endY - startY) / (endX - startX);
        double b = startY - m * startX;
        double equation = (m * x) + b;
        return Math.abs(y - equation) <= 4;
    }

    @Override
    public void setPosition(int newX, int newY) {
        int moveX = newX - startX;
        int moveY = newY - startY;

        this.startX = newX;
        this.startY = newY;

        this.endX += moveX;
        this.endY += moveY;
    }

}
