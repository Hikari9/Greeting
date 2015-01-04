package greeting.assets;

import greeting.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Light extends Circle {
    
    List<Color> colors = new ArrayList<>();
    public int flickers = 0;

    public Light(int x, int y, int diameter) {
        super(x, y, diameter);
    }
    
    public Light(int x, int y, int diameter, Color outline, Color fill) {
        super(x, y, diameter, outline, fill);
        this.addColor(fill);
    }
    
    // copy and translate
    public Light(Light copy, int x, int y) {
        super(x + copy.x, y + copy.y, copy.diameter(), copy.outline, copy.fill);
        this.colors = copy.colors;
        this.flickers = copy.flickers;
    }
    
    public void addColor(Color color) {
        colors.add(color);
    }
    
    public static double GLOW_ALPHA = 0.5;
    public static double GLOW_SIZE = 1.1;
    
    @Override
    public void draw(Graphics g) {
        super.draw(g);
    }
    
    @Override
    public void animate() {
        flickers = (flickers + 1) % colors.size();
        fill = colors.get(flickers);
    }
    
}
