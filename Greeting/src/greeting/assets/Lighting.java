package greeting.assets;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public final class Lighting implements Shape {
    
    public List<Light> light = new ArrayList<>();
    public int framesPerFlicker;
    public int frames = 0;
    
    public void addLight(Light l) {
        light.add(l);
    }
    
    public Lighting(int framesPerFlicker) {
        this.framesPerFlicker = framesPerFlicker;
    }
    
    @Override
    public void draw(Graphics g) {
        for (Light l : light) {
            l.draw(g);
        }
    }

    @Override
    public void animate() {
        frames = (frames + 1) % framesPerFlicker;
        if (frames == 0) {
            for (Light l : light) {
                l.animate();
            }
        }
    }
}
