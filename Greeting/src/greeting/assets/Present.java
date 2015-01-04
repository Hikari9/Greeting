package greeting.assets;

import java.awt.Color;
import java.awt.Graphics;

public class Present extends Square {
    
    Color ribbonfill = Color.RED;
    
    public Present(int x, int y, int sideLength) { super(x, y, sideLength); }
    public Present(int x, int y, int sideLength, Color outline, Color fill, Color ribbonfill) {
        super(x, y, sideLength, outline, fill);
        this.ribbonfill = ribbonfill;
    }
    
    @Override
    public void draw(Graphics g) {
        super.draw(g);
    }
    
    
}
