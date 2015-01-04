package greeting.assets;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Flake extends Polygon {
    
    public Point top;
    public int radius;
    public int leaves, leafRadius;
    public int startAngle, arcAngle; // in degrees
    public int offset = 0;
    
    public Flake(int x, int y, int radius, int startAngle, int arcAngle, int leaves, int leafRadius) {
        super();
        this.top = new Point(x, y);
        this.radius = radius;
        this.startAngle = startAngle;
        this.arcAngle = arcAngle;
        this.leaves = leaves;
        this.leafRadius = leafRadius;
        this.outline = Color.BLACK;
        this.fill = Color.WHITE;
        setPolygon();
    }
    
    public Flake(int x, int y, int radius, int startAngle, int arcAngle, int leaves, int leafRadius, Color outline, Color fill) {
        this(x, y, radius, startAngle, arcAngle, leaves, leafRadius);
        this.outline = outline;
        this.fill = fill;
    }
    
    public int divAngle() {
        return arcAngle / divisions();
    }
    
    public int getOffset() {
        return offset %= divAngle() * 2;
    }
    
    public final void setPolygon() {
        // divide arc angle by number of leaves
        this.polygon = new java.awt.Polygon();
        this.add(top);
        if (getOffset() > divAngle())
            this.add(getFirstPointNoOffset());
        this.add(getFirstPoint());
        int div = divisions();
        for (int i = 0; i < div; ++i) {
            this.add(getDivision(i));
        }
        
        this.add(getSecondPoint());
        
        if (getOffset() > divAngle())
            this.add(getSecondPointNoOffset());

    }
    
    public int divisions() {
        return (leaves + 1) * 2;
    }
    
    public Point getDivision(int i) {
        if (getOffset() > divAngle()) --i;
        int div = divisions();
        return getRotatedPoint(top,(i % 2)==0 ? radius : radius - leafRadius, -Math.toRadians(startAngle + getOffset() + (int) Math.round((double) arcAngle * i / div)));
    }
    
    public static Point getRotatedPoint(Point pivot, double radius, double radians) {
        return new Point((int) Math.round(pivot.x + radius * Math.cos(radians)), (int) Math.round(pivot.y + radius * Math.sin(radians)));
    }
    
    public Point getFirstPointNoOffset() {
        return getRotatedPoint(top, radius, -Math.toRadians(startAngle));
    }
    
    public Point getSecondPointNoOffset() {
        return getRotatedPoint(top, radius, -Math.toRadians(startAngle + arcAngle));
    }
    
    public Point getFirstPoint() {
        Line l = new Line(top, getFirstPointNoOffset());
        Line l2 = new Line(getDivision(-1), getDivision(0));
        return Line.intersection(l, l2);
    }
    
    public Point getSecondPoint() {
        Line l = new Line(top, getSecondPointNoOffset());
        Line l2 = new Line(getDivision(divisions() - 1), getDivision(divisions()));
        return Line.intersection(l, l2);
                
    }
    
    public int ArcX() {
        return (top.x - radius);
    }
    
    public int ArcY() {
        return (top.y - radius);
    }
    
    public int ArcWidth() {
        return (radius * 2);
    }
    
    public int ArcHeight() {
        return (radius * 2);
    }
    
    public Flake cloneFlake() {
        return new Flake(top.x, top.y, radius, startAngle, arcAngle, leaves, leafRadius, outline, fill);
    }
    
    @Override
    public void animate() {
    }
    
}
