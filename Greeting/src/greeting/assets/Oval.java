package greeting.assets;

import java.awt.Color;
import java.awt.Graphics;

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

    @Override
    public void animate() {
    
    }
    
}
