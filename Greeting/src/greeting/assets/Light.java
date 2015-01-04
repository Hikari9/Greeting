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
    
    public double GLOW_ALPHA = 0.2;
    public double GLOW_SIZE = 5.0;
    
    public Circle getGlow() {
        if (fill == null) fill = colors.get(flickers);
        return new Circle(
                (int) Math.round(center().x - GLOW_SIZE * radius()),
                (int) Math.round(center().y - GLOW_SIZE * radius()),
                (int) Math.round(GLOW_SIZE * diameter()),
                null,
                new Color(fill.getRed(), fill.getGreen(), fill.getBlue(), (int) Math.round(GLOW_ALPHA * fill.getAlpha()))
        );
    }
    
    @Override
    public void draw(Graphics g) {
        getGlow().draw(g);
        super.draw(g);
    }
    
    @Override
    public void animate() {
        flickers = (flickers + 1) % colors.size();
        fill = colors.get(flickers);
    }
    
    public static Light BASIC;
    static {
        BASIC = new Light(-5, 0, 10);
        BASIC.addColor(Color.RED);
        BASIC.addColor(Color.BLUE);
        BASIC.addColor(Color.GREEN);
        BASIC.addColor(Color.YELLOW);
    }

    
}
