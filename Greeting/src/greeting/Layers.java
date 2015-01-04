package greeting;

import greeting.assets.*;
import greeting.assets.Baymax;
import java.awt.Color;

public final class Layers extends ShapeCanvas {
    
    // initializer here
    
    int treecenter = 210;
    
    public Layers() {
        addBackground();
        addTree();
        addBaymax();
    }
    
    void addBaymax() {
        Baymax baymax = new Baymax(0, 65, 0.7f) {         
            double time = System.currentTimeMillis() / 1000.0;
            double speed = 1;
            @Override
            public void animate() {
                double ntime = System.currentTimeMillis() / 1000.0;
                double deltaTime = ntime - time;
                double need = this.mouseOffsetX();
                double addend = need * Math.min(1, deltaTime * speed);
                this.x += addend;
                time = ntime; 
            }
        };
        addShape(baymax);
    }
    
    void addBackground() {
        Shape background = new Rectangle(0, 0, Greeting.Main.getWidth(), Greeting.Main.getHeight() / 2, null, new Color(0x63D2FF)) {
            int[] delta = {0, 0, 1};
            @Override
            public void animate() {
                int[] curr = {
                    fill.getRed(), fill.getGreen(), fill.getBlue()
                };
                for (int i = 0; i < curr.length; ++i) {
                    int test = curr[i] + delta[i];
                    if (test < 0 || test > 0xFF)
                        delta[i] *= -1;
                    curr[i] += delta[i];
                }
                fill = new Color((curr[0] << 16) | (curr[1] << 8) | (curr[2]));
            }
        };
        
        BrickWall brickwall = new BrickWall(0, treecenter - 10, 0, 350, 30, 300, 20, 5, Color.WHITE, new Color(230, 118, 70));
        BrickWall brickwall2 = new BrickWall(brickwall.right, Greeting.Main.getWidth(), brickwall.rightTop, brickwall.rightBottom, 10, 325, 20, 6, brickwall.outline, brickwall.fill);
        // Brick b = new Brick("")
        // addShape(background);
        addShape(brickwall);
        addShape(brickwall2);
    }
    
    void addTree() {
        Oval base = new Oval(treecenter, 306, 140, 60, Color.WHITE, new Color(200, 50, 50));
        Rectangle trunk = new Rectangle(treecenter, base.y - 30, 40, 50, Color.BLACK, Color.ORANGE);
        base.x -= base.width / 2;
        trunk.x -= trunk.width / 2;
        addShape(base);
        addShape(trunk);
    }
    
}
