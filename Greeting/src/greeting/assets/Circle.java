package greeting.assets;

import java.awt.Color;

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
