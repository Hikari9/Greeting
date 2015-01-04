package greeting.assets;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.Color;

public class Rectangle extends java.awt.Rectangle implements Shape {
    public Color outline = Color.BLACK, fill = null;

    /*
    public Rectangle() { super(); }
    public Rectangle(Rectangle r) { super(r); }
    public Rectangle(Point a, Point b) { this(a.x, a.y, b.x - a.x, b.y - a.y); }
    public Rectangle(Point a, int width, int height) { this(a.x, a.y, width, height); }
    */
    
    public Rectangle(int x, int y, int width, int height) { super(x, y, width, height); }
    public Rectangle(int x, int y, int width, int height, Color outline, Color fill) {
        this(x, y, width, height);
        this.outline = outline;
        this.fill = fill;
    }
	
    @Override
    public void draw(Graphics g) {
        if (fill != null) {
            g.setColor(fill);
            g.fillRect(x, y, width, height);
        }
        if (outline != null) {
            g.setColor(outline);
            g.drawRect(x, y, width, height);
        }
    }

    @Override
    public void animate() {
        
    }
    
}
