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

public class Brick extends Polygon {
    
    public int R, G, B;
    protected Color originalFill = null;
    
    public Brick(java.awt.Polygon p, Color outline, Color fill, int R, int G, int B) {
        super(p, outline, fill);
        this.originalFill = this.fill;
        this.R = R;
        this.G = G;
        this.B = B;
        
        if (originalFill != null) {
            int r = processColor(originalFill.getRed(), R);
            int g = processColor(originalFill.getGreen(), G);
            int b = processColor(originalFill.getBlue(), B);
            this.fill = new Color(r, g, b);
        }
    }
        
    public int processColor(int orig, int delta) {
        int sign = (Math.random() * 2 < 0.5) ? -1 : 1;
        return Math.max(0, Math.min(0xFF, orig + sign * (int) (Math.random() * delta)));
    }
        
    @Override
    public void animate() {
    }
    
}
