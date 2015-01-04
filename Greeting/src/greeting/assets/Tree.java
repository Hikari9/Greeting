/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package greeting.assets;

import greeting.*;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rico
 */
public final class Tree implements Shape {
        
    List<Shape> shapes = new ArrayList<>();
    public int treeCenter;
    
    public Tree(int treeCenter) {
        this.treeCenter = treeCenter;
        addShapes();
    }
    
    public void addShapes() {
        
        Oval base = new Oval(treeCenter, 306, 140, 60, Color.WHITE, new Color(200, 50, 50));
        Rectangle trunk = new Rectangle(treeCenter, base.y - 30, 40, 50, Color.BLACK, Color.ORANGE);
        
        base.x -= base.width / 2;
        trunk.x -= trunk.width / 2;
        
        shapes.add(base);
        shapes.add(trunk);
        
        Flake[] f = new Flake[11];
        for (int i = 0; i < f.length; ++i) {
            f[i] = new Flake(treeCenter, 50 + 10 * i - ((i & 1) == 1 ? 5 : 0), 80 + 10 * i, 220, 100, 5 + i / 2, 20, Color.BLACK, (i & 1) == 0 ? Color.GREEN : Color.DARK_GRAY) {
                int flickers = 0;
                @Override
                public void animate() {
                    super.animate();
                    flickers++;
                    if (flickers >= 2) {
                        this.offset++;
                        setPolygon();
                        flickers = 0;
                    }
                }
                
            };
        }
        for (int i = f.length -1 ; i >= 0; --i)
            shapes.add(f[i]);
        
        // Present
    }

    @Override
    public void draw(Graphics g) {
        for (Shape s : shapes)
            s.draw(g);
    }

    @Override
    public void animate() {
        for (Shape s : shapes)
            s.animate();
    }
}
