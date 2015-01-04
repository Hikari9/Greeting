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

public class Square extends Rectangle {
    public Square(int x, int y, int width, int height) { super(x, y, width, height); this.normalize(); }
    public Square(int x, int y, int sideLength) { this(x, y, sideLength, sideLength); }
    public Square(int x, int y, int sideLength, Color outline, Color fill) {
        this(x, y, sideLength);
        this.outline = outline;
        this.fill = fill;
    }
	
    private void normalize() { this.width = this.height = Math.min(width, height); }
}
