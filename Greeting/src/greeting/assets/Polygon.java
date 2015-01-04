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
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rico
 */
public class Polygon implements Shape {
    
    public static void main(String[] args) throws IOException {
        Polygon p = Polygon.readFromFile("SantaHatBodyRaw.txt");
        p.translate(5, 0);
        Polygon.printToFile(p, "SantaHatBodyRaw.txt");
    }
        
    java.awt.Polygon polygon = new java.awt.Polygon();
    public Color outline = Color.BLACK, fill = null;
    
    public Polygon() {
    }
    
    public Polygon(java.awt.Polygon p, Color outline, Color fill) {
        this.polygon = p;
        this.outline = outline;
        this.fill = fill;
    }

    public Polygon(Polygon p) {
        polygon = new java.awt.Polygon(p.polygon.xpoints, p.polygon.ypoints, p.polygon.npoints);
        this.outline = p.outline;
        this.fill = p.fill;
    }
    
    
    @Override
    public void draw(Graphics g) {
        if (fill != null) {
            g.setColor(fill);
            g.fillPolygon(polygon);
        }
        if (outline != null) {
            g.setColor(outline);
            g.drawPolygon(polygon);
        }
    }

    @Override
    public void animate() {
    }
    
    
    public int size() {
        return polygon.npoints;
    }
    
    public Point get(int index) {
        return new Point(getX(index), getY(index));
    }
    
    public int getX(int index) {
        return polygon.xpoints[index];
    }
    
    public int getY(int index) {
        return polygon.ypoints[index];
    }
    
    public void set(int index, int x, int y) {
        polygon.xpoints[index] = x;
        polygon.ypoints[index] = y;
    }
    
    public void add(int x, int y) {
        polygon.addPoint(x, y);
    }
    
    public void add(Point p) {
        add(p.x, p.y);
    }
    
    public void translate(int x, int y) {
        for (int i = 0; i < size(); ++i)
            set(i, getX(i) + x, getY(i) + y);
    }
    
    public void flipAlongX(int x) {
        for (int i = 0; i < size(); ++i)
            set(i, 2 * x - getX(i), getY(i));
    }
    
    public void flipAlongY(int y) {
        for (int i = 0; i < size(); ++i)
            set(i, getX(i), 2 * y - getY(i));
    }
    
    public double[] getCenter() {
        double x = 0, y = 0;
        for (int i = 0; i < size(); ++i) {
            x += getX(i);
            y += getY(i);
        }
        return new double[] {x / size(), y / size()};
    }
    
    public void scale(double factor) {
        double[] center = getCenter();
        for (int i = 0; i < size(); ++i) {
            double dx = getX(i) - center[0];
            double dy = getY(i) - center[1];
            set(i, (int) Math.round(center[0] + dx * factor), (int) Math.round(center[1] + dy * factor));
        }
    }
    
    public void normalize() {
        if (size() == 0) return;
        List<Integer> x = new ArrayList<>();
        List<Integer> y = new ArrayList<>();
        x.add(getX(0));
        y.add(getY(0));
        for (int i = 1; i < size(); ++i) {
            if (getX(i) == getX(i - 1) && getY(i) == getY(i - 1))
                continue;
            x.add(getX(i));
            y.add(getY(i));
        }
        polygon.npoints = x.size();
        for (int i = 0; i < x.size(); ++i)
            set(i, x.get(i), y.get(i));
    }
    
    public static Polygon readFromFile(String filename) throws IOException {
        List<Integer> listX = new ArrayList<>();
        List<Integer> listY = new ArrayList<>();

        String path = System.getProperties().getProperty("java.class.path");
        BufferedReader br = new BufferedReader(new FileReader(path + "/" + filename));
        while (br.ready()) {
            String[] sp = br.readLine().split(" ");
            int x = Integer.parseInt(sp[0]);
            int y = Integer.parseInt(sp[1]);
            listX.add(x);
            listY.add(y);
        }
        int n = listX.size();
        int[] xp = new int[n];
        int[] yp = new int[n];
        for (int i = 0; i < n; ++i) {
            xp[i] = listX.get(i);
            yp[i] = listY.get(i);
        }
        Polygon p = new Polygon();
        p.polygon.xpoints = xp;
        p.polygon.ypoints = yp;
        p.polygon.npoints = n;
        
        return p;
    }
    
    public static void printToFile(Polygon p, String filename) throws IOException {
        String path = System.getProperties().getProperty("java.class.path");
        PrintWriter pw = new PrintWriter(new FileWriter(path + "/" + filename));
        for (int i = 0; i < p.size(); ++i) {
            pw.println(p.getX(i) + " " + p.getY(i));
        }
        pw.close();
    }
    
}
