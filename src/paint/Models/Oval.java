package paint.Models;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import paint.Consts;

public class Oval extends Rectangle {

    public Oval() {
    }

    public Oval(int startX, int startY, int endX, int endY, Color color, boolean solid, boolean stroke, boolean dotted) {
        super(startX, startY, endX, endY, color, solid, stroke, dotted);
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
            g2d.fillOval(x, y, getWidth(), getHeigth());
            if (stroke) {
                g2d.setColor(Color.BLACK);
                g2d.drawOval(x, y, getWidth(), getHeigth());
            }
        } else {
            g2d.drawOval(x, y, getWidth(), getHeigth());
        }

    }

    @Override
    public boolean contains(int x, int y) {

        int radiusX = getWidth() / 2;
        int radiusY = getHeigth() / 2;
        int centerX = startX + radiusX;
        int centerY = startY + radiusY;

        double fs = Math.pow(x - centerX, 2) / Math.pow(radiusX, 2);
        double ls = Math.pow(y - centerY, 2) / Math.pow(radiusY, 2);

        return (fs + ls) <= 1;

    }

}
