package greeting;

import greeting.assets.*;
import greeting.assets.Baymax;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;

public final class Layers extends ShapeCanvas {
    
    // initializer here
    
    int treecenter = 210;
    
    public Layers() {
        addBackground();
        addTree();
        addBaymax();
        addSnowball();
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
    
    BrickWall brickwall, brickwall2;
    
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
        
        brickwall = new BrickWall(0, treecenter - 10, 0, 350, 30, 300, 20, 5, Color.WHITE, new Color(230, 118, 70));
        brickwall2 = new BrickWall(brickwall.right, Greeting.Main.getWidth(), brickwall.rightTop, brickwall.rightBottom, 10, 325, 20, 6, brickwall.outline, brickwall.fill);
        // Brick b = new Brick("")
        // addShape(background);
        addShape(brickwall);
        addShape(brickwall2);
    }
    
    void addTree() {
        addShape (new Tree(treecenter));
    }
    
    void addSnowball() {
        Timer timer = new Timer(50, new SnowballRandomizer());
        timer.start();
    }
    
    class SnowballRandomizer implements ActionListener {
        final int[] colorChanger = {0, 1, 2, 10}; 

        int randomInt(int start, int end) {
            int r = (int) (Math.random() * (end - start)) + start;
            return r;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Snowball snowball = new Snowball(randomInt(0, Greeting.Main.getWidth()), null, new Color(randomInt(200, 255), randomInt(200, 255), randomInt(200, 255), 250), colorChanger) {
                    
                    int targetY = -1;
                    
                    @Override
                    protected void fall() {
                        if (targetY == -1) {
                            Line lower = null;
                            if (x <= brickwall.right) 
                                lower = brickwall.getLine(brickwall.rows);
                            else
                                lower = brickwall2.getLine(brickwall.rows);
                            targetY = randomInt((int) Math.ceil(lower.yValue(x)), Greeting.Main.getHeight());
                        }
                        if (y <= targetY) {
                            y += FALL_PIXELS_PER_FRAME;
                        }
                    }
                };
                addShape(snowball);
            } catch (Exception ex) {
                Logger.getLogger(Layers.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
