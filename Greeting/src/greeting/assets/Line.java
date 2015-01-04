/*
 *  CS 21b Midterm Project Greeting (c) 2015
 *  by Rico Tiongson
 *  Submitted to Prof. John Boaz Lee on January 4, 2015
 */
package greeting.assets;

/**
 *
 * @author Rico Tiongson
 */
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
    
    public Point getVector() {
        return new Point(b.x - a.x, b.y - a.y);
    }
    
    public static int cross(Point a, Point b) {
        return a.x * b.y - a.y * b.x;
    }
    
    public static int dot(Point a, Point b) {
        return a.x * b.x + a.y * b.y;
    }
    
    public static boolean parallel(Line l1, Line l2) {
        return cross(l1.getVector(), l2.getVector()) == 0;
    }
    
    public static Point intersection (Line l1, Line l2) {
        if (parallel(l1, l2))
            return null;
        Point v1 = l1.getVector();
        Point v2 = l2.getVector();
        Point v3 = new Point(l2.a.x - l1.a.x, l2.a.y - l1.a.y);
        if (dot(v1, v1) == 0) return l1.a;
        if (dot(v2, v2) == 0) return v3;
        return new Point((int) Math.round(l1.a.x + (v1.x * (double) cross(v3, v2) / cross(v1, v2))), (int) Math.round(l1.a.y + (v1.y * (double) cross(v3, v2) / cross(v1, v2))));
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
