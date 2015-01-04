package greeting.assets;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;

public class StringShape implements Shape {
    
    public Color color = Color.BLACK;
    
    public String text;
    public int x, y, size;
    
    public StringShape(String text, int x, int y, int size) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.size = size;
    }
    
    public Font getFont() {
        return new Font("MV Boli", Font.PLAIN, size);
    }
    
    public FontRenderContext getFontRenderContext() {
        AffineTransform at = new AffineTransform();
        FontRenderContext frc = new FontRenderContext(at, true, true);
        return frc;
    }
    
    public int getWidth() {
        return (int) (getFont().getStringBounds(text, getFontRenderContext()).getWidth());
    }
    
    public int getHeight() {
        
        return (int) (getFont().getStringBounds(text, getFontRenderContext()).getHeight());
    }

    @Override
    public void draw(Graphics g) {
        g.setFont(getFont());
        g.setColor(color);
        g.drawString(text, x, y);
    }

    @Override
    public void animate() {
    }
    
}
