package greeting.assets;

import java.awt.Color;
import java.awt.Graphics;

public class FlakeWithLight extends Flake {

    public FlakeWithLight(int x, int y, int radius, int startAngle, int arcAngle, int leaves, int leafRadius) {
        super(x, y, radius, startAngle, arcAngle, leaves, leafRadius);
    }
    public FlakeWithLight(int x, int y, int radius, int startAngle, int arcAngle, int leaves, int leafRadius, Color outline, Color fill) {
        super(x, y, radius, startAngle, arcAngle, leaves, leafRadius, outline, fill);
    }
    
    @Override
    public void draw(Graphics g) {
        super.draw(g);
    }
    
    @Override
    public void animate() {
        super.animate();
    }
    
}
