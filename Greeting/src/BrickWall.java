/*
 *  CS 21b Midterm Project Greeting (c) 2015
 *  by Rico Tiongson
 *  Submitted to Prof. John Boaz Lee on January 4, 2015
 */


/**
 *
 * @author Rico Tiongson
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class BrickWall implements Shape {
    
    public int left, right;
    public int leftTop, leftBottom, rightTop, rightBottom;
    public int rows, columns;
    public Lighting lighting;
    public Brick[][] bricks;
    public Color outline = Color.BLACK, fill = Color.RED;
    
    public BrickWall(int left, int right, int leftTop, int leftBottom, int rightTop, int rightBottom, int rows, int columns) {
        this.left = left;
        this.right = right;
        this.leftTop = leftTop;
        this.leftBottom = leftBottom;
        this.rightTop = rightTop;
        this.rightBottom = rightBottom;
        this.rows = rows;
        this.columns = columns;
        
        if (left > right) { this.left = right; this.right = left; }
        if (leftTop < leftBottom) { this.leftTop = leftBottom; this.leftBottom = leftTop; }
        if (rightTop < rightBottom) { this.rightTop = rightBottom; this.rightBottom = rightTop; }
        
        bricks = new Brick[rows][columns];
        dpLine = new Line[rows + 1];
        lighting = randomLighting();
    }
    
    public BrickWall(int left, int right, int leftTop, int leftBottom, int rightTop, int rightBottom, int rows, int columns, Color outline, Color fill) {
        this(left, right, leftTop, leftBottom, rightTop, rightBottom, rows, columns);
        this.outline = outline;
        this.fill = fill;
    }
        
    Line[] dpLine;
    
    public Line getLine(int index) {
        // [0, rows] inclusive
        if (dpLine[index] != null) return dpLine[index];
        return dpLine[index] =  new Line(new Point(left, (int)  Math.round(leftBottom + (double) (leftTop - leftBottom) * index / rows)), new Point(right, (int) Math.round(rightBottom + (double) (rightTop - rightBottom) * index / rows)));
    }
       
    public Integer getX(int index) {
        // search for position where area is index / columns * orig
        return  left + (right - left) * index / columns;
        
        // return (int) Math.round((double) index * (right - left) / columns + ((index < columns) ? Math.pow(skewFactor(), columns - index) : 0.0));
    }
    
    public Point getPoint(int row, int col) {
        int x = getX(col);
        int y = (int) Math.round(getLine(row).yValue(x));
        return new Point(x, y);
    }
    
    public Brick getBrick(int row, int col) {
        if (bricks[row][col] != null)
            return bricks[row][col];
        // interpolate from lines
        Point nw, ne, se, sw;
        nw = getPoint(row, col);
        ne = getPoint(row, col + 1);
        se = getPoint(row + 1, col + 1);
        sw = getPoint(row + 1, col);
        Brick b = new Brick(new java.awt.Polygon(), outline, fill, 50, 36, 18);
        b.add(nw.x, nw.y);
        b.add(ne.x, ne.y);
        b.add(se.x, se.y);
        b.add(sw.x, sw.y);
        return bricks[row][col] = b;
    }
    
    public final Lighting randomLighting() {
        Lighting L = new Lighting(Greeting.Main.FPS);
        Light light = Light.BASIC;
        for (int i = 1; i < rows; ++i) {
            int randomColumn = (int) (Math.random() * columns);
            L.addLight(new Light(light, getPoint(i, randomColumn).x, getPoint(i, randomColumn).y));
            light.animate();
        }
        return L;
    }

    @Override
    public void draw(Graphics g) {
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < columns; ++j) {
                getBrick(i, j).draw(g);
            }
        }
        lighting.draw(g);
    }
    
    @Override
    public void animate() {
        lighting.animate();
    }
    
}
