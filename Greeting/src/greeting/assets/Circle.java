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
import java.awt.Point;

public class Circle extends Oval {
    
    
    public Circle(int x, int y, int diameter) {
        super(x, y, diameter, diameter);
    }
    
    public Circle(int x, int y, int diameter, Color outline, Color fill) {
        super(x, y, diameter, diameter, outline, fill);
    }
        
    public int diameter() {
        return width;
    }
    
    public double radius() {
        return diameter() / 2.0;
    }
    
        
    @Override
    public void animate() {
        
    }
    
}
