package greeting.assets;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Color;

public class Line implements Shape {
    
    public Point a, b;
    public Color outline = Color.BLACK;
    
    public Line(Point a, Point b) {
        this.a = a;
        this.b = b;
    }
    
    public Line(Point a, Point b, Color outline) {
        this(a, b);
        this.outline = outline;
    }
    
    public double length() {
        double dx = b.x - a.x;
        double dy = b.y - a.y;
        return Math.sqrt(dx * dx + dy * dy);
    }
    
    public double angle() {
        return Math.atan2(b.y - a.y, b.x - a.x);
    }
    
    public double yValue(double x) {
        double dy = b.y - a.y;
        double dx = b.x - a.x;
        if (Double.compare(dx, 0.0) == 0)
            return a.x;
        double slope = dy / dx;
        double yIntercept = a.y - slope * a.x;
        return slope * x + yIntercept;
    }
    
    @Override
    public void draw(Graphics g) {
        g.setColor(outline);
        g.drawLine(a.x, a.y, b.x, b.y);
    }
    
    @Override
    public void animate() {
        
    }
    
}
