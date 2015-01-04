package greeting.assets;

import java.awt.Font;
import java.awt.Graphics;

public class StringShape implements Shape {
    
    public String text;
    public int x, y, size;
    
    public StringShape(String text, int x, int y, int size) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.size = size;
    }
    
    public Font getFont() {
        return new Font("SansSerif", Font.PLAIN, size);
    }

    @Override
    public void draw(Graphics g) {
        g.setFont(getFont());
        g.drawString(text, x, y);
    }

    @Override
    public void animate() {
    }
    
}
