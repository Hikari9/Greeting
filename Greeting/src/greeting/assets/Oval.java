package greeting.assets;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Oval implements Shape {
    
    public int x, y, width, height;
    public Color outline = Color.BLACK, fill = null;
        
    public Oval(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
    public Oval(int x, int y, int width, int height, Color outline, Color fill) {
        this(x, y, width, height);
        this.outline = outline;
        this.fill = fill;
    }
    
    @Override
    public void draw(Graphics g) {
        if (fill != null) {
            g.setColor(fill);
            g.fillOval(x, y, width, height);
        }
        if (outline != null) {
            g.setColor(outline);
            g.drawOval(x, y, width, height);
        }
    }
    
    public Point center() {
        return new Point((int) Math.round(x + width / 2.0), (int) Math.round(y + height / 2.0));
    }
    
    public static boolean pointInOval(Point p, Oval o) {
        double dx = p.x - o.center().x;
        double dy = p.y - o.center().y;
        return (4 * (dx * dx / (o.width * o.width) + dy * dy / (o.height * o.height)) <= 1.0);
    }
    

    @Override
    public void animate() {
    
    }
    
}
